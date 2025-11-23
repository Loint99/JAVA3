<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 4 - Upload file</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 4 - Upload hình ảnh</h2>

    <c:url value="/upload" var="uplUrl" />
    <form action="${uplUrl}" method="post" enctype="multipart/form-data">
        <div>
            <label>Chọn hình:</label><br>
            <input name="photo" type="file">
        </div>
        <button>Upload</button>
    </form>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <c:if test="${not empty fileName}">
        <p class="note">
            File đã lưu tại thư mục <code>/static/file/${fileName}</code> trong ứng dụng.
        </p>
        <p>Preview (nếu là ảnh):</p>
        <img src="${pageContext.request.contextPath}/static/file/${fileName}" style="max-width: 300px;">
    </c:if>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>