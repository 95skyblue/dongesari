package com.dongnesari.comm.admin.vo;

public class AdminVO {
	private String adm_id; 			// adm + 시퀀스
	private String adm_login_id;	// 영문/숫자 혼합 6~20글자
	private String adm_pw;			// 영문/숫자/특수문자 혼합 8~20글자, 암호화 염두
	private String adm_name;		// 한글 2~17 글자
	private String rsdnt_num;		// 하이픈 없는 순수 주민번호 13자리
	
	
	public AdminVO(String adm_id, String adm_login_id, String adm_pw, String adm_name, String rsdnt_num) {
		super();
		this.adm_id = adm_id;
		this.adm_login_id = adm_login_id;
		this.adm_pw = adm_pw;
		this.adm_name = adm_name;
		this.rsdnt_num = rsdnt_num;
	}
	
	public String getAdm_id() {
		return adm_id;
	}
	public void setAdm_id(String adm_id) {
		this.adm_id = adm_id;
	}
	public String getAdm_login_id() {
		return adm_login_id;
	}
	public void setAdm_login_id(String adm_login_id) {
		this.adm_login_id = adm_login_id;
	}
	public String getAdm_pw() {
		return adm_pw;
	}
	public void setAdm_pw(String adm_pw) {
		this.adm_pw = adm_pw;
	}
	public String getAdm_name() {
		return adm_name;
	}
	public void setAdm_name(String adm_name) {
		this.adm_name = adm_name;
	}
	public String getRsdnt_num() {
		return rsdnt_num;
	}
	public void setRsdnt_num(String rsdnt_num) {
		this.rsdnt_num = rsdnt_num;
	}
	
	
	
}
