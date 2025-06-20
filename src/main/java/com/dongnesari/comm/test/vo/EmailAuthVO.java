package com.dongnesari.comm.test.vo;

import java.time.LocalDate;

public class EmailAuthVO {
	private int seqId;
	private String email;
	private String code;
	private LocalDate expireAt;
	private String verifed;
	private String authToken;

	public EmailAuthVO() {}
	
	public EmailAuthVO(String email, String code, String authToken) {
		this.email = email;
		this.authToken = authToken;
		this.code = code;
	}
	
	public int getSeqId() {		return seqId;	}
	public void setSeqId(int seqId) {		this.seqId = seqId;	}
	public String getEmail() {		return email;	}
	public void setEmail(String email) {		this.email = email;	}
	public String getCode() {		return code;	}
	public void setCode(String code) {		this.code = code;	}
	public LocalDate getExpireAt() {		return expireAt;	}
	public void setExpireAt(LocalDate expireAt) {		this.expireAt = expireAt;	}
	public String getVerifed() {		return verifed;	}
	public void setVerifed(String verifed) {		this.verifed = verifed;	}
	public String getAuthToken() {		return authToken;	}
	public void setAuthToken(String authToken) {		this.authToken = authToken;	}
}