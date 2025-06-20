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
    <a href="<%=request.getContextPath()%>/admin/adminmemberlist.jsp" class="btn btn-secondary">뒤로가기</a>
  </div>
</div>
</body>
</html>
