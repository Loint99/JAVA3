<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/views/layout/header.jsp" %>

<div class="col-lg-9 col-md-8">
  <div class="card">
    <div class="card-header">Nhập OTP</div>
    <div class="card-body">
      <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
      </c:if>

      <form method="post" action="${pageContext.request.contextPath}/verify-otp">
        <label class="form-label">OTP (6 số)</label>
        <input class="form-control mb-2" name="otp" required>
        <button class="btn btn-primary">Xác nhận</button>
      </form>
    </div>
  </div>
</div>

<div class="col-lg-3 col-md-4"><%@ include file="/views/layout/sidebar.jsp" %></div>
<%@ include file="/views/layout/footer.jsp" %>
