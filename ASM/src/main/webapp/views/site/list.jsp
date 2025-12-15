<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/views/layout/header.jsp" %>

<div class="col-lg-9 col-md-8">
    <!-- Tiêu đề loại tin -->
    <h5 class="mb-3 fw-bold">${category.name}</h5>

    <!-- Danh sách bản tin theo loại -->
    <c:forEach var="n" items="${listNews}">
        <div class="news-item">
            <div class="row g-3">
                <div class="col-md-4">
                    <img src="${n.image}" alt="" />
                </div>
                <div class="col-md-8">
                    <a href="detail?id=${n.id}" class="news-title text-decoration-none">
                        ${n.title}
                    </a>
                    <div class="news-meta">
                        Ngày đăng: ${n.postedDate} · Tác giả: ${n.author}
                    </div>
                    <div class="news-excerpt">
                        ${n.content}
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<div class="col-lg-3 col-md-4">
    <%@ include file="/views/layout/sidebar.jsp" %>
</div>

<%@ include file="/views/layout/footer.jsp" %>