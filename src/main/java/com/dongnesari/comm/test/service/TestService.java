package com.dongnesari.comm.test.service;

import com.dongnesari.comm.test.dao.TestDAO;
import com.dongnesari.comm.test.vo.EmailAuthVO;
import com.dongnesari.comm.util.EmailSender;

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
	
	public boolean canIUseThisNick(String nickname) {
		return !dao.checkNickname(nickname);
	}
	
	public String sendCodeToThisEmail(String email) {
		String code = EmailSender.generateCode();
		try {
			EmailSender.sendEmail(code, email);
			return code;
		} catch(Exception e) {
			return null;
		}
	}
	
	public void storeThisToEmailTable(String email, String code, String uuid) {
		EmailAuthVO vo = new EmailAuthVO(email, code, uuid);
		dao.storeEmailAuth(vo);
	}
	
	public boolean checkThisEmailData(EmailAuthVO vo) {
		return dao.checkEmailAuth(vo);
	}
}