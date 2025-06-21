<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">

<style>
#register-btn { width: 200px; padding: 12px 24px; font-size: 18px; border: none; border-radius: 12px; color: white; background-color: #444; cursor: pointer; transition: box-shadow 0.3s;}
.shiny { background: linear-gradient(235deg, red, orange, yellow, green, blue, indigo, violet, red); background-size: 600% 100%; background-position: 0% 50%; animation: rainbow 5s linear infinite; }
@keyframes rainbow { 0% { background-position: 0% 50%; } 100% { background-position: 100% 50%; } }
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-serializejson@3.2.1/jquery.serializejson.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/resources/js/test/registerForm.js"></script>



</head>
<body data-context="<%=request.getContextPath()%>">
<main>
<section class="mt-4 mx-auto" style="width: 600px;">
	<form class="row g-3" id="login-form">

		<!-- 아이디 -->
		<div class="row align-items-center mt-2">
			<label for="user-id" class="col-form-label col-2">ID</label>
			<div class="col-5">
				<input type="text" class="form-control" id="user-id" name="loginId" aria-describedby="id-msg">
			</div>
			<div class="offset-2">
				<div id="id-msg" class="form-text">영문/숫자 혼합 6~20글자</div>
			</div>
		</div>

		<!-- 비밀번호 -->
		<div class="row align-items-center mt-2">
			<label for="user-pw" class="col-form-label col-2">PW</label>
			<div class="col-5">
				<input type="password" class="form-control" id="user-pw" name="password" aria-describedby="pw-msg">
			</div>
			<div class="offset-2">
				<div id="pw-msg" class="form-text">영문/숫자 혼합 8~20글자</div>
			</div>
		</div>

		<!-- 비밀번호 확인 -->
		<div class="row align-items-center mt-2">
			<label for="user-pw2" class="col-form-label col-2">PW 확인</label>
			<div class="col-5">
				<input type="password" class="form-control" id="user-pw2" aria-describedby="pw2-msg">
			</div>
			<div class="offset-2">
				<div id="pw2-msg" class="form-text">비밀번호를 한번 더 입력하세요.</div>
			</div>
		</div>

		<!-- 이름 -->
		<div class="row align-items-center mt-2">
			<label for="user-name" class="col-form-label col-2">이름</label>
			<div class="col-5">
				<input type="text" class="form-control" id="user-name" name="name">
			</div>
			<div class="offset-2">
				<div id="name-msg" class="form-text">실명을 입력해 주세요.</div>
			</div>
		</div>

		<!-- 닉네임 -->
		<div class="row align-items-center mt-2">
			<label for="nickname" class="col-form-label col-2">닉네임</label>
			<div class="col-5">
				<input type="text" class="form-control" id="nickname" name="nickname" aria-describedby="nick-msg">
			</div>
			<div class="offset-2">
				<div id="nick-msg" class="form-text">사용하실 닉네임을 입력하세요.</div>
			</div>
		</div>

		<!-- 우편번호 -->
		<div class="row align-items-center mt-2">
			<label for="zipcode" class="col-form-label col-2">우편번호</label>
			<div class="col-3">
				<input type="text" class="form-control" id="zipcode" name="zipcode">
			</div>
			<div class="col-3">
				<button type="button" class="btn btn-primary" id="zipcode-search">우편번호검색</button>
			</div>
		</div>

		<!-- 기본주소 -->
		<div class="row align-items-center mt-2">
			<label for="base-addr" class="col-form-label col-2">기본주소</label>
			<div class="col-6">
				<input type="text" class="form-control" id="base-addr" name="baseAddr">
			</div>
		</div>

		<!-- 상세주소 -->
		<div class="row align-items-center mt-2">
			<label for="plus-addr" class="col-form-label col-2">상세주소</label>
			<div class="col-6">
				<input type="text" class="form-control" id="plus-addr" name="plusAddr">
			</div>
		</div>

		<!-- 이메일 -->
		<div class="row align-items-center mt-2">
			<label for="email" class="col-form-label col-2">이메일</label>
			<div class="col-5">
				<input type="text" class="form-control" id="email" name="email">
			</div>
			<div class="col-3">
				<button type="button" class="btn btn-primary w-100" id="email-btn" disabled>인증번호전송</button>
			</div>
			<div class="spinner-border text-success visually-hidden" role="status" id="email-spin">
				<span class="visually-hidden">Loading...</span>
			</div>
			<div class="offset-2">
				<div id="email-msg" class="form-text">이메일 주소를 입력하세요.</div>
			</div>
		</div>
		 
		<!-- 이메일 인증 -->
		<div class="row align-items-center mt-2 collapse" id="veri-code-container">
			<label for="veri-code" class="col-form-label col-2">인증코드</label>
			<div class="col-4">
				<input type="text" class="form-control" id="veri-code">
			</div>
			<div class="text-danger col-2" id="timer-box">05:00</div>
			<div class="col-2">
				<button type="button" class="btn btn-primary w-100" id="code-btn">인증</button>
			</div>
			
			<div class="offset-2">
				<div id="veri-msg" class="form-text"></div>
			</div>
		</div>
		
		<button type="button" id="register-btn" disabled>회원가입</button>

	</form>
</section>
</main>

<footer></footer>
</body>
</html>