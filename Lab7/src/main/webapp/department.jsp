<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lab7 - Department Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h1>Lab 7 – Department Management</h1>

    <nav>
        <a href="${pageContext.request.contextPath}/department/index">Departments</a>
        <a href="${pageContext.request.contextPath}/employee/index">Employees</a>
    </nav>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <!-- Search -->
    <form action="${pageContext.request.contextPath}/department/search" method="get" style="margin-top:12px;">
        <label>Tìm theo tên:</label>
        <input type="text" name="keyword" value="${keyword}" style="width:200px;">
        <button type="submit" class="secondary">Search</button>
        <a href="${pageContext.request.contextPath}/department/index">Clear</a>
    </form>

    <!-- FORM -->
    <h2>Thông tin phòng ban</h2>
    <form method="post">
        <div class="form-grid">
            <div>
                <label>Mã phòng:</label>
                <input name="id" value="${item.id}">
            </div>
            <div>
                <label>Tên phòng:</label>
                <input name="name" value="${item.name}">
            </div>
            <div style="grid-column:1 / span 2;">
                <label>Mô tả:</label>
                <textarea name="description" rows="3">${item.description}</textarea>
            </div>
        </div>

        <button formaction="${pageContext.request.contextPath}/department/create">Create</button>
        <button formaction="${pageContext.request.contextPath}/department/update">Update</button>
        <button formaction="${pageContext.request.contextPath}/department/delete" class="danger">Delete</button>
        <button formaction="${pageContext.request.contextPath}/department/reset" class="secondary">Reset</button>
    </form>

    <!-- TABLE -->
    <h2 style="margin-top:22px;">Danh sách phòng ban</h2>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="d" items="${list}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${d.id}</td>
                <td>${d.name}</td>
                <td>${d.description}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/department/edit/${d.id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
