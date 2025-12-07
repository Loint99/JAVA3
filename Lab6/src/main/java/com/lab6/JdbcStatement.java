package com.lab6;

import java.sql.*;

public class JdbcStatement {

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

    // executeUpdate cho INSERT/UPDATE/DELETE
    public static int executeUpdate(String sql) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement st = con.createStatement()) {
            return st.executeUpdate(sql);
        }
    }

    // executeQuery cho SELECT
    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        Statement st = con.createStatement();
        // Không đóng ở đây để còn đọc ResultSet, đóng ở chỗ gọi
        return st.executeQuery(sql);
    }

    // Hàm main demo
    public static void main(String[] args) {
        try {
            System.out.println("=== SELECT bằng Statement ===");
            ResultSet rs = executeQuery("SELECT * FROM Departments");
            while (rs.next()) {
                System.out.println(
                        rs.getString("Id") + " - " +
                        rs.getString("Name") + " - " +
                        rs.getString("Description"));
            }
            rs.getStatement().getConnection().close();

            System.out.println("\n=== INSERT bằng Statement ===");
            String sqlInsert = "INSERT INTO Departments(Id, Name, Description) " +
                               "VALUES ('MKT', N'Marketing', N'Phòng marketing')";
            int rows = executeUpdate(sqlInsert);
            System.out.println("Rows inserted: " + rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

