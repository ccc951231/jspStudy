<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>회원목록보기</h1></div>
	<div>
		<c:forEach items="${members }" var="member">
			${member.id } : ${member.name } : ${member.author }<br>
		</c:forEach>
		</div>
</div>
</body>
</html>