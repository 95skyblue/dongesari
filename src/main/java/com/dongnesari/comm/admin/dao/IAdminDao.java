package com.dongnesari.comm.admin.dao;

import java.util.List;
import java.util.Map;

import com.dongnesari.comm.admin.vo.AdminVO;

public interface IAdminDao {
	
	// 전체 관리자 조회
    public List<AdminVO> getAllAdmins();

    // 관리자 상세 조회 (ID로)
    public AdminVO getAdminById(String admId);

    // 관리자 로그인 (Login ID + Password)
    public AdminVO loginAdmin(AdminVO vo);

    // 관리자 등록
    public int insertAdmin(AdminVO vo);

    // 관리자 ID 중복 체크
    public String checkLoginId(String loginId);

    // 관리자 삭제
    public int deleteAdmin(String admId);
    
    // 페이지 네이션 (구현안됨)
    List<AdminVO> getAdminsByPage(Map<String, Integer> map); // 페이지별 목록
    int getAdminCount(); // 전체 개수
    
    int updateAdmin(AdminVO vo);
}
