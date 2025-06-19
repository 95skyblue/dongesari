package com.dongnesari.comm.board.dao;

import org.apache.ibatis.session.SqlSession;

import com.dongnesari.comm.board.vo.PostVO;

public class PostDAOImpl implements IPostDAO {
	
	private static IPostDAO dao;
	
	public static IPostDAO getDao() {
		if(dao == null) dao = new PostDAOImpl();
		return dao;
	}
	
	//생성자
	public PostDAOImpl() {
		
	}
	
	@Override
	public int insertPost(PostVO vo) {
		//SqlSession session = MybatisUtil.getInstance();
		
		return 0;
	}

}
