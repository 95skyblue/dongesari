<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dongnesari.comm.admin.vo.MemberVO" %>

<%
    MemberVO member = (MemberVO) request.getAttribute("member");
    if (member == null) {
%>
    <div class="alert alert-danger">회원 정보가 없습니다.</div>
<%
    return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 정보</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2 class="mb-4">회원 상세 정보</h2>
  
  
    <!-- 회원 사진 영역 -->
  <div class="text-center mb-4">
    <img 
    	 src="<%= request.getContextPath() %>/images/ganadi2.jpg" <%--가짜 사진 경로 --%>
    	 <%-- 진짜 사진경로 src="<%=request.getContextPath()%>/images/default-profile.png" --%> 
         alt="회원 사진" 
         class="rounded-circle border shadow" 
         style="width: 150px; height: 150px; object-fit: cover;">
    <p class="mt-2 text-muted">
    ※ 추후 사진이 등록되면 여기에 경로따와서 표시
   
    </p>
  </div>


	<!-- 회원데이터영영 -->
  <table class="table table-bordered">
    <tr>
      <th>회원 ID</th>
      <td><%= member.getMemId() %></td>
    </tr>
    <tr>
      <th>로그인 ID</th>
      <td><%= member.getLoginId() %></td>
    </tr>
    <tr>
      <th>이름</th>
      <td><%= member.getName() %></td>
    </tr>
    <tr>
      <th>닉네임</th>
      <td><%= member.getNickname() %></td>
    </tr>
    <tr>
      <th>이메일</th>
      <td><%= member.getEmail() %></td>
    </tr>
    <tr>
      <th>전화번호</th>
      <td><%= member.getPhoneNum() %></td>
    </tr>
    <tr>
      <th>주소 ID</th>
      <td><%= member.getAddrId() %></td>
    </tr>
    <tr>
      <th>가입일</th>
      <td><%= member.getJoinAt() %></td>
    </tr>
  </table>

  <div class="mt-3">
    <a href="<%=request.getContextPath()%>/admin/memberlist.jsp" class="btn btn-secondary">뒤로가기</a>
  </div>
</div>
</body>
</html>
