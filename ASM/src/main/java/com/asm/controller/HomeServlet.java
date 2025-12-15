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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private NewsDao newsDao = new NewsDao();
    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Category> categories = categoryDao.findAll();
        List<News> homeNews   = newsDao.findHomeNews();

        List<News> topViewed  = newsDao.findTopViewed(5);
        List<News> latestNews = newsDao.findLatest(5);

        req.setAttribute("categories", categories);
        req.setAttribute("homeNews", homeNews);
        req.setAttribute("topViewed", topViewed);
        req.setAttribute("latestNews", latestNews);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

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
