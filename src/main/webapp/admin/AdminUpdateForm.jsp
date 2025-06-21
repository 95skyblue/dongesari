<%@page import="com.dongnesari.comm.admin.vo.AdminVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
  AdminVO admin = (AdminVO) request.getAttribute("admin");
%>

<!DOCTYPE html>
<html>
<head>
  <title>관리자 정보 수정</title>
</head>
<body>
  <h2>관리자 수정</h2>
  <form action="AdminUpdateServlet.do" method="post">
    <input type="hidden" name="admId" value="<%= admin.getAdmId() %>">
    이름: <input type="text" name="admName" value="<%= admin.getAdmName() %>"><br>
    로그인ID: <input type="text" name="admLoginId" value="<%= admin.getAdmLoginid() %>"><br>
    비밀번호: <input type="password" name="admPw" value="<%= admin.getAdmPw() %>"><br>
    주민등록번호: <input type="text" name="rsdntNum" value="<%= admin.getRsdntNum() %>"><br>
    <button type="submit">수정 완료</button>
  </form>
</body>
</html>