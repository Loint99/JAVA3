<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/views/layout/admin-header.jsp" %>

<div class="container-fluid mt-4">
    <div class="row">
        <!-- FORM THÊM / SỬA USER -->
        <div class="col-md-4">
            <div class="card mb-3">
                <div class="card-header">
                    Quản lý người dùng
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/admin/user">
                        <div class="mb-2">
                            <label class="form-label">Mã người dùng</label>
                            <input name="id" class="form-control"
                                   value="${editUser.id}">
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Mật khẩu</label>
                            <input type="password" name="password" class="form-control"
                                   value="${editUser.password}">
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Họ tên</label>
                            <input name="fullname" class="form-control"
                                   value="${editUser.fullname}">
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Ngày sinh</label>
                            <input type="date" name="birthday" class="form-control"
                                   value="${editUser.birthday}">
                        </div>

                        <div class="mb-2">
                            <label class="form-label d-block">Giới tính</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio"
                                       name="gender" id="genderMale" value="true"
                                       <c:if test="${editUser == null || editUser.gender}">checked</c:if>>
                                <label class="form-check-label" for="genderMale">Nam</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio"
                                       name="gender" id="genderFemale" value="false"
                                       <c:if test="${editUser != null && !editUser.gender}">checked</c:if>>
                                <label class="form-check-label" for="genderFemale">Nữ</label>
                            </div>
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Điện thoại</label>
                            <input name="mobile" class="form-control"
                                   value="${editUser.mobile}">
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control"
                                   value="${editUser.email}">
                        </div>

                        <div class="mb-2 form-check">
                            <input class="form-check-input" type="checkbox"
                                   id="roleCheck" name="role"
                                   <c:if test="${editUser != null && editUser.role}">checked</c:if>>
                            <label class="form-check-label" for="roleCheck">
                                Là quản trị (admin)
                            </label>
                        </div>

                        <div class="d-flex gap-2 mt-2">
                            <button type="submit" name="action" value="create"
                                    class="btn btn-sm btn-primary">
                                Thêm mới
                            </button>
                            <button type="submit" name="action" value="update"
                                    class="btn btn-sm btn-warning">
                                Cập nhật
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- DANH SÁCH USER -->
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    Danh sách người dùng
                </div>
                <form class="d-flex gap-2 mb-3"
				      method="get"
				      action="${pageContext.request.contextPath}/admin/user">
				    <input class="form-control form-control-sm"
				           name="q"
				           value="${q}"
				           placeholder="Tìm theo id / họ tên / email / sđt...">
				    <button class="btn btn-sm btn-primary">Tìm</button>
				    <a class="btn btn-sm btn-outline-secondary"
				       href="${pageContext.request.contextPath}/admin/user">Xóa lọc</a>
				</form>
				                
                <div class="card-body table-responsive">
                    <table class="table table-sm table-striped align-middle">
                        <thead>
                        <tr>
                            <th>Mã</th>
                            <th>Họ tên</th>
                            <th>Giới tính</th>
                            <th>Email</th>
                            <th>Điện thoại</th>
                            <th>Quyền</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="u" items="${listUser}">
                            <tr>
                                <td>${u.id}</td>
                                <td>${u.fullname}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${u.gender}">Nam</c:when>
                                        <c:otherwise>Nữ</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${u.email}</td>
                                <td>${u.mobile}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${u.role}">Quản trị</c:when>
                                        <c:otherwise>Người dùng</c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/admin/user?editId=${u.id}"
                                       class="btn btn-sm btn-outline-secondary">
                                        Sửa
                                    </a>

                                    <form method="post"
                                          action="${pageContext.request.contextPath}/admin/user"
                                          class="d-inline">
                                        <input type="hidden" name="id" value="${u.id}">
                                        <button type="submit" name="action" value="delete"
                                                class="btn btn-sm btn-outline-danger"
                                                onclick="return confirm('Xóa người dùng này?');">
                                            Xóa
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/views/layout/admin-footer.jsp" %>
