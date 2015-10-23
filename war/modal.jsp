<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	
	String title = request.getParameter("title");
	String scroll = request.getParameter("scroll");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=title%></title>
<script type="text/javascript">
	/*
	$().ready(function() {
		var vForm = document.getElementById("viewForm");
		var callUrl = window.dialogArguments.url;
		
		vForm.action = callUrl;
		var f = $("#viewForm");
		if (window.dialogArguments.param) {
			var params = window.dialogArguments.param;
			for ( var name in params) {
				f.append($(document.createElement("input")).attr({
					"type" : "hidden",
					"name" : name,
					"value" : params[name]
				}));
			}
		}
		vForm.submit();
	});
	*/
	function confirm() {
		var companyId = document.getElementById("companyId");
		var companyName = document.getElementById("companyName");
		window.returnValue = { id : companyId.value, name : companyName.value }; //자바스크립트 객체 리터럴
		window.close();
	}
	
	function cancel() {
		self.close();
	}
</script>
</head>
<body>
	<form id="viewForm" name="viewForm" method="post" target="main">
		<%
			for (java.util.Enumeration paramEnum = request.getParameterNames(); paramEnum.hasMoreElements();) {
				String paramName = (String) paramEnum.nextElement();
				String paramValue = request.getParameter(paramName);
				paramValue = request.getParameter(paramName);
		%>
		<input type="hidden" name="<%=paramName%>" value="<%=paramValue%>">
		<%
			}
		%>
	</form>
	<div>
		<ul>
			<li><input type="text" id="companyId" value="418" />아이디</li>
			<li><input type="text" id="companyName" value="삼성" />회사이름</li>
			<li><a href="javascript:;" onclick="confirm();">확인</a></li>
			<li><a href="javascript:;" onclick="cancel();">취소</a></li>
		</ul>
	</div>
</body>
</html>
