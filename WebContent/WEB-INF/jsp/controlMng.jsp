<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>다음정보기술</title>
<script type="text/javascript">
function control() {
	document.cForm.action = "<c:url value='commandControl.do' />";
	document.cForm.submit();
}
</script>
<c:if test="${sessionScope.loginResult ne 'C'}">
	<script>
		alert("비정상적인 접근이거나 잘못된 경로입니다.");
		location.href = "<c:url value='main.do' />";
	</script>
</c:if>
</head>

<body>
<%@ include file="header.jsp"%>
<form name="cForm" method="post">
	<div><input type="button" value="예약제어" onclick="control();" /></div>
</form>
</body>
</html>
