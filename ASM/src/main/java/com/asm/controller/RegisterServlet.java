package com.asm.controller;

import com.asm.dao.UserDao;
import com.asm.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDao userDao = new UserDao();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/site/register.jsp").forward(req, resp);
        // nếu m để file chỗ khác thì đổi path cho đúng
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String id       = req.getParameter("id");
        String password = req.getParameter("password");
        String confirm  = req.getParameter("confirm");
        String fullname = req.getParameter("fullname");
        String birthday = req.getParameter("birthday");
        String gender   = req.getParameter("gender");
        String mobile   = req.getParameter("mobile");
        String email    = req.getParameter("email");

        // để hiển thị lại trên form nếu lỗi
        req.setAttribute("id", id);
        req.setAttribute("fullname", fullname);
        req.setAttribute("birthday", birthday);
        req.setAttribute("gender", gender);
        req.setAttribute("mobile", mobile);
        req.setAttribute("email", email);

        // 1. Check cơ bản
        if (id == null || id.isBlank()
                || password == null || password.isBlank()
                || fullname == null || fullname.isBlank()) {

            req.setAttribute("error", "Vui lòng nhập đầy đủ Tên đăng nhập, Mật khẩu và Họ tên.");
            req.getRequestDispatcher("/views/site/register.jsp").forward(req, resp);
            return;
        }

        // 2. Check trùng password
        if (!password.equals(confirm)) {
            req.setAttribute("error", "Xác nhận mật khẩu không khớp.");
            req.getRequestDispatcher("/views/site/register.jsp").forward(req, resp);
            return;
        }

        // 3. Check user đã tồn tại chưa
        User existed = userDao.findById(id);
        if (existed != null) {
            req.setAttribute("error", "Tên đăng nhập đã tồn tại, hãy chọn tên khác.");
            req.getRequestDispatcher("/views/site/register.jsp").forward(req, resp);
            return;
        }

        // 4. Tạo user mới
        try {
            User u = new User();
            u.setId(id);
            u.setPassword(password);
            u.setFullname(fullname);

            if (birthday != null && !birthday.isBlank()) {
                Date d = sdf.parse(birthday);
                u.setBirthday(d);
            }

            if (gender != null) {
                u.setGender("true".equals(gender));
            }

            u.setMobile(mobile);
            u.setEmail(email);
            u.setRole(false); // đăng ký từ ngoài site -> user thường, không phải admin

            userDao.insert(u);

            // 5. Đăng ký xong → chuyển về login, kèm message
            HttpSession session = req.getSession();
            session.setAttribute("registerMessage", "Đăng ký thành công, mời bạn đăng nhập.");
            resp.sendRedirect(req.getContextPath() + "/login");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra khi tạo tài khoản, thử lại sau.");
            req.getRequestDispatcher("/views/site/register.jsp").forward(req, resp);
        }
    }
}
