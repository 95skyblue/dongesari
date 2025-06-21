package com.dongnesari.comm.board.dao;

import java.util.List;

import com.dongnesari.comm.board.vo.PostVO;

public interface IPostDAO {
	
	//글쓰기 insertPost
	public int insertPost(PostVO vo);
	
	//글 상세보기
	PostVO getPostDetail(Integer postId);
	
	//글 리스트
	List<PostVO> getAllPosts();
	
	//글 삭제
	public Integer deletePost(Integer postId);

}
