package com.asm.controller;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {

    private NewsDao newsDao = new NewsDao();
    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        // tăng view
        newsDao.increaseView(id);
        News news = newsDao.findById(id);
        if (news == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        // Lưu lịch sử xem trong session (tối đa 5 bản tin)
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        List<News> recent = (List<News>) session.getAttribute("recentViewed");
        if (recent == null) {
            recent = new ArrayList<>();
        }

        // Xóa nếu đã tồn tại trong list để khỏi trùng
        recent.removeIf(n -> n.getId().equals(news.getId()));
        // Thêm lên đầu
        recent.add(0, news);
        // Nếu > 5 thì cắt bớt
        if (recent.size() > 5) {
            recent = recent.subList(0, 5);
        }
        session.setAttribute("recentViewed", recent);

        List<Category> categories = categoryDao.findAll();
        List<News> related = newsDao.findRelated(news.getCategoryId(), news.getId(), 5);
        List<News> topViewed = newsDao.findTopViewed(5);
        List<News> latestNews = newsDao.findLatest(5);

        req.setAttribute("categories", categories);
        req.setAttribute("news", news);
        req.setAttribute("related", related);
        req.setAttribute("topViewed", topViewed);
        req.setAttribute("latestNews", latestNews);
        req.setAttribute("recentViewed", recent);

        req.getRequestDispatcher("/views/site/detail.jsp").forward(req, resp);
    }
}
