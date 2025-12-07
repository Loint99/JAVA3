package com.lab6;

import java.sql.*;

public class JdbcPrepared {

    static String URL  = "jdbc:sqlserver://localhost:1433;databaseName=HRM;encrypt=false;trustServerCertificate=true";
    static String USER = "sa";        
    static String PASS = "123";    

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE/INSERT/DELETE với tham số
    public static int executeUpdate(String sql, Object... args) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        }
    }

    // SELECT với tham số
    public static ResultSet executeQuery(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
        return ps.executeQuery();
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== INSERT bằng PreparedStatement ===");
            String sqlInsert = "INSERT INTO Departments(Id, Name, Description) VALUES (?, ?, ?)";
            int rows = executeUpdate(sqlInsert,
                    "QA",
                    "Quality Assurance",
                    "Phòng kiểm thử");
            System.out.println("Rows inserted: " + rows);

            System.out.println("\n=== SELECT 1 phòng ban theo Id ===");
            String sqlSelect = "SELECT * FROM Departments WHERE Id = ?";
            ResultSet rs = executeQuery(sqlSelect, "QA");
            while (rs.next()) {
                System.out.println(
                        rs.getString("Id") + " - " +
                        rs.getString("Name") + " - " +
                        rs.getString("Description"));
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
