package com.bit.utils.emailist.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.bit.utils.emailist.vo.EmailistVo;


// 데이터 전송 객체
public class EmailistDaoOrcl implements EmailistDao {
	// 데이터베이스 접속 정보
	private static String dburl = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static String dbuser = "webdb";
	private static String dbpass = "webdb";
	
	// 접속부는 공통! -> 접속 객체를 반환하는 메소드
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			// Driver 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패");
		} 
		
		return conn;
	}
	
	// SELECT no, last_name. first_name. email, created_at FROM eamilist ORDER BY created_at DESC;
	@Override
	public List<EmailistVo> getList() {
		// 결과를 반환할 List
		List<EmailistVo> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 접속 객체
			conn = getConnection();
			// 질의 수행 객체
			stmt = conn.createStatement();
			String sql = "SELECT no, last_name, first_name, email, " + 
						"created_at FROM emailist ORDER BY created_at DESC";
			// 질의 수랭 및 결과 객체 생성
			rs = stmt.executeQuery(sql);
			
			// java.sql 결과 객체  -> DTO의 리스트로 변환
			while (rs.next()) {
				// 변환 작업 수행
				Long no = rs.getLong(1);
				String lastname = rs.getString(2);
				String firstname = rs.getString(3);
				String email = rs.getString(4);
				java.util.Date createdAt = rs.getDate(5);
				
				// DTO 객체 생성
				EmailistVo vo = new EmailistVo();
				vo.setNo(no);
				vo.setLastname(lastname);
				vo.setFirstname(firstname);
				vo.setEmail(email);
				vo.setCreatedAt(createdAt);
				
				// 리스트에 등록
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리
			try {
				if (rs != null) rs.close();
				if (stmt != null) rs.close();
				if (conn != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int insert(EmailistVo vo) {
		int insertedCount = 0;	// 영향 받은 레코드 수
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql  = "INSERT INTO emailist VALUES (seq_emailist_pk.nextval, ?, ?, ?, sysdate)";

			// 뎀플릿 쿼리 사용을 위한 PreparedStatement
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  vo.getLastname());
			pstmt.setString(2, vo.getFirstname());
			pstmt.setString(3, vo.getEmail());
			
			insertedCount = pstmt.executeUpdate();	//	INSERT, UPDATE, DELETE
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return insertedCount;
	}

	@Override
	public int delete(Long no) {
		int deleteCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM emailist WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return deleteCount;
	}

	@Override
	public int update(EmailistVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
