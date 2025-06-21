package com.dongnesari.comm.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dongnesari.comm.admin.service.AdminServiceImpl;
import com.dongnesari.comm.admin.service.IAdminService;
import com.dongnesari.comm.admin.service.IMemberService;
import com.dongnesari.comm.admin.service.MemberServiceImpl;
import com.dongnesari.comm.admin.vo.AdminVO;
import com.dongnesari.comm.admin.vo.MemberVO;
import com.dongnesari.comm.util.MybatisUtil;
import com.google.gson.Gson;

/**
 * Servlet implementation class AdminList
 */
@WebServlet("/AdminListServlet.do")
public class AdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	// service 객체 추가 (싱글톤 방식 사용 시)
	private IAdminService service;
	
	
	@Override
	public void init() throws ServletException {
		service = AdminServiceImpl.getService();
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    SqlSession sql = MybatisUtil.getInstance();
	    List<AdminVO> adminList  = sql.selectList("admin.getAllAdmins");
	    System.out.println("adminList: " + adminList);
	    // null 제거 (🚨 이거 꼭 필요!)
	    adminList.removeIf(member -> member == null);

	    // JSON으로 바로 응답
	    response.setContentType("application/json;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    Gson gson = new Gson();
	    String result = gson.toJson(adminList);
	    
	    System.out.println("gson: " + gson);
	    out.print(result);
	    out.flush();
	}

}
