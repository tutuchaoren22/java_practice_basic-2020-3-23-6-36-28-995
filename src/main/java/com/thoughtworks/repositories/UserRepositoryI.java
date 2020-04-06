package com.thoughtworks.repositories;

import com.thoughtworks.entities.User;

import java.util.List;

public interface UserRepositoryI {
    Boolean userRegister(User user);

    List<User> userLogin(String name, String password);

    List<User> isUserExist(String name);

    void updateErrorNumber(User user) ;

    void updateIsLocked(User user) ;
}
