package com.dongnesari.comm.like.vo;

import java.sql.Timestamp;

public class PostLikeVO {
	
/**
 * DB와 직접 연결되는 데이터 객체 (VO = Value Object)
 * 실제로 INSERT, DELETE 쿼리에서 사용됩니다.
 */

	private int post_id;	// 게시글 ID
	private int mem_id;		// 사용자 ID
	private Timestamp create_at;	// 좋아요 누른 시간
	
	// getter / setter
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	
	


}
