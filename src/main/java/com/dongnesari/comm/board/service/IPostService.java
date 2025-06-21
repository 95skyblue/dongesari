package com.dongnesari.comm.board.service;

import com.dongnesari.comm.board.vo.PostVO;

public interface IPostService {
	
	//글쓰기 insertPost
	public int insertPost(PostVO vo);
	
	//글 상세 보기 postDetail
	PostVO getPostDetail(Integer postId);

}
