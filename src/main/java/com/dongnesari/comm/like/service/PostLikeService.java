package com.dongnesari.comm.like.service;

import com.dongnesari.comm.like.dto.PostLikeDTO;

public interface PostLikeService {

/**
 * 비즈니스 로직 정의 - 인터페이스
 * 좋아요 추가/취소를 어떻게 처리할지 선언만 함
 */
	boolean addLike(PostLikeDTO dto);		// 좋아요 추가
	boolean cancelLike(PostLikeDTO dto);	// 좋아요 취소
	
	boolean isLiked(PostLikeDTO dto);		// 이용자 좋아요 중복 여부 확인
	int getLikeCount(int postId);			// 해당 게시글의 총 좋아요 수 조회
}
