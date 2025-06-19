package com.dongnesari.comm.admin.service;

import java.util.List;

import com.dongnesari.comm.admin.dao.IMemberDao;
import com.dongnesari.comm.admin.dao.MemberDaoImpl;
import com.dongnesari.comm.admin.vo.MemberVO;
import com.dongnesari.comm.admin.vo.ZipVO;

public class MemberServiceImpl implements IMemberService {
	//싱글톤패턴 -자기자신의 객체 -  controller에서 공유 하여 사용
		private static IMemberService  service;
		
		public static IMemberService  getService() {
			
			if(service == null)  service = new MemberServiceImpl();
			return service;
		}
		
		
		//dao객체가 필요 
		private IMemberDao  dao;
		
		//생성자 
		private MemberServiceImpl() {
			dao = MemberDaoImpl.getDao();
		}

		@Override
		public List<MemberVO> getAllMember() {
			// TODO Auto-generated method stub
			return dao.getAllMember();
		}

		@Override
		public MemberVO loginMember(MemberVO vo) {
			// TODO Auto-generated method stub
			return dao.loginMember(vo);
		}

		@Override
		public String idcheck(String id) {
			// TODO Auto-generated method stub
			return dao.idcheck(id);
		}

		@Override
		public List<ZipVO> selectByDong(String dong) {
			// TODO Auto-generated method stub
			return dao.selectByDong(dong);
		}

		@Override
		public int insertMember(MemberVO vo) {
			// TODO Auto-generated method stub
			return dao.insertMember(vo);
		}

}
