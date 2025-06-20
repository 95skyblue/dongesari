package com.dongnesari.comm.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dongnesari.comm.admin.service.IMemberService;
import com.dongnesari.comm.admin.service.MemberServiceImpl;
import com.dongnesari.comm.admin.vo.MemberVO;

/**
 * Servlet implementation class MemberDetailServlet
 */
@WebServlet("/MemberDetailServlet.do")
public class MemberDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private IMemberService service;

  @Override
  public void init() throws ServletException {
    service = MemberServiceImpl.getService();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String memId = request.getParameter("memId");
    
    System.out.println("넘어온 memId: " + memId); // 로그 찍어봐
    
    MemberVO vo = service.getMemberDetail(memId);
    
    //회원 상세 불러올때 값 조회하기
    System.out.println("조회된 member: " + vo); // 이 로그에서 null이면 문제! 

    request.setAttribute("member", vo);
    request.getRequestDispatcher("/admin/memberdetail.jsp").forward(request, response);
  }
}
