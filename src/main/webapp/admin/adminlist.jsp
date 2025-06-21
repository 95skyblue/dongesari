<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 계정 관리</title>
<meta name="contextPath" content="<%=request.getContextPath()%>">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>




<div class="container mt-5">
  <h2 class="mb-4 text-center">관리자 계정 관리</h2>

  <!-- 검색 영역 -->
  <form id="searchForm" class="row g-3 mb-4">
    <div class="col-md-4">
      <input type="text" class="form-control" name="keyword" placeholder="ID 또는 이름 검색">
    </div>
    <div class="col-md-2">
      <button type="submit" class="btn btn-primary w-100">검색</button>
    </div>
    <div class="col-md-6 text-end">
      <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addAdminModal">
        + 관리자 추가
      </button>
    </div>
  </form>

  <!-- 관리자 목록 테이블 -->
  <table class="table table-bordered table-hover">
    <thead class="table-light">
      <tr>
        <th>번호</th>
        <th>로그인 ID</th>
        <th>이름</th>
        <th>주민등록번호</th>
        <th>관리</th>
      </tr>
    </thead>
    <tbody id="adminList"></tbody>
  </table>
</div>

<!-- <button class="btn btn-sm btn-warning">수정</button>
     <button class="btn btn-sm btn-danger">삭제</button> -->
     



<!-- 관리자 추가 모달 -->
<div class="modal fade" id="addAdminModal" tabindex="-1" aria-labelledby="addAdminModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form id="addAdminForm">
        <div class="modal-header">
          <h5 class="modal-title" id="addAdminModalLabel">관리자 추가</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label class="form-label">관리자 로그인 ID</label>
            <input type="text" class="form-control" name="admLoginid" required>
          </div>
          <div class="mb-3">
            <label class="form-label">비밀번호</label>
            <input type="password" class="form-control" name="admPw" required>
          </div>
          <div class="mb-3">
            <label class="form-label">이름</label>
            <input type="text" class="form-control" name="admName" required>
          </div>
          <div class="mb-3">
            <label class="form-label">주민등록번호</label>
            <input type="text" class="form-control" name="rsdntNum" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">저장</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        </div>
      </form>
    </div>
  </div>
</div>

     
     
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>     
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/js/adminlist.js"></script>
</body>
</html>
