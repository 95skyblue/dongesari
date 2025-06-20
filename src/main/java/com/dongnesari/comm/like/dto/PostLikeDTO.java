package com.dongnesari.comm.like.dto;

public class PostLikeDTO {

/**
 *  사용자가 좋아요 요청을 보낼 때 전달되는 데이터 객체
 *  postId: 어떤 게시글에
 *  memId: 어떤 이용자가
 */
	
	private int postId; // 게시글 ID
	private int memId; // 사용자 ID
	
	// 기본 생성자
	public PostLikeDTO() {}
	
	public PostLikeDTO(int postID, int memId) {
		this.postId = postId;
		this.memId = memId;
	}
	
	// getter/setter
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	
}
