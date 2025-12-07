package com.lab7.web;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.lab7.dao.DepartmentDAO;
import com.lab7.dao.DepartmentDAOImpl;
import com.lab7.entity.Department;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
        "/department/index",
        "/department/edit/*",
        "/department/create",
        "/department/update",
        "/department/delete",
        "/department/reset",
        "/department/search"
})
public class DepartmentServlet extends HttpServlet {

    private DepartmentDAO dao = new DepartmentDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();

        Department form = new Department();
        try {
            BeanUtils.populate(form, req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String keyword = req.getParameter("keyword");
        if (keyword == null) {
			keyword = "";
		}

        String message = null;

        if (path.contains("edit")) {
            String id = req.getPathInfo().substring(1);
            form = dao.findById(id);
            message = "Editing department " + id;
        } else if (path.contains("create")) {
            dao.create(form);
            form = new Department();
            message = "Created successfully!";
        } else if (path.contains("update")) {
            dao.update(form);
            message = "Updated successfully!";
        } else if (path.contains("delete")) {
            dao.deleteById(form.getId());
            form = new Department();
            message = "Deleted successfully!";
        } else if (path.contains("reset")) {
            form = new Department();
        } else if (path.contains("search")) {
            // keyword xử lý bên dưới
        }

        List<Department> list =
                (keyword.isBlank()) ? dao.findAll() : dao.findByKeyword(keyword);

        req.setAttribute("item", form);
        req.setAttribute("list", list);
        req.setAttribute("keyword", keyword);
        req.setAttribute("message", message);

        req.getRequestDispatcher("/department.jsp").forward(req, resp);
    }
}
