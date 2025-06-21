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
 * Servlet implementation class AdminDetailServlet
 */
@WebServlet("/AdminDetailServlet.do")
public class AdminDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private IAdminService service = AdminServiceImpl.getService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginId = request.getParameter("admId");
        System.out.println("받은 loginId = " + loginId); // 디버깅용

        AdminVO admin = service.getAdminById(loginId);
        System.out.println("조회된 관리자 = " + admin); // 디버깅용

        request.setAttribute("admin", admin);
        request.getRequestDispatcher("/admin/adminDetail.jsp").forward(request, response);
    }

}
