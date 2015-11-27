<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<base target="_self" />
<title>다음정보기술</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js' />"></script>
<script type="text/javascript">
function login() {
	document.loginForm.action = "loginCheck.do";
	document.loginForm.submit();
}

function cancel() {
	self.close();
}

function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	if (start != -1) {
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if (end == -1)
			end = cookieData.length;
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}

function setCookie(cookieName, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

function deleteCookie(cookieName) {
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}

$(document).ready(function() {
	// 저장된 쿠키값을 가져와서 ID 입력칸에 넣어준다. 없으면 공백으로 들어간다.
	var userInputId = getCookie("userInputId");
	$("input[name='userId']").val(userInputId);
	
	if ($("input[name='userId']").val() != "") { // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
		$("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 둔다.
	}
	
	$("#idSaveCheck").change(function() { // 체크박스에 변화가 있다면,
		if ($("#idSaveCheck").is(":checked")) { // ID 저장하기 체크했을 때,
			var userInputId = $("input[name='userId']").val();
			setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키를 보관한다.
		} else { // ID 저장하기 체크 해제 시,
			deleteCookie("userInputId");
		}
	});
	
	// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키를 저장한다.
	$("input[name='userId']").keyup(function() { // ID 입력칸에 ID를 입력할 때,
		if ($("#idSaveCheck").is(":checked")) { // ID 저장하기를 체크한 상태라면,
			var userInputId = $("input[name='userId']").val();
			setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키를 보관한다.
		}
	});
});
</script>
</head>

<body>
<form name="loginForm" method="post">
	<div>
		<ul>
			<li><input type="text" name="userId" />아이디</li>
			<li><input type="password" name="userPw" />비밀번호</li>
			<li><a href="javascript:;" onclick="login();">로그인</a></li>
			<li><a href="javascript:;" onclick="cancel();">취소</a></li>
			<li>계정요청</li>
			<li><input type="checkbox" id="idSaveCheck">아이디 저장</li>
		</ul>
	</div>
</form>
</body>
</html>
