<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>LoiNews</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <style>
        body {
            background-color: #f5f7fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }

        .top-bar {
            background: #0d6efd;
            color: #fff;
            padding: 0.5rem 0;
        }

        .site-logo {
            font-weight: 700;
            font-size: 1.5rem;
        }

        .main-menu {
            background: #ffc107;
            font-weight: 500;
        }

        .main-menu a {
            color: #000;
            text-decoration: none;
            padding: 0.75rem 1rem;
            display: inline-block;
        }

        .main-menu a:hover,
        .main-menu a.active {
            background: rgba(255,255,255,0.4);
        }

        .content-wrapper {
            padding-top: 1.5rem;
            padding-bottom: 2rem;
        }

        .sidebar-block {
            margin-bottom: 1rem;
            border-radius: 0.75rem;
            overflow: hidden;
        }

        .sidebar-block-header {
            padding: 0.6rem 0.9rem;
            font-weight: 600;
            color: #fff;
            font-size: 0.95rem;
        }

        .sidebar-block-body {
            background: #fff;
            padding: 0.75rem 0.9rem;
        }

        .sidebar-block-body ul {
            margin-bottom: 0;
        }

        .sidebar-block-body li + li {
            margin-top: 0.4rem;
        }

        .sidebar-hot    { background: #fd7e14; }
        .sidebar-latest { background: #6c757d; }
        .sidebar-viewed { background: #28a745; }

        .news-item {
            background: #fff;
            border-radius: 0.75rem;
            padding: 1rem;
            margin-bottom: 1rem;
            box-shadow: 0 2px 4px rgba(15, 23, 42, 0.06);
        }

        .news-item img {
            width: 100%;
            height: 140px;
            object-fit: cover;
            border-radius: 0.5rem;
        }

        .news-title {
            font-size: 1.05rem;
            font-weight: 600;
            color: #0d6efd;
            margin-bottom: 0.25rem;
        }

        .news-meta {
            font-size: 0.8rem;
            color: #6c757d;
            margin-bottom: 0.5rem;
        }

        .news-excerpt {
            font-size: 0.9rem;
            color: #333;
        }

        footer.site-footer {
            background: #0d6efd;
            color: #fff;
            margin-top: 2rem;
            padding: 0.8rem 0;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>

<div class="top-bar">
    <div class="container d-flex justify-content-between align-items-center">
        <div class="site-logo">
            LoiNews
        </div>
        <div class="small">
            Tin tức mỗi ngày · Thời sự · Văn hóa · Thể thao
        </div>
    </div>
</div>

<nav class="main-menu">
    <div class="container d-flex align-items-center justify-content-between">
        <!-- MENU TRÁI -->
        <div>
            <a href="<c:url value='/home'/>" class="active">Trang chủ</a>

            <c:forEach var="c" items="${categories}">
                <a href="category?id=${c.id}">${c.name}</a>
            </c:forEach>
        </div>

        <!-- BÊN PHẢI: search + login -->
        <div class="d-flex align-items-center gap-2">
            <form class="d-flex" role="search" method="get" action="search">
                <input class="form-control form-control-sm me-2" type="search"
                       name="q" placeholder="Tìm kiếm bản tin..." aria-label="Search">
                <button class="btn btn-sm btn-outline-dark" type="submit">Tìm</button>
            </form>

            <!-- LOGIN / LOGOUT -->
			<c:set var="user" value="${sessionScope.currentUser}" />
			<c:choose>
			    <c:when test="${user == null}">
			        <div class="d-flex align-items-center ms-2 gap-2">
			            <a href="${pageContext.request.contextPath}/login"
			               class="btn btn-sm btn-outline-primary">
			                Đăng nhập
			            </a>
			            <a href="${pageContext.request.contextPath}/register"
			               class="btn btn-sm btn-primary">
			                Đăng ký
			            </a>
			        </div>
			    </c:when>
			
			    <c:otherwise>
			        <div class="d-flex align-items-center ms-2">
			            <span class="small me-2">
			                Xin chào, <strong>${user.fullname}</strong>
			            </span>
			
			            <c:if test="${user.role}">
			                <a href="${pageContext.request.contextPath}/admin/news"
			                   class="btn btn-sm btn-outline-secondary me-2">
			                    Quản trị
			                </a>
			            </c:if>
			
			            <a href="${pageContext.request.contextPath}/logout"
			               class="btn btn-sm btn-outline-danger">
			                Đăng xuất
			            </a>
			        </div>
			    </c:otherwise>
			</c:choose>
        </div>
    </div>
</nav>


<div class="container content-wrapper">
    <div class="row">