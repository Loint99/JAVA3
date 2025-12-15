<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 5 bản tin được xem nhiều -->
<div class="sidebar-block">
    <div class="sidebar-block-header sidebar-hot">
        5 bản tin được xem nhiều
    </div>
    <div class="sidebar-block-body">
        <ul class="list-unstyled small">
            <c:forEach var="n" items="${topViewed}">
                <li>
                    <a href="detail?id=${n.id}" class="text-decoration-none">
                        ${n.title}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<!-- 5 bản tin mới nhất -->
<div class="sidebar-block">
    <div class="sidebar-block-header sidebar-latest">
        5 bản tin mới nhất
    </div>
    <div class="sidebar-block-body">
        <ul class="list-unstyled small">
            <c:forEach var="n" items="${latestNews}">
                <li>
                    <a href="detail?id=${n.id}" class="text-decoration-none">
                        ${n.title}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<!-- 5 bản tin bạn đã xem -->
<div class="sidebar-block">
    <div class="sidebar-block-header sidebar-viewed">
        5 bản tin bạn đã xem
    </div>
    <div class="sidebar-block-body">
        <ul class="list-unstyled small">
            <c:forEach var="n" items="${recentViewed}">
                <li>
                    <a href="detail?id=${n.id}" class="text-decoration-none">
                        ${n.title}
                    </a>
                </li>
            </c:forEach>
            <c:if test="${empty recentViewed}">
                <li class="text-muted">Bạn chưa xem bản tin nào.</li>
            </c:if>
        </ul>
    </div>
</div>

<!-- Newsletter -->
<div class="sidebar-block">
    <div class="sidebar-block-header" style="background:#ffffff; color:#000;">
        Newsletter
    </div>
    <div class="sidebar-block-body">
        <form method="post" action="newsletter">
            <div class="mb-2">
                <label class="form-label small mb-1">Đăng ký nhận tin mới:</label>
                <input type="email" name="email" class="form-control form-control-sm"
                       placeholder="Nhập email của bạn" required>
            </div>
            <button type="submit" class="btn btn-sm btn-primary w-100">
                Đăng ký
            </button>
        </form>

        <c:if test="${not empty newsletterMessage}">
            <div class="small mt-2 text-success">${newsletterMessage}</div>
        </c:if>
    </div>
</div>
