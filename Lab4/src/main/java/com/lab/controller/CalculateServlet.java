package com.lab.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/calculate", "/calculate/add", "/calculate/sub"})
public class CalculateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("message", "Nhập số và chọn phép tính");
        req.getRequestDispatcher("/views/calc.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String aStr = req.getParameter("a");
        String bStr = req.getParameter("b");
        String path = req.getServletPath();

        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);
            double c;
            String msg;

            if (path.endsWith("/add")) {
                c = a + b;
                msg = a + " + " + b + " = " + c;
            } else {
                c = a - b;
                msg = a + " - " + b + " = " + c;
            }

            req.setAttribute("message", msg);
        } catch (Exception e) {
            req.setAttribute("message", "Giá trị nhập không hợp lệ!");
        }

        req.getRequestDispatcher("/views/calc.jsp").forward(req, resp);
    }
}