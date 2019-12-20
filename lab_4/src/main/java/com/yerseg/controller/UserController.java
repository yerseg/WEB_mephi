package com.yerseg.controller;

import com.yerseg.model.User_;
import com.yerseg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public String listUsers(Model model){
        model.addAttribute("users", userService.listAll());
        return "list";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/failure_message")
    public String failure(){
        return "failure_message";
    }

    @RequestMapping("/auth")
    public String login(Model model){
        model.addAttribute("user", new User_());
        return "auth";
    }

    @RequestMapping("/register")
    public String newUser(Model model){
        model.addAttribute("user", new User_());
        return "register";
    }


    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String authUser(@Valid User_ user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "auth";
        }

        if (userService.checkIfExists(user)) {
            return "redirect:/list";
        } else {
            return "redirect:/failure_message";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(@Valid User_ user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "Something went wrong <div><a href ='/'> Menu </a></div>";
        }

        if (userService.checkIfEmailIsReserved(user)) {
            return "<div>Sorry, the login is already taken</div> " +
                    "<div><a href ='/register'> Sign up </a> again</div>";
        }
        else {
            User_ savedUser = userService.saveUser(user);
            return "<h1>Congratulations!</h1>" +
                    "<div>You have been registered successfully.</div> " +
                    "<div>Now you can <a href ='/'>login</a>.";
        }
    }
}

