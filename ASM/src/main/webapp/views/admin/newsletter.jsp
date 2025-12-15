<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/views/layout/admin-header.jsp" %>

<div class="container-fluid mt-4">
    <div class="row">
        <!-- FORM THÊM / SỬA NEWSLETTER -->
        <div class="col-md-4">
            <div class="card mb-3">
                <div class="card-header">
                    Newsletter
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/admin/newsletter">
                        <div class="mb-2">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control"
                                   value="${editNewsletter.email}">
                        </div>

                        <div class="mb-2 form-check">
                            <input class="form-check-input" type="checkbox"
                                   id="enabledCheck" name="enabled"
                                   <c:if test="${editNewsletter != null && editNewsletter.enabled}">checked</c:if>>
                            <label class="form-check-label" for="enabledCheck">
                                Đang hoạt động (nhận mail)
                            </label>
                        </div>

                        <div class="d-flex gap-2 mt-2">
                            <button type="submit" name="action" value="create"
                                    class="btn btn-sm btn-primary">
                                Thêm
                            </button>
                            <button type="submit" name="action" value="update"
                                    class="btn btn-sm btn-warning">
                                Cập nhật
                            </button>
                        </div>
                    </form>
                    <p class="small text-muted mt-2 mb-0">
                        * Thường thì email được tạo từ phía người dùng (sidebar),
                        trang này chủ yếu để bật/tắt hoặc xóa.
                    </p>
                </div>
            </div>
        </div>

        <!-- DANH SÁCH EMAIL ĐĂNG KÝ -->
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    Danh sách email đăng ký nhận tin
                </div>
                <div class="card-body table-responsive">
                    <table class="table table-sm table-striped align-middle">
                        <thead>
                        <tr>
                            <th>Email</th>
                            <th>Trạng thái</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="n" items="${listNewsletter}">
                            <tr>
                                <td>${n.email}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${n.enabled}">Đang nhận</c:when>
                                        <c:otherwise>Tạm dừng</c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/admin/newsletter?editEmail=${n.email}"
                                       class="btn btn-sm btn-outline-secondary">
                                        Sửa
                                    </a>

                                    <form method="post"
                                          action="${pageContext.request.contextPath}/admin/newsletter"
                                          class="d-inline">
                                        <input type="hidden" name="email" value="${n.email}">
                                        <button type="submit" name="action" value="delete"
                                                class="btn btn-sm btn-outline-danger"
                                                onclick="return confirm('Xóa email này khỏi danh sách?');">
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
