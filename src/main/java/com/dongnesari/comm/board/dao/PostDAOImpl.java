package com.dongnesari.comm.board.dao;

import java.util.ArrayList;
import java.util.List;
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
		factory = MybatisUtil.getSqlSessionFactory();
	}
	
	@Override
	public int insertPost(PostVO vo) {
		try(SqlSession session = factory.openSession()){
			int res = session.insert("post.insertPost", vo);
			session.commit(); // insert는 commit 필요
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 글 상세보기
	 */
	@Override
	public PostVO getPostDetail(Integer postId) {
		try(SqlSession session = factory.openSession()){
			return session.selectOne("post.postDetail", postId);
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 글 리스트 조회
	 */
	@Override
	public List<PostVO> getAllPosts() {
		try(SqlSession session = factory.openSession()){
			return session.selectList("post.getAllPosts");
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<>(); // 예외 발생시 빈 리스트 반환
		}
	}
}