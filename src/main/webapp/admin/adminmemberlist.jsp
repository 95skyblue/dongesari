<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지 회원 리스트 조회</title>
<meta name="contextPath" content="<%=request.getContextPath()%>">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
  <h2 class="mb-4">회원 리스트 조회</h2>

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
