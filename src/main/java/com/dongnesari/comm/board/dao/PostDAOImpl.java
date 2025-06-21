package com.dongnesari.comm.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dongnesari.comm.board.vo.PostVO;

import config.MybatisUtil;

public class PostDAOImpl implements IPostDAO {
	
	private static IPostDAO dao;
	private SqlSessionFactory factory;
	
	public static IPostDAO getDao() {
		if(dao == null) dao = new PostDAOImpl();
		return dao;
	}
	
	//생성자
	public PostDAOImpl() {
		//
		factory = MybatisUtil.getSqlSessionFactory();
	}
	
	@Override
	public int insertPost(PostVO vo) {
		SqlSession session = MybatisUtil.getInstance();
		int res = 0;
		
		try {
			res = session.insert("post.insertPost", vo);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session != null) {
				session.commit();
				session.close();
			}
		}
		
		return res;
	}

	@Override
	public PostVO getPostDetail(Integer postId) {
		
		try(SqlSession session = factory.openSession()){
			return session.selectOne("post.postDetail", postId);
		}

	}

}
