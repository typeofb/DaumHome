<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<category>코드관리</category>
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
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="4">검색된 결과가 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="x" items="${list}">
					<tr onclick="codeSel('${x.id}', '${x.category}', '${x.name}');" style="cursor: pointer;">
						<th class="th_line">${x.id}</th>
						<td>${x.category}</td>
						<td>${x.name}</td>
						<td>${x.updatedAt}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="pagination">${paging}</div>
</body>
</html>
