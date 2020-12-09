package com.bit.utils.emailist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.utils.emailist.dao.EmailistDao;
import com.bit.utils.emailist.dao.EmailistDaoOrcl;
import com.bit.utils.emailist.vo.EmailistVo;

// Controller 역할을 담당하는 Servlet
// web.xml 내에 server과 servlet-mapping을 등록 한 것과 동일한 효과
@WebServlet(name="Emailist", urlPatterns="/el")
public class EmailistServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 	파라미터 a를 확인해서 a.equals("form")이면 form.jsp로 포워드
		String action = req.getParameter("a");
		// if (action.equals("form") -> 안됨 action이 null 일수 있기 때문에
		if ("form".equals(action)) {
			// 폼 페이지로 Forward
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/form.jsp");
			rd.forward(req, resp);
		} else {
			//	목록
			EmailistDao dao = new EmailistDaoOrcl();
			List<EmailistVo> list = dao.getList();
			// 	처리 중인 req, resp -> /WEB-INF/views/emailist/index.jsp 쪽으로 처리 주도권 넘기기
			// 	(Forward)
			//	JSP에게 처리한 목록 전달해 줘야 함
			req.setAttribute("list", list);
			// Forward를 위한 RequestDispatcher
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/index.jsp");
			// Forward
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//	저장 처리
		String lastname = req.getParameter("ln");
		String firstname = req.getParameter("fn");
		String email = req.getParameter("email");
		
		//	DTO 객체 생성, 설정
		EmailistVo vo = new EmailistVo();
		vo.setLastname(lastname);
		vo.setFirstname(firstname);
		vo.setEmail(email);
		
		// 	DAO
		EmailistDao dao = new EmailistDaoOrcl();
		dao.insert(vo);
		
		//	주소록 메인 페이지로 Redirect
		resp.sendRedirect(req.getContextPath() + "/el");
	}
	
}
