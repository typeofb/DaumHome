<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<script type="text/javascript">
function boardDel() {
	if(!confirm("삭제하시겠습니까?")) return;
	$.ajax({
		url : "boardDel.do",
		data : $("form").serialize(),
		type : "post",
		cache : false,
		dataType : "html",
		success : function(result) {
			var json = JSON.parse(result);
			if (json.result == true) {
				alert("삭제되었습니다.");
				location.href = "boardList.do" + location.search;
			} else {
				alert("실패하였습니다.");
			}
		}
	});
}

function goMod() {
	location.href = "goMod.do" + location.search;
}

function goList() {
	location.href = "boardList.do" + location.search;
}
</script>

<div class="container">
	<%@ include file="../../layout/lnbArea.jsp"%>
	<div class="contentsArea">
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
						<td>${map.REG_DT}</td>
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
</div>
<%@ include file="../../layout/footer.jsp"%>
