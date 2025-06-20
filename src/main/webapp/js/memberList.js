/**
 * 
 */


const mypath = document.querySelector("meta[name='contextPath']").getAttribute("content");
console.log("mypath =", mypath); //


// 페이지 로딩 시 전체 회원 조회
$(document).ready(function () {
  loadMemberList();
});


// 검색 이벤트 처리
$('#searchForm').on('submit', function (e) {
  e.preventDefault();
  loadMemberList();
});



// 회원 관리 버튼 클릭 이벤트
$(document).on('click', '.manage-btn', function () {
  const memId = $(this).data('id');
  console.log("넘어가는 memId:", memId); // 잘 나오는지 콘솔 확인
  alert(memId + '번 회원 관리 기능 실행!');
  location.href = mypath + "/MemberDetailServlet.do?memId=" + memId;
});




// AJAX를 통한 회원 목록 불러오기
function loadMemberList() {
  const keyword = $('input[name="keyword"]').val();
  const memberType = $('select[name="memberType"]').val();

  $.ajax({
    url: mypath + "/MemberListServlet.do",
    method: 'GET',
    data: {
      keyword: keyword,
      memberType: memberType
    },
    dataType: 'json',
    success: function (data) {
      console.log("전체 데이터:", data);
      let html = '';
      let count = 1;
      if (data.length === 0) {
        html = '<tr><td colspan="9" class="text-center">검색 결과가 없습니다.</td></tr>';
      } else {
        $.each(data, function (i, member) {
          if (!member) return;
          html += `
            <tr>
              <td>${count++}</td>
              <td>${member.loginId}</td>
              <td>${member.name}</td>
              <td>${member.nickname}</td>
              <td>${member.email}</td>
              <td>${member.addrId}</td>
              <td>${member.phoneNum}</td>
              <td>${member.joinAt}</td>
              <td>
                <button type="button" class="btn btn-primary manage-btn" data-id="${member.loginId}">
                  관리
                </button>
              </td>
            </tr>
          `;
        });
      }
      $('#memberList').html(html);
    },
    error: function () {
      alert('회원 조회 중 오류가 발생했습니다.');
    }
  });
}