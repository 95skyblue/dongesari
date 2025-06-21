package com.dongnesari.comm.board.service;

import java.util.List;

import com.dongnesari.comm.board.vo.PostVO;

public interface IPostService {
	
	//글쓰기 insertPost
	public Integer insertPost(PostVO vo);
	
	//글 상세 보기 postDetail
	PostVO getPostDetail(Integer postId);

	//글 리스트
	List<PostVO> getAllPosts();
	
	//글 삭제
	public Integer deletePost(Integer postId);
	
	//글 수정
	public Integer updatePost(PostVO vo);
	
}
