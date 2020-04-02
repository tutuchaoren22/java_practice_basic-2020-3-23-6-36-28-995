package com.thoughtworks;

import com.thoughtworks.entities.QueryForUser;
import com.thoughtworks.entities.UpdateForUser;
import com.thoughtworks.entities.User;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    homeScreen();
  }

  public static void homeScreen() {
    System.out.println("1. 注册");
    System.out.println("2. 登录");
    System.out.println("3. 退出");
    System.out.println("请输入你的选择(1~3)：");
    Scanner scanner = new Scanner(System.in);
    switch (scanner.nextInt()) {
      case 1:
        System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)：");
        registerScreen();
        break;
      case 2:
        System.out.println("请输入用户名和密码(格式：用户名,密码)：");
        loginScreen();
        break;
      case 3:
        break;
      default:
    }
  }

  public static void registerScreen() {
    Scanner scanner = new Scanner(System.in);
    String[] input = scanner.nextLine().split(",");
    String s = checkRegisterFormat(input);
    switch (s) {
      case "register successful":
        System.out.println(String.format("%s，恭喜你注册成功！", input[0]));
        String sql = "INSERT INTO user(name,telephoneNumber,email,password) values(?,?,?,?)";
        UpdateForUser.update(sql, input[0], input[1], input[2], input[3]);
        homeScreen();
        break;
      case "username error":
        System.out.println("用户名不合法\n" +
                "请输入合法的注册信息：");
        registerScreen();
        break;
      case "telephone error":
        System.out.println("手机号不合法\n" +
                "请输入合法的注册信息：");
        registerScreen();
        break;
      case "email error":
        System.out.println("邮箱不合法\n" +
                "请输入合法的注册信息：");
        registerScreen();
        break;
      case "password error":
        System.out.println("密码不合法\n" +
                "请输入合法的注册信息：");
        registerScreen();
        break;
      case "format error":
        System.out.println("格式错误\n" +
                "请按正确格式输入注册信息：\n");
        registerScreen();
        break;
      default:
        break;
    }
  }

  public static void loginScreen() {
    Scanner scanner = new Scanner(System.in);
    String[] input = scanner.nextLine().split(",");
    if (input.length != 2) {
      System.out.println("格式错误\n" +
              "请按正确格式输入用户名和密码：");
      loginScreen();
    } else {
      withRightFormat(input);
    }
  }

  public static String checkRegisterFormat(String[] input) {
    if (input.length != 4) {
      return "format error";
    } else {
      if (input[0].length() < 2 || input[0].length() > 10) {
        return "username error";
      }
      if (input[1].length() != 11 || !input[1].startsWith("1")) {
        return "telephone error";
      }
      if (!input[2].contains("@")) {
        return "email error";
      }
      String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
      if (!input[3].matches(regex)) {
        return "password error";
      }
    }
    return "register successful";
  }

  public static void withRightFormat(String[] input) {
    String sqlSelectUser = "SELECT id,name,password,telephoneNumber,email,errorNumber,isLocked  FROM user WHERE name=?";
    User user = QueryForUser.queryForUser(sqlSelectUser, input[0]);
    if (user.getName() == null) {
      System.out.println("密码或用户名错误\n" +
              "请重新输入用户名和密码：");
      loginScreen();
    }
    if (user.getIsLocked() == true) {
      System.out.println("您已3次输错密码，账号被锁定");
    } else if (user.getName().equals(input[0]) && user.getPassword().equals(input[1]) && user.getIsLocked() == false) {
      System.out.println(String.format("%s，欢迎回来！", user.getName()));
      System.out.println(String.format("您的手机号是%s，邮箱是%s", user.getTelephoneNumber(), user.getEmail()));
      homeScreen();
    } else {
      withWrongNameAndPassword(user);
    }

  }

  public static void withWrongNameAndPassword(User user) {
    if (user.getErrorNumber() == 2) {
      user.setIsLocked(true);
      String sqlUpdateErrorNumber = "UPDATE user SET errorNumber=?,isLocked=? WHERE name=?";
      UpdateForUser.update(sqlUpdateErrorNumber, user.getErrorNumber() + 1, user.getIsLocked(), user.getName());
      System.out.println("您已3次输错密码，账号被锁定");
      homeScreen();
    }
    String sqlUpdateErrorNumber = "UPDATE user SET errorNumber=? WHERE name=?";
    UpdateForUser.update(sqlUpdateErrorNumber, user.getErrorNumber() + 1, user.getName());
    System.out.println("密码或用户名错误\n" +
            "请重新输入用户名和密码：");
    loginScreen();
  }
}
