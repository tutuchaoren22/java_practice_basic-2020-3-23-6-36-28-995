package com.thoughtworks.controllers;

import com.thoughtworks.entities.User;
import com.thoughtworks.services.UserService;
import com.thoughtworks.services.UserServiceI;

public class UserController {
    private UserServiceI userService = new UserService();

    public Boolean userRegister(String input) {
        String[] userInput = input.split(",");
        if (userInput.length != 4) {
            System.out.println("格式错误\n请按正确格式输入注册信息：");
            return false;
        } else {
            User user = new User(userInput[0], userInput[1], userInput[2], userInput[3]);
            return userService.userRegister(user);
        }
    }

    public Boolean userLogin(String input) {
        String[] userInput = input.split(",");
        if (userInput.length != 2) {
            System.out.println("格式错误\n请按正确格式输入用户名和密码：");
            return false;
        } else {
            return userService.userLogin(userInput[0], userInput[1]);
        }
    }
}
