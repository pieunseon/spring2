<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원가입</title>
	<script src="//code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	<h1>회원가입</h1>
	<form method="post" action="<%=request.getContextPath()%>/signup">
		<div>
			아이디<input type="text" name="id" id="id">
			<button id="dup" type="button">중복체크</button>   <!-- 기본 type 이 submit이기 때문 -->
		</div>
		<div>
			비밀번호<input type="password" name="pw">
		</div>
		<div>
			이메일<input type="email" name="email">
		</div>
		<div>
			<input type="radio" value="male" name="gender" id="male" checked>
			<label for="male">남성</label>
			<input type="radio" value="female" name="gender" id="female">
			<label for="female">여성</label>
		</div>
		<button>회원가입</button>
	</form>
	<script type="text/javascript">
		 var dup;
	     $('#dup').click( function() {
	        var id = "";
	         id = $('#id').val();
	         $.ajax({
	        	 async:true,
	        	 type:'POST',
	        	 data:id, //배열,문자열 
	             url: '/springtest/signup/dup',
	             dataType: 'json',
	             contentType:"application/json; charset=UTF-8",
	       		 
	             success: function(data) {   //성공했을때 실행되는 함수
	            	 console.log(data.dup);
	            	 if(data.dup){
	            		 alert('이미 사용중인 아이디 입니다.');
	            	 }
	            	 else{
	            		 alert('사용가능한 아이디 입니다.');
	            	 }
	             }
	        });
      })
	</script>
</body>
</html>
