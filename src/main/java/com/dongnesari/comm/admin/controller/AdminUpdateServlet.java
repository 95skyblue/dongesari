package com.dongnesari.comm.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dongnesari.comm.admin.service.AdminServiceImpl;
import com.dongnesari.comm.admin.service.IAdminService;
import com.dongnesari.comm.admin.vo.AdminVO;

/**
 * Servlet implementation class AdminUpdateServlet
 */
@WebServlet("/AdminUpdateServlet.do")
public class AdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private IAdminService service = AdminServiceImpl.getService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 	String loginId = request.getParameter("admLoginid");
		    String pw = request.getParameter("admPw");
		    String name = request.getParameter("admName");
		    String rsdntNum = request.getParameter("rsdntNum");

		    AdminVO vo = new AdminVO(loginId, pw, name,rsdntNum);

		    IAdminService service = AdminServiceImpl.getService();
		    int res = service.updateAdmin(vo);

		    if (res > 0) {
		      response.sendRedirect("admin/memberlist.jsp"); // 또는 상세페이지로 redirect
		    } else {
		      response.getWriter().print("수정 실패");
		    }
		  }

}
