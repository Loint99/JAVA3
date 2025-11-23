<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 1 - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 1 - Phân biệt GET &amp; POST (Login)</h2>

    <c:url value="/account/login" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <div>
            <label>Username:</label><br>
            <input name="username">
        </div>
        <div>
            <label>Password:</label><br>
            <input name="password" type="password">
        </div>
        <button>Login</button>
    </form>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>