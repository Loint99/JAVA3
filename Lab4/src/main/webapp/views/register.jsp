<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 3 - Đọc tham số</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 3 - Form đăng ký &amp; đọc tham số</h2>

    <c:url value="/register" var="regUrl" />
    <form action="${regUrl}" method="post">
        <div>
            <label>Username:</label><br>
            <input name="username">
        </div>

        <div>
            <label>Password:</label><br>
            <input type="password" name="password">
        </div>

        <div>
            <label>Gender:</label><br>
            <input type="radio" name="gender" value="Male" checked> Male
            <input type="radio" name="gender" value="Female"> Female
        </div>

        <div>
            <label>Country:</label><br>
            <select name="country">
                <option value="VN">Việt Nam</option>
                <option value="US">United States</option>
                <option value="CN">China</option>
            </select>
        </div>

        <div>
            <label>Hobbies:</label><br>
            <input type="checkbox" name="hobbies" value="Game"> Game
            <input type="checkbox" name="hobbies" value="Music"> Music
            <input type="checkbox" name="hobbies" value="Sport"> Sport
        </div>

        <div>
            <label>Note:</label><br>
            <textarea name="note" rows="3" cols="40"></textarea>
        </div>

        <button>Submit</button>
    </form>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>