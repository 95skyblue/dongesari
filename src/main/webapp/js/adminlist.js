/**
 * 
 */



const mypath = document.querySelector("meta[name='contextPath']").getAttribute("content");
console.log("mypath =", mypath); //


// 페이지 로딩 시 전체 회원 조회
$(document).ready(function () {
  loadAdminList();
});


// 검색 이벤트 처리
$('#searchForm').on('submit', function (e) {
  e.preventDefault(); // 폼 기본 제출 막고
  loadAdminList();   // 검색 수행
});



// 회원 관리 버튼 클릭 이벤트
// 회원 리스트에서 관리 버튼을 눌렀을때 회원 상세 페이지로 이동하는 경로  
$(document).on('click', '.manage-btn', function () {
  const memId = $(this).data('id');
  console.log("넘어가는 memId:", memId); // 잘 나오는지 콘솔 확인
  alert(memId + '번 회원 관리 기능 실행!');
  location.href = mypath + "/AdminDetailServlet.do?memId=" + memId; // 이동경로 설정
});



// AJAX를 통한 회원 목록 불러오기
function loadAdminList() {
  const keyword = $('input[name="keyword"]').val();

  $.ajax({
    url: mypath + "/AdminListServlet.do", // ★ admin용 서블릿으로 경로 변경
    method: 'GET',
    data: {
      keyword: keyword
    },
    dataType: 'json',
    success: function (data) {
      console.log("관리자 전체 데이터:", data);
      let html = '';
      let count = 1;
      if (data.length === 0) {
        html = '<tr><td colspan="5" class="text-center">검색 결과가 없습니다.</td></tr>';
      } else {
        $.each(data, function (i, admin) {
          if (!admin) return;
		  
		  // 디버깅용 콘솔 출력!
		  console.log("admin 객체 전체:", admin);
		  console.log("ID:", admin.admLoginid);  // ✅ camelCase로 바꿔야 실제 값 나옴
		  console.log("이름:", admin.admName);
		  console.log("주민번호:", admin.rsdntNum);

		  
          html += `
            <tr>
              <td>${count++}</td>
              <td>${admin.admLoginid}</td>
              <td>${admin.admName}</td>
              <td>${admin.rsdntNum}</td>
              <td>
                <button type="button" class="btn btn-primary manage-btn" data-id="${admin.admLoginid}">
                  관리
                </button>
              </td>
            </tr>
          `;
        });
      }
      $('#adminList').html(html);
    },
    error: function () {
      alert('관리자 조회 중 오류가 발생했습니다.');
    }
  });
}



// 관리자 추가 폼 제출 이벤트
$(document).on('submit', '#addAdminForm', function (e) {
  e.preventDefault();

  const formData = $(this).serialize(); // form 내용을 URL 인코딩 문자열로 변환

  $.ajax({
    url: mypath + "/AdminInsertServlet.do", // 너가 만든 서블릿 주소
    method: "POST",
    data: formData,
    success: function (res) {
      if (res === 'success') {
        alert("관리자 추가 완료!");
        $('#addAdminModal').modal('hide');  // 모달 닫기
        loadAdminList(); // 목록 새로고침
        $('#addAdminForm')[0].reset(); // 폼 초기화
      } else {
        alert("추가 실패: " + res);
      }
    },
    error: function () {
      alert("서버 오류로 추가에 실패했습니다.");
    }
  });
});