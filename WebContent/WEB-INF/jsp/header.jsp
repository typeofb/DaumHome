<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>다음정보기술</title>
</head>

<body>
	<div>
		<ul>
			<li><a href="${pageContext.request.contextPath}/login.do" onclick="window.open(this.href, '_blank', 'toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400'); return false;"><c:if test="${not empty userId}">${userId}</c:if><c:if test="${empty userId}">로그인</c:if></a></li>
			<li><a href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath}/iBatis.do">아이바티스 연습</a></li>
			<li><a href="<c:out value='${pageContext.request.contextPath}'/>/controlMng.do">제어관리</a></li>
			<li><a href="<c:out value='${pageContext.request.contextPath}'/>/bbs.do">게시판</a></li>
		</ul>
	</div>
</body>
</html>
