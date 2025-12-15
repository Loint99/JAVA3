<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - ABC News</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <style>
        body {
            background: #f5f7fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }
    </style>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center" style="min-height:100vh;">
    <div class="card shadow-sm" style="max-width: 420px; width: 100%;">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0">Đăng nhập hệ thống LoiNews</h5>
        </div>
        <div class="card-body">
            <form method="post" action="login">
			    <div class="mb-3">
			        <label class="form-label">Tên đăng nhập</label>
			        <input class="form-control" name="username" required>
			    </div>
			    <div class="mb-3">
			        <label class="form-label">Mật khẩu</label>
			        <input type="password" class="form-control" name="password" required>
			    </div>
			    <a href="${pageContext.request.contextPath}/forgot-password" class="small">
				  Quên mật khẩu?
				</a>	    
			    <button class="btn btn-primary w-100">Đăng nhập</button>
			
			    <c:if test="${not empty error}">
			        <div class="alert alert-danger mt-2">${error}</div>
			    </c:if>
			    
			    <c:if test="${not empty registerMessage}">
				    <div class="alert alert-success">${registerMessage}</div>
				</c:if>							   
			</form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>