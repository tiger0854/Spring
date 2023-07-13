<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
	  var cnt = 1;
	  
	  $("#btnAdd").click(function(){
		  
		  $('#divFile').append("<input type='file' name='file"+(cnt++)+"' accept='image/*'>");
		  
	  }); // btnAdd-click
	  
  });

</script>

</head>
<body>
		<h1>form.jsp</h1>
		
		<fieldset>
			<legend> 파일업로드(다중파일업로드) </legend>
			<form action="/fileUpload" method="post" enctype="multipart/form-data">
			
				아이디 : <input type="text" name="id"> <hr>
				메세지 : <input type="text" name="msg"> <hr>
				<input type="button" value="파일 추가" id="btnAdd"> 
				<hr>
				 	<div id="divFile"></div>
				<hr>
				<input type="submit" value="파일업로드">
			</form>		
		</fieldset>
		
</body>
</html>