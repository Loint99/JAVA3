package com.lab5.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Base64;   // QUAN TRỌNG

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    String encoded = cookie.getValue();

                    byte[] bytes = Base64.getDecoder().decode(encoded);
                    String[] userInfo = new String(bytes).split(",");
                    if (userInfo.length == 2) {
                        req.setAttribute("username", userInfo[0]);
                        req.setAttribute("password", userInfo[1]);
                    }
                }
            }
        }

        req.getRequestDispatcher("/views/login-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");

        if (username.equalsIgnoreCase("FPT") && "poly".equals(password)) {
            req.setAttribute("message", "Đăng nhập thành công");
            req.getSession().setAttribute("username", username);

            if (remember != null) {
                byte[] bytes = (username + "," + password).getBytes();
                String userInfo = Base64.getEncoder().encodeToString(bytes);

                Cookie cookie = new Cookie("user", userInfo);
                cookie.setMaxAge(30 * 24 * 60 * 60);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        } else {
            req.setAttribute("message", "Sai user hoặc mật khẩu");
        }

        req.getRequestDispatcher("/views/login-form.jsp").forward(req, resp);
    }
}