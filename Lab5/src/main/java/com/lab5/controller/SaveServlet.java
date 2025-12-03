package com.lab5.controller;

import com.lab5.entity.Staff;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.io.IOException;
import java.util.Date;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/staff-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Staff bean = new Staff();
        try {
            DateConverter dtc = new DateConverter(new Date());
            dtc.setPattern("MM/dd/yyyy");
            ConvertUtils.register(dtc, Date.class);

            BeanUtils.populate(bean, req.getParameterMap());

            req.setAttribute("staff", bean);
            req.setAttribute("message", "Đọc tham số thành công – xem console!");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi đọc tham số: " + e.getMessage());
        }

        req.getRequestDispatcher("/views/staff-form.jsp").forward(req, resp);
    }
}