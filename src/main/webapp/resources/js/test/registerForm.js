
$(function() {
	// 스크립트 익스터널 방식으로 분리할 경우 jsp에서 제공하는 request.contextPath()를 못 쓰므로
	// jsp는 바디의 data-context에 미리 저장해두고, 스크립트는 그 속성을 뽑아서 씀.
	const contextPath = $('body').data('context');
	
	let isIdOK = false;
	let isPwOK = false;
	let isPwChecked = false;
	
	// 아이디 사용 가능한지 요청 날려서 코드 반환하는 함수
	const checkDupl = (loginId) => {
			return new Promise((resolve, reject) => {
				$.ajax({
					url: contextPath + '/test/checkdupl.do',
					method: 'POST',
					contentType: 'text/plain',
					data: loginId,
					dataType: 'json',
					success: function(response) {
						if (response.status === 'ok') resolve(1);
						else if (response.status === 'no') resolve(2);
						else resolve(3);
					},
					error: function(error) {
						console.error('AJAX 에러(아마도 응답없음)', error);
						reject(error);
					}
				});
			});
		};
	
	// 아이디 패턴 일치여부 확인 후 중복검사 해서 화면에 출력하는 함수
	const validateLoginID = async (loginId) => {
		if(!loginId) {
			$('#user-id').removeClass('is-invalid');
			$('#user-id').removeClass('is-valid');
			$('#id-msg').html('');
			return;
		}
		
		const errors = [];

		if (loginId.length < 6) errors.push("6자 이상이어야 합니다.");
		if (loginId.length > 20) errors.push("20자 이하여야 합니다.");
		if (!/[A-Za-z]/.test(loginId)) errors.push("영문자를 최소 1자 포함해야 합니다.");
		if (!/\d/.test(loginId)) errors.push("숫자를 최소 1자 포함해야 합니다.");
		if (/[^A-Za-z\d]/.test(loginId)) errors.push("영문자와 숫자 이외의 문자는 사용할 수 없습니다.");

		if (errors.length === 0) {
			const result = await checkDupl(loginId); // ✅ await 사용 가능
			switch (result) {
				case 1:
					$('#id-msg').css("color", "green");
					$('#id-msg').html("사용 가능한 아이디입니다.");
					$('#user-id').removeClass('is-invalid');
					$('#user-id').addClass('is-valid');
					isIdOK = true;
					return;
				case 2:
					errors.push("이미 존재하는 아이디입니다.");
					break;
				case 3:
					errors.push("** 서버 통신 오류 **");
					break;
				default:
					errors.push("** 서버 응답 없음 **");
			}
		}

		const errorMsg = errors.map(msg => msg + '<br>').join('');
		$('#user-id').removeClass('is-valid');
		$('#user-id').addClass('is-invalid');
		$('#id-msg').css("color", "red");
		$('#id-msg').html(errorMsg);
	};
	
	const validatePW = (password) => { // 추출한 비밀번호를 이 함수에 넣으면
		if(!password) {
			$('#user-pw').removeClass('is-invalid');
			$('#user-pw').removeClass('is-valid');
			$('#pw-msg').html('');
			return;
		}
		
		
		const msgs = [];

		if (password.length < 8) msgs.push("8자 이상이어야 합니다.");
		if (password.length > 20) msgs.push("20자 이하여야 합니다.");
		if (!/[A-Za-z]/.test(password)) msgs.push("영문자를 최소 1자 포함해야 합니다.");
		if (!/\d/.test(password)) msgs.push("숫자를 최소 1자 포함해야 합니다.");
		if (/[^A-Za-z\d]/.test(password)) msgs.push("영문자와 숫자 이외의 문자는 사용할 수 없습니다.");
		// 각종 표현식을 통과하는지 검사해서 에러 메시지를 아까 선언해둔 배열에 담음.
		
		if(msgs.length == 0) { // 아무 오류메시지도 안 담겼으면 쓸 수 있는 비밀번호임
			$('#pw-msg').css("color", "green");
			$('#pw-msg').html("사용 가능한 비밀번호입니다.");
			$('#user-pw').removeClass('is-invalid');
			$('#user-pw').addClass('is-valid');
			isPwOK = true;	
		} else { // 하나라도 담겼으면 오류메시지 표시해주기
			const msg = msgs.map(msg => msg + '<br>').join('');
			$('#pw-msg').css("color", "red");
			$('#pw-msg').html(msg);
			$('#user-pw').removeClass('is-valid');
			$('#user-pw').addClass('is-invalid');	
		}
	}
	
	const validatePW2 = (password2) => {
		if(!password2) {
			$('#user-pw2').removeClass('is-invalid');
			$('#user-pw2').removeClass('is-valid');
			$('#pw2-msg').html('');
			return;
		}
		
		if(password2 !== $('#user-pw').val()) {
			$('#pw2-msg').css("color", "red");
			$('#pw2-msg').html('비밀번호가 일치하지 않습니다.');
			$('#user-pw2').removeClass('is-valid');
			$('#user-pw2').addClass('is-invalid');
			
		} else {
			$('#pw2-msg').css('color', 'green');
			$('#pw2-msg').html('입력하신 비밀번호와 일치합니다.');
			$('#user-pw2').removeClass('is-invalid');
			$('#user-pw2').addClass('is-valid');
		}
	}
	
	let timer; // 타이머 ID를 저장할 변수 선언 (디바운싱용). 
	// 이걸 전역으로 선언해야 입력이 들어올 때마다 이전 타이머를 취소(clearTimeout)할 수 있음.
	$("#user-id").on("input", function() { // 유저아이디 입력창에 입력이 들어오면
		isIdOK = false; // 아이디가 바뀌니 아이디 검증여부는 기본값인 false로 변경
		clearTimeout(timer);
		// 기존에 설정된 타이머가 있으면 취소. 
		// 이전 입력 이후 일정 시간 대기 후 실행될 예정이었던 함수 실행을 막음 (디바운싱 핵심).
		timer = setTimeout(() => { // 새로운 타이머를 설정. 입력이 멈춘 후 750ms가 지나면 다음 함수가 실행되도록 예약.
			validateLoginID($('#user-id').val()); // 입력값을 읽어서 validateLoginID 함수에 전달 → 유효성 검사 등을 수행.
		}, 750); // 입력이 멈춘 뒤 750ms 후에 실행되도록 설정함.
	});
	
	$("#user-pw").on("input", function() {
		isPwOK = false; isPwChecked = false; // 비번검증여부, 2중확인여부 초기화
		clearTimeout(timer);
		timer = setTimeout(() => {
			validatePW($('#user-pw').val()); // 비번검증후 출력하고
			if(isPwOK && $('#user-pw2').val()) validatePW2($('#user-pw2').val());
			// 비번검사 통과했고 비번확인창에 입력있을때만 비번확인 같이 하기.
		}, 750);
	});
	
	$("#user-pw2").on("input", function() {
		isPwChecked = false;
		clearTimeout(timer);
		timer = setTimeout(() => {
			if(isPwOK) validatePW2($('#user-pw2').val());
			// 비번검증됐을때만 비번2중체크 하기
		}, 750);
	});
	
	$('#id-dupl-btn').on('click', checkDupl);
});