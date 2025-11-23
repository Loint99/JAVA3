<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 2 - Country Table</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 2 - Đổ dữ liệu vào Bảng</h2>

    <table>
        <thead>
        <tr>
            <th>No.</th>
            <th>Id</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ct" items="${countries}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${ct.id}</td>
                <td>${ct.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p class="code-note">
        Sử dụng varStatus để lấy số thứ tự (vs.count) theo đúng đề Lab 3.
    </p>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>