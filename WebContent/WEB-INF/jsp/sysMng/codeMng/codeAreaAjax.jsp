<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>코드관리</title>
<c:if test="${not empty strMessage}">
	<script type="text/javascript">
		alert("${strMessage}");
	</script>
</c:if>
</head>

<body>
<div>${menu}</div>
	<table class="table5" width="100%" cellpadding="0" cellspacing="0">
		<colgroup>
			<col width="30%" />
			<col width="40%" />
			<col width="30%" />
		</colgroup>
		<tr>
			<th>키워드</th>
			<th>유의어</th>
			<th>직제순서</th>
		</tr>
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="3">검색된 결과가 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="x" items="${list}">
					<tr onclick="areaSel('${x.KEYWORD}', '${x.THESAURUS}', '${x.IDX}');" style="cursor: pointer;">
						<th class="th_line">${x.KEYWORD}</th>
						<td>${x.THESAURUS}</td>
						<td>${x.IDX}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>
