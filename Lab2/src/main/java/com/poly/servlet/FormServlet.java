package com.poly.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/form/update")
public class FormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HashMap<String, Object> user = new HashMap<>();
        user.put("fullname", "Nguyễn Văn Tèo");
        user.put("gender", true);
        user.put("country", "VN");

        req.setAttribute("user", user);
        req.setAttribute("editable", true);

        req.getRequestDispatcher("/views/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Fullname: " + req.getParameter("fullname"));
        req.getRequestDispatcher("/views/form.jsp").forward(req, resp);
    }
}
