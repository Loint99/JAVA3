package com.asm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.asm.entity.User;
import com.asm.utils.DBConnect;

public class UserDao {

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getString("Id"));
        u.setPassword(rs.getString("Password"));
        u.setFullname(rs.getString("Fullname"));
        u.setBirthday(rs.getDate("Birthday"));
        u.setGender((Boolean) rs.getObject("Gender"));
        u.setMobile(rs.getString("Mobile"));
        u.setEmail(rs.getString("Email"));
        u.setRole(rs.getBoolean("Role"));
        return u;
    }

    public User findById(String id) {
        String sql = "SELECT * FROM USERS WHERE Id=?";
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
    
    public User findByEmail(String email) {
        String sql = "SELECT * FROM USERS WHERE Email=?";
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

    public User login(String id, String password) {
        String sql = "SELECT * FROM USERS WHERE Id = ? AND LTRIM(RTRIM(Password)) = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS ORDER BY Fullname";
        List<User> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
				list.add(mapRow(rs));
			}
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void insert(User u) {
        String sql = "INSERT INTO USERS (Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getId());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFullname());
            if (u.getBirthday() != null) {
				ps.setDate(4, new java.sql.Date(u.getBirthday().getTime()));
			} else {
				ps.setNull(4, Types.DATE);
			}
            if (u.getGender() != null) {
				ps.setBoolean(5, u.getGender());
			} else {
				ps.setNull(5, Types.BIT);
			}
            ps.setString(6, u.getMobile());
            ps.setString(7, u.getEmail());
            ps.setBoolean(8, u.isRole());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(User u) {
        String sql = "UPDATE USERS SET Password=?, Fullname=?, Birthday=?, Gender=?, Mobile=?, Email=?, Role=? " +
                     "WHERE Id=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getPassword());
            ps.setString(2, u.getFullname());
            if (u.getBirthday() != null) {
				ps.setDate(3, new java.sql.Date(u.getBirthday().getTime()));
			} else {
				ps.setNull(3, Types.DATE);
			}
            if (u.getGender() != null) {
				ps.setBoolean(4, u.getGender());
			} else {
				ps.setNull(4, Types.BIT);
			}
            ps.setString(5, u.getMobile());
            ps.setString(6, u.getEmail());
            ps.setBoolean(7, u.isRole());
            ps.setString(8, u.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(String id) {
        String sql = "DELETE FROM USERS WHERE Id=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public List<User> search(String keyword) {
        String sql = """
            SELECT * FROM USERS
            WHERE Id LIKE ? OR Fullname LIKE ? OR Email LIKE ? OR Mobile LIKE ?
            ORDER BY Id ASC
        """;
        List<User> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String k = "%" + keyword + "%";
            ps.setString(1, k);
            ps.setString(2, k);
            ps.setString(3, k);
            ps.setString(4, k);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public void updatePasswordByEmail(String email, String newPassword) {
        String sql = "UPDATE USERS SET Password = ? WHERE Email = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }


}