<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 3 - Định dạng số & ngày</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 3 - Định dạng thời gian & số</h2>

    <ul>
        <li><b>Name:</b> ${item.name}</li>
        <li><b>Price:</b>
            <fmt:formatNumber value="${item.price}" pattern="#,###.00"/>
        </li>
        <li><b>Date:</b>
            <fmt:formatDate value="${item.date}" pattern="MMM dd, yyyy"/>
        </li>
    </ul>

    <p class="code-note">
        Giá được định dạng "#,###.00" và ngày ở dạng "MMM dd, yyyy".
    </p>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>