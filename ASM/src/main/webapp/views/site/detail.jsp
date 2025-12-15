<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/views/layout/header.jsp" %>

<div class="col-lg-9 col-md-8">
    <!-- BẢN TIN CHI TIẾT -->
    <article class="news-item">
        <!-- Tiêu đề -->
        <h2 class="mb-2">
            ${news.title}
        </h2>

        <!-- Meta: ngày đăng, tác giả, lượt xem -->
        <div class="news-meta mb-3">
            Ngày đăng: ${news.postedDate}
            · Tác giả: ${news.author}
            · Lượt xem: ${news.viewCount}
        </div>

        <!-- Hình ảnh -->
        <c:if test="${not empty news.image}">
            <img src="${news.image}" alt="${news.title}"
                 class="img-fluid rounded mb-3">
        </c:if>

        <!-- Nội dung -->
        <div class="news-excerpt">
            ${news.content}
        </div>
    </article>

    <!-- TIN CÙNG LOẠI -->
    <div class="mt-3">
        <h6 class="fw-bold">Tin cùng loại</h6>

        <c:if test="${empty related}">
            <p class="small text-muted mb-0">Hiện chưa có tin nào cùng loại.</p>
        </c:if>

        <c:if test="${not empty related}">
            <ul class="small">
                <c:forEach var="n" items="${related}">
                    <li>
                        <a href="detail?id=${n.id}" class="text-decoration-none">
                            ${n.title}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</div>

<div class="col-lg-3 col-md-4">
    <%@ include file="/views/layout/sidebar.jsp" %>
</div>

<%@ include file="/views/layout/footer.jsp" %>
