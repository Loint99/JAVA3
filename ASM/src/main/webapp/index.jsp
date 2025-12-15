<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/views/layout/header.jsp" %>

<!-- Cột trái: tin trang nhất -->
<div class="col-lg-9 col-md-8">
    <h5 class="mb-3 fw-bold">Trang nhất</h5>

    <c:forEach var="n" items="${homeNews}">
        <div class="news-item">
            <div class="row g-3">
                <div class="col-md-4">
                    <img src="${n.image}" alt="Ảnh minh họa" />
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