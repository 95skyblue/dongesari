package com.dongnesari.comm.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


import com.google.gson.Gson;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/MemberListServlet.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 검색 파라미터 받기 (필요하면 처리)
		String keyword = request.getParameter("keyword");
		String memberType = request.getParameter("memberType");

		// 2. 서비스 호출 (파라미터 전달)
		//List<MemberVO> memberList = service.selectMemberList(keyword, memberType);

		// 3. 응답 JSON 변환
		response.setContentType("application/json;charset=UTF-8");
		Gson gson = new Gson();
		//String json = gson.toJson(memberList);
		//response.getWriter().write(json);

	}
}
