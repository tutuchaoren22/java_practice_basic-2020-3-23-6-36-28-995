package com.thoughtworks.util;

import java.sql.*;

public class JDBCUtils {
    public static Connection getConnection() throws Exception {
        //1:读取配置文件中的基本信息
        String url = "jdbc:mysql://localhost:3306/user_login_sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong&useSSL=false";
        String user = "root";
        String password = "123456";
        //2:加载Driver驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //3:获取连接
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeResource(Connection conn, Statement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection conn, Statement preparedStatement, ResultSet resultSet) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
