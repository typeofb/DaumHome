<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>세이브더칠드런</title>
<c:if test="${not empty strMessage}">
	<script type="text/javascript">
		alert("${strMessage}");
	</script>
</c:if>
</head>

<body>
	<ul class="newsList">
		<c:choose>
			<c:when test="${empty list}">
		<li>
			<span>검색된 결과가 없습니다.</span>
		</li>
			</c:when>
			<c:otherwise>
				<c:forEach var="x" items="${list}">
		<li>
			<span class="vertical_line">${x.CODE_GROUP_ID}</span>
			<span class="vertical_line">${x.CODE}</span>
			<span class="vertical_line">${x.CODE_NAME}</span>
			<span class="vertical_line">${x.LAST_UPDATE_DTIME}</span>
		</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>
	<div class="pagingNav">${paging}</div>
</body>
</html>
