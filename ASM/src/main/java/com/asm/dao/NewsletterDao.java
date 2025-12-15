package com.asm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asm.entity.Newsletter;
import com.asm.utils.DBConnect;

public class NewsletterDao {

    private Newsletter mapRow(ResultSet rs) throws SQLException {
        Newsletter n = new Newsletter();
        n.setEmail(rs.getString("Email"));
        n.setEnabled(rs.getBoolean("Enabled"));
        return n;
    }

    public List<Newsletter> findAll() {
        String sql = "SELECT * FROM NEWSLETTERS";
        List<Newsletter> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
				list.add(mapRow(rs));
			}
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public Newsletter findByEmail(String email) {
        String sql = "SELECT * FROM NEWSLETTERS WHERE Email=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
					return mapRow(rs);
				}
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public void insert(Newsletter n) {
        String sql = "INSERT INTO NEWSLETTERS (Email, Enabled) VALUES (?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, n.getEmail());
            ps.setBoolean(2, n.isEnabled());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(Newsletter n) {
        String sql = "UPDATE NEWSLETTERS SET Enabled=? WHERE Email=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, n.isEnabled());
            ps.setString(2, n.getEmail());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(String email) {
        String sql = "DELETE FROM NEWSLETTERS WHERE Email=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
