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
            padding: 0;
        }
        .container {
            max-width: 720px;
            margin: 40px auto;
            background: #fff;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 12px;
        }
        .profile {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }
        .profile img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 10px;
        }
        .nickname {
            font-weight: bold;
            font-size: 1.1rem;
        }
        .post-title {
            font-size: 1.4rem;
            font-weight: bold;
            margin: 15px 0 10px;
        }
        .content {
            font-size: 1rem;
            white-space: pre-wrap;
            margin-bottom: 20px;
        }
        .images img {
            max-width: 100%;
            margin: 10px 0;
            border-radius: 8px;
        }
        .actions {
            display: flex;
            gap: 20px;
            margin: 15px 0;
            font-size: 0.95rem;
            color: #666;
        }
        .comments {
            border-top: 1px solid #ddd;
            margin-top: 20px;
            padding-top: 20px;
        }
        .comment {
            margin-bottom: 15px;
        }
        .comment .nickname {
            font-weight: bold;
            margin-right: 8px;
        }
        .comment .text {
            display: inline;
        }
    </style>
</head>
<body>
<div class="container">

	<div class="profile">
        <img src="/images/default-profile.png" alt="프로필">
        <div class="nickname">${post.nickname}</div>
    </div>

    <div class="post-title">${post.postTitle}</div>

    <div class="content">${post.content}</div>

    <div class="images">
        <c:forEach var="pic" items="${post.pictures}">
            <img src="/upload/${pic.picDir}" alt="첨부 이미지">
        </c:forEach>
    </div>

    <div class="actions">
        <div>👍 좋아요 ${post.likeCount}</div>
        <div>💬 댓글 ${post.commentCount}</div>
        <div>👁 조회수 ${post.viewCount}</div>
    </div>

    <div class="comments">
        <c:forEach var="reply" items="${post.comments}">
            <div class="comment">
                <span class="nickname">${reply.nickname}</span>
                <span class="text">${reply.content}</span>
            </div>
        </c:forEach>
    </div>

</div>
</body>
</html>