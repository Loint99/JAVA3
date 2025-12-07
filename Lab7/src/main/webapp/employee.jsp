<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lab7 - Employee Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h1>Lab 7 – Employee Management</h1>

    <nav>
        <a href="${pageContext.request.contextPath}/department/index">Departments</a>
        <a href="${pageContext.request.contextPath}/employee/index">Employees</a>
    </nav>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <!-- FORM -->
    <h2>Thông tin nhân viên</h2>
    <form method="post" enctype="multipart/form-data">
        <div class="form-grid">
            <div>
                <label>Mã NV:</label>
                <input name="id" value="${item.id}">
            </div>
            <div>
                <label>Mật khẩu:</label>
                <input name="password" value="${item.password}">
            </div>
            <div>
                <label>Họ tên:</label>
                <input name="fullname" value="${item.fullname}">
            </div>
            <div>
                <label>Email:</label>
                <input name="email" type="email" value="${item.email}">
            </div>
            <div>
                <label>Lương:</label>
                <input name="salary" type="number" step="100000" value="${item.salary}">
            </div>
            <div>
                <label>Ngày sinh:</label>
                <input name="birthday" type="date"
				       value="${item.birthday != null ? item.birthday : ''}">	
            </div>
            <div>
                <label>Giới tính:</label><br>
                <input type="radio" name="gender" value="true"  ${item.gender ? "checked" : ""}> Nam
                <input type="radio" name="gender" value="false" ${!item.gender ? "checked" : ""}> Nữ
            </div>
            <div>
                <label>Phòng ban:</label>
                <select name="departId">
                    <c:forEach var="d" items="${departments}">
                        <option value="${d.id}" ${d.id == item.departId ? "selected" : ""}>
                            ${d.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label>Ảnh:</label>
                <input type="file" name="photoFile">
            </div>
            <div>
                <c:if test="${not empty item.photo}">
                    <img src="${pageContext.request.contextPath}/uploads/${item.photo}"
                         alt="Photo" style="max-height:80px;border-radius:6px;">
                </c:if>
            </div>
        </div>

        <button formaction="${pageContext.request.contextPath}/employee/create">Create</button>
        <button formaction="${pageContext.request.contextPath}/employee/update">Update</button>
        <button formaction="${pageContext.request.contextPath}/employee/delete" class="danger">Delete</button>
        <button formaction="${pageContext.request.contextPath}/employee/reset" class="secondary">Reset</button>
    </form>

    <!-- TABLE -->
    <h2 style="margin-top:22px;">Danh sách nhân viên</h2>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Id</th>
            <th>Họ tên</th>
            <th>Phòng ban</th>
            <th>Email</th>
            <th>Lương</th>
            <th>Ảnh</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="e" items="${list}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${e.id}</td>
                <td>${e.fullname}</td>
                <td>${e.departId}</td>
                <td>${e.email}</td>
                <td>${e.salary}</td>
                <td>
                    <c:if test="${not empty e.photo}">
                        <img src="${pageContext.request.contextPath}/uploads/${e.photo}"
                             style="max-height:60px;border-radius:4px;">
                    </c:if>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/employee/edit/${e.id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
