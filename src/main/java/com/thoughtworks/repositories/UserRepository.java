package com.thoughtworks.repositories;

import com.thoughtworks.entities.QueryForUser;
import com.thoughtworks.entities.UpdateForUser;
import com.thoughtworks.entities.User;

import java.util.List;

public class UserRepository implements UserRepositoryI {

    @Override
    public Boolean userRegister(User user) {
        String sql = "INSERT INTO user(name,telephoneNumber,email,password) values(?,?,?,?)";
        UpdateForUser.update(sql, user.getName(), user.getTelephoneNumber(), user.getEmail(), user.getPassword());
        return true;
    }

    @Override
    public List<User> userLogin(String name, String password) {
        String sqlSelectUser = "SELECT id,name,password,telephoneNumber,email,errorNumber,isLocked  FROM user WHERE name=? AND password=?";
        return QueryForUser.queryForUser(sqlSelectUser, name, password);
    }
    @Override
    public List<User> isUserExist(String name) {
        String sqlSelectUser = "SELECT id,name,password,telephoneNumber,email,errorNumber,isLocked  FROM user WHERE name=?";
        return QueryForUser.queryForUser(sqlSelectUser, name);
    }

    @Override
    public void updateErrorNumber(User user) {
        String sqlUpdateErrorNumber = "UPDATE user SET errorNumber=? WHERE name=?";
        UpdateForUser.update(sqlUpdateErrorNumber, user.getErrorNumber()+1, user.getName());
    }

    @Override
    public void updateIsLocked(User user) {
        String sqlUpdateErrorNumber = "UPDATE user SET errorNumber=?,isLocked=? WHERE name=?";
        UpdateForUser.update(sqlUpdateErrorNumber, user.getErrorNumber(), user.getIsLocked(), user.getName());
    }
}
