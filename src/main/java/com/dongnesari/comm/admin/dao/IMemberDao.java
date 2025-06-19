package com.dongnesari.comm.admin.dao;

import java.util.List;

import com.dongnesari.comm.admin.vo.MemberVO;
import com.dongnesari.comm.admin.vo.ZipVO;

public interface IMemberDao {
  
	//매소드 선언 
	// 회원 전체 조회
	public List<MemberVO>  getAllMember();
	
	public MemberVO  loginMember(MemberVO  vo);
	
	
	//아이디 체크
	public  String idcheck(String id);
	
	//우편번호 검색
	public List<ZipVO> selectByDong(String dong);
	
	//저장하기 - 가입하기
	public int insertMember(MemberVO vo);
}
