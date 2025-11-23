package com.poly.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/crud/create", "/crud/update", "/crud/detele", "/crud/edit/*"})
public class CrudServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = req.getRequestURI();
		
		if (url.contains("create")) {
			resp.getWriter().println("URI create");
		} else if (url.contains("update")) {
			resp.getWriter().println("URI update");
		} else if (url.contains("edit")) {
			resp.getWriter().println("URI edit");
		}
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
