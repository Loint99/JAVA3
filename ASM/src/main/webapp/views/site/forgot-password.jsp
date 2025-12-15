<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/views/layout/header.jsp" %>

<div class="col-lg-9 col-md-8">
  <div class="card">
    <div class="card-header">Quên mật khẩu</div>
    <div class="card-body">
      <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
      </c:if>

      <form method="post" action="${pageContext.request.contextPath}/forgot-password">
        <label class="form-label">Nhập email đã đăng ký</label>
        <input class="form-control mb-2" name="email" type="email" required>
        <button class="btn btn-primary">Gửi OTP</button>
      </form>
    </div>
  </div>
</div>

<div class="col-lg-3 col-md-4"><%@ include file="/views/layout/sidebar.jsp" %></div>
<%@ include file="/views/layout/footer.jsp" %>
