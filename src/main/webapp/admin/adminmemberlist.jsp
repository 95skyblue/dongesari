<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지 회원 리스트 조회</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->3232
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
<!-- 윤현준 바보 -->
	
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
        <th>닉네임</th><div></div>
        <th>이메일</th>
        <th>주소</th>
        <th>번호</th>
        <th>가입날짜</th>
      </tr>
    </thead>
    <tbody id="memberList">
      <!-- 여기에 JS로 회원 목록이 들어갈 예정 -->
      <!-- 예시 -->
      <!--
      <tr>
        <td>1</td>
        <td>mattaeng</td>
        <td>맛탱이</td>
        <td>mat@example.com</td>
        <td>2024-06-01</td>
        <td>일반</td>
        <td>활동중</td>
        <td><button class="btn btn-sm btn-danger">삭제</button></td>
      </tr>
      -->
    </tbody>
  </table>
</div>

<script>
  // JS로 검색 기능이나 AJAX 연결 추가 예정
  // ex) fetch('/admin/memberList.do')
</script>
	
	
	
	<!-- 
	<div>
		<table>
			<thead>
				<tr>
					<th>회원번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>닉네임</th>
					<th>이메일</th>
					<th>주소</th>
					<th>번호</th>
					<th>가입날짜</th>
				</tr>
			</thead>
		</table>
	</div>
	 -->
	
	
	
</body>
</html>