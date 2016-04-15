<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<script type="text/javascript">
	var isWait = false;
	function connect(msg) {
		if (isWait && msg == "") {
			return;
		}
		if (msg == "") {
			isWait = true;
		}
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/chatClient.do",
			dataType : "html",
			data : "message=" + msg,
			success : function(data) {
				if (msg == "") {
					if (data != null && data != "") {
						$("#messageBox").val($("#messageBox").val() + data + "\n");
					}
					isWait = false;
					connect("");
				}
			}
		});
	}
	
	function send() {
		var message = $("#message").val();
		$("#message").val("");
		$("#message").focus();
		if (message != null && message != "") {
			message = "[" + $("#userName").val() + "] " + message;
			connect(message);
		}
	}
	
	$(document).ready(function() {
		$("#message").focus();
		connect("");
	});
</script>

<div class="container">
	<div>◎ 채팅</div>
	<div><textarea id="messageBox" style="width: 250px; height: 300px;" readonly></textarea></div>
	<span>대화명 : </span><input type="text" id="userName" name="userName" value="아무개" />
	<span>메시지 : </span><input type="text" id="message" name="message" onkeypress="if (event.keyCode==13) send();" />
	<input type="button" value="전송" onclick="send();" />
</div>
<%@ include file="layout/footer.jsp"%>
</body>
</html>
