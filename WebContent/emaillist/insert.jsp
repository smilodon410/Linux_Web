<%@page import="com.bit.utils.emailist.dao.EmailistDaoOrcl"%>
<%@page import="com.bit.utils.emailist.dao.EmailistDao"%>
<%@page import="com.bit.utils.emailist.vo.EmailistVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 요청정보로부터 파라미터 추출
String lastname = request.getParameter("ln");
String firstname = request.getParameter("fn");
String email = request.getParameter("email");

//	DTo 객체 생성
EmailistVo vo = new EmailistVo();
// 필드 세팅
vo.setLastname(lastname);
vo.setFirstname(firstname);
vo.setEmail(email);
// DAO 객체 생성
EmailistDao dao = new EmailistDaoOrcl();
dao.insert(vo);

// REDIRECT
// 30x 응답: 브라우저에게 요청 완료를 위해 새 페이지로 접속해 줄 것을 요청

response.sendRedirect(request.getContextPath() + "/emaillist/"); // 이동할 페이지를 명시
%>