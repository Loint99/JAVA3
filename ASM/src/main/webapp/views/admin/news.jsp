<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/views/layout/admin-header.jsp" %>

<div class="container-fluid mt-4">
    <div class="row">
        <!-- FORM THÊM / SỬA -->
        <div class="col-md-4">
            <div class="card mb-3">
                <div class="card-header">
                    Quản lý bản tin
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/admin/news">
                        <div class="mb-2">
                            <label class="form-label">Mã tin</label>
                            <input name="id" class="form-control"
                                   value="${editNews.id}">
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Tiêu đề</label>
                            <input name="title" class="form-control"
                                   value="${editNews.title}">
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Hình ảnh (URL)</label>
                            <input name="image" class="form-control"
                                   value="${editNews.image}">
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Nội dung</label>
                            <textarea name="content" class="form-control" rows="4">${editNews.content}</textarea>
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Loại tin</label>
                            <select name="categoryId" class="form-select">
                                <c:forEach var="c" items="${categories}">
                                    <option value="${c.id}"
                                        <c:if test="${editNews != null && editNews.categoryId == c.id}">
                                            selected
                                        </c:if>>
                                        ${c.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-2 form-check">
                            <input type="checkbox" class="form-check-input" id="homeCheck"
                                   name="home"
                                   <c:if test="${editNews != null && editNews.home}">checked</c:if>>
                            <label class="form-check-label" for="homeCheck">Hiển thị trang nhất</label>
                        </div>

                        <div class="mb-2">
                            <label class="form-label">Lượt xem</label>
                            <input name="viewCount" class="form-control"
                                   value="${editNews.viewCount != null ? editNews.viewCount : 0}">
                        </div>

                        <div class="d-flex gap-2">
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

        <!-- BẢNG DANH SÁCH -->
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    Danh sách bản tin
                </div>
                <div class="card-body table-responsive">
       				<!-- Thống kê top 5 tin -->
                	<div class="card mb-3">
					    <div class="card-header">
					        Top 5 tin xem nhiều nhất
					    </div>
					    <div class="card-body table-responsive">
					        <table class="table table-sm mb-0">
					            <thead>
					            <tr>
					                <th>Mã</th>
					                <th>Tiêu đề</th>
					                <th>Lượt xem</th>
					            </tr>
					            </thead>
					            <tbody>
					            <c:forEach var="t" items="${topViewedNews}">
					                <tr>
					                    <td>${t.id}</td>
					                    <td>${t.title}</td>
					                    <td>${t.viewCount}</td>
					                </tr>
					            </c:forEach>
					            </tbody>
					        </table>
					    </div>
					</div>
				<!-- 	 Tìm kiếm -->
                	<form class="d-flex gap-2 mb-3"
					      method="get"
					      action="${pageContext.request.contextPath}/admin/news">
					    <input class="form-control form-control-sm"
					           name="q"
					           value="${q}"
					           placeholder="Tìm theo tiêu đề / nội dung / tác giả...">
					    <button class="btn btn-sm btn-primary">Tìm</button>
					    <a class="btn btn-sm btn-outline-secondary"
					       href="${pageContext.request.contextPath}/admin/news">Xóa lọc</a>
					</form>
					                	
                    <table class="table table-sm table-striped align-middle">
                        <thead>
                        <tr>
                            <th>Mã</th>
                            <th>Tiêu đề</th>
                            <th>Loại</th>
                            <th>Tác giả</th>
                            <th>Ngày</th>
                            <th>Trang nhất</th>
                            <th>Lượt xem</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="n" items="${listNews}">
                            <tr>
                                <td>${n.id}</td>
                                <td>${n.title}</td>
                                <td>${n.categoryId}</td>
                                <td>${n.author}</td>
                                <td>${n.postedDate}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${n.home}">Có</c:when>
                                        <c:otherwise>Không</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${n.viewCount}</td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/admin/news?editId=${n.id}"
                                       class="btn btn-sm btn-outline-secondary">
                                        Sửa
                                    </a>

                                    <form method="post"
                                          action="${pageContext.request.contextPath}/admin/news"
                                          class="d-inline">
                                        <input type="hidden" name="id" value="${n.id}">
                                        <button type="submit" name="action" value="delete"
                                                class="btn btn-sm btn-outline-danger"
                                                onclick="return confirm('Xóa bản tin này?');">
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
