<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h2>로그인</h2>
	<form name="form1" method="post" action="j_spring_security_check">
		<table>
			<tr height="40px">
				<td>유저네임</td>
				<td><input type="text" name="j_username" value="typeofb"></td>
			</tr>
			<tr height="40px">
				<td>패스워드</td>
				<td><input type="password" name="j_password" value="1234"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td align="center"><input type="submit" value="로그인"></td>
				<td align="center"><input type="reset" value="리셋"></td>
			</tr>
		</table>
	</form>
</body>
</html>
