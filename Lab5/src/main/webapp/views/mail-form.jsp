<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 2 - Gửi email</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 2 - Gửi email</h2>

    <c:url value="/mail" var="mailUrl"/>
    <form action="${mailUrl}" method="post">
        <label>From (Gmail):</label>
        <input name="from"><br>

        <label>Password / App password:</label>
        <input type="password" name="password"><br>

        <label>To:</label>
        <input name="to"><br>

        <label>Subject:</label>
        <input name="subject"><br>

        <label>Body:</label>
        <textarea name="body" rows="5" cols="50"></textarea><br>

        <button>Send</button>
    </form>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>