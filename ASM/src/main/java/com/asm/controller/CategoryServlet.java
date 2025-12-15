package com.asm.controller;

import java.io.IOException;
import java.util.List;

import com.asm.dao.CategoryDao;
import com.asm.dao.NewsDao;
import com.asm.entity.Category;
import com.asm.entity.News;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    private NewsDao newsDao = new NewsDao();
    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id"); // id loại tin: TS, VH...
        if (id == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        Category cat = categoryDao.findById(id);
        List<Category> categories = categoryDao.findAll();
        List<News> listNews = newsDao.findByCategory(id);
        List<News> topViewed = newsDao.findTopViewed(5);
        List<News> latestNews = newsDao.findLatest(5);

        req.setAttribute("categories", categories);
        req.setAttribute("category", cat);
        req.setAttribute("listNews", listNews);
        req.setAttribute("topViewed", topViewed);
        req.setAttribute("latestNews", latestNews);

        req.getRequestDispatcher("/views/site/list.jsp").forward(req, resp);

        HttpSession session = req.getSession(false);
        if (session != null) {
            @SuppressWarnings("unchecked")
            List<News> recent = (List<News>) session.getAttribute("recentViewed");
            req.setAttribute("recentViewed", recent);

            String msg = (String) session.getAttribute("newsletterMessage");
            if (msg != null) {
                req.setAttribute("newsletterMessage", msg);
                session.removeAttribute("newsletterMessage");
            }
        }

    }
}
