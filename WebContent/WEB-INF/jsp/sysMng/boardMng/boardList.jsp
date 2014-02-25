<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<script type="text/javascript">
$(function() {
	$('#beginDate').datepicker({
		changeYear : true,
		changeMonth : true,
		yearRange : '2011:2030',
		showOn : 'button',
		buttonImage : '<c:url value='/images/icon_calendar.gif' />',
		buttonImageOnly : true,
		altField : '#beginDate',
		altFormat : 'yy-mm-dd',
		dateFormat : 'yy-mm-dd',
		showButtonPanel : false,
		showMonthAfterYear : true,
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
		defaultDate : '+0m+0d'
	});
	$('.ui-datepicker-trigger').mouseover(function() {
		$(this).css('cursor', 'pointer');
	});
	
	$('#endDate').datepicker({
		changeYear : true,
		changeMonth : true,
		yearRange : '2011:2030',
		showOn : 'button',
		buttonImage : '<c:url value='/images/icon_calendar.gif' />',
		buttonImageOnly : true,
		altField : '#endDate',
		altFormat : 'yy-mm-dd',
		dateFormat : 'yy-mm-dd',
		showButtonPanel : false,
		showMonthAfterYear : true,
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
		defaultDate : '+0m+0d'
	});
	$('.ui-datepicker-trigger').mouseover(function() {
		$(this).css('cursor', 'pointer');
	});
});

function goReg() {
	$("form").attr("method", "post");
	$("form").attr("action", "goReg.do");
	$("form").submit();
}

function goDetail(postId) {
	$("#postId").val(postId);
	$("form").attr("method", "post");
	$("form").attr("action", "boardDetail.do");
	$("form").submit();
}

function goPage(targetPage) {
	$("#targetPage").val(targetPage);
	$("form").attr("method", "post");
	$("form").attr("action", "boardList.do");
	$("form").submit();
}
</script>

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
	<div class="tab_v1">
		<ul>
			<li class="on"><a href="#">게시판</a></li>
			<li><a href="#">자료실</a></li>
		</ul>
	</div>
	<form>
	<div class="list_search_bg">
		<table class="list_search" cellpadding="0" cellspacing="0">
			<tr>
				<td>검색조건</td>
				<td>
					<input type="text" id="beginDate" name="beginDate" value="${beginDate}" readonly> ~ <input type="text" id="endDate" name="endDate" value="${endDate}" readonly>
				</td>
				<td id="btnReg"><a href="javascript:goPage(1);" class="btnT"><span>검색</span></a></td>
			</tr>
		</table>
	</div>
	<div class="list_table">
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
				<td>
					<fmt:parseDate value="${x.REG_DT}" var="fmtDateTime" pattern="yyyyMMdd" />
					<fmt:formatDate value="${fmtDateTime}" pattern="yyyy-MM-dd" />
				</td>
			</tr>
		</c:forEach>
		</table>
		<div class="btn"><a href="javascript:goReg();" class="btnB"><span>등록</span></a></div>
		<div class="pagination">${paging}</div>
	</div>
	</form>
</div>
</body>
</html>
