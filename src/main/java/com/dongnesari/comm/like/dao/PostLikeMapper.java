package com.dongnesari.comm.like.dao;

import com.dongnesari.comm.like.vo.PostLikeVO;

public interface PostLikeMapper {

/**
 * MyBatis XML과 연결되는 인터페이스입니다.
 * SQL 쿼리를 실행할 메서드 이름과 매개변수를 정의
 */
	int insertPostLike(PostLikeVO vo);	// 좋아요 추가
	int deletePostLike(PostLikeVO vo);	// 좋아요 취소
	int checkUserLiked(PostLikeVO vo);	// 좋아요 여부 확인
	int countLikesByPost(int postId);	// 좋아요 개수
}
