<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản trị - LoiNews</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <style>
        body {
            background-color: #f5f7fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }
        .admin-header {
            background: #0d6efd;
            color: #fff;
            padding: 0.75rem 0;
        }
        .admin-sidebar {
            background: #ffffff;
            min-height: 100vh;
            border-right: 1px solid #e5e7eb;
        }
        .admin-sidebar a {
            display: block;
            padding: 0.6rem 1rem;
            color: #111827;
            text-decoration: none;
            font-size: 0.95rem;
        }
        .admin-sidebar a:hover,
        .admin-sidebar a.active {
            background: #e5f1ff;
        }
    </style>
</head>
<body>

<div class="admin-header">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <div class="fw-bold">
            LoiNews · Khu vực quản trị
        </div>
		<div class="small">
		    <c:choose>
		        <c:when test="${not empty sessionScope.currentUser}">
		            Xin chào, <strong>${sessionScope.currentUser.fullname}</strong>
		            |
		            <a href="${pageContext.request.contextPath}/logout"
		               class="text-white text-decoration-none">
		                Đăng xuất
		            </a>
		        </c:when>
		        <c:otherwise>
		            <a href="${pageContext.request.contextPath}/login"
		               class="text-white text-decoration-none">
		                Đăng nhập
		            </a>
		        </c:otherwise>
		    </c:choose>
		</div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <aside class="col-md-3 col-lg-2 admin-sidebar">
            <a href="<c:url value='/'/>">Trang chủ (đọc giả)</a>
            <hr class="my-2">
            <a href="<c:url value='/admin/news'/>" class="active">Tin tức</a>
            <a href="<c:url value='/admin/category'/>">Loại tin</a>
            <a href="<c:url value='/admin/user'/>">Người dùng</a>
            <a href="<c:url value='/admin/newsletter'/>">Newsletter</a>
        </aside>

        <main class="col-md-9 col-lg-10 p-4">