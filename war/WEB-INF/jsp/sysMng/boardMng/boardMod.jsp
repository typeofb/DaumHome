<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
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
			<input type="hidden" id="targetPage" name="targetPage" value="${targetPage}" />
			<input type="hidden" id="beginDate" name="beginDate" value="${beginDate}" />
			<input type="hidden" id="endDate" name="endDate" value="${endDate}" />
			<input type="hidden" id="selectItem" name="selectItem" value="${selectItem}" />
			<input type="hidden" id="searchText" name="searchText" value="${searchText}" />
			<input type="hidden" id="sortYn" name="sortYn" value="${sortYn}" />
			<input type="hidden" id="sortField" name="sortField" value="${sortField}" />
			<input type="hidden" id="sortOrderBy" name="sortOrderBy" value="${sortOrderBy}" />
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
<%@ include file="../../footer.jsp"%>
</body>
</html>
