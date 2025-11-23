package com.lab.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender   = req.getParameter("gender");
        String country  = req.getParameter("country");
        String[] hobbies = req.getParameterValues("hobbies");
        String note     = req.getParameter("note");

        System.out.println("=== REGISTER FORM DATA ===");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Gender: " + gender);
        System.out.println("Country: " + country);
        System.out.println("Hobbies: " + (hobbies == null ? "none" : Arrays.toString(hobbies)));
        System.out.println("Note: " + note);
        System.out.println("===========================");

        req.setAttribute("message", "Đã nhận dữ liệu, xem console để kiểm tra.");
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }
}