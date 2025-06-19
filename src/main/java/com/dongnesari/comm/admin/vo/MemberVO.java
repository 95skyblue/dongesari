package com.dongnesari.comm.admin.vo;

import java.io.Serializable;

public class MemberVO implements Serializable {
	private String memId;       // 회원 고유 ID (mem + 시퀀스)
	private String loginId;     // 로그인용 ID	(UNUQUE, 영문/숫자 혼합하여 6~20글자
	private String password;    // 비밀번호 (영문/숫자/특수문자 혼합 8~20글자, 암호화 염두)
	private String name;        // 실명 (한글 2~ 17글자)
	private String nickname;    // 닉네임	(UNIQUE, 2~10글자(한글/영어/숫자 사용가능)
	private String email;       // 이메일 (이메일 형식, 이메일 인증시 필수 항목)
	private Integer    addrId;      	// 주소 ID (외래키 주소테이블 기본키 참조)
	private String phoneNum;    // 전화번호
	private String joinAt;      // 가입일자 
	
	
	public MemberVO(String memId, String loginId, String password, String name, String nickname, String email,
			Integer addrId, String phoneNum, String joinAt) {
		super();
		this.memId = memId;
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.addrId = addrId;
		this.phoneNum = phoneNum;
		this.joinAt = joinAt;
	}


	public String getMemId() {
		return memId;
	}


	public void setMemId(String memId) {
		this.memId = memId;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getAddrId() {
		return addrId;
	}


	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getJoinAt() {
		return joinAt;
	}


	public void setJoinAt(String joinAt) {
		this.joinAt = joinAt;
	}


	
}
