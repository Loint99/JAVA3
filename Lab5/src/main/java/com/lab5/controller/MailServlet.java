package com.lab5.controller;

import java.io.IOException;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mail")
public class MailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/mail-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String from = req.getParameter("from");
        String pass = req.getParameter("password"); // app password
        String to = req.getParameter("to");
        String subject = req.getParameter("subject");
        String body = req.getParameter("body");

        try {
            Mailer.send(from, pass, to, subject, body);
            req.setAttribute("message", "Gửi mail thành công!");
        } catch (MessagingException e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi gửi mail: " + e.getMessage());
        }

        req.getRequestDispatcher("/views/mail-form.jsp").forward(req, resp);
    }
}