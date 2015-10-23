<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<script src="js/common.js"></script>
<!-- jquery plugin -->
<script src="js/jquery-popup.js"></script>
<script type="text/javascript">
function fnUpload() {
	document.mForm.action = "<c:url value='daumeditor/pages/trex/upload.do' />";
	document.mForm.submit();
}

function callback(data) {
	alert(data.id + " : " + data.name);
}
</script>
<iframe name="my_iframe" style="display: none;"></iframe>
<div class="container">
	<div>http://javascript-array.com/scripts/jquery_simple_drop_down_menu/</div>
	<form name="mForm" method="post" enctype="multipart/form-data" target="my_iframe">
		<input type="file" name="attachment" />
		<input type="button" value="Upload" onclick="fnUpload()" />
	</form>
	<div>
		<a href="#" onclick="EP.showPopup('MULTI_DEPT', callback, '418', '');">jquery plugin modal test</a>
	</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
