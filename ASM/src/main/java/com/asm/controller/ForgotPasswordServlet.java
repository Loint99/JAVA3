package com.asm.controller;

import com.asm.dao.PasswordResetDao;
import com.asm.dao.UserDao;
import com.asm.entity.User;
import com.asm.utils.EmailUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

    private UserDao userDao = new UserDao();
    private PasswordResetDao resetDao = new PasswordResetDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/site/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");

        // Check email có trong DB không
        User u = userDao.findByEmail(email);
        if (u == null) {
            req.setAttribute("error", "Email không tồn tại trong hệ thống.");
            req.getRequestDispatcher("/views/site/forgot-password.jsp").forward(req, resp);
            return;
        }

        // Tạo OTP 6 số
        String otp = String.format("%06d", new Random().nextInt(1_000_000));
        String otpHash = PasswordResetDao.sha256(otp);

        Timestamp expireAt = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5));
        resetDao.upsertOtp(email, otpHash, expireAt);

        // Gửi mail
        EmailUtil.sendOtp(email, otp);

        // Lưu email vào session để bước sau dùng
        HttpSession session = req.getSession();
        session.setAttribute("resetEmail", email);

        resp.sendRedirect(req.getContextPath() + "/verify-otp");
    }
}
