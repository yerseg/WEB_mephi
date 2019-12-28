package com.yerseg.services;

import com.yerseg.model.Users;

import java.util.List;

public interface UserService {

    List<Users> listAll();

    Users getById(Long id);

    Users saveUser(Users user);

    boolean checkIfExists(Users user);

    boolean checkIfEmailIsReserved(Users user);

}

