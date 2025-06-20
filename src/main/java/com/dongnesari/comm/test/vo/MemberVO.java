package com.dongnesari.comm.test.vo;

import java.time.LocalDateTime;

public class MemberVO {
	private int memId;
	private String loginId;
	private String password;
	private String name;
	private String nickname;
	private String email;
	private int addrId;
	private String phoneNum;
	private LocalDateTime joinAt;
	
	public int getMemId() {return memId;}
	public void setMemId(int memId) {this.memId = memId;}
	public String getLoginId() {return loginId;}
	public void setLoginId(String loginId) {this.loginId = loginId;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getNickname() {return nickname;}
	public void setNickname(String nickname) {this.nickname = nickname;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}	
	public int getAddrId() {		return addrId;	}
	public void setAddrId(int addrId) {		this.addrId = addrId;	}
	public String getPhoneNum() {return phoneNum;}
	public void setPhoneNum(String phoneNum) {this.phoneNum = phoneNum;}
	public LocalDateTime getJoinAt() {return joinAt;}
	public void setJoinAt(LocalDateTime joinAt) {this.joinAt = joinAt;}
	
	// 영문+숫자, 6~20자
    public boolean isLoginIdValid() {
        return loginId != null && loginId.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$");
    }

    // 영문+숫자, 8~20자
    public boolean isPasswordValid() {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$");
    }

    // 한글만 사용, 2~17자
    public boolean isNameValid() {
        return name != null && name.matches("^[가-힣]{2,17}$");
    }

    // 한글/영문/숫자, 2~10자
    public boolean isNicknameValid() {
        return nickname != null && nickname.matches("^[가-힣a-zA-Z0-9]{2,10}$");
    }

    // 전체 검사
    public boolean isAllValid() {
        return isLoginIdValid() && isPasswordValid() && isNameValid() && isNicknameValid();
    }
	
	public String toLogString() {
	    return "User{memId=" + memId + ", loginId='" + loginId + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + ", nickname='" + nickname + '\'' + ", email='" + email + '\'' + ", addr_id=" + addrId + ", phoneNum='" + phoneNum + '\'' + ", joinAt=" + (joinAt != null ? joinAt : "null") + '}';
	}
}