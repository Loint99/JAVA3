package com.asm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Hủy session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Quay về trang chủ
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
