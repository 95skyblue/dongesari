package com.dongnesari.comm.member.controller;

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 2L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String method = request.getMethod();
	    String action = request.getPathInfo();

	    // 지원하는 요청 경로 목록
	    Set<String> validPaths = Set.of("/login", "/login.do");

	    if (!validPaths.contains(action)) { // 이놈이 지원하는 경로가 아니면
	        response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 오류 보내기
	        return;
	    }
	    
	    // 나중에 스위치문으로 바꾸면 될듯
	    
	    if ("/login".equals(action)) {
	        if ("GET".equals(method)) {
	            handleLogin(request, response);
	        } else { // URL은 맞는데 메서드가 잘못된 경우
	            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 405 오류 (지원하는 방식이 아님)
	        }
	        return;
	    }

	    if ("/login.do".equals(action)) {
	        if ("POST".equals(method)) {
	            handleLoginDo(request, response);
	        } else {
	            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	        }
	        return;
	    }
	}
	
	// /member/login 으로 요청하면 실행하는 메서드
	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp").forward(request, response);
	}
	
	// /member/login.do 로 ajax 요청하면 실행되는 메서드
	private void handleLoginDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}