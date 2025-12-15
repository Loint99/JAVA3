package com.asm.admin;

import com.asm.dao.NewsletterDao;
import com.asm.entity.Newsletter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/newsletter")
public class AdminNewsletterServlet extends HttpServlet {

    private NewsletterDao dao = new NewsletterDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Newsletter> list = dao.findAll();
        req.setAttribute("listNewsletter", list);

        req.getRequestDispatcher("/views/admin/newsletter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String email = req.getParameter("email");

        if ("delete".equals(action)) {
            dao.delete(email);
        } else {
            Newsletter n = new Newsletter();
            n.setEmail(email);
            n.setEnabled("on".equals(req.getParameter("enabled")));
            if ("create".equals(action)) dao.insert(n);
            else if ("update".equals(action)) dao.update(n);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/newsletter");
    }
}
