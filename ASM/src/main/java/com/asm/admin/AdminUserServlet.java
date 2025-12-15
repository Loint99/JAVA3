package com.asm.admin;

import com.asm.dao.UserDao;
import com.asm.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/admin/user")
public class AdminUserServlet extends HttpServlet {

    private UserDao userDao = new UserDao();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	String q = req.getParameter("q");
    	List<User> list;

    	if (q != null && !q.trim().isEmpty()) {
    	    list = userDao.search(q.trim());
    	} else {
    	    list = userDao.findAll();
    	}

    	req.setAttribute("q", q);
    	req.setAttribute("listUser", list);

        String editId = req.getParameter("editId");

        if (editId != null) {
            User edit = userDao.findById(editId);
            req.setAttribute("editUser", edit);
        }

        req.getRequestDispatcher("/views/admin/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String id = req.getParameter("id");

        if ("delete".equals(action)) {
            userDao.delete(id);
        } else {
            User u = new User();
            u.setId(id);
            u.setPassword(req.getParameter("password"));
            u.setFullname(req.getParameter("fullname"));
            String birthday = req.getParameter("birthday");
            try {
                if (birthday != null && !birthday.isBlank())
                    u.setBirthday(sdf.parse(birthday));
            } catch (Exception ignored) {}

            String gender = req.getParameter("gender");
            if (gender != null) u.setGender("true".equals(gender));
            u.setMobile(req.getParameter("mobile"));
            u.setEmail(req.getParameter("email"));
            u.setRole("on".equals(req.getParameter("role")));

            if ("create".equals(action)) userDao.insert(u);
            else if ("update".equals(action)) userDao.update(u);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/user");
    }
}
