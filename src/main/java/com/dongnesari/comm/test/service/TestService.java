package com.dongnesari.comm.test.service;

import com.dongnesari.comm.test.dao.TestDAO;

public class TestService {
	private static TestService instance;
	private TestDAO dao = TestDAO.getInstance();
	
	public static TestService getInstance() {
		if(instance == null) instance = new TestService();
		return instance;
	}
	
	/**
	 * @param loginId 중복체크해서 사용가능여부 확인받을 로그인용 ID 
	 * @return 사용 가능 여부
	 */
	public boolean canIUseThisID(String loginId) {
		boolean a = dao.checkIdFromMember(loginId); 
		boolean b = dao.checkIdFromMemQuit(loginId);
		return !(a||b);
	}
	
}
