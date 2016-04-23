<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<script type="text/javascript">
function dispEditArea(sMod, group, key, value) {
	$("#codeGroupId").val(group);
	$("#code").val(key);
	$("#codeName").val(value);
	if (sMod == "reg") {
		$("#codeGroupId").removeAttr("readonly");
		$("#code").removeAttr("readonly");
		$("#btnReg").show();
		$("#btnMod").hide();
		$("#btnDel").hide();
		$("#btnCan").hide();
	} else {
		$("#codeGroupId").attr("readonly", true);
		$("#code").attr("readonly", true);
		$("#btnReg").hide();
		$("#btnMod").show();
		$("#btnDel").show();
		$("#btnCan").show();
	}
}

function search() {
	ep.ajax({
		url : "codeSearch.do",
		data : $("form").serializeArray(),
		type : "post",
		cache : false,
		dataType : "html",
		callback : function(result) {
			$("#divResultArea").html(result);
			dispEditArea("reg", "", "", "");
		},
		block : {message : "잠시만 기다려주세요."}
	});
}

function goPage(targetPage) {
	$("#targetPage").val(targetPage);
	ep.ajax({
		url : "codeSearch.do",
		data : $("form").serializeArray(),
		type : "post",
		cache : false,
		dataType : "html",
		callback : function(result) {
			$("#divResultArea").html(result);
			dispEditArea("reg", "", "", "");
		},
		block : {type : "image"}
	});
}

function codeView() {
	$.ajax({
		url : "codeView.do",
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

function codeReg() {
	$.ajax({
		url : "codeReg.do",
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

function codeMod() {
	$.ajax({
		url : "codeMod.do",
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

function codeDel() {
	if(!confirm("삭제하시겠습니까?")) return;
	$.ajax({
		url : "codeDel.do",
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

function codeCan() {
	dispEditArea("reg", "", "", "");
}

function codeSel(group, key, value) {
	dispEditArea("mod", group, key, value);
}

window.onload = function() {
	search();
}
</script>

<div class="container">
	<%@ include file="../../layout/lnbArea.jsp"%>
	<div class="contentsArea">
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
				<li class="on"><a href="javascript:;">본부</a></li>
				<li><a href="javascript:;">지사</a></li>
			</ul>
		</div>
		<div class="list_search_bg">
		<div>${menu}</div>
			<form>
				<input type="hidden" id="targetPage" name="targetPage" value="${targetPage}" />
				<table class="list_search" cellpadding="0" cellspacing="0">
					<tr>
						<td>그룹</td>
						<td><input id="codeGroupId" name="codeGroupId" /></td>
						<td>키</td>
						<td><input id="code" name="code" /></td>
						<td>값</td>
						<td><input id="codeName" name="codeName" /></td>
						<td id="btnView"><a href="javascript:codeView();" class="btnT"><span>보기</span></a></td>
						<td id="btnReg"><a href="javascript:codeReg();" class="btnT"><span>등록</span></a></td>
						<td id="btnMod"><a href="javascript:codeMod();" class="btnT"><span>수정</span></a></td>
						<td id="btnDel"><a href="javascript:codeDel();" class="btnT"><span>삭제</span></a></td>
						<td id="btnCan"><a href="javascript:codeCan();" class="btnT"><span>취소</span></a></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="list_table" id="divResultArea">
			<table class="table5" width="100%" cellpadding="0" cellspacing="0">
				<colgroup>
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
				</colgroup>
				<tr>
					<th>그룹</th>
					<th>키</th>
					<th>값</th>
					<th>수정일자</th>
				</tr>
				<tr><td colspan="4">검색된 결과가 없습니다.</td></tr>
			</table>
		</div>
	</div>
</div>
<%@ include file="../../layout/footer.jsp"%>
