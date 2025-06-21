package com.dongnesari.comm.board.service;

import com.dongnesari.comm.board.dao.IPostDAO;
import com.dongnesari.comm.board.dao.PostDAOImpl;
import com.dongnesari.comm.board.vo.PostVO;

public class PostServiceImpl implements IPostService {
	
	private static IPostService service;
	
	//싱긅톤
	public static IPostService getService() {
		//처음 요청되면 new해서 만들고, 이미 있으면 기존 거 그대로 씀.
		if(service == null)  service = new PostServiceImpl();
		
		return service;
	}

	//dao객체 담을 변수 선언
	private IPostDAO dao;
	
	public PostServiceImpl() {
		dao = PostDAOImpl.getDao();
	}
	
	@Override
	public int insertPost(PostVO vo) { //vo에 게시글 제목, 내용, 작성자등 정보가 담김
		
		//insert성공하면 1, 실패 0
		return dao.insertPost(vo);
	}

	@Override
	public PostVO getPostDetail(Integer postId) {
		
		return dao.getPostDetail(postId);
	}

}
