<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>다음정보기술</title>
<script type="text/javascript">
function dispEditArea(sMod, cd, nm, prio) {
	$("#areaCd").val(cd);
	$("#areaNm").val(nm);
	$("#areaPrio").val(prio);
	if (sMod == "reg") {
		$("#areaCd").removeAttr("readonly");
		$("#btnReg").show();
		$("#btnMod").hide();
		$("#btnDel").hide();
		$("#btnCan").hide();
	} else {
		$("#areaCd").attr("readonly", true);
		$("#btnReg").hide();
		$("#btnMod").show();
		$("#btnDel").show();
		$("#btnCan").show();
	}
}

function search() {
	$.ajax({
		url : "codeAreaSearch.do",
		type : "post",
		cache : false,
		dataType : "html",
		success : function(result) {
			$("#divResultArea").html(result);
			dispEditArea("reg", "", "", "");
		}
	});
}

function areaReg() {
	$.ajax({
		url : "codeAreaReg.do",
		data : $("form").serializeArray(),
		type : "post",
		cache : false,
		dataType : "html",
		success : function(result) {
			$("#divResultArea").html(result);
			dispEditArea("reg", "", "", "");
		}
	});
}

function areaMod() {
	$.ajax({
		url : "codeAreaMod.do",
		data : $("form").serializeArray(),
		type : "post",
		cache : false,
		dataType : "html",
		success : function(result) {
			$("#divResultArea").html(result);
			dispEditArea("reg", "", "", "");
		}
	});
}

function areaDel() {
	if(!confirm("삭제하시겠습니까?")) return;
	$.ajax({
		url : "codeAreaDel.do",
		data : $("form").serializeArray(),
		type : "post",
		cache : false,
		dataType : "html",
		success : function(result) {
			$("#divResultArea").html(result);
			dispEditArea("reg", "", "", "");
		}
	});
}

function areaCan() {
	dispEditArea("reg", "", "", "");
}

function areaSel(cd, nm, prio) {
	dispEditArea("mod", cd, nm, prio);
}

window.onload = function() {
	search();
}
</script>
</head>

<body>
<%@ include file="../../header.jsp"%>
<div class="container">
	<div class="location">
		<h2>코드관리</h2>
		<ul>
			<li>
				<span><img src="<c:url value='/images/icon_home.gif' />" /></span>
				<span>기능들</span>
				<span><img src="<c:url value='/images/icon_arrow.png' />" /></span>
				<span class="bold">코드관리</span>
			</li>
		</ul>
	</div>
	<div class="tab_v1">
		<ul>
			<li class="on"><a href="#">본부</a></li>
			<li><a href="#">지사</a></li>
		</ul>
	</div>
	<div class="list_search_bg">
		<form>
			<table class="list_search" cellpadding="0" cellspacing="0">
				<tr>
					<td>본부코드</td>
					<td><input id="areaCd" name="areaCd" readonly /></td>
					<td>본부명</td>
					<td><input id="areaNm" name="areaNm" /></td>
					<td>직제순서</td>
					<td><input id="areaPrio" name="areaPrio" /></td>
					<td id="btnReg"><a href="javascript:areaReg();" class="btnT"><span>등록</span></a></td>
					<td id="btnMod"><a href="javascript:areaMod();" class="btnT"><span>수정</span></a></td>
					<td id="btnDel"><a href="javascript:areaDel();" class="btnT"><span>삭제</span></a></td>
					<td id="btnCan"><a href="javascript:areaCan();" class="btnT"><span>취소</span></a></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="list_table" id="divResultArea">
		<table class="table5" width="100%" cellpadding="0" cellspacing="0">
			<colgroup>
				<col width="30%" />
				<col width="40%" />
				<col width="30%" />
			</colgroup>
			<tr>
				<th>본부코드</th>
				<th>본부명</th>
				<th>직제순서</th>
			</tr>
			<tr><td colspan="3">검색된 결과가 없습니다.</td></tr>
		</table>
	</div>
</div>
</body>
</html>
