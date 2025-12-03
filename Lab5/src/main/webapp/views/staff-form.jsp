<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 1 - BeanUtils</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 1 - Đọc tham số bằng BeanUtils</h2>

    <c:url value="/save" var="saveUrl"/>
    <form action="${saveUrl}" method="post">
        <label>Fullname:</label>
        <input name="fullname"><br>

        <label>Birthday (MM/dd/yyyy):</label>
        <input name="birthday"><br>

        <label>Gender:</label><br>
        <input type="radio" name="gender" value="true"> Male
        <input type="radio" name="gender" value="false"> Female<br>

        <label>Hobbies:</label><br>
        <input type="checkbox" name="hobbies" value="R"> Reading
        <input type="checkbox" name="hobbies" value="Traveling"> Traveling
        <input type="checkbox" name="hobbies" value="M"> Music<br>

        <label>Country:</label><br>
        <select name="country">
            <option value="VN">Việt Nam</option>
            <option value="US" selected>United States</option>
        </select><br>

        <label>Salary:</label>
        <input name="salary"><br>

        <hr>
        <button>Submit</button>
    </form>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <c:if test="${not empty staff}">
        <p><b>Fullname:</b> ${staff.fullname}</p>
        <p><b>Gender:</b> <c:out value="${staff.gender ? 'Male' : 'Female'}"/></p>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>