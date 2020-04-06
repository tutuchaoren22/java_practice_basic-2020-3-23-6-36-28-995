package com.thoughtworks.services;

import com.thoughtworks.entities.User;

import java.util.List;

public interface UserServiceI {
    Boolean userRegister(User user);
    Boolean userLogin(String name, String password);
    List<User> isUserExist(String name);
    void updateErrorNumber(User user) ;
    void updateIsLocked(User user) ;
}
