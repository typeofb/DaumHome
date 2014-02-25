<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<script type="text/javascript">
function boardReg() {
	$.ajax({
		url : "boardReg.do",
		data : $("form").serializeArray(),
		type : "post",
		cache : false,
		dataType : "html",
		success : function(result) {
			var json = JSON.parse(result);
			if (json.result == true) {
				$("targetPage").val(json.targetPage);
				$("beginDate").val(json.beginDate);
				$("endDate").val(json.endDate);
				alert("등록되었습니다.");
				$("form").attr("method", "post");
				$("form").attr("action", "boardList.do");
				$("form").submit();
			} else {
				alert("실패하였습니다.");
			}
		}
	});
}

function goList() {
	$("form").attr("method", "post");
	$("form").attr("action", "boardList.do");
	$("form").submit();
}
</script>

<div class="container">
	<div class="location">
		<h2>게시판 등록</h2>
		<ul>
			<li>
				<span><img src="<c:url value='/images/icon_home.gif' />" /></span>
				<span>기능들</span>
				<span><img src="<c:url value='/images/icon_arrow.png' />" /></span>
				<span class="bold">게시판</span>
			</li>
		</ul>
	</div>
	<div class="list_table">
		<form>
			<input type="hidden" id="targetPage" name="targetPage" value="${targetPage}" />
			<input type="hidden" id="beginDate" name="beginDate" value="${beginDate}" />
			<input type="hidden" id="endDate" name="endDate" value="${endDate}" />
			<table class="table5" width="100%" cellpadding="0" cellspacing="0">
				<colgroup>
					<col width="50" />
					<col width="950" />
				</colgroup>
				<tr>
					<th>제 목</th>
					<td><input type="text" id="title" name="title" style="width: 850px; padding-left: 5px;" /></td>
				</tr>
				<tr>
					<td colspan="4"><textarea id="contents" name="contents"></textarea></td>
				</tr>
			</table>
		</form>
		<div class="btn">
			<a href="javascript:boardReg();" class="btnB"><span>등록</span></a>
			<a href="javascript:goList();" class="btnB"><span>목록</span></a>
		</div>
	</div>
</div>
</body>
</html>
