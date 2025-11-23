package com.poly.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.poly.model.User;


@WebServlet("/form/bean")
public class BeanFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User u = new User();
        u.setFullname("Nguyễn Văn Tèo");
        u.setGender(true);
        u.setCountry("VN");

        req.setAttribute("user", u);
        req.setAttribute("editable", true);

        req.getRequestDispatcher("/views/form-bean.jsp").forward(req, resp);
    }
}
