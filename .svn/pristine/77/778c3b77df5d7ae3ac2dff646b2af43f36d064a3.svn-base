<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-serializejson@3.2.1/jquery.serializejson.min.js"></script>
<script>
$(function() {
	$('#error-msg').css('visibility', 'hidden');
	
	// 스크립트 익스터널 방식으로 분리할 경우 jsp에서 제공하는 request.contextPath()를 못 쓰므로
	// jsp는 바디의 data-context에 미리 저장해두고, 스크립트는 그 속성을 뽑아서 씀.
	const contextPath = $('body').data('context');
	
	$('#login-btn').on('click', function(e) { // 로그인 버튼에 이벤트리스너 등록
		e.preventDefault(); // 기본 form 전송 막기
		
		// 로그인 폼을 찾아서, serializejson 라이브러리를 사용해
		// 각 폼의 name 속성을 가진 모든 input/select/textarea 요소를 읽고
		// name을 key, 값을 value로 json 객체 생성해 줌.
		// 참고1 : checkbox, radio도 적절하게 처리해 주고,
		// 참고2 : name="user[info][email]" 와 같은 중첩 구조도 지원한다고 함.
		const jsonData = $('#login-form').serializeJSON();
		
		$.ajax({ // jQuery로 ajax 요청하기
            url: contextPath + '/test/login.do', // 요청할 url 설정
            method: 'POST', // 요청 방식 설정
            contentType: 'application/json', // 보내는 데이터 타입 설정
            data: JSON.stringify(jsonData), // 아까 만든 json 객체를 문자열로 변경.
            dataType: 'json', // 내가 받을 데이터는 json 형식이라고 선언.
            // 컨트롤러 쪽에서 response.setContentType("application/json"); 해두면 없어도 됨.
            // 하지만 FM대로 하려면 양쪽에서 선언하는게 맞는듯.
            
            success: function(response) { // 응답을 받으면 success: 뒤쪽을 실행.
                if (response.status === 'success') { // 여기서부턴 서버측에서 보낸 내용에 따라 원하는 동작 수행
                    window.location.href = contextPath + '/board/main';
                } else if (response.status === 'login_fail') {
                    $('#user-id').addClass('is-invalid');
                    $('#user-pw').addClass('is-invalid');
                    $('#error-msg').text('로그인 실패. 아이디와 비밀번호를 확인하세요.')
                    $('#error-msg').css('visibility', '');
                } else {
                    alert('알 수 없는 오류가 발생했습니다.');
                }
            },
            error: function(xhr, status, error) {
                alert('요청 처리 중 오류가 발생했습니다.');
                console.log('xhr : ', xhr);
                console.log('status : ', status);
                console.error(error);
            }
        });
	});
});

</script>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>

</head>
<body data-context="<%=request.getContextPath()%>">

<main>

	<section class="mt-4 mx-auto" style="width: 400px;">
		<form class="row g-3" id="login-form">
			<div class="form-floating">
				<input type="text" class="form-control" id="user-id" name="memId" placeholder=".">
				<label for="user-id">아이디</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="user-pw" name="memPw" placeholder=".">
				<label for="user-pw">비밀번호</label>
			</div>
			<div id="error-msg" class="form-text">아이디와 비밀번호를 입력하세요.</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-3" id="login-btn">로그인</button>
			</div>
		</form>
	</section>

</main>

<footer> </footer>

</body>
</html>