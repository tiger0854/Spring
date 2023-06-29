<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jquery CDN 추가 -->
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#btnC").click(function(){
			alert(" 글쓰기 버튼 클릭! ");
			var obj = { 
					title:$('#title').val(),
					writer:$('#writer').val(),
					content:$('#content').val()					
			};
			//alert(obj);
			//console.log(obj);
			// REST컨트롤러로 이동해서 데이터 처리(ajax)
			$.ajax({
				url:"${contextPath}/boards",
				type:"POST",
				contentType : "application/json",
				data : JSON.stringify(obj),
				success:function(data){
					alert("성공 :"+data);
					$('#title').val("");
					$('#writer').val("");
					$('#content').val("");
				},
				error:function(){
					alert("실패");
				}
			});// ajax
		});//click
		
		
		$('#btnL').click(function(){
			alert('리스트 가져오기-시작');
			
			// ajax-> REST정보요청
			$.ajax({
				url : "${contextPath}/boards",
				type : "get",
				success : function(data){
					//alert("성공!"+data);
					alert(data);
					console.log(data);
					
					// 요소에 순차적으로 접근 
// 					$.each(접근할요소,function);
// 					$(접근할요소).each(function);
					$.each(data,function(idx,item){
						// 테이블에 값을 각각 추가
						var tag ="<tr>";
						  tag += "<td>";
						  tag += item.bno;
						  tag += "</td>";
						  tag += "<td>";
						  tag += item.title;
						  tag += "</td>";
						  tag += "<td>";
						  tag += item.writer;
						  tag += "</td>";
						  tag += "</tr>";
						
						$("table").append(tag);
						//$("table").append("<tr><td>"+item.bno+"</td><td>"+item.title+"</td><td>"+item.writer+"</td></tr>");
					});

					
					
// 					for(var i=0;i<data.length;i++){
// 						$('#divL').append(" bno :"+data[i].bno);
// 						$('#divL').append(" title : "+data[i].title);
// 						$('#divL').append(" writer : "+data[i].writer);
// 						$('#divL').append(" content : "+data[i].content+"<hr>");
// 					}
					
				}//success
				
			});

		});
		
		
		
	});// jquery

</script>

</head>
<body>
   	<h1>board.jsp</h1>
   	<h2>REST방식으로 게시판코드 작성</h2>
   	
   	<h2> 글쓰기  /boards + JSON데이터 	POST</h2>
   	
   	제목 : <input type="text" id="title"> <br>
   	이름 : <input type="text" id="writer"> <br>
   	내용 : <input type="text" id="content"> <br>
   	
   	<input type="button" id="btnC" value="글쓰기">
   	<hr>
   	
   	<h2> 글 리스트 정보 조회      /boards		GET</h2>

   	<input type="button" id="btnL" value="리스트 가져오기">
   	
   	<!-- <div id="divL"></div> -->
   	
   	<table border="1">
   	   <tr>
   	      <td>번호</td>
   	      <td>제목</td>
   	      <td>글쓴이</td>
   	   </tr>
   	</table>
   	
   	<hr>
   	
   	
   	
   	

</body>
</html>