package com.asm.controller;

import java.io.IOException;

import com.asm.dao.UserDao;
import com.asm.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null) {
            String msg = (String) session.getAttribute("registerMessage");
            if (msg != null) {
                req.setAttribute("registerMessage", msg);
                session.removeAttribute("registerMessage");
            }
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // DEBUG
        System.out.println(">>> LOGIN username=" + username + ", password=" + password);

        User user = userDao.login(username, password);

        if (user == null) {
            System.out.println(">>> LOGIN FAILED");
            req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        System.out.println(">>> LOGIN OK: " + user.getId());

        HttpSession session = req.getSession();
        session.setAttribute("currentUser", user);

        resp.sendRedirect(req.getContextPath() + "/admin/news");
    }
}

