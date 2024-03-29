<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<script type="text/javascript">
function boardMod() {
	$.ajax({
		url : "boardMod.do",
		data : $("form").serializeArray(),
		type : "post",
		cache : false,
		dataType : "html",
		success : function(result) {
			var json = JSON.parse(result);
			if (json.result == true) {
				alert("수정되었습니다.");
				location.href = "boardList.do" + location.search;
			} else {
				alert("실패하였습니다.");
			}
		}
	});
}

function goList() {
	location.href = "boardList.do" + location.search;
}
</script>

<div class="container">
	<%@ include file="../../layout/lnbArea.jsp"%>
	<div class="contentsArea">
		<div class="location">
			<h2>게시판 수정</h2>
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
						<td><input type="text" id="title" name="title" value="${map.TITLE}" style="width: 700px; padding-left: 5px;" /></td>
						<th>등록일</th>
						<td>${map.REG_DT}</td>
					</tr>
					<tr>
						<td colspan="4"><textarea id="contents" name="contents">${map.CONTENTS}</textarea></td>
					</tr>
				</table>
			</form>
			<div class="btn">
				<a href="javascript:boardMod();" class="btnB"><span>수정</span></a>
				<a href="javascript:goList();" class="btnB"><span>목록</span></a>
			</div>
		</div>
	</div>
</div>
<%@ include file="../../layout/footer.jsp"%>
