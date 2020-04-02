package com.thoughtworks.entities;

import com.thoughtworks.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateForUser {
    public static void update(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, preparedStatement);
        }
    }
}
