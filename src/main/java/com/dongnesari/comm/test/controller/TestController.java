package com.dongnesari.comm.test.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.dongnesari.comm.test.dao.TestDAO;
import com.dongnesari.comm.test.dto.LoginDTO;
import com.dongnesari.comm.test.dto.SessionDTO;
import com.dongnesari.comm.test.service.TestService;
import com.dongnesari.comm.test.vo.EmailAuthVO;
import com.dongnesari.comm.util.ServletUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/test/*")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 3L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String method = request.getMethod();
	    String action = request.getPathInfo();

	    // 지원하는 요청 경로 목록
	    Set<String> validPaths = new HashSet<>();
	    validPaths.add("/login");
	    validPaths.add("/login.do");
	    validPaths.add("/register");
	    validPaths.add("/checkdupl.do");
	    validPaths.add("/checkduplnick.do");
	    validPaths.add("/sendcode.do");
	    validPaths.add("/checkemailcode.do");

	    if (!validPaths.contains(action)) { // 이놈이 지원하는 경로가 아니면
	        response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 오류 보내기
	        return;
	    }
	    
	    switch(method) {
	    case "GET" :
	    	switch(action) {
	    	case "/login" :
	    		handleLogin(request, response);
	    		break;
	    	case "/register" : 
	    		handleRegister(request, response);
	    		break;
	    	default : 
	    		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	    	}
	    	return;
	    	
	    case "POST" : 
	    	switch(action) {
	    	case "/login.do" :
	    		handleLoginDo(request, response);
	    		break;
	    	case "/checkdupl.do" :
	    		handleCheckDuplDo(request, response);
	    		break;
	    	case "/checkduplnick.do" :
	    		handleCheckDuplNickDo(request, response);
	    		break;
	    	case "/sendcode.do" :
	    		handleSendCodeDo(request, response);
	    		break;
	    	case "/checkemailcode.do" :
	    		handleCheckEmailCodeDo(request, response);
	    		break;
	    	default : 
	    		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	    	}
	    	return;
	    	
	    default : 
	    	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	    	return;
	    }
	}
	
	// /member/login 으로 요청하면 실행하는 메서드
	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/test/loginForm.jsp").forward(request, response);
	}
	
	private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/test/registerForm.jsp").forward(request, response);
	}
	
	// /member/login.do 로 ajax 요청하면 실행되는 메서드
	private void handleLoginDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 바디(JSON) 읽기
	    String jsonStr= ServletUtil.readRequestBody(request);
	    
	    System.out.println("나여 에이잭스");
	    System.out.println(jsonStr);
	    
	    Gson gson = new Gson();
	    LoginDTO dto = gson.fromJson(jsonStr, LoginDTO.class);
	    
	    TestDAO dao = TestDAO.getInstance();
	    
	    try {
	        SessionDTO sessionDTO = dao.login(dto); // 로그인 검증
	        HttpSession session = request.getSession();
	        session.setAttribute("sessionDTO", sessionDTO);

	        // 로그인 성공 응답 전송
	        Map<String, String> result = new HashMap<>();
	        result.put("status", "success");
	        
	        response.setContentType("application/json");
	        response.getWriter().write(gson.toJson(result));
	    } catch (Exception e) {
	    	e.printStackTrace();
	        // 로그인 실패 시
	        Map<String, String> result = new HashMap<>();
	        if ("loginFail".equals(e.getMessage())) {
	            result.put("status", "login_fail");
	        } else {
	            result.put("status", "error");
	        }
	        
	        response.setContentType("application/json");
	        response.getWriter().write(gson.toJson(result));
	    }
	}
	
	private void handleCheckDuplDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 바디(JSON) 읽기
	    String loginId = ServletUtil.readRequestBody(request);
	    
	    System.out.println("어 아이디 중복확인중이여");
	    System.out.println(loginId);
	    
	    TestService service = TestService.getInstance();
	    
	    boolean b = service.canIUseThisID(loginId);
	    
	    // 중복체크 결과 전송
        Map<String, String> result = new HashMap<>();
        result.put("status", b ? "ok" : "no");
	    
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(result));
	}
	
	private void handleCheckDuplNickDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 바디(JSON) 읽기
		String nickname = ServletUtil.readRequestBody(request);
		
		System.out.println("어 닉네임 중복확인중이여");
		System.out.println(nickname);
		
		TestService service = TestService.getInstance();
		
		boolean b = service.canIUseThisNick(nickname);
		
		// 중복체크 결과 전송
		Map<String, String> result = new HashMap<>();
		result.put("status", b ? "ok" : "no");
		
		Gson gson = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(result));
	}
	
	private void handleSendCodeDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 바디(JSON) 읽기
		String email = ServletUtil.readRequestBody(request);
		
		System.out.println("어 이메일 보낼준비중이여");
		System.out.println(email);
		
		TestService service = TestService.getInstance();
		
		String code = service.sendCodeToThisEmail(email);
		
		if(code != null) {
			String uuid = UUID.randomUUID().toString();
			HttpSession session = request.getSession();
			session.setAttribute("emailAuthToken", uuid);
			service.storeThisToEmailTable(email, code, uuid);
		}
		
		Map<String, String> result = new HashMap<>();
		result.put("status", (code != null) ? "ok" : "no");
		
		Gson gson = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(result));
	}
	
	private void handleCheckEmailCodeDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 바디(JSON) 읽기
		String jsonStr = ServletUtil.readRequestBody(request);
		
		System.out.println("어 보낸 이메일이랑 코드랑 세션 확인하는중이여");
		
		System.out.println(jsonStr);
		HttpSession session = request.getSession();
		String authToken = (String) session.getAttribute("emailAuthToken");
		
		Map<String, String> result = new HashMap<>();
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
	        public LocalDate deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) {
	            return LocalDate.parse(json.getAsString());
	        }
	    })
	    .create();
		
		if(authToken == null) {
			result.put("status", "no-auth-token");
		} else {
			EmailAuthVO vo = gson.fromJson(jsonStr, EmailAuthVO.class);
			vo.setAuthToken(authToken);
			
			TestService service = TestService.getInstance();
			
			boolean checkResult = service.checkThisEmailData(vo);
			
			if(checkResult) {
				result.put("status", "ok");
				session.removeAttribute("emailAuthToken");
				session.setAttribute("verifiedEmail", vo.getEmail());
			} else {
				result.put("status", "not-found");
			}
		}
		
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(result));
	}
}