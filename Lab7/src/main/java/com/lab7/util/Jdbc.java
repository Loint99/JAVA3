package com.lab7.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc {

    static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String URL    = "jdbc:sqlserver://localhost:1433;databaseName=HRM;encrypt=false;trustServerCertificate=true";
    static String USER   = "sa";        // ĐỔI CHO ĐÚNG
    static String PASS   = "123";    // ĐỔI CHO ĐÚNG

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Dùng cho INSERT, UPDATE, DELETE
    public static int executeUpdate(String sql, Object... values) {
        try (Connection con = getConnection();
             PreparedStatement stmt = prepare(con, sql, values)) {

            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Dùng cho SELECT – trả về ResultSet (gọi xong nhớ đóng connection ở chỗ caller)
    public static ResultSet executeQuery(String sql, Object... values) {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = prepare(con, sql, values);
            return stmt.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Tạo PreparedStatement / CallableStatement chung
    private static PreparedStatement prepare(Connection con, String sql, Object... values) throws Exception {
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = con.prepareCall(sql);      // cho stored procedure
        } else {
            stmt = con.prepareStatement(sql); // query thường
        }
        for (int i = 0; i < values.length; i++) {
            stmt.setObject(i + 1, values[i]);
        }
        return stmt;
    }
}
