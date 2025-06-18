package com.dongnesari.comm.board.controller;

import java.io.IOException;

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
//
//	private void handleWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 글쓰기 처리 로직
//		request.getRequestDispatcher("/WEB-INF/views/board/boardWrite.jsp").forward(request, response);
//	}
}