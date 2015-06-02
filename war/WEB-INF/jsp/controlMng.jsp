<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<script type="text/javascript">
function control() {
	document.cForm.action = "<c:url value='commandControl.do' />";
	document.cForm.submit();
}
</script>
<div class="container">
	<form name="cForm" method="post">
		<div><input type="button" value="예약제어" onclick="control();" /></div>
	</form>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
