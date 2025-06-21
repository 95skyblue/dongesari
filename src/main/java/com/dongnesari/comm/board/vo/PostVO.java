package com.dongnesari.comm.board.vo;

import java.sql.Date;

public class PostVO {
	
	private Integer postId;          //글고유번호
	private String memId;            //글쓴회원번호
	private Integer categoryId;      //카테고리
	private String postTitle;        //제목
	private String content;          //내용
	private Integer picBundleId;     //사진묶음ID - 시퀀스
	private Integer coordId;         //좌표ID
	private String videoLink;        //첨부영상주소
	private Date createAt;           //작성일시
	private String postStat;         //글상태
	private Integer versionNow;      //현재글버전
	
	private String nickname;         //닉네임
	
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPicBundleId() {
		return picBundleId;
	}
	public void setPicBundleId(Integer picBundleId) {
		this.picBundleId = picBundleId;
	}
	public Integer getCoordId() {
		return coordId;
	}
	public void setCoordId(Integer coordId) {
		this.coordId = coordId;
	}
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public String getPostStat() {
		return postStat;
	}
	public void setPostStat(String postStat) {
		this.postStat = postStat;
	}
	public Integer getVersionNow() {
		return versionNow;
	}
	public void setVersionNow(Integer versionNow) {
		this.versionNow = versionNow;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getCategoryName() {
		switch (categoryId) {
		case 1:
			return "공지사항";
		case 2:
			return "시설물 신고";
		case 3:
			return "칭찬";
		case 4:
			return "동네생활";
		default:
			return "알 수 없음";
		}
	}
}