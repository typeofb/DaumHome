<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>다음정보기술</title>
<script type="text/javascript">
function goDetail(postId) {
	$("#postId").val(postId);
	$("form").attr("method", "post");
	$("form").attr("action", "boardDetail.do");
	$("form").submit();
}

function goReg() {
	$("form").attr("method", "post");
	$("form").attr("action", "goReg.do");
	$("form").submit();
}

function goPage(targetPage) {
	$("#targetPage").val(targetPage);
	$("form").attr("method", "post");
	$("form").attr("action", "boardList.do");
	$("form").submit();
}
</script>
</head>

<body>
<%@ include file="../header.jsp"%>
<div class="container">
	<div class="location">
		<h2>게시판</h2>
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
			<input type="hidden" id="rowSize" name="rowSize" value="15" />
			<input type="hidden" id="targetPage" name="targetPage" value="1" />
			<input type="hidden" id="pageGroupSize" name="pageGroupSize" value="10" />
			<input type="hidden" id="postId" name="postId" value="" />
			<table class="table5" width="100%" cellpadding="0" cellspacing="0">
				<colgroup>
					<col width="50" />
					<col width="720" />
					<col width="100" />
					<col width="50" />
					<col width="80" />
				</colgroup>
				<tr>
					<th>번 호</th>
					<th>제 목</th>
					<th>작성자</th>
					<th>조 회</th>
					<th>등록일</th>
				</tr>
			<c:forEach items="${list}" var="x">
				<tr onclick="goDetail('${x.POST_ID}')" style="cursor:pointer;">
					<td>${x.POST_ID}</td>
					<td>${x.TITLE}</td>
					<td>${x.USR_NM}</td>
					<td>${x.READ_CNT}</td>
					<td>${x.REG_DT}</td>
				</tr>
			</c:forEach>
			</table>
		</form>
		<div class="btn"><a href="javascript:goReg();" class="btnB"><span>등록</span></a></div>
		<div class="pagination">${paging}</div>
	</div>
</div>
</body>
</html>
