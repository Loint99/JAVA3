package com.asm.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asm.entity.News;
import com.asm.utils.DBConnect;

public class NewsDao {

    private News mapRow(ResultSet rs) throws SQLException {
        News n = new News();
        n.setId(rs.getString("Id"));
        n.setTitle(rs.getString("Title"));
        n.setContent(rs.getString("Content"));
        n.setImage(rs.getString("Image"));
        n.setPostedDate(rs.getDate("PostedDate"));
        n.setAuthor(rs.getString("Author"));
        n.setViewCount(rs.getInt("ViewCount"));
        n.setCategoryId(rs.getString("CategoryId"));
        n.setHome(rs.getBoolean("Home"));
        return n;
    }

    public List<News> findHomeNews() {
        String sql = "SELECT * FROM NEWS WHERE Home = 1 ORDER BY PostedDate DESC";
        List<News> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
				list.add(mapRow(rs));
			}
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<News> findTopViewed(int topN) {
    	String sql = """
    	        SELECT TOP (?) * FROM NEWS
    	        ORDER BY ViewCount DESC
    	    """;
    	    List<News> list = new ArrayList<>();
    	    try (Connection con = DBConnect.getConnection();
    	         PreparedStatement ps = con.prepareStatement(sql)) {

    	        ps.setInt(1, topN);
    	        try (ResultSet rs = ps.executeQuery()) {
    	            while (rs.next()) list.add(mapRow(rs));
    	        }
    	    } catch (Exception e) { e.printStackTrace(); }
    	    return list;
    }

    public List<News> findLatest(int limit) {
        String sql = "SELECT TOP (?) * FROM NEWS ORDER BY PostedDate DESC";
        List<News> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
					list.add(mapRow(rs));
				}
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<News> findByCategory(String categoryId) {
        String sql = "SELECT * FROM NEWS WHERE CategoryId = ? ORDER BY PostedDate DESC";
        List<News> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
					list.add(mapRow(rs));
				}
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public News findById(String id) {
        String sql = "SELECT * FROM NEWS WHERE Id = ?";
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

    public List<News> findRelated(String categoryId, String excludeId, int limit) {
        String sql = "SELECT TOP (?) * FROM NEWS WHERE CategoryId = ? AND Id <> ? ORDER BY PostedDate DESC";
        List<News> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ps.setString(2, categoryId);
            ps.setString(3, excludeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
					list.add(mapRow(rs));
				}
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<News> findAll() {
        String sql = "SELECT * FROM NEWS ORDER BY PostedDate DESC";
        List<News> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
				list.add(mapRow(rs));
			}
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void insert(News n) {
        String sql = "INSERT INTO NEWS (Id, Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) " +
                     "VALUES (?, ?, ?, ?, GETDATE(), ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, n.getId());
            ps.setString(2, n.getTitle());
            ps.setString(3, n.getContent());
            ps.setString(4, n.getImage());
            ps.setString(5, n.getAuthor());
            ps.setInt(6, n.getViewCount());
            ps.setString(7, n.getCategoryId());
            ps.setBoolean(8, n.isHome());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(News n) {
        String sql = "UPDATE NEWS SET Title=?, Content=?, Image=?, CategoryId=?, Home=?, ViewCount=? " +
                     "WHERE Id=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, n.getTitle());
            ps.setString(2, n.getContent());
            ps.setString(3, n.getImage());
            ps.setString(4, n.getCategoryId());
            ps.setBoolean(5, n.isHome());
            ps.setInt(6, n.getViewCount());
            ps.setString(7, n.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(String id) {
        String sql = "DELETE FROM NEWS WHERE Id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void increaseView(String id) {
        String sql = "UPDATE NEWS SET ViewCount = ViewCount + 1 WHERE Id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<News> search(String keyword) {
    	String sql = """
    	        SELECT * FROM NEWS
    	        WHERE Title LIKE ? OR Content LIKE ? OR Author LIKE ?
    	        ORDER BY PostedDate DESC
    	    """;
    	    List<News> list = new ArrayList<>();
    	    try (Connection con = DBConnect.getConnection();
    	         PreparedStatement ps = con.prepareStatement(sql)) {

    	        String k = "%" + keyword + "%";
    	        ps.setString(1, k);
    	        ps.setString(2, k);
    	        ps.setString(3, k);

    	        try (ResultSet rs = ps.executeQuery()) {
    	            while (rs.next()) list.add(mapRow(rs));
    	        }
    	    } catch (Exception e) { e.printStackTrace(); }
    	    return list;
    }

}
