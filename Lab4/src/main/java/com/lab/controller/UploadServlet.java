package com.lab.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Part photo = req.getPart("photo");
        String fileName = photo.getSubmittedFileName();

        // thư mục lưu: /static/file
        String folderPath = req.getServletContext().getRealPath("/static/file");
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = folderPath + File.separator + fileName;
        photo.write(filePath);

        req.setAttribute("message", "Upload thành công: " + fileName);
        req.setAttribute("fileName", fileName);
        req.getRequestDispatcher("/views/upload.jsp").forward(req, resp);
    }
}