<%@ page session="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h2>로그인 성공</h2>
	이름 : ${sessionScope.userLoginInfo.username}
	<a href="j_spring_security_logout">로그아웃</a>
	<a href="main.do">메인</a>
</body>
</html>
