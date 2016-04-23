<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<script type="text/javascript">
function control() {
	document.cForm.action = "<c:url value='commandControl.do' />";
	document.cForm.submit();
}
</script>
<div class="container">
	<%@ include file="layout/lnbArea.jsp"%>
	<div class="contentsArea">
		<form name="cForm" method="post">
			<div><input type="button" value="예약제어" onclick="control();" /></div>
		</form>
	</div>
</div>
<%@ include file="layout/footer.jsp"%>
