<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성 (비동기)</title>
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

        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .msg {
            text-align: center;
            color: green;
            margin-top: 20px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

<div class="container">
    <h2>📌 게시글 작성 (비동기)</h2>
    <form id="postForm" enctype="multipart/form-data">
    	
    	<label for="categoryId">카테고리</label>
        <select id="categoryId" name="categoryId" required>
            <option value="" disabled selected>카테고리를 선택하세요</option>
            <option value="1">공지사항</option>
            <option value="2">시설물 신고</option>
            <option value="3">칭찬</option>
            <option value="4">동네생활</option>
        </select>
    
        <label for="postTitle">제목</label>
        <input type="text" id="postTitle" name="postTitle" required>
        
        <label for="upload">이미지 첨부</label>
        <input type="file" id="upload" name="upload" accept="image/*" multiple>
        
        <!-- 미리보기 영역 -->
        <div id="preview"></div>

        <label for="content">내용</label>
        <textarea id="content" name="content" required></textarea>

        <label for="memId">작성자 ID</label>
        <input type="text" id="memId" name="memId" required>

        <button type="button" id="write">등록하기</button>
        <div class="msg" id="msgBox"></div>
    </form>
</div>

<script>

	let selectedFiles = [];

	//미리보기
	$('#upload').on('change', function(e){
		const newFiles = Array.from(e.target.files);
		selectedFiles = selectedFiles.concat(newFiles);
		
		$('#preview').empty(); //이전이미지 지우기
		
		selectedFiles.forEach(file => {
			const reader = new FileReader();
			reader.onload = function(event){
				const img = $('<img>').attr('src', event.target.result).css({
					width : '80px',
					margin : '10px',
					border : '1px solid #ccc'
				});
				$('#preview').append(img);
			};
			reader.readAsDataURL(file);
		})
		//input초기화
		$('#upload').val('');
	})
		
	
	//전송 버튼 클릭시
    $('#write').on('click', function () {
        const formData = new FormData();
        
        formData.append('postTitle', $('#postTitle').val());
        formData.append('content', $('#content').val());
        formData.append('memId', $('#memId').val());
        formData.append('categoryId', $('#categoryId').val());
        
		/* postTitle : $('#postTitle').val(),
		content : $('#content').val(),
		memId : $('#memId').val(),
		categoryId : parseInt($('#categoryId').val()) */
		
		selectedFiles.forEach(file => {
			formData.append('uploadFiles', file); // key값은 백엔드에서 처리할 필드명
		})
    	
    	//console.log("보낼 데이터: " + JSON.stringify(formData));
		formData.forEach((value, key) => console.log(`${key} : ${value}`));
    	
    	$.ajax({
    		url : `<%=request.getContextPath() %>/board/write`,
    		data : formData,
    		type : 'post',
    		processData : false,
    		contentType : false,
    		success : function(res){
    			console.log(res); // {result : "테스트 성공!"}
    			$('#msgBox').text('게시글 등록 완료!');
    		},
    		error : function(xhr){
    			alert("오류 " + xhr.status + " - " + xhr.statusText);
    		},
    		dataType : 'json'
    		
    	})
    	
    });
</script>

</body>
</html>
