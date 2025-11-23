package com.poly.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("message", "Welcome to FPT Polytechnic");

        HashMap<String, Object> user = new HashMap<>();
        user.put("fullname", "Nguyễn Văn Tèo");
        user.put("gender", "Male");
        user.put("country", "Việt Nam");

        req.setAttribute("user", user);

        req.getRequestDispatcher("/views/page.jsp").forward(req, resp);
    }
}
