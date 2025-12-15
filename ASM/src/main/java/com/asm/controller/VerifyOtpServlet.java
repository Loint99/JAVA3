package com.asm.controller;

import com.asm.dao.PasswordResetDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/verify-otp")
public class VerifyOtpServlet extends HttpServlet {

    private PasswordResetDao resetDao = new PasswordResetDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/site/otp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String otp = req.getParameter("otp");

        HttpSession session = req.getSession(false);
        String email = (session == null) ? null : (String) session.getAttribute("resetEmail");

        if (email == null) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }

        boolean ok = resetDao.verifyOtp(email, PasswordResetDao.sha256(otp));
        if (!ok) {
            req.setAttribute("error", "OTP sai hoặc đã hết hạn.");
            req.getRequestDispatcher("/views/site/otp.jsp").forward(req, resp);
            return;
        }

        // Đánh dấu đã qua OTP
        session.setAttribute("otpVerified", true);
        resp.sendRedirect(req.getContextPath() + "/reset-password");
    }
}
