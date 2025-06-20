package com.dongnesari.comm.like.service;

import org.apache.ibatis.session.SqlSession;

import com.dongnesari.comm.like.dao.PostLikeMapper;
import com.dongnesari.comm.like.dto.PostLikeDTO;
import com.dongnesari.comm.like.vo.PostLikeVO;

import config.MybatisUtil;


public class PostLikeServiceImpl implements PostLikeService {
	
	private final SqlSession session;
	private final PostLikeMapper mapper;
	
	public PostLikeServiceImpl(SqlSession session) {
		this.session = session;
		this.mapper = session.getMapper(PostLikeMapper.class);
	}

/**
* 비즈니스 로직 실제 구현 클래스입니다.
* 좋아요 추가, 취소 기능을 처리합니다.
*/

	@Override
	public boolean addLike(PostLikeDTO dto) {

			PostLikeVO vo = new PostLikeVO();
			vo.setPost_id(dto.getPostId());
			vo.setMem_id(dto.getMemId());
			
			int result = mapper.insertPostLike(vo);	// 좋아요 추가 SQL 실행
			session.commit();	// DB에 반영 (수동커밋이용함)
			
			return result > 0;	// 성공 여부 반환
		}

	@Override
	public boolean cancelLike(PostLikeDTO dto) {
			
			PostLikeVO vo = new PostLikeVO();
			vo.setPost_id(dto.getPostId());
			vo.setMem_id(dto.getMemId());
			
			int result = mapper.deletePostLike(vo);	//좋아요 취소 SQL 실행
			session.commit();
			
			return result >0;
		}
	
	// 좋아요 눌렀는지 확인
	@Override
	public boolean isLiked(PostLikeDTO dto) {
		PostLikeVO vo = new PostLikeVO();
		vo.setPost_id(dto.getPostId());
		vo.setMem_id(dto.getMemId());
		return mapper.checkUserLiked(vo) > 0;
	}
	
	// 좋아요 수 조회
	@Override
	public int getLikeCount(int postId) {
		return mapper.countLikesByPost(postId);
	}
}
