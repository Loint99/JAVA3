package com.asm.admin;

import com.asm.dao.CategoryDao;
import com.asm.entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/category")
public class AdminCategoryServlet extends HttpServlet {

    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String editId = req.getParameter("editId");

        List<Category> list = categoryDao.findAll();
        req.setAttribute("listCategory", list);

        if (editId != null) {
            Category edit = categoryDao.findById(editId);
            req.setAttribute("editCategory", edit);
        }

        req.getRequestDispatcher("/views/admin/category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String id = req.getParameter("id");

        if ("delete".equals(action)) {
            categoryDao.delete(id);
        } else {
            Category c = new Category();
            c.setId(id);
            c.setName(req.getParameter("name"));

            if ("create".equals(action)) categoryDao.insert(c);
            else if ("update".equals(action)) categoryDao.update(c);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }
}
