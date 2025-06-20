<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지 회원 리스트 조회</title>
<meta name="contextPath" content="<%=request.getContextPath()%>">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<!-- head 태그 안에 Bootstrap Icons CDN 추가 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

<!-- css링크  -->
<link rel="stylesheet" href="../css/admin.css">




<body>

<div class="container mt-5">
  <h2 class="mb-4">회원 리스트 조회</h2>

  <!-- 검색 영역 -->
  
  <!-- 검색 필터 영역 -->
	<!-- 검색 필터 영역 -->
	<div class="card mb-4">
	  <div class="card-body">
	    <form id="searchForm" class="row g-3 align-items-end">
	
	
		  
	      <!-- 가입일 직접 입력 -->
	      <div class="col-md-2">
	        <label for="startDate" class="form-label">시작일</label>
	        <input type="date" class="form-control" id="startDate" name="startDate">
	      </div>
	      <div class="col-md-2">
	        <label for="endDate" class="form-label">종료일</label>
	        <input type="date" class="form-control" id="endDate" name="endDate">
	      </div>
	
	     
	      
	      <!-- 가입일 범위 선택 버튼 -->
	      <div class="col-md-4">
	        <label class="form-label">가입일 범위</label>
	        <div class="d-flex flex-wrap gap-2">
	          <button type="button" class="btn btn-outline-secondary date-range-btn" data-range="1">1개월</button>
	          <button type="button" class="btn btn-outline-secondary date-range-btn" data-range="3">3개월</button>
	          <button type="button" class="btn btn-outline-secondary date-range-btn" data-range="6">6개월</button>
	          <button type="button" class="btn btn-outline-secondary date-range-btn" data-range="12">1년</button>
	        </div>
	      </div>
	
		 <!-- 열이 4칸있음 -->
		 <!-- 칸밀기용 div 부트스트랩으로 만든 폼이라 이거 말고는 미는 방법을 모르곘음!! -->
	  	 <div class="col-md-3"></div>
		  
	      <!-- 검색어 -->
	      <div class="col-md-3">
	        <label for="keyword" class="form-label">검색어 입력</label>
	        <input type="text" class="form-control" id="keyword" name="keyword" placeholder="검색어를 입력하세요">
	      </div>
	      
	      <!-- 검색 조건 + 입력 -->
	      <div class="col-md-3">
	        <label for="searchField" class="form-label">검색 조건</label>
	        <select class="form-select" id="searchField" name="searchField">
	          <option value="name">이름</option>
	          <option value="loginId">아이디</option>
	          <option value="nickname">닉네임</option>
	          <option value="email">이메일</option>
	          <option value="phoneNum">전화번호</option>
	          <option value="addrId">주소ID</option>
	        </select>
	      </div>
		
		  
	      <!-- 회원유형 선택 -->
	      <div class="col-md-2">
	        <label for="memberType" class="form-label">회원 유형</label>
	        <select class="form-select" id="memberType" name="memberType">
	          <option value="">전체</option>
	          <option value="일반">일반</option>
	          <option value="관리자">관리자</option>
	        </select>
	      </div>
	
	  	 
		 <!-- 검색 버튼 -->
	      <div class="col-md-2">
	        <button type="submit" class="btn btn-primary w-100">검색</button>
	      </div>
	
	    </form>
	  </div>
	</div>
		  
  <!-- 
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
 -->


  <!-- 회원 조회 리스트 -->
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
        <th>관리</th>
      </tr>
    </thead>
    <tbody id="memberList"></tbody>
  </table>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/memberList.js"></script>

</body>
</html>
