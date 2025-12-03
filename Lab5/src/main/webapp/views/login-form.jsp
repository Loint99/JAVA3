<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 3 - Cookie &amp; Session</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 3 - Login với Cookie &amp; Session</h2>

    <c:url value="/login" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
        <label>Username:</label>
        <input name="username" value="${username}"><br>

        <label>Password:</label>
        <input name="password" value="${password}" type="password"><br>

        <label>
            <input type="checkbox" name="remember-me"> Remember me?
        </label>
        <hr>
        <button>Login</button>
    </form>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>