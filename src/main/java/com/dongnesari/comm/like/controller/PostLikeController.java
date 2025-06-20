package com.dongnesari.comm.like.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.dongnesari.comm.like.dto.PostLikeDTO;
import com.dongnesari.comm.like.service.PostLikeService;
import com.dongnesari.comm.like.service.PostLikeServiceImpl;
import com.google.gson.Gson;

import config.MybatisUtil;

/**
 * 비동기 요청으로 좋아요를 토글 처리하는 Controller
 * POST /post/like.do
 */
@WebServlet("/post/like.do")
public class PostLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostLikeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 요청 파라미터 추출 (post_id, mem_id)
		int postId = Integer.parseInt(request.getParameter("post_id"));
		int memId = Integer.parseInt(request.getParameter("mem_id"));
		
		// 2. DTO 생성
		PostLikeDTO dto = new PostLikeDTO();	// 객체 생성
		dto.setPostId(postId);					// postId 설정
		dto.setMemId(memId);					// memId 설정
		
		// 3. MyBatis 세션 시작
		try(SqlSession session = MybatisUtil.getSqlSession()){
			PostLikeService service = new PostLikeServiceImpl(session);
			
			// 4. 좋아요 눌렀는지 확인
			boolean alreadyLiked = service.isLiked(dto);
			
			// 5. 좋아요 토글 처리
			boolean result;
			if(alreadyLiked) {
				result = service.cancelLike(dto);
			}else {
				result = service.addLike(dto);
			}
			
			// 6. 현재 좋아요 수 조회
			int likeCount = service.getLikeCount(postId);
			
			// 7. 응답 객체 생성
			LikeResponse resObj = new LikeResponse(result, postId, likeCount, !alreadyLiked);
			
			// 8. JSON 응답 보내기
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(new Gson().toJson(resObj));
		}
	}
}

	// 내부 응답 DTO 클래스
	class LikeResponse{
		boolean success;
		int postId;
		int likeCount;
		boolean likeByMe;
		
		public LikeResponse(boolean success, int postId, int likeCount, boolean likeByMe) {
			this.success = success;
			this.postId = postId;
			this.likeCount = likeCount;
			this.likeByMe = likeByMe;
		}
	}
