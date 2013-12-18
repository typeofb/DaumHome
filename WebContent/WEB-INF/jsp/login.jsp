<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>다음정보기술</title>
<script type="text/javascript">
function login() {
	document.loginForm.action = "loginCheck.do";
	document.loginForm.submit();
}

function cancel() {
	self.close();
}
</script>
</head>

<body>
<form name="loginForm" method="post">
	<div>
		<ul>
			<li><input type="text" name="userId" value="typeofb" />아이디</li>
			<li><input type="password" name="userPw" id="userPw" value="1234" />비밀번호</li>
			<li><a href="javascript:;" onclick="login();">로그인</a></li>
			<li><a href="javascript:;" onclick="cancel();">취소</a></li>
			<li>계정요청</li>
		</ul>
	</div>
</form>
</body>
</html>
