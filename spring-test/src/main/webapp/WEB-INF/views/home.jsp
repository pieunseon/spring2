<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.css">
</head>
<body>
<h1>
	Login  
</h1>
<!-- jsp에서 컨트롤러로 데이터를 전달하려면 form태그를 이용 -->
<br>
<form method="post" action="<%=request.getContextPath() %>/" 
	style="<c:if test="${user != null}">display:none;</c:if>">
	<!-- 전달하려는 데이터가 있는 태그의 속성 name을 입력한다. -->
	 <div class="form-group row col-sm-4">
      <label for="usr">User ID:</label>
      <input type="text" class="form-control" name="id" id="usr">
    </div>
    <div class="form-group row col-sm-4">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control"name="pw"  id="pwd">
    </div>

	<button class="btn btn-primary">로그인</button>
</form>
	<a href="<%=request.getContextPath() %>/signup">
	<button type="button" class="btn btn-warning">회원가입</button>
	</a>
</body>
</html>
