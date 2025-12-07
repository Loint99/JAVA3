package com.lab6;

import java.sql.*;

public class JdbcCallable {

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

    public static int executeUpdate(String sql, Object... args) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = con.prepareCall(sql)) {

            for (int i = 0; i < args.length; i++) {
                cs.setObject(i + 1, args[i]);
            }
            return cs.executeUpdate();
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        CallableStatement cs = con.prepareCall(sql);
        for (int i = 0; i < args.length; i++) {
            cs.setObject(i + 1, args[i]);
        }
        return cs.executeQuery();
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== Gọi spInsert ===");
            String sqlInsert = "{CALL spInsert(?, ?, ?)}";
            int rows = executeUpdate(sqlInsert,
                    "PRD",
                    "Sản xuất",
                    "Phòng sản xuất");
            System.out.println("Rows inserted: " + rows);

            System.out.println("\n=== Gọi spSelectAll ===");
            String sqlSelectAll = "{CALL spSelectAll()}";
            ResultSet rs = executeQuery(sqlSelectAll);
            while (rs.next()) {
                System.out.println(
                        rs.getString("Id") + " - " +
                        rs.getString("Name") + " - " +
                        rs.getString("Description"));
            }
            rs.getStatement().getConnection().close();

            System.out.println("\n=== Gọi spSelectById('IT') ===");
            String sqlSelectById = "{CALL spSelectById(?)}";
            ResultSet rs2 = executeQuery(sqlSelectById, "IT");
            while (rs2.next()) {
                System.out.println(
                        rs2.getString("Id") + " - " +
                        rs2.getString("Name") + " - " +
                        rs2.getString("Description"));
            }
            rs2.getStatement().getConnection().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
