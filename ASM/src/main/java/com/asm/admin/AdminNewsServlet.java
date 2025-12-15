package com.asm.admin;

import com.asm.dao.NewsDao;
import com.asm.dao.CategoryDao;
import com.asm.entity.News;
import com.asm.entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/news")
public class AdminNewsServlet extends HttpServlet {

    private NewsDao newsDao = new NewsDao();
    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	String q = req.getParameter("q");
    	List<News> list;

    	if (q != null && !q.trim().isEmpty()) {
    	    list = newsDao.search(q.trim());
    	} else {
    	    list = newsDao.findAll();
    	}

    	List<News> topViewed = newsDao.findTopViewed(5); // top 5
    	req.setAttribute("q", q);
    	req.setAttribute("listNews", list);
    	req.setAttribute("topViewedNews", topViewed);

        String editId = req.getParameter("editId");

        List<Category> categories = categoryDao.findAll();

        req.setAttribute("categories", categories);

        if (editId != null) {
            News editNews = newsDao.findById(editId);
            req.setAttribute("editNews", editNews);
        }

        req.getRequestDispatcher("/views/admin/news.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String id = req.getParameter("id");

        if ("delete".equals(action)) {
            newsDao.delete(id);
        } else {
            News n = new News();
            n.setId(id);
            n.setTitle(req.getParameter("title"));
            n.setContent(req.getParameter("content"));
            n.setImage(req.getParameter("image"));
            n.setCategoryId(req.getParameter("categoryId"));
            n.setHome("on".equals(req.getParameter("home")));
            n.setAuthor("admin"); // hoặc lấy từ session currentUser
            try {
                n.setViewCount(Integer.parseInt(req.getParameter("viewCount")));
            } catch (Exception e) {
                n.setViewCount(0);
            }

            if ("create".equals(action)) {
                newsDao.insert(n);
            } else if ("update".equals(action)) {
                newsDao.update(n);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/admin/news");
    }
}
