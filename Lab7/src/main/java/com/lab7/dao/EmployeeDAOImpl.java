package com.lab7.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lab7.entity.Employee;
import com.lab7.util.Jdbc;

public class EmployeeDAOImpl implements EmployeeDAO {

    private Employee map(ResultSet rs) throws Exception {
        Employee e = new Employee();
        e.setId(rs.getString("Id"));
        e.setPassword(rs.getString("Password"));
        e.setFullname(rs.getString("Fullname"));
        e.setPhoto(rs.getString("Photo"));
        e.setGender(rs.getBoolean("Gender"));
        e.setBirthday(rs.getDate("Birthday"));
        e.setSalary(rs.getDouble("Salary"));
        e.setEmail(rs.getString("Email"));
        e.setDepartId(rs.getString("DepartId"));
        return e;
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM Employees ORDER BY Fullname";
        List<Employee> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = Jdbc.executeQuery(sql);
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
        }
        return list;
    }

    @Override
    public Employee findById(String id) {
        String sql = "SELECT * FROM Employees WHERE Id=?";
        ResultSet rs = null;
        try {
            rs = Jdbc.executeQuery(sql, id);
            if (rs.next()) {
                return map(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
        }
        return null;
    }

    @Override
    public void create(Employee e) {
        String sql = """
                INSERT INTO Employees
                (Id, Password, Fullname, Photo, Gender, Birthday, Salary, Email, DepartId)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        Jdbc.executeUpdate(sql,
                e.getId(),
                e.getPassword(),
                e.getFullname(),
                e.getPhoto(),
                e.isGender(),
                new Date(e.getBirthday().getTime()),
                e.getSalary(),
                e.getEmail(),
                e.getDepartId());
    }

    @Override
    public void update(Employee e) {
        String sql = """
                UPDATE Employees SET
                Password=?, Fullname=?, Photo=?, Gender=?, Birthday=?, Salary=?, Email=?, DepartId=?
                WHERE Id=?
                """;
        Jdbc.executeUpdate(sql,
                e.getPassword(),
                e.getFullname(),
                e.getPhoto(),
                e.isGender(),
                new Date(e.getBirthday().getTime()),
                e.getSalary(),
                e.getEmail(),
                e.getDepartId(),
                e.getId());
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM Employees WHERE Id=?";
        Jdbc.executeUpdate(sql, id);
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.getStatement().getConnection().close();
            } catch (Exception ignored) {
            }
        }
    }
}
