package com.asm.controller;

import java.io.IOException;

import com.asm.dao.NewsletterDao;
import com.asm.entity.Newsletter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/newsletter")
public class NewsletterRegisterServlet extends HttpServlet {

    private NewsletterDao dao = new NewsletterDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        if (email != null) {
			email = email.trim();
		}

        String msg;
        if (email == null || email.isBlank()) {
            msg = "Email không hợp lệ.";
        } else {
            Newsletter n = dao.findByEmail(email);
            if (n == null) {
                n = new Newsletter();
                n.setEmail(email);
                n.setEnabled(true);
                dao.insert(n);
                msg = "Đăng ký nhận tin thành công.";
            } else {
                if (!n.isEnabled()) {
                    n.setEnabled(true);
                    dao.update(n);
                    msg = "Email đã được mở lại để nhận tin.";
                } else {
                    msg = "Email này đã đăng ký trước đó.";
                }
            }
        }

        // Lưu message vào session để sidebar đọc
        HttpSession session = req.getSession();
        session.setAttribute("newsletterMessage", msg);

        // quay về trang trước (nếu có) hoặc home
        String referer = req.getHeader("Referer");
        if (referer == null) {
            referer = req.getContextPath() + "/home";
        }
        resp.sendRedirect(referer);
    }
}