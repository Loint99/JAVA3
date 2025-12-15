package com.asm.filter;

import java.io.IOException;

import com.asm.entity.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebFilter("/admin/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("currentUser");
        }

        // 1. Chưa đăng nhập -> đá ra login
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. Đã đăng nhập nhưng KHÔNG phải admin -> cấm vào admin
        if (!user.isRole()) {
            // Cách 1: trả mã 403
            // resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập");

            // Cách 2: redirect về home
            HttpSession s = req.getSession();
            s.setAttribute("authMessage", "Bạn không có quyền truy cập khu vực quản trị.");
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        // 3. Admin hợp lệ -> cho đi tiếp
        chain.doFilter(request, response);
        
        String uri = req.getRequestURI();

	     // Mọi /admin đều yêu cầu đã đăng nhập
	     // Riêng /admin/user, /admin/category thì phải admin
	     if ((uri.contains("/admin/user") || uri.contains("/admin/category"))
	             && !user.isRole()) {
	         resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập");
	         return;
	     }
    }
}

