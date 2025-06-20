package com.dongnesari.comm.board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.dongnesari.comm.board.service.IPostService;
import com.dongnesari.comm.board.service.PostServiceImpl;
import com.dongnesari.comm.board.vo.PostVO;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig
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
		
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json;charset=UTF-8");

	    // 텍스트 파라미터
	    String postTitle = request.getParameter("postTitle");
	    String content = request.getParameter("content");
	    String memId = request.getParameter("memId");
	    int categoryId = Integer.parseInt(request.getParameter("categoryId"));

	    System.out.println("제목: " + postTitle);
	    System.out.println("내용: " + content);
	    System.out.println("작성자: " + memId);
	    System.out.println("카테고리: " + categoryId);

	    // VO에 담기
	    PostVO vo = new PostVO();
	    vo.setPostTitle(postTitle);
	    vo.setContent(content);
	    vo.setMemId(memId);
	    vo.setCategoryId(categoryId);

	    // service 호출
	    IPostService service = PostServiceImpl.getService();
	    int result = service.insertPost(vo);

	    System.out.println("insert 결과: " + result);

	    // JSON 응답
	    PrintWriter out = response.getWriter();
	    if (result > 0) {
	        out.print("{\"result\":\"글 등록 성공!\"}");
	    } else {
	        out.print("{\"result\":\"글 등록 실패ㅠㅠ\"}");
	    }
	    out.flush();
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String action = request.getPathInfo();
		 
		 if("/write".equals(action)) {
			 handleWrite(request,response);

		 }else {
			 response.sendError(HttpServletResponse.SC_NOT_FOUND);
		 }
	}
	
	
	
}