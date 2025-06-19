<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글 작성</h2>
	<p>현재 context path: <%= request.getContextPath() %></p>
	<form action="<%=request.getContextPath()%>/insertPost.do" method="post">
		제목: <input type="text" name="postTitle"><br>
		내용: <textarea name="content"></textarea><br>
		작성자 ID: <input type="text" name="memId"><br> <!-- 나중엔 세션에서 꺼내게 바꾸면 돼 -->
		카테고리 ID: <input type="text" name="categoryId"><br> <!-- 이건 게시판 종류별로 다를 수 있음 -->
		<input type="submit" value="등록"> 
	</form>

</body>
</html>