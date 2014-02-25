<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${loginResult eq 'E3'}">
		<script>
			alert("등록되지 않은 아이디입니다. 계정요청을 하셔야 로그인할 수 있습니다.");
			location.href = "<c:url value='login.do' />";
		</script>
	</c:when>
	<c:when test="${loginResult eq 'E2'}">
		<script>
			alert("패스워드를 확인해주세요.");
			location.href = "<c:url value='login.do' />";
		</script>
	</c:when>
	<c:when test="${loginResult eq 'E1'}">
		<script>
			alert("아이디가 사용가능한 상태가 아닙니다.");
			location.href = "<c:url value='login.do' />";
		</script>
	</c:when>
	<c:when test="${loginResult eq 'C'}">
		<script>
			window.onload = function() {
				var code = document.getElementById("userAuth").value;
				if (code == "00") {
					document.lForm.action = "<c:url value='main.do' />";
				} else if (code == "10") {
					document.lForm.action = "<c:url value='bams_mng/bams_status1.do' />"; // 시스템관리자 권한 화면
				} else if (code == "20") {
					document.lForm.action = "<c:url value='bams_mng/bams_status2.do' />"; // 약정수용가 권한 화면
				} else if (code == "30") {
					document.lForm.action = "<c:url value='bams_mng/bams_status3.do' />"; // 일반사용자 권한 화면
				}
				window.close();
				window.opener.document.location.reload();
			}
		</script>
	</c:when>
</c:choose>

<form name="lForm" method="post">
	<input type="hidden" id="userAuth" value="${userAuth}">
	<input type="hidden" id="userId" name="userId" value="${userId}">
</form>