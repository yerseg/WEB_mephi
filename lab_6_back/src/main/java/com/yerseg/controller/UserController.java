package com.yerseg.controller;

import com.yerseg.model.Users;
import com.yerseg.repositories.UserRepository;
import com.yerseg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value="api/users")
public class UserController {
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Users> getAll(){
        return (List<Users>) userRepository.findAll();
    }

    @GetMapping("{id}")
    public Users getById(@PathVariable Long id){
        return userRepository.findById(id).get();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Users save(@RequestBody @Valid Users user){
        return userRepository.save(user);
    }

    @PutMapping("{id}")
    public Users update(@PathVariable Long id, @RequestBody Users user){
        if (user.getId() == null){
            user.setId(id);
        }
        return userRepository.save(user);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        userRepository.deleteById(id);
    }


}

