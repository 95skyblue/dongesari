package com.dongnesari.comm.board.dao;

import com.dongnesari.comm.board.vo.PostVO;

public interface IPostDAO {
	
	//글쓰기 insertPost
	public int insertPost(PostVO vo);
	
	//글 상세보기
	PostVO getPostDetail(Integer postId);
	
	

}
