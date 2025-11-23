<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 4 - Xử lý chuỗi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 4 - Xử lý chuỗi với JSTL Functions</h2>

    <ul>
        <li><b>Title:</b> ${fn:toUpperCase(item.title)}</li>
        <li><b>Content:</b>
            <c:choose>
                <c:when test="${fn:length(item.content) > 100}">
                    ${fn:substring(item.content, 0, 100)}...
                </c:when>
                <c:otherwise>
                    ${item.content}
                </c:otherwise>
            </c:choose>
        </li>
    </ul>

    <p class="code-note">
        Dùng fn:toUpperCase, fn:length, fn:substring + c:choose để xử lý chuỗi
        theo đúng yêu cầu Lab 3.
    </p>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>