<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<style>
body {
    font-family: 'Segoe UI', sans-serif;
    background-color: #f9f9f9;
    margin: 0;
    padding: 20px;
}
.container {
    max-width: 900px;
    margin: 0 auto;
    background: #fff;
    padding: 30px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
    border-radius: 12px;
}
h2 {
    margin-bottom: 20px;
    font-size: 1.6rem;
}
.table {
    width: 100%;
    border-collapse: collapse;
}
.table thead {
    background-color: #f1f1f1;
}
.table th, .table td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}
.table tbody tr:hover {
    background-color: #fafafa;
}
.title-link {
    color: #007BFF;
    text-decoration: none;
}
.title-link:hover {
    text-decoration: underline;
}
.write-button {
    float: right;
    margin-bottom: 15px;
    padding: 8px 16px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 0.95rem;
    cursor: pointer;
}
.write-button:hover {
    background-color: #0056b3;
}
</style>
</head>
<body>
<div class="container">
    <h2>📋 게시글 목록</h2>

    <form action="/dongnesari/board/write" method="get">
        <button type="submit" class="write-button">+ 글쓰기</button>
    </form>
    
    <!-- 상세보기 링크 수정 -->
	<td><a href="/dongnesari/board/detail?postId=${post.postId}" class="title-link">${post.postTitle}</a></td>

    <table class="table">
        <thead>
            <tr>
                <th>No</th>
                <th>제목</th>
                <th>작성자</th>
                <th>카테고리</th>
                <th>작성일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="post" items="${postList}">
                <tr>
                    <td>${post.postId}</td>
                    <td><a href="/dongnesari/board/detail?postId=${post.postId}" class="title-link">${post.postTitle}</a></td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty post.nickname}">${post.nickname}</c:when>
                            <c:otherwise>${post.memId}</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${post.categoryId}</td>
                    <td>${post.createAt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
