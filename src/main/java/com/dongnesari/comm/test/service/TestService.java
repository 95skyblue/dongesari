package com.dongnesari.comm.test.service;

import javax.mail.internet.AddressException;

import com.dongnesari.comm.test.dao.TestDAO;
import com.dongnesari.comm.test.vo.AddressVO;
import com.dongnesari.comm.test.vo.EmailAuthVO;
import com.dongnesari.comm.test.vo.MemberVO;
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
	
	
	/**
	 * @param email 코드를 보낼 이메일 주소
	 * @return 실제로 보낸 이메일
	 * @throws AddressException 주소가 잘못되어서 이메일을 보내지 못했을 경우
	 */
	public String sendCodeToThisEmail(String email) throws AddressException {
		String code = EmailSender.generateCode();
		EmailSender.sendEmail(code, email);
		return code;
	}
	
	public void storeThisToEmailTable(String email, String code, String uuid) {
		EmailAuthVO vo = new EmailAuthVO(email, code, uuid);
		dao.storeEmailAuth(vo);
	}
	
	public boolean checkThisEmailData(EmailAuthVO vo) {
		return dao.checkEmailAuth(vo);
	}
	
	public int storeThisAddressAndGetAddrId(AddressVO vo) throws IllegalStateException {
		return dao.insertAddress(vo);
	}
	
	// 지금은 안되면 그냥 안된다고 하지만, 나중엔 어떤게 문제라서 안되는지 표시해줘야 함.
	public boolean registerThisMan(MemberVO memberVO, AddressVO addressVO, String verifiedEmail) {
		if(!memberVO.isAllValid()) {
			throw new IllegalStateException("유효성 검사 실패");
		}
		
		Integer addrId;
		try { 
			storeThisAddressAndGetAddrId(addressVO); // 일단 테이블에 주소 저장하고
			addrId = addressVO.getAddrId(); // 몇번에 저장했는지 시퀀스 갖고오기
			memberVO.setAddrId(addrId); // 그걸 멤버VO에 넣어주고
		} catch (IllegalStateException e) {
			throw e;
		}
		
		if(!memberVO.getEmail().equals(verifiedEmail)) {
			throw new IllegalStateException("인증된 이메일이 아님.");
		}
		
		return dao.insertMember(memberVO);
	}
}