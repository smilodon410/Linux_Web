<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World</title>
</head>
<body>
	<!-- 사용자가 파라미터로 변수를 전달할 경우 -->
	<!-- url?파라미터명=값&파라미터명=값 형태로 전달 -->
	<%	// jsp 내에서 자바 코드 사용
	String name = request.getParameter("name");
	// 파라미터가 전달되지 않으면 기본 값으로 설정
	if (name == null) {
		name = "Anonymous";
	}
	%>
	<h1>Hello, JSP</h1>
	<p>Welcome <%= name %></p>
	
	<a href="index.html">인덱스 페이지로</a>
</body>
</html>