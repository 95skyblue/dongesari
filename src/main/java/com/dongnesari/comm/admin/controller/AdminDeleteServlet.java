package com.dongnesari.comm.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dongnesari.comm.admin.service.AdminServiceImpl;
import com.dongnesari.comm.admin.service.IAdminService;

/**
 * Servlet implementation class AdminDeleteServlet
 */
@WebServlet("/AdminDeleteServlet.do")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private IAdminService service = AdminServiceImpl.getService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String admId = request.getParameter("admId");
		
		int cnt = service.deleteAdmin(admId);
		
		if (cnt > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/adminList.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/adminDetail.jsp?admId=" + admId + "&error=deleteFail");
		}
	}

}
