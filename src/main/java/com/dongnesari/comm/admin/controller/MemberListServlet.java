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

import com.dongnesari.comm.admin.service.IMemberService;
import com.dongnesari.comm.admin.service.MemberServiceImpl;
import com.dongnesari.comm.admin.vo.MemberVO;
import com.dongnesari.comm.util.MybatisUtil;
import com.google.gson.Gson;


/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/MemberListServlet.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// service 객체 추가 (싱글톤 방식 사용 시)
	private IMemberService service;

	@Override
	public void init() throws ServletException {
		service = MemberServiceImpl.getService();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    SqlSession sql = MybatisUtil.getInstance();
	    List<MemberVO> list = sql.selectList("member.getAllMember");
	    
	    // null 제거 (🚨 이거 꼭 필요!)
	    list.removeIf(member -> member == null);

	    // JSON으로 바로 응답
	    response.setContentType("application/json;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    Gson gson = new Gson();
	    String result = gson.toJson(list);
	    out.print(result);
	    out.flush();
	}

}
