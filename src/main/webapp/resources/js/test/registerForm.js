$(function() {
	// 스크립트 익스터널 방식으로 분리할 경우 jsp에서 제공하는 request.contextPath()를 못 쓰므로
	// jsp는 바디의 data-context에 미리 저장해두고, 스크립트는 그 속성을 뽑아서 씀.
	const contextPath = $('body').data('context');
	
	let isIdOK = false;
	let isPwOK = false;
	let isPwChecked = false;
	let isNameOK = false;
	let isNickOK = false;
	let isAddrOK = false;
	let isEmailOK = false;
	let isEmailVerified = false;
	
	const debug = () => {
		if (isIdOK) console.log('아이디 OK'); else console.error('아이디 미통과');
		if (isPwOK) console.log('비밀번호 OK'); else console.error('비밀번호 미통과');
		if (isPwChecked) console.log('비밀번호 확인 OK'); else console.error('비밀번호 확인 미통과');
		if (isNameOK) console.log('이름 OK'); else console.error('이름 미통과');
		if (isNickOK) console.log('닉네임 OK'); else console.error('닉네임 미통과');
		if (isAddrOK) console.log('주소 입력 OK'); else console.error('주소 필수값 미통과');
		if (isEmailOK) console.log('이메일 형식 OK'); else console.error('이메일 형식 미통과');
		if (isEmailVerified) console.log('이메일 인증 OK'); else console.error('이메일 인증 미통과');
	};

	const isAllOk = () => {
		debug();
		if (isIdOK && isPwOK &&	isPwChecked && isNameOK && isNickOK && isAddrOK && isEmailOK && isEmailVerified) {
			$('#register-btn').prop('disabled', false);
			$('#register-btn').addClass('shiny');
		} else {
			$('#register-btn').prop('disabled', true);
			$('#register-btn').removeClass('shiny');
		}
	};

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
	
	const checkDuplNick = (nickname) => {
		return new Promise((resolve, reject) => {
			$.ajax({
				url: contextPath + '/test/checkduplnick.do',
				method: 'POST',
				contentType: 'text/plain',
				data: nickname,
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
	const validateLoginID = async (loginId) => { // async 사용
		isIdOK = false; // 아이디 검증여부 초기화
		if(!loginId) { // 들어온 값이 없으면
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
		// 각종 유효성검사 시키고 에러문구 담기
			
		if (errors.length === 0) { // 유효성검사 통과 시
			const result = await checkDupl(loginId); // await 사용
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
					errors.push("** 서버 내부 오류 **");
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
		isPwChecked = false;
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
			isPwChecked = true;
		}
	}
	
	const validateName = (userName) => {
		isNameOK = false;
		if(!userName) {
			$('#user-name').removeClass('is-invalid');
			$('#user-name').removeClass('is-valid');
			$('#name-msg').html('');
			return;
		}
		
		const errors = [];

		if (userName.length < 2) errors.push("이름은 2자 이상이어야 합니다.");
		if (userName.length > 17) errors.push("이름은 17자 이하여야 합니다.");
		if (!/[가-힣]/.test(userName)) errors.push("이름은 한글로만 입력해 주세요.");
		if (/\s/.test(userName)) errors.push("이름에는 공백이 포함될 수 없습니다.");
		
		if (errors.length === 0) { // 유효성검사 통과 시
			$('#user-name').removeClass('is-invalid');
			$('#user-name').addClass('is-valid');
			$('#name-msg').css("color", "green");
			$('#name-msg').text("멋진 이름이네요!");
			isNameOK = true;
		} else {
			const errorMsg = errors.map(msg => msg + '<br>').join('');
			$('#user-name').removeClass('is-valid');
			$('#user-name').addClass('is-invalid');
			$('#name-msg').css("color", "red");
			$('#name-msg').html(errorMsg);	
		}		
	}
	
	const validateNickname = async (nickname) => { // 닉네임 패턴 검사 후 중복 검사
		if(!nickname) { // 닉네임이 null이거나 empty 면
			$('#nickname').removeClass('is-invalid');
			$('#nickname').removeClass('is-valid');
			$('#nick-msg').html('');
			return;
		}
		
		const errors = [];

		if (nickname.length < 2) errors.push("2자 이상이어야 합니다.");
		if (nickname.length > 10) errors.push("10자 이하여야 합니다.");
		if (/[^A-Za-z\d가-힣ㄱ-ㅎㅏ-ㅣ]/.test(nickname)) errors.push("한글/영어/숫자만 사용할 수 있습니다.");

		if (errors.length === 0) {
			const result = await checkDuplNick(nickname);
			switch (result) {
				case 1:
					$('#nick-msg').css("color", "green");
					$('#nick-msg').html("사용 가능한 닉네임입니다.");
					$('#nickname').removeClass('is-invalid');
					$('#nickname').addClass('is-valid');
					isNickOK = true;
					return;
				case 2:
					errors.push("이미 존재하는 닉네임입니다.");
					break;
				case 3:
					errors.push("** 서버 통신 오류 **");
					break;
				default:
					errors.push("** 서버 응답 없음 **");
			}
		}

		const errorMsg = errors.map(msg => msg + '<br>').join('');
		$('#nickname').removeClass('is-valid');
		$('#nickname').addClass('is-invalid');
		$('#nick-msg').css("color", "red");
		$('#nick-msg').html(errorMsg);
	};
	
	// 다음 api 복붙해옴.
	function getAddressByPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $('#zipcode').val(data.zonecode);
				$('#base-addr').val(roadAddr);
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    $('#plus-addr').val(extraRoadAddr);
                } else {
                    $('#plus-addr').val('');
                }		
				isAddrExist();
				isAllOk();
            }
        }).open();
    }
	
	const isAddrExist = () => {
		if(!$('#zipcode').val() || !$('#base-addr').val()) {
			isAddrOK = false;
		} else {
			isAddrOK = true;
		}		
	}

	const validateEmail = (email) => {
	$('#email').removeClass('is-invalid');
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/.test(email)) {
        $('#email-msg').text("이메일 형식이 아닌 것 같아요.");
        $('#email-msg').css("color", "red");
        $('#email-btn').prop("disabled", true); // 버튼 비활성화
		$('#email').addClass('is-invalid');
        isEmailOK = false;
    } else {
		$('#email-msg').text('');
        $('#email-btn').prop("disabled", false); // 버튼 활성화
        isEmailOK = true;
    }
};
	
	const sendVerifyMail = () => {
		$('#email-spin').removeClass('visually-hidden');
	
		$.ajax({
			url: contextPath + '/test/sendcode.do',
			method: 'POST',
			contentType: 'text/plain',
			data: $('#email').val(),
			dataType: 'json',
			success: function(response) {
				if (response.status === 'ok') {
					$('#veri-code-container').removeClass('collapse');
					$('#veri-code-container').addClass('collapse.show');
					
					$('#verify-btn').prop('disabled', false);
					if (mailTimer) clearInterval(mailTimer);
					$('#code-btn').prop('disabled', false);
					startTimer(300, $('#timer-box'), $('#code-btn'));
				} else {
					$('#email-msg').text("메일을 보내지 못했어요. 메일을 다시 확인해주세요.");
					$('#email-msg').css("color", "red");
				}
			},
			error: function(error) {
				console.error('AJAX 에러', error);
				alert('서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해 보세요.');
			},
			complete: function() {
				$('#email-spin').addClass('visually-hidden');
			}
		});
	}
	
	const sendVerifyCode = () => {
		$.ajax({
			url: contextPath + '/test/checkemailcode.do',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({
				email: $('#email').val().trim(),
				code: $('#veri-code').val().trim()
			}),
			dataType: 'json',
			success: function(response) {
				if (response.status === 'ok') {
					// 정보가 다 맞으면 인증창 콜랩스는 다시 숨김
					$('#veri-code-container').addClass('collapse');
					$('#veri-code-container').removeClass('collapse.show');
					$('#email').addClass('is-valid');
					$('#email-msg').text("인증 성공!");
					$('#email-msg').css("color", "green");
					$('#email-btn').addClass("d-none");
					isEmailVerified = true;
					isAllOk();
				} else if (response.status === 'not-found') {
					$('#email-msg').text("인증에 실패했어요. 다시 인증해 주세요.");
					$('#email-msg').css("color", "red");
				} else {
					$('#email-msg').text("인증번호를 전송하셨나요? 다시 메일을 보내야 해요.");
					$('#email-msg').css("color", "red");
				}
			},
			error: function(error) {
				console.error('AJAX 에러', error);
				alert('서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해 보세요.');
			}
		});
	}

	// 회원가입 요청 함수
	const register = () => {
		$.ajax({
			url: contextPath + '/test/register.do',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify($('#login-form').serializeJSON()), 
			dataType: 'json',
			success: function(response) {
				if (response.status === 'ok') {
					alert('회원가입 완료!');
					window.location.href = contextPath + '/test/login';
				} else {
					alert(response.errorMsg);
					// 나중엔 퉁치치 말고 제대로 뭐가틀렸는지 표시하도록
				}
			},
			error: function(error) {
				console.error('AJAX 에러(아마도 응답없음)', error);
			}
		});
	};


	let mailTimer;

	const startTimer = (duration, display, button) => {
		let remaining = duration;

		// 처음 표시
		const formatTime = (seconds) => {
			const min = String(Math.floor(seconds / 60)).padStart(2, '0');
			const sec = String(seconds % 60).padStart(2, '0');
			return `${min}:${sec}`;
		};

		display.text(formatTime(remaining));

		mailTimer = setInterval(() => {
			remaining--;
			display.text(formatTime(remaining));

			if (remaining <= 0) {
				clearInterval(mailTimer);
				button.prop('disabled', true);
				display.text("시간초과");
			}
		}, 1000);
	};

	// 5분 타이머 종료 시 disabled 속성 추가

	let timer; // 타이머 ID를 저장할 변수 선언 (디바운싱용). 
	// 이걸 전역으로 선언해야 입력이 들어올 때마다 이전 타이머를 취소(clearTimeout)할 수 있음.
	$("#user-id").on("input", function() { // 유저아이디 입력창에 입력이 들어오면
		clearTimeout(timer);
		// 기존에 설정된 타이머가 있으면 취소. 
		// 이전 입력 이후 일정 시간 대기 후 실행될 예정이었던 함수 실행을 막음 (디바운싱 핵심).
		timer = setTimeout(() => { // 새로운 타이머를 설정. 입력이 멈춘 후 750ms가 지나면 다음 함수가 실행되도록 예약.
			validateLoginID($('#user-id').val()); // 입력값을 읽어서 validateLoginID 함수에 전달 → 유효성 검사 등을 수행.
			isAllOk();
		}, 750); // 입력이 멈춘 뒤 750ms 후에 실행되도록 설정함.
	});
	
	$("#user-pw").on("input", function() {
		isPwOK = false; isPwChecked = false; // 비번검증여부, 2중확인여부 초기화
		clearTimeout(timer);
		timer = setTimeout(() => {
			validatePW($('#user-pw').val()); // 비번검증후 출력하고
			validatePW2($('#user-pw2').val());
			isAllOk();
		}, 750);
	});
	
	$("#user-pw2").on("input", function() {
		isPwChecked = false;
		clearTimeout(timer);
		timer = setTimeout(() => {
			if(isPwOK) validatePW2($('#user-pw2').val());
			// 비번검증됐을때만 비번2중체크 하기
			isAllOk();
		}, 750);
	});
	
	$("#user-name").on("input", function() {
		clearTimeout(timer);
		timer = setTimeout(() => {
			validateName($('#user-name').val());
			isAllOk();
		}, 750);	
	});
	
	$("#nickname").on("input", function() {
		clearTimeout(timer);
		timer = setTimeout(() => {
			validateNickname($('#nickname').val());
			isAllOk();
		}, 750);	
	});
	
	$('#zipcode-search').on('click', getAddressByPostcode);
	
	$('#zipcode').on("input", function() {
		clearTimeout(timer);
		timer = setTimeout(() => {
			isAddrExist();
			isAllOk();
		}, 750);	
	});

	$('#base-addr').on("input", function() {
		clearTimeout(timer);
		timer = setTimeout(() => {
			isAddrExist();
			isAllOk();
		}, 750);	
	});

	$('#email').on("input", function() {
		clearTimeout(timer);
		timer = setTimeout(() => {
			validateEmail($('#email').val().trim());
			isAllOk();
		}, 750);	
	});

	$('#email-btn').on('click', function() {
		sendVerifyMail($('#email').val().trim());
		isAllOk();
	})

	$('#code-btn').on('click', function() {
		sendVerifyCode();
		isAllOk();
	})

	$('#register-btn').on('click', function() {
		register();
	})
});