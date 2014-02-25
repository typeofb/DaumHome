<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<script type="text/javascript">
function boardDel() {
	if(!confirm("삭제하시겠습니까?")) return;
	$.ajax({
		url : "boardDel.do",
		data : {postId : $("#postId").val()},
		type : "post",
		cache : false,
		dataType : "html",
		success : function(result) {
			if ($.trim(result) == "true") {
				alert("삭제되었습니다.");
				window.location.href = "<c:url value='/boardList.do' />";
			} else if ($.trim(result) == "false") {
				alert("실패하였습니다.");
			}
		}
	});
}

function goMod() {
	$("form").attr("method", "post");
	$("form").attr("action", "goMod.do");
	$("form").submit();
}

function goList() {
	$("form").attr("method", "post");
	$("form").attr("action", "boardList.do");
	$("form").submit();
}
</script>

<div class="container">
	<div class="location">
		<h2>게시판 상세보기</h2>
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
			<input type="hidden" id="postId" name="postId" value="${map.POST_ID}" />
			<table class="table5" width="100%" cellpadding="0" cellspacing="0">
				<colgroup>
					<col width="50" />
					<col width="770" />
					<col width="100" />
					<col width="80" />
				</colgroup>
				<tr>
					<th>제 목</th>
					<td>${map.TITLE}</td>
					<th>등록일</th>
					<td>
						<fmt:parseDate value="${map.REG_DT}" var="fmtDateTime" pattern="yyyyMMdd" />
						<fmt:formatDate value="${fmtDateTime}" pattern="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<td colspan="4">${map.CONTENTS}</td>
				</tr>
			</table>
		</form>
		<div class="btn">
			<a href="javascript:goMod();" class="btnB"><span>수정</span></a>
			<a href="javascript:boardDel();" class="btnB"><span>삭제</span></a>
			<a href="javascript:goList();" class="btnB"><span>목록</span></a>
		</div>
	</div>
</div>
</body>
</html>
