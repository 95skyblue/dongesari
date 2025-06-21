<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dongnesari.comm.admin.vo.AdminVO" %>

<%
    AdminVO admin = (AdminVO) request.getAttribute("admin");
    if (admin == null) {
%>
    <div class="alert alert-danger">관리자 정보가 없습니다.</div>
<%
    return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 상세 정보</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2 class="mb-4">관리자 상세 정보</h2>

  <!-- 관리자 사진 영역 -->
  <div class="text-center mb-4">
    <img 
         src="<%= request.getContextPath() %>/images/admin-profile.jpg"  <%-- 가짜 이미지 --%>
         alt="관리자 사진"
         class="rounded-circle border shadow"
         style="width: 150px; height: 150px; object-fit: cover;">
    <p class="mt-2 text-muted">※ 추후 관리자 프로필이 등록되면 자동 표시됩니다.</p>
  </div>

  <!-- 관리자 정보 테이블 -->
  <table class="table table-bordered">
    <tr>
      <th>관리자 고유 ID</th>
      <td><%= admin.getAdmId() %></td>
    </tr>
    <tr>
      <th>로그인 ID</th>
      <td><%= admin.getAdmLoginid() %></td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td><%= admin.getAdmPw() %></td>
    </tr>
    <tr>
      <th>이름</th>
      <td><%= admin.getAdmName() %></td>
    </tr>
    <tr>
      <th>주민번호</th>
      <td><%= admin.getRsdntNum() %></td>
    </tr>
  </table>
  
  

  <div class="mt-4 d-flex justify-content-between">
  <a href="<%=request.getContextPath()%>/admin/adminList.jsp" class="btn btn-secondary">목록으로</a>

  <div>
  	<!-- 관리자 수정 버튼(아직 안만듬)  -->
    <a href="<%=request.getContextPath()%>/admin/AdminUpdateForm.jsp?admId=<%= admin.getAdmId() %>" class="btn btn-warning me-2">수정</a>
    
    <!-- 관리자 삭제 버튼(문구 수정가능) -->
    <form action="<%=request.getContextPath()%>/AdminDeleteServlet.do" method="post" style="display:inline;" onsubmit="return confirm('삭제 된 아이디는 복구 할 수 없습니다.\n정말 삭제하시겠습니까?');">
      <input type="hidden" name="admId" value="<%= admin.getAdmId() %>">
      <button type="submit" class="btn btn-danger">삭제</button>
    </form>
  </div>
</div>
</div>
</body>
</html>
