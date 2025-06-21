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
            
             <!-- 좋아요 기능 시작 -->
            <div id="likeArea" style="text-align: center; margin-top: 20px;">
            	<!-- 게시글 번호를 숨겨서 자바스크립트로 넘길때 사용 -->
                <input type="hidden" id="postId" value="${post.postId}" />
                
                <!-- 좋아요 버튼: 로그인한 사용자가 좋아요를 눌렀는지 여부에 따라 표시 변경 -->
                <button type="button" id="likeBtn">
                    <c:choose>
                        <c:when test="${post.likedByMe}">
                            ❤️ 좋아요 취소
                        </c:when>
                        <c:otherwise>
                            🤍 좋아요
                        </c:otherwise>
                    </c:choose>
                </button>
                <div style="font-size: 0.9rem; margin-top: 5px;">
                    <span id="likeCount">${post.likeCount}</span>명이 좋아요
                </div>
            </div>
            <!-- 좋아요 기능 끝 -->
            
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

<!--  좋아요 Ajax 스크립트 -->
<script>
// 좋아요 버튼 클릭 시 서버로 비동기 요청
document.getElementById("likBtn")?.addEventListener("click", function(){
	const postId = document.getElementById("postId").value;
	
	fetch("/like", {
		method: "POST",
		headers:{
			"Content-Type" : "application/json"
		},
		body: JSON.stringify({postId: postId})	// 게시글 번호만 서버로 전달
	})
	.then(res => res.json())
	.then(data => {
		const btn = document.getElementById("likeBtn");
		const count = document.getElementById("likeCount");
		
		// 서버 응답에 따라 버튼 텍스트 및 좋아요 수 업데이트
		if (data.liked){
			btn.innerText = "❤️ 좋아요 취소";
		} else{
			btn.innerText = "🤍 좋아요";
		}
		count.innerText = data.likeCount;
	});
});
</script>

<!--  ! 비로그인 사용자는 버튼 비활성화 처리 -->
<c:if test="${empty sessionScope.loginUser}">
<script>
    document.getElementById("likeBtn").disabled = true;
    document.getElementById("likeBtn").innerText = "로그인 후 이용 가능";
</script>
</c:if>

</body>
</html>