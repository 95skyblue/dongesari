<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<style>
body {
    font-family: 'Segoe UI', sans-serif;
    background-color: #f9f9f9;
    margin: 0;
    padding: 20px;
}
.container {
    max-width: 720px;
    margin: 0 auto;
    background: #fff;
    padding: 30px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
    border-radius: 12px;
}
.post-title {
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 20px;
    color: #333;
}
.post-content {
    font-size: 1rem;
    line-height: 1.6;
    margin-bottom: 20px;
    white-space: pre-wrap;
}
.post-info {
    padding: 15px;
    background: #f8f9fa;
    border-radius: 8px;
    margin-bottom: 20px;
}
.post-info div {
    margin-bottom: 5px;
}
</style>
</head>
<body>
<div class="container">
    <h1>게시글 상세보기</h1>
    
    <c:choose>
        <c:when test="${not empty post}">
            <div class="post-title">${post.postTitle}</div>
            
            <div class="post-info">
                <div><strong>작성자:</strong> 
                    <c:choose>
                        <c:when test="${not empty post.nickname}">${post.nickname}</c:when>
                        <c:otherwise>${post.memId}</c:otherwise>
                    </c:choose>
                </div>
                <div><strong>작성일:</strong> ${post.createAt}</div>
                <div><strong>카테고리 ID:</strong> ${post.categoryId}</div>
                <div><strong>상태:</strong> ${post.postStat}</div>
            </div>
            
            <div class="post-content">${post.content}</div>
            
            <!-- 디버깅 정보 -->
            <div style="border-top: 1px solid #ddd; padding-top: 20px; margin-top: 20px; font-size: 0.9rem; color: #666;">
                <strong>디버깅 정보:</strong><br>
                Post ID: ${post.postId}<br>
                Member ID: ${post.memId}<br>
                Version: ${post.versionNow}
            </div>
            
        </c:when>
        <c:otherwise>
            <div style="color: red; font-weight: bold; text-align: center; padding: 50px;">
                게시글을 찾을 수 없습니다.
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>