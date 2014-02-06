<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>다음정보기술</title>
<script type="text/javascript">
function boardReg() {
	$("form").attr("method", "post");
	$("form").attr("action", "boardReg.do");
	$("form").submit();
}

function goList() {
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
