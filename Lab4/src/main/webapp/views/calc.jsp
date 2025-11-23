<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 2 - Tính toán</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 2 - Xử lý form nhiều nút</h2>

    <c:url value="/calculate" var="cal" />

    <form method="post">
        <div>
            <label>Số a:</label><br>
            <input name="a">
        </div>
        <div>
            <label>Số b:</label><br>
            <input name="b">
        </div>
        <div>
            <button formaction="${cal}/add">+</button>
            <button formaction="${cal}/sub">-</button>
        </div>
    </form>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>