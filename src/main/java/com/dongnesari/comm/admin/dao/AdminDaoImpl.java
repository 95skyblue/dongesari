package com.dongnesari.comm.admin.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import com.dongnesari.comm.admin.vo.AdminVO;
import com.dongnesari.comm.util.MybatisUtil;

public class AdminDaoImpl implements IAdminDao {
	
	//싱글톤패턴 - 자기 자신의 객체 - service에서  공유 해서 사용  
	//싱글톤 패턴 적용: dao 객체를 static으로 하나만 생성
	private static IAdminDao  dao;
	
	//외부에서 객체를 얻기 위한 정적 메서드 (싱글톤 getter)
	public static IAdminDao  getDao() {
		
		if(dao == null) dao = new AdminDaoImpl(); // 최초 1회만 생성
		
		return dao;
	}
	
	// 생성자 private으로 막아서 외부에서 new 못하게 함 (싱글톤)
	private AdminDaoImpl() {  }
			
	
	/**
	 * 전체 관리자 조회
	 */
	@Override
	public List<AdminVO> getAllAdmins() {
		SqlSession   session = MybatisUtil.getInstance(); // MyBatis 세션 열기
		List<AdminVO>  list = null;
		
		try {
			
			list = session.selectList("admin.getAllAdmins"); // Mapper의 SQL 실행
			 
		} catch (Exception e) {
			  e.printStackTrace();// TODO: handle exception
		}finally {
			if(session != null) {
				
				session.commit();
				session.close();
			}
		}
		
		return list;
	}
	
	/**
	 * 관리자 상세 조회 (ID)로
	 */
	@Override
	public AdminVO getAdminById(String admId) {
		AdminVO mv = null;
		
		try (SqlSession session = MybatisUtil.getInstance();) {
			
			mv = session.selectOne("admin.getAdminById", admId);
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 관리자 로그인 (Login ID + Password)
	 */
	@Override
	public AdminVO loginAdmin(AdminVO vo) {
		SqlSession   session = MybatisUtil.getInstance();
		
		AdminVO  mvo = null;
		
		try {
			
			mvo  = session.selectOne("admin.loginAdmin", vo); // 로그인 쿼리 실행
			
		} catch (Exception e) {
			  e.printStackTrace();// TODO: handle exception
		}finally {
			if(session != null) {
				
				session.commit();
				session.close();
			}
		}
		
		return mvo;
	}
	
	/**
	 * 관리자 등록
	 */
	@Override
	public int insertAdmin(AdminVO vo) {
		SqlSession   session = MybatisUtil.getInstance();
		
		int res = 0;
		
		try {
			
			res  = session.insert("admin.insertAdmin",vo);
			
		} catch (Exception e) {
			  e.printStackTrace();// TODO: handle exception
		}finally {
			if(session != null) {
				
				session.commit();
				session.close();
			}
		}
		
		return res;	
	}
	
	/**
	 * 관리자 ID 중복 체크
	 */
	@Override
	public String checkLoginId(String loginId) {
		SqlSession   session = MybatisUtil.getInstance();
		
		String res = null;
		
		try {
			
			res  = session.selectOne("admin.checkLoginId",loginId); // 해당 ID 존재 여부 확인
			
		} catch (Exception e) {
			  e.printStackTrace();// TODO: handle exception
		}finally {
			if(session != null) {
				
				session.commit();
				session.close();
			}
		}
		
		return res;
	}
	
	/**
	 * 관리자 삭제
	 */
	@Override
	public int deleteAdmin(String admId) {
		SqlSession   session = MybatisUtil.getInstance();
		
		int res = 0;
		
		try {
			
			res  = session.delete("admin.deleteAdmin",admId);
			
		} catch (Exception e) {
			  e.printStackTrace();// TODO: handle exception
		}finally {
			if(session != null) {
				
				session.commit();
				session.close();
			}
		}
		
		return res;	
	}
}
