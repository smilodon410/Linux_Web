package com.bit.utils.emailist.dao;

import java.util.List;

import com.bit.utils.emailist.vo.EmailistVo;

// DAO 설계도
public interface EmailistDao {
	// 목록 보기
	public List<EmailistVo> getList();
	
	// Insert를 위한 메소드
	public int insert(EmailistVo vo);
	
	// Update를 위한 메소드
	public int update(EmailistVo vo);
	
	//Delete를 위한 메소드
	public int delete(Long no);
}
