package com.lab7.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lab7.entity.Department;
import com.lab7.util.Jdbc;

public class DepartmentDAOImpl implements DepartmentDAO {

    private Department map(ResultSet rs) throws Exception {
        Department d = new Department();
        d.setId(rs.getString("Id"));
        d.setName(rs.getString("Name"));
        d.setDescription(rs.getString("Description"));
        return d;
    }

    @Override
    public List<Department> findAll() {
        String sql = "SELECT * FROM Departments ORDER BY Name";
        List<Department> list = new ArrayList<>();
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
    public Department findById(String id) {
        String sql = "SELECT * FROM Departments WHERE Id = ?";
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
    public List<Department> findByKeyword(String keyword) {
        String sql = "SELECT * FROM Departments WHERE Name LIKE ? ORDER BY Name";
        List<Department> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = Jdbc.executeQuery(sql, "%" + keyword + "%");
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
    public void create(Department item) {
        String sql = "INSERT INTO Departments(Id, Name, Description) VALUES (?, ?, ?)";
        Jdbc.executeUpdate(sql, item.getId(), item.getName(), item.getDescription());
    }

    @Override
    public void update(Department item) {
        String sql = "UPDATE Departments SET Name=?, Description=? WHERE Id=?";
        Jdbc.executeUpdate(sql, item.getName(), item.getDescription(), item.getId());
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM Departments WHERE Id=?";
        Jdbc.executeUpdate(sql, id);
    }

    // Hàm tiện đóng ResultSet + Connection
    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.getStatement().getConnection().close();
            } catch (Exception ignored) {
            }
        }
    }
}
