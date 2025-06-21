package com.dongnesari.comm.board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
			case "/postList":  // 새로 추가
		        handleMain(request, response);  // 같은 로직 사용
		        break;	
			case "/write":
//				handleWrite(request, response);
			case "/detail":
				handleDetail(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	//글 삭제하기
	private void handleDelete(HttpServletRequest request, HttpServletResponse response) {
		//파라미터로 글 번호 받아오기
		Integer postId = Integer.parseInt(request.getParameter("postId"));
		
		//서비스 호출
		IPostService service = PostServiceImpl.getService();
		
	}
	

	//글 상세보기
	private void handleDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//디버깅로그
		System.out.println("postId 파라미터: " + request.getParameter("postId"));

		
		//파라미터로 글 번호 받아오기
		Integer postId = Integer.parseInt(request.getParameter("postId"));
		
		//서비스 호출
		IPostService service = PostServiceImpl.getService();
		PostVO vo = service.getPostDetail(postId);
		
		//객체 null 확인용 로그
		System.out.println("post 객체 확인 : " + vo);
		
		//jsp에 전달
		request.setAttribute("post", vo);
		
		//상세보기 페이지로 이동
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request, response);
		
	}

	
	//글 리스트
	private void handleMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("/WEB-INF/views/board/boardMain.jsp").forward(request, response);
		
		try {
			 System.out.println("handleMain 메서드 호출됨"); // 디버깅 로그
			 
			 IPostService service = PostServiceImpl.getService();
	         List<PostVO> list = service.getAllPosts();
	         
	         System.out.println("조회된 게시글 수: " + list.size()); // 디버깅 로그
	            
	            // 각 게시글 정보 출력 (디버깅용)
	            for(PostVO post : list) {
	                System.out.println("Post ID: " + post.getPostId() + 
	                                 ", Title: " + post.getPostTitle() + 
	                                 ", Author: " + post.getMemId());
	            }   
	                request.setAttribute("postList", list);
	        	    request.getRequestDispatcher("/post/postList.jsp").forward(request, response);    
			
		}catch (Exception ex){
			ex.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		
	}

	
	//글쓰기
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
			 
		 }else if("/delete".equals(action)){
			 handleDelete(request,response);
			 
		 }else if("/update".equals(action)){
			 
			
		 }else {
			 response.sendError(HttpServletResponse.SC_NOT_FOUND);
		 }
	}



	

}