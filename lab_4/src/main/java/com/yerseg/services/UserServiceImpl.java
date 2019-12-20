package com.yerseg.services;


import com.yerseg.model.User_;
import com.yerseg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User_> listAll() {
        List<User_> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User_ getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User_ saveUser(User_ user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean checkIfExists(User_ user) {
        if (userRepository.existsByEmail(user.getEmail())){
            if (userRepository.findByEmail(user.getEmail()).getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkIfEmailIsReserved(User_ user) {
        if (userRepository.existsByEmail(user.getEmail())){
            return true;
        } else {
            return false;
        }
    }
}
