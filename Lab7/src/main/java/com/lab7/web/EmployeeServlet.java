package com.lab7.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.lab7.dao.DepartmentDAO;
import com.lab7.dao.DepartmentDAOImpl;
import com.lab7.dao.EmployeeDAO;
import com.lab7.dao.EmployeeDAOImpl;
import com.lab7.entity.Department;
import com.lab7.entity.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet({
        "/employee/index",
        "/employee/edit/*",
        "/employee/create",
        "/employee/update",
        "/employee/delete",
        "/employee/reset"
})
@MultipartConfig
public class EmployeeServlet extends HttpServlet {

    private EmployeeDAO empDao = new EmployeeDAOImpl();
    private DepartmentDAO depDao = new DepartmentDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        // Convert chuỗi -> Date
        DateConverter dc = new DateConverter(new Date());
        dc.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dc, Date.class);

        Employee form = new Employee();
        try {
            BeanUtils.populate(form, req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = req.getServletPath();
        String message = null;

        /* ---------- XỬ LÝ UPLOAD ẢNH (CHỈ KHI multipart) ---------- */
        String contentType = req.getContentType();
        if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
            Part photoPart = req.getPart("photoFile");
            if (photoPart != null && photoPart.getSize() > 0) {
                String fileName = Paths.get(photoPart.getSubmittedFileName())
                        .getFileName().toString();
                String uploadPath = req.getServletContext().getRealPath("/uploads");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}
                photoPart.write(uploadPath + File.separator + fileName);
                form.setPhoto(fileName);
            }
        }

        /* ----------------- XỬ LÝ CÁC CHỨC NĂNG ------------------- */
        if (path.contains("edit")) {
            // /employee/edit/NV01
            String id = req.getPathInfo().substring(1);
            form = empDao.findById(id);
            message = "Editing " + id;
        } else if (path.contains("create")) {
            if (form.getPassword() == null || form.getPassword().isBlank()) {
                form.setPassword("123");
            }
            empDao.create(form);
            form = new Employee();
            message = "Created employee!";
        } else if (path.contains("update")) {
            empDao.update(form);
            message = "Updated employee!";
        } else if (path.contains("delete")) {
            empDao.deleteById(form.getId());
            form = new Employee();
            message = "Deleted employee!";
        } else if (path.contains("reset")) {
            form = new Employee();
        }

        List<Employee> employees = empDao.findAll();
        List<Department> departments = depDao.findAll();

        req.setAttribute("item", form);
        req.setAttribute("list", employees);
        req.setAttribute("departments", departments);
        req.setAttribute("message", message);

        req.getRequestDispatcher("/employee.jsp").forward(req, resp);
    }
}
