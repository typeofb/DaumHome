<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=10" />
<title>다음정보기술</title>
<link rel="stylesheet" href="css/common.css" type="text/css" charset="utf-8"/>
<link rel="stylesheet" href="css/dropDownMenu.css" type="text/css" charset="utf-8"/>
<script src="js/jquery-1.4.3.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var timeout = 500;
var closetimer = 0;
var ddmenuitem = 0;

function jsddm_open() {
	jsddm_canceltimer();
	jsddm_close();
	ddmenuitem = $(this).find('ul').css('visibility', 'visible');
}

function jsddm_close() {
	if (ddmenuitem)
		ddmenuitem.css('visibility', 'hidden');
}

function jsddm_timer() {
	closetimer = window.setTimeout(jsddm_close, timeout);
}

function jsddm_canceltimer() {
	if (closetimer) {
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

$(document).ready(function() {
	$('#jsddm > li').bind('mouseover', jsddm_open);
	$('#jsddm > li').bind('mouseout', jsddm_timer);
});

document.onclick = jsddm_close;
</script>
</head>

<body>
	<div class="topBar">
		<ul>
			<li class="logo"><a href="${pageContext.request.contextPath}/main.do"><img src="<c:url value='/images/logo.gif' />" /></a></li>
		</ul>
		<ul class="admin">
			<li><a href="${pageContext.request.contextPath}/login.do" onclick="window.open(this.href, '_blank', 'toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400'); return false;"><c:if test="${not empty userId}">${userId}</c:if><c:if test="${empty userId}">로그인</c:if></a></li>
			<li>|</li>
			<li><a href="${pageContext.request.contextPath}/logout.do">LOGOUT</a></li>
		</ul>
	</div>
	<div class="gnbBar">
		<ul id="jsddm">
			<li><a href="#">기능들</a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/boardList.do">게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/iBatis.do">아이바티스 연습</a></li>
					<li><a href="<c:out value='${pageContext.request.contextPath}'/>/controlMng.do">제어관리</a></li>
				</ul>
			<li><a href="<c:out value='${pageContext.request.contextPath}'/>/bbs.do">다음에디터</a></li>
		</ul>
	</div>
</body>
</html>
