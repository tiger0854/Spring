<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>result.jsp</h1>
	${map }
	<hr>
	메세지 : ${map.msg }<br>
	아이디 : ${map.id }<br>
	
	<h3>첨부파일 목록</h3>	
	<c:forEach var="name" items="${map.fileList }">
		 <%-- 파일명 : <a href="C:\\spring\\upload\\"+${name } download>${name }</a><br> --%>
		<%--  파일명 : <a href="/fileDown?fileName=${name}" >${name }</a><br> --%>
		 <img src="/imgDown?fileName=${name}">
	</c:forEach>
	
	
</body>
</html>