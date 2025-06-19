<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지 회원 리스트 조회</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
  <h2 class="mb-4">회원 리스트 조회</h2>

  <!-- 검색 필터 영역 -->
  <form id="searchForm" class="row g-3 mb-4">
    <div class="col-md-4">
      <input type="text" class="form-control" name="keyword" placeholder="이름 또는 ID 검색">
    </div>
    <div class="col-md-2">
      <select class="form-select" name="memberType">
        <option value="전체">전체</option>
        <option value="일반">일반</option>
        <option value="관리자">관리자</option>
      </select>
    </div>
    <div class="col-md-2">
      <button type="submit" class="btn btn-primary">검색</button>
    </div>
  </form>

  <!-- 회원 목록 테이블 -->
  <table class="table table-bordered table-hover">
    <thead class="table-light">
      <tr>
        <th>번호</th>
        <th>아이디</th>
        <th>이름</th>
        <th>닉네임</th>
        <th>이메일</th>
        <th>주소</th>
        <th>번호</th>
        <th>가입날짜</th>
      </tr>
    </thead>
    <tbody id="memberList">
      <!-- 여기에 JS로 회원 목록이 들어감 -->
    </tbody>
  </table>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
  const mypath = "<%=request.getContextPath()%>";

  // 페이지 로딩 시 전체 회원 조회
  $(document).ready(function () {
    loadMemberList();
  });

  // 검색 이벤트 처리
  $('#searchForm').on('submit', function (e) {
    e.preventDefault();
    loadMemberList();
  });

  // AJAX를 통한 회원 목록 불러오기
  function loadMemberList() {
    const keyword = $('input[name="keyword"]').val();
    const memberType = $('select[name="memberType"]').val();

    $.ajax({
      url: mypath + "/MemberListServlet.do", // 컨트롤러 서블릿 URL
      method: 'GET',
      data: {
        keyword: keyword,
        memberType: memberType
      },
      dataType: 'json',
      success: function (data) {
    	  console.log(data);
        let html = '';
        if (data.length === 0) {
          html = '<tr><td colspan="8" class="text-center">검색 결과가 없습니다.</td></tr>';
        } else {
          $.each(data, function (i, member) {
            html += `
              <tr>
                <td>${i + 1}</td>
                <td>${member.loginId}</td>
                <td>${member.name}</td>
                <td>${member.nickname}</td>
                <td>${member.email}</td>
                <td>${member.addrId}</td>
                <td>${member.phoneNum}</td>
                <td>${member.joinAt}</td>
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
</script>
</body>
</html>
