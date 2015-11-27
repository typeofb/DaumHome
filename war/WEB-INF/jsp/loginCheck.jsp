<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<base target="_self" />
<form name="lForm" method="post">
	<input type="hidden" id="userAuth" value="${userAuth}">
	<input type="hidden" id="userId" name="userId" value="${userId}">
</form>

<c:choose>
	<c:when test="${loginResult eq 'E3'}">
		<script>
			alert("등록되지 않은 아이디입니다. 계정요청을 하셔야 로그인할 수 있습니다.");
			document.lForm.action = "<c:url value='login.do' />";
			document.lForm.submit();
		</script>
	</c:when>
	<c:when test="${loginResult eq 'E2'}">
		<script>
			alert("패스워드를 확인해주세요.");
			document.lForm.action = "<c:url value='login.do' />";
			document.lForm.submit();
		</script>
	</c:when>
	<c:when test="${loginResult eq 'E1'}">
		<script>
			alert("아이디가 사용가능한 상태가 아닙니다.");
			document.lForm.action = "<c:url value='login.do' />";
			document.lForm.submit();
		</script>
	</c:when>
	<c:when test="${loginResult eq 'C'}">
		<script>
			window.onload = function() {
				var code = document.getElementById("userAuth").value;
				if (code == "00") {
					document.lForm.action = "<c:url value='boardList.do' />";
				} else if (code == "10") {
					document.lForm.action = "<c:url value='bams_mng/bams_status1.do' />"; // 시스템관리자 권한 화면
				} else if (code == "20") {
					document.lForm.action = "<c:url value='bams_mng/bams_status2.do' />"; // 약정수용가 권한 화면
				} else if (code == "30") {
					document.lForm.action = "<c:url value='bams_mng/bams_status3.do' />"; // 일반사용자 권한 화면
				}
				var userAuth = document.getElementById("userAuth");
				var userId = document.getElementById("userId");
				window.returnValue = { userAuth : userAuth.value, userId : userId.value }; //자바스크립트 객체 리터럴
				window.close();
			}
		</script>
	</c:when>
</c:choose>
