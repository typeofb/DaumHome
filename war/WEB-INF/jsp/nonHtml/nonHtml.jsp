<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="container">
	<ul>
		<li>비 HTML
			<ul>
				<li><a href="${pageContext.request.contextPath}/file.do">파일 다운로드</a></li>
				<li><a href="${pageContext.request.contextPath}/excel.do">엑셀 생성</a></li>
				<li><a href="${pageContext.request.contextPath}/pdf.do">PDF 생성</a></li>
			</ul>
		</li>
	</ul>
</div>
<%@ include file="../footer.jsp"%>
</body>
</html>
