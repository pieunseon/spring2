<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원가입</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.css">
	<script src="//code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	<div class="box-signup offset-4 col-4">
		<h1>Signup</h1>
		
		<form method="post" action="<%=request.getContextPath()%>/signup" id="form">
			<div class="form-group row col-12">
				 <label for="usr">User ID:</label>
				 <input type="text" class="form-control" name="id" id="id">
				 <button id="dup" type="button" class="btn btn-danger">중복체크</button>   <!-- 기본 type 이 submit이기 때문 -->
			</div>
			<div class="form-group row col-12">
				<label for="pwd">Password:</label>
	  			<input type="password" class="form-control" id="pwd" name="pw">
			</div>
			<div class="form-group row col-12">
				<label for="email">Email:</label>
	  			<input type="email" class="form-control" id="email" name="email">
			</div>
			<div class="radio">
		      	<label>
		      	<input type="radio" value="male" name="gender" id="male"checked>남성</label>
		      	<label>
		      	<input type="radio" value="female" name="gender" id="female">여성</label>
	    	</div>
			
			<button type="button" id="btnOk" class="btn btn-warning">회원가입</button>
		</form>
	</div>
	<script type="text/javascript">  //ajax(부분만 수정)이용한 아이디 중복검사 코드
		 var dup=0;
	     $('#dup').click( function() {
	        var id = "";
	         id = $('#id').val();
	         $.ajax({					//{}object 형태 
	        	 async:true,   			//동기화여부
	        	 type:'POST',
	        	 data:id, 				//보내고 싶은 데이터 값, 배열(값이 여러개) 또는 문자열 형태 가능  
	             url: "/springtest/signup/dup",
	             dataType: 'json',		//json >> pom.xml에 의존성 추가 
	             contentType:"application/json; charset=UTF-8",
	       		 
	             success: function(data) {  		 //데이터를 서버에 요청하고 성공했을때 실행되는 함수
	            	 if(data.dup){					//서버한테 전달받은 데이터가 매개변수로 들어옴
	            		 dup = 1;
	            		 alert('이미 사용중인 아이디 입니다.');
	            	 }else{
	            		 dup = -1;
	            		 alert('사용가능한 아이디 입니다.');
	            	 }
	             },
	             error : function (jqXHR, textStatus,errorThrom){
	            	 
	             }
	        });
      })
      
      $('#btnOk').click(function(){
    	 if(dup == 0){
    		 alert("아이디 중복 체크하세요");
    		 return false;
    	 } 
    	 if(dup == 1){
    		 alert("중복된 아이디입니다. 다른아이디를 입력하세요");
    		 return false;
    	 }
    	 $('#form').submit();       //회원가입 버튼 누르면 회원가입 실행됨
    	 return true;
      });
	</script>
</body>
</html>
