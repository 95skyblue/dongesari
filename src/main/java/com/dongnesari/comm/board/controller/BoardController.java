package com.dongnesari.comm.board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.dongnesari.comm.board.service.IPostService;
import com.dongnesari.comm.board.service.PostServiceImpl;
import com.dongnesari.comm.board.vo.PostVO;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo(); // 예: /main, /write, /view 등
		switch (action) {
			case "/main":
				handleMain(request, response);
				break;
			case "/write":
//				handleWrite(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void handleMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardMain.jsp").forward(request, response);
	}

	
	private void handleWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * // 글쓰기 처리 로직
		 * request.getRequestDispatcher("/WEB-INF/views/board/boardWrite.jsp").forward(
		 * request, response);
		 */
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String action = request.getPathInfo();
		 
		 if("/write".equals(action)) {
			 request.setCharacterEncoding("UTF-8");
			 response.setContentType("application/json;charset=UTF-8");
			 
			 BufferedReader reader = request.getReader();
			 Gson gson = new Gson();
			 
			 PostVO vo = gson.fromJson(reader, PostVO.class); // JSON -> VO객체
			 
			 //테스트용 로그 찍기
			 System.out.println("title: " + vo.getPostTitle());
	         System.out.println("memId: " + vo.getMemId());
	         System.out.println("categoryId: " + vo.getCategoryId());
	         System.out.println("content: " + vo.getContent());
	         
	         //DB작업 없이 그냥 응답만 보내기
	         PrintWriter out = response.getWriter();
	         out.print("{\"result\":\"테스트 성공!\"}");
	         out.flush();
			 
		 }
	}
	
	
	
}