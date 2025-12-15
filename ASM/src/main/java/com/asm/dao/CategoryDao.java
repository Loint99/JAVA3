package com.asm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asm.entity.Category;
import com.asm.utils.DBConnect;

public class CategoryDao {

    private Category mapRow(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setId(rs.getString("Id"));
        c.setName(rs.getString("Name"));
        return c;
    }

    public List<Category> findAll() {
        String sql = "SELECT * FROM CATEGORIES ORDER BY Name";
        List<Category> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
				list.add(mapRow(rs));
			}
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public Category findById(String id) {
        String sql = "SELECT * FROM CATEGORIES WHERE Id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
					return mapRow(rs);
				}
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public void insert(Category c) {
        String sql = "INSERT INTO CATEGORIES (Id, Name) VALUES (?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getId());
            ps.setString(2, c.getName());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(Category c) {
        String sql = "UPDATE CATEGORIES SET Name=? WHERE Id=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(String id) {
        String sql = "DELETE FROM CATEGORIES WHERE Id=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
