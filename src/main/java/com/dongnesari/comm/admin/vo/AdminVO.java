package com.dongnesari.comm.admin.vo;

public class AdminVO {
	private String admId; 			// adm + 시퀀스
	private String admLoginid;	// 영문/숫자 혼합 6~20글자
	private String admPw;			// 영문/숫자/특수문자 혼합 8~20글자, 암호화 염두
	private String admName;		// 한글 2~17 글자
	private String rsdntNum;		// 하이픈 없는 순수 주민번호 13자리
	
	
	
	public AdminVO(String admId, String admLoginid, String admPw, String admName, String rsdntNum) {
		super();
		this.admId = admId;
		this.admLoginid = admLoginid;
		this.admPw = admPw;
		this.admName = admName;
		this.rsdntNum = rsdntNum;
	}
	
	
	public AdminVO(String admLoginid, String admPw, String admName, String rsdntNum) {
	    this.admLoginid = admLoginid;
	    this.admPw = admPw;
	    this.admName = admName;
	    this.rsdntNum = rsdntNum;
	}







	public String getAdmId() {
		return admId;
	}



	public void setAdmId(String admId) {
		this.admId = admId;
	}



	public String getAdmLoginid() {
		return admLoginid;
	}



	public void setAdmLoginid(String admLoginid) {
		this.admLoginid = admLoginid;
	}



	public String getAdmPw() {
		return admPw;
	}



	public void setAdmPw(String admPw) {
		this.admPw = admPw;
	}



	public String getAdmName() {
		return admName;
	}



	public void setAdmName(String admName) {
		this.admName = admName;
	}



	public String getRsdntNum() {
		return rsdntNum;
	}



	public void setRsdntNum(String rsdntNum) {
		this.rsdntNum = rsdntNum;
	}



	
	

	
}
