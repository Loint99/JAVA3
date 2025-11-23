<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Message from servlet:</h2>
	<h1>${message}</h1>
	
	<h2>${message}</h2>
	
	<jsp:include page="/views/user-info.jsp" />
		
</body>
</html>