package com.thoughtworks.entities;

import com.thoughtworks.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class QueryForUser {
    public static List<User> queryForUser(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                User user = new User();
                for (int i = 0; i < count; i++) {
                    Object value = resultSet.getObject(i + 1);
                    String columnName = resultSetMetaData.getColumnName(i + 1);
                    Field field = User.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(user, value);
                }
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        } finally {
            JDBCUtils.closeResource(conn, preparedStatement, resultSet);
        }
    }
}
