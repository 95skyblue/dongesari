package com.dongnesari.comm.admin.service;

import java.util.List;
import java.util.Map;

import com.dongnesari.comm.admin.vo.AdminVO;

public interface IAdminService {

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
    
    // 페이지 네이션 (구현 실패)
    List<AdminVO> getAdminsByPage(Map<String, Integer> map);
    int getAdminCount();

    // 관리자수정
	public int updateAdmin(AdminVO vo);
    
}
