<%@page import="com.bit.utils.emailist.vo.EmailistVo"%>
<%@page import="java.util.List"%>
<%@page import="com.bit.utils.emailist.dao.EmailistDaoOrcl"%>
<%@page import="com.bit.utils.emailist.dao.EmailistDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일링 리스트:메인(JSP 버전)</title>
</head>
<body>
	<h1>메일링 리스트</h1>

<%
EmailistDao dao = new EmailistDaoOrcl();	// DAO 객체 생성
List<EmailistVo> list = dao.getList();
%>

	<!-- 리스트 -->
	<!-- vo 객체의 getter를 이용, 리스트를 표시 -->
	<% for (EmailistVo vo:list) { %>
	<table border="1" cellpadding="5" cellspacing="2">
		<tr>
			<th>성</th>
			<td><%= vo.getLastname() %></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%= vo.getFirstname() %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%= vo.getEmail() %></td>
		</tr>
		<tr>
			<td colspan="2">
			<a href="<%= request.getContextPath() %>/emaillist/delete.jsp?no=<%= vo.getNo() %>">삭제</a>
			</td>
		</tr>
	</table>
	<br />
	<% } %>
	<p>
		<!-- /HelloWorld/emailist/form.jsp -->
		<!-- /Context Path : 가급적 동적으로 설정/emailist/form.jsp -->
		<a href="<%= request.getContextPath()%>/emaillist/form.jsp">추가 이메일 등록</a>
	</p>

</body>
</html>