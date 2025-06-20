package com.dongnesari.comm.admin.service;

import java.util.List;

import com.dongnesari.comm.admin.dao.AdminDaoImpl;
import com.dongnesari.comm.admin.dao.IAdminDao;
import com.dongnesari.comm.admin.vo.AdminVO;

public class AdminServiceImpl implements IAdminService{
	
	//싱글톤패턴 -자기자신의 객체 -  controller에서 공유 하여 사용
	private static IAdminService  service;
	
	public static IAdminService  getService() {
		
		if(service == null)  service = new AdminServiceImpl();
		return service;
	}
	
	//dao객체가 필요 
	private IAdminDao  dao;
	
	//생성자 
	public AdminServiceImpl() {
		dao = AdminDaoImpl.getDao();
	}
	
	@Override
	public List<AdminVO> getAllAdmins() {
		// TODO Auto-generated method stub
		return dao.getAllAdmins();
	}

	@Override
	public AdminVO getAdminById(String adm_id) {
		// TODO Auto-generated method stub
		return dao.getAdminById(adm_id);
	}

	@Override
	public AdminVO loginAdmin(AdminVO vo) {
		// TODO Auto-generated method stub
		return dao.loginAdmin(vo);
	}

	@Override
	public int insertAdmin(AdminVO vo) {
		// TODO Auto-generated method stub
		return dao.insertAdmin(vo);
	}

	@Override
	public String checkLoginId(String loginId) {
		// TODO Auto-generated method stub
		return dao.checkLoginId(loginId);
	}

	@Override
	public int deleteAdmin(String adm_id) {
		// TODO Auto-generated method stub
		return dao.deleteAdmin(adm_id);
	}

}
