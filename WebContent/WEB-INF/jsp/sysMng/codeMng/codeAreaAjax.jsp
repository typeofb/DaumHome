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
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="3">검색된 결과가 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="x" items="${list}">
					<tr onclick="areaSel('${x.AREA_CD}', '${x.AREA_NM}', '${x.AREA_PRIO}');" style="cursor: pointer;">
						<th class="th_line">${x.AREA_CD}</th>
						<td>${x.AREA_NM}</td>
						<td>${x.AREA_PRIO}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>
