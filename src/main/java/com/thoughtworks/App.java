package com.thoughtworks;

import com.thoughtworks.controllers.UserController;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        UserController userController = new UserController();

        boolean isBegin = true;
        while (isBegin) {
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 退出");
            System.out.println("请输入你的选择(1~3)：");
            Scanner scanner = new Scanner(System.in);
            String input;
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)：");
                    boolean isRegisterSuccess = false;
                    while (!isRegisterSuccess) {
                        input = scanner.next();
                        isRegisterSuccess = userController.userRegister(input);
                    }
                    break;
                case 2:
                    System.out.println("请输入用户名和密码(格式：用户名,密码)：");
                    input = scanner.next();
                    userController.userLogin(input);
                    break;
                case 3:
                    isBegin = false;
                    break;
                default:
                    System.out.println("请重新输入你的选择(1~3)");
            }
        }
    }
}
