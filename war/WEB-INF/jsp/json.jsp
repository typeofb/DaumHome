<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ include file="layout/header.jsp"%>
<div class="container">
	<%@ include file="layout/lnbArea.jsp"%>
	<div class="contentsArea">
		<c:forEach var="x" items="${result}">
			<c:set var="obj" value="${x.contents}" />
			<%
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = (String) pageContext.getAttribute("obj");
				ArrayList<HashMap<String, Object>> readValue = mapper.readValue(jsonString, ArrayList.class);
				for (int i = 0; i < readValue.size(); i++) {
					HashMap<String, Object> map = readValue.get(i);
					out.print("<a href='" + ((HttpServletRequest)(pageContext.getRequest())).getContextPath() + map.get("fakeNm") + "'>");
					out.print(map.get("realNm"));
					out.print("</a>");
				}
				out.println("<br>");
			%>
		</c:forEach>
		<a href='<c:url value="/message/write" />'>쪽지보내기</a><br>
		<h:json value="${result[0].contents}" />
		<h:json value="{\"fakeNm\":\"/mobile/file/download/0/1466387904304\",\"realNm\":\"Penguins.jpg\"}" />
	</div>
</div>
<%@ include file="layout/footer.jsp"%>
