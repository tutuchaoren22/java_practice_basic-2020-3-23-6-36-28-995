package com.thoughtworks.services;

import com.thoughtworks.entities.User;
import com.thoughtworks.repositories.UserRepository;
import com.thoughtworks.repositories.UserRepositoryI;

import java.util.List;

public class UserService implements UserServiceI {
    private UserRepositoryI userRepository = new UserRepository();

    @Override
    public Boolean userRegister(User user) {
        String checkInf = checkRegisterFormat(user);
        switch (checkInf) {
            case "register successful":
                if (userRepository.userRegister(user)) {
                    System.out.println((String.format("%s，恭喜你注册成功！", user.getName())));
                    return true;
                } else {
                    System.out.println("注册失败");
                    return false;
                }
            case "username error":
                System.out.println(("用户名不合法\n请输入合法的注册信息："));
                return false;
            case "telephone error":
                System.out.println(("手机号不合法\n请输入合法的注册信息："));
                return false;
            case "email error":
                System.out.println(("邮箱不合法\n请输入合法的注册信息："));
                return false;
            case "password error":
                System.out.println(("密码不合法\n请输入合法的注册信息："));
                return false;
            default:
                return null;
        }
    }

    @Override
    public Boolean userLogin(String name, String password) {
        if (isNameTrueFormat(name) && isPasswordTrueFormat(password)) {
            User user = isUserExist(name).get(0);
            if (user.getName() == null) {
                System.out.println("密码或用户名错误\n请重新输入用户名和密码：");
                return false;
            } else {
                if (user.getIsLocked()) {
                    System.out.println("您已3次输错密码，账号被锁定");
                    return false;
                } else if (user.getPassword().equals(password))  {
                    System.out.println(String.format("%s，欢迎回来！", user.getName()));
                    System.out.println(String.format("您的手机号是%s，邮箱是%s", user.getTelephoneNumber(), user.getEmail()));
                    return true;
                }else {
                    if (user.getErrorNumber() == 2) {
                        user.setErrorNumber(user.getErrorNumber() + 1);
                        user.setIsLocked(true);
                        updateIsLocked(user);
                        System.out.println("您已3次输错密码，账号被锁定");
                    }else{
                        updateErrorNumber(user);
                        System.out.println("密码或用户名错误\n请重新输入用户名和密码：");
                    }
                    return false;
                }
            }
        } else {
            System.out.println("格式错误\n请按正确格式输入用户名和密码：");
            return false;
        }
    }

    public String checkRegisterFormat(User user) {
        if (!isNameTrueFormat(user.getName())) {
            return "username error";
        } else if (!isTelephoneTrueFormat(user.getTelephoneNumber())) {
            return "telephone error";
        } else if (!isEmailTrueFormat(user.getEmail())) {
            return "email error";
        } else if (!isPasswordTrueFormat(user.getPassword())) {
            return "password error";
        } else {
            return "register successful";
        }
    }

    public boolean isNameTrueFormat(String name) {
        return name.length() > 2 && name.length() < 10;
    }

    public boolean isTelephoneTrueFormat(String telephoneNumber) {
        return (11 == telephoneNumber.length()) && (telephoneNumber.startsWith("1"));
    }

    public boolean isEmailTrueFormat(String email) {
        return email.contains("@");
    }

    public boolean isPasswordTrueFormat(String password) {
        return password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
    }

    @Override
    public List<User> isUserExist(String name) {
      return   userRepository.isUserExist(name);
    }

    @Override
    public void updateErrorNumber(User user) {
        userRepository.updateErrorNumber(user);
    }

    @Override
    public void updateIsLocked(User user) {
        userRepository.updateIsLocked(user);
    }
}
