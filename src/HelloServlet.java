import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 	localhost:8080/hs -> 요청을 처리하도록
//	Java Servlet 을 만들려면 HttpServlet 을 상속 받아야 한다
public class HelloServlet extends HttpServlet{

	@Override
	// 데이터를 요청 header로 전달 : ?이름=값&이름=값
	protected void doGet(HttpServletRequest req, // 요청 정보 객체
			HttpServletResponse resp) // 응답 정보 객체
			throws ServletException, IOException {
		// Get 방식의 요청을 처리
		// 요청에서 name 파라미터를 받아와서 출력
		String name = req.getParameter("name");
		if (name == null) {
			name = "Anonymous";
		}
		// 출력: 응답 객체로부터 PrintWriter를 받아와서 내용을 출력
		PrintWriter out = resp.getWriter();
		out.println("<h1>Hello Servlet</h1>");
		out.println("<p>Welcome," + name + "</p>");
	}

	@Override
	// 데이터를 요청 Body로 전달 : Form
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// Post 방식의 요청을 처리
		// 파라미터를 받아올 때 파라미터의 적적한 인코딩 정보를 입력
//		req.setCharacterEncoding("UTF-8");
		// 데이터 불러 오기
		String firstname = req.getParameter("first_name");
		String lastname = req.getParameter("Last_name");
		
		// 출력
		// 응답의 타입과 인코딩 정보를 출력 
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<h1>Form Data</h1>");
		out.println("<p>Family name: " + lastname + "</p>");
		out.println("<p>First name: " + firstname + "</p>");
	}
	
	

}
