<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/views/layout/header.jsp" %>

<div class="col-lg-9 col-md-8">
    <div class="card mb-4">
        <div class="card-header">
            Đăng ký tài khoản
        </div>
        <div class="card-body">
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/register">
                <div class="mb-3">
                    <label class="form-label">Tên đăng nhập</label>
                    <input name="id" class="form-control"
                           value="${id}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Mật khẩu</label>
                    <input type="password" name="password" class="form-control"
                           required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Nhập lại mật khẩu</label>
                    <input type="password" name="confirm" class="form-control"
                           required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Họ tên</label>
                    <input name="fullname" class="form-control"
                           value="${fullname}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Ngày sinh</label>
                    <input type="date" name="birthday" class="form-control"
                           value="${birthday}">
                </div>

                <div class="mb-3">
                    <label class="form-label d-block">Giới tính</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio"
                               name="gender" id="genderMale" value="true"
                               <c:if test="${empty gender or gender == 'true'}">checked</c:if>>
                        <label class="form-check-label" for="genderMale">Nam</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio"
                               name="gender" id="genderFemale" value="false"
                               <c:if test="${gender == 'false'}">checked</c:if>>
                        <label class="form-check-label" for="genderFemale">Nữ</label>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Điện thoại</label>
                    <input name="mobile" class="form-control"
                           value="${mobile}">
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control"
                           value="${email}">
                </div>

                <button type="submit" class="btn btn-primary">
                    Đăng ký
                </button>
            </form>
        </div>
    </div>
</div>

<div class="col-lg-3 col-md-4">
    <%@ include file="/views/layout/sidebar.jsp" %>
</div>

<%@ include file="/views/layout/footer.jsp" %>
