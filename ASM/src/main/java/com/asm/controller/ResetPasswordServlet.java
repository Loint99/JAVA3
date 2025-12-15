package com.asm.controller;

import com.asm.dao.PasswordResetDao;
import com.asm.dao.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {

    private UserDao userDao = new UserDao();
    private PasswordResetDao resetDao = new PasswordResetDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/site/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        String email = (session == null) ? null : (String) session.getAttribute("resetEmail");
        Boolean verified = (session == null) ? null : (Boolean) session.getAttribute("otpVerified");

        if (email == null || verified == null || !verified) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }

        String pass = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        if (pass == null || pass.isBlank() || !pass.equals(confirm)) {
            req.setAttribute("error", "Mật khẩu không hợp lệ hoặc không khớp.");
            req.getRequestDispatcher("/views/site/reset-password.jsp").forward(req, resp);
            return;
        }

        // Update password theo email
        userDao.updatePasswordByEmail(email, pass);

        // Xóa OTP và cleanup session
        resetDao.deleteOtp(email);
        session.removeAttribute("resetEmail");
        session.removeAttribute("otpVerified");

        session.setAttribute("registerMessage", "Đổi mật khẩu thành công, mời đăng nhập.");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
