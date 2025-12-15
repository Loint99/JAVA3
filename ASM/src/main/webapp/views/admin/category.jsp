<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/views/layout/admin-header.jsp" %>

<div class="container-fluid mt-4">
    <div class="row">
        <!-- FORM -->
        <div class="col-md-4">
            <div class="card mb-3">
                <div class="card-header">Loại bản tin</div>
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/admin/category">
                        <div class="mb-2">
                            <label class="form-label">Mã loại</label>
                            <input name="id" class="form-control" value="${editCategory.id}">
                        </div>
                        <div class="mb-2">
                            <label class="form-label">Tên loại</label>
                            <input name="name" class="form-control" value="${editCategory.name}">
                        </div>

                        <div class="d-flex gap-2">
                            <button type="submit" name="action" value="create"
                                    class="btn btn-sm btn-primary">Thêm</button>
                            <button type="submit" name="action" value="update"
                                    class="btn btn-sm btn-warning">Cập nhật</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- BẢNG -->
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Danh sách loại tin</div>
                <div class="card-body table-responsive">
                    <table class="table table-sm table-striped">
                        <thead>
                        <tr>
                            <th>Mã</th>
                            <th>Tên loại</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="c" items="${listCategory}">
                            <tr>
                                <td>${c.id}</td>
                                <td>${c.name}</td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/admin/category?editId=${c.id}"
                                       class="btn btn-sm btn-outline-secondary">Sửa</a>

                                    <form method="post"
                                          action="${pageContext.request.contextPath}/admin/category"
                                          class="d-inline">
                                        <input type="hidden" name="id" value="${c.id}">
                                        <button type="submit" name="action" value="delete"
                                                class="btn btn-sm btn-outline-danger"
                                                onclick="return confirm('Xóa loại tin này?');">
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
