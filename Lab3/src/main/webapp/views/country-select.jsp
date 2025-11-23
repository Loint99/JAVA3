<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 1 - Country Select</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <h2>Bài 1 - Đổ dữ liệu vào Select Box</h2>

    <form>
        <label>Chọn quốc gia:</label><br>
        <select name="country">
            <c:forEach var="ct" items="${countries}">
                <option value="${ct.id}">${ct.name}</option>
            </c:forEach>
        </select>
    </form>

    <p class="code-note">
        Dữ liệu được truyền từ servlet bằng List&lt;Country&gt; và hiển thị bằng &lt;c:forEach&gt;.
    </p>

    <p><a href="${pageContext.request.contextPath}/">← Về trang chính</a></p>
</div>
</body>
</html>