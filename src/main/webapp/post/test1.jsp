<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 40px;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #444;
        }

        input[type="text"], textarea, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
            height: 150px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #28a745;
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>📌 게시글 작성</h2>
    <form action="<%=request.getContextPath()%>/insertPost.do" method="post">
        <label for="postTitle">제목</label>
        <input type="text" id="postTitle" name="postTitle" required>

        <label for="content">내용</label>
        <textarea id="content" name="content" required></textarea>

        <label for="memId">작성자 ID</label>
        <input type="text" id="memId" name="memId" required>

        <label for="categoryId">카테고리</label>
        <select id="categoryId" name="categoryId" required>
            <option value="" disabled selected>카테고리를 선택하세요</option>
            <option value="1">공지사항</option>
            <option value="2">시설물신고</option>
            <option value="3">칭찬</option>
            <option value="4">동네생활</option>
        </select>

        <input type="submit" value="등록하기">
    </form>
</div>

</body>
</html>


