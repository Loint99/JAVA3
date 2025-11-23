package com.lab3.controller;

import com.lab3.entity.Country;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/lab3/table")
public class CountryTableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Country> list = Arrays.asList(
                new Country("VN", "Việt Nam"),
                new Country("US", "United States"),
                new Country("CN", "China")
        );

        req.setAttribute("countries", list);
        req.getRequestDispatcher("/views/country-table.jsp").forward(req, resp);
    }
}
