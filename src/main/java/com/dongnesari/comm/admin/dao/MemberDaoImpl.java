package com.dongnesari.comm.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dongnesari.comm.admin.vo.MemberVO;
import com.dongnesari.comm.admin.vo.ZipVO;
import com.dongnesari.comm.util.MybatisUtil;

public class MemberDaoImpl implements IMemberDao {


	//싱글톤패턴 - 자기 자신의 객체 - service에서  공유 해서 사용  
		//싱글톤 패턴 적용: dao 객체를 static으로 하나만 생성
		private static IMemberDao  dao;
		
		//외부에서 객체를 얻기 위한 정적 메서드 (싱글톤 getter)
		public static IMemberDao  getDao() {
			
			if(dao == null) dao = new MemberDaoImpl(); // 최초 1회만 생성
			
			return dao;
		}
		
		// 생성자 private으로 막아서 외부에서 new 못하게 함 (싱글톤)
		private MemberDaoImpl() {  }
		
		
		 /**
	     * 전체 회원 목록 조회
	     */
		@Override
		public List<MemberVO> getAllMember() {
			SqlSession   session = MybatisUtil.getInstance(); // MyBatis 세션 열기
			List<MemberVO>  list = null;
			
			try {
				
				list = session.selectList("member.getAllMember"); // Mapper의 SQL 실행
				 
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
	     * 로그인 확인 메서드 (ID/PW 확인)
	     */
		@Override
		public MemberVO loginMember(MemberVO vo) {
			SqlSession   session = MybatisUtil.getInstance();
			
			MemberVO  mvo = null;
			
			try {
				
				mvo  = session.selectOne("member.loninMember", vo); // 로그인 쿼리 실행
				
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
	     * 로그인 ID 중복 체크 (회원가입 시 사용)
	     */
		@Override
		public String idcheck(String id) {
			SqlSession   session = MybatisUtil.getInstance();
			
			String res = null;
			
			try {
				
				res  = session.selectOne("member.idcheck",id); // 해당 ID 존재 여부 확인
				
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
	     * 동(지번 주소)로 우편번호 조회
	     */
		@Override
		public List<ZipVO> selectByDong(String dong) {
			SqlSession   session = MybatisUtil.getInstance();
			
			List<ZipVO>  list = null;
			
			try {
				
				list  = session.selectList("zip.selectByDong",dong); // Mapper의 zip 쿼리 실행
				
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
	     * 회원 가입 처리 (INSERT)
	     */
		@Override
		public int insertMember(MemberVO vo) {
			SqlSession   session = MybatisUtil.getInstance();
			
			int res = 0;
			
			try {
				
				res  = session.insert("member.insertMember",vo);
				
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