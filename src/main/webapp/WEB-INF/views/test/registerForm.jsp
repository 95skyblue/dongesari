<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-serializejson@3.2.1/jquery.serializejson.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/test/registerForm.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>

</head>
<body data-context="<%=request.getContextPath()%>">
<main>
<section class="mt-4 mx-auto" style="width: 600px;">
	<form class="row g-3" id="login-form">

		<!-- 아이디 -->
		<div class="row align-items-center mt-2">
			<label for="user-id" class="col-form-label col-2">ID</label>
			<div class="col-5">
				<input type="text" class="form-control" id="user-id" name="memId" aria-describedby="id-msg">
			</div>
			<div class="offset-2">
				<div id="id-msg" class="form-text">영문/숫자 혼합 6~20글자</div>
			</div>
		</div>

		<!-- 비밀번호 -->
		<div class="row align-items-center mt-2">
			<label for="user-pw" class="col-form-label col-2">PW</label>
			<div class="col-5">
				<input type="password" class="form-control" id="user-pw" name="memPw" aria-describedby="pw-msg">
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
		</div>

		<!-- 닉네임 -->
		<div class="row align-items-center mt-2">
			<label for="nickname" class="col-form-label col-2">닉네임</label>
			<div class="col-5">
				<input type="text" class="form-control" id="nickname" name="nickname" aria-describedby="nick-msg">
			</div>
			<div class="offset-2">
				<div id="nick-msg" class="form-text">이미 사용중인 닉네임</div>
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
				<input type="text" class="form-control" id="base-addr" name="base_addr" readonly>
			</div>
		</div>

		<!-- 상세주소 -->
		<div class="row align-items-center mt-2">
			<label for="plus-addr" class="col-form-label col-2">상세주소</label>
			<div class="col-6">
				<input type="text" class="form-control" id="plus-addr" name="plus_addr">
			</div>
		</div>

		<!-- 이메일 -->
		<div class="row align-items-center mt-2">
			<label for="email" class="col-form-label col-2">이메일</label>
			<div class="col-6">
				<input type="text" class="form-control" id="email" name="email">
			</div>
			<div class="col-3">
				<button type="button" class="btn btn-primary w-100" id="email-identify">이메일인증</button>
			</div>
		</div>

	</form>
</section>
</main>

<footer></footer>
</body>
</html>