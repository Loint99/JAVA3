package com.asm.dao;

import com.asm.utils.DBConnect;

import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDateTime;

public class PasswordResetDao {

    public void upsertOtp(String email, String otpHash, Timestamp expireAt) {
        String sql = """
            MERGE PasswordResetOTP AS t
            USING (SELECT ? AS Email, ? AS OtpHash, ? AS ExpireAt) AS s
            ON t.Email = s.Email
            WHEN MATCHED THEN
                UPDATE SET OtpHash = s.OtpHash, ExpireAt = s.ExpireAt, Attempt = 0
            WHEN NOT MATCHED THEN
                INSERT (Email, OtpHash, ExpireAt, Attempt)
                VALUES (s.Email, s.OtpHash, s.ExpireAt, 0);
        """;

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, otpHash);
            ps.setTimestamp(3, expireAt);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyOtp(String email, String otpHash) {
        String sql = """
            SELECT OtpHash, ExpireAt, Attempt
            FROM PasswordResetOTP
            WHERE Email = ?
        """;

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return false;

                String dbHash = rs.getString("OtpHash");
                Timestamp exp = rs.getTimestamp("ExpireAt");
                int attempt = rs.getInt("Attempt");

                if (attempt >= 5) return false; // chặn đoán OTP
                if (exp.before(Timestamp.valueOf(LocalDateTime.now()))) return false;

                boolean ok = dbHash.equals(otpHash);

                // tăng attempt nếu sai
                if (!ok) {
                    try (PreparedStatement up = con.prepareStatement(
                            "UPDATE PasswordResetOTP SET Attempt = Attempt + 1 WHERE Email = ?")) {
                        up.setString(1, email);
                        up.executeUpdate();
                    }
                }

                return ok;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteOtp(String email) {
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM PasswordResetOTP WHERE Email = ?")) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Hash SHA-256 đơn giản cho OTP (đủ cho bài)
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
