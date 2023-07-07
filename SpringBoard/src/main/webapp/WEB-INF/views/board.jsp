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
						  tag += "<td>"+item.writer+"</td>";
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
		});// click
		
		
		$("#btnR").click(function(){
			alert("글정보 조회(read)");
			
			//  /boards/100   (GET) 호출
			$.ajax({
				url : "${contextPath}/boards/1",
				type : "get",
				success : function(data){
					alert("성공");
					$("#divR").append(data.bno+",");
					$("#divR").append(data.title+",");
					$("#divR").append(data.writer+",");
					$("#divR").append(data.content+",");
					$("#divR").append(data.viewcnt+",");
					$("#divR").append(new Date(data.regdate)+"<hr>");
					// JSON타입은 DATE 타입정보를 지원X
					// => DB에서 생성된 날짜정보를 ms단위 정수데이터로 전달
					//    전달받은 정수데이터 ->DATE타입으로 변경 사용
					//    전달할때 문자타입으로 변경해서 전달가능
					
					var rday = new Date(data.regdate);
					$("#divR").append(
							rday.getFullYear()
							+"-"+(rday.getMonth()+1)
							+"-"+rday.getDate()+"<hr>");
					//////////////////////////////////
					// 수정을 위한 동작
					$("#ubno").val(data.bno);
					$("#utitle").val(data.title);
					$("#uwriter").val(data.writer);
					$("#ucontent").val(data.content);
					
					
				} // success
			});
			
		});// R click
		
		
		$("#btnU").click(function(){
			alert(" 수정 하기! (update) ");
			//    /boards/100 + JSON	PUT
			
			var obj ={
				bno : $("#ubno").val(),
				title : $("#utitle").val(),
				writer : $("#uwriter").val(),
				content : $("#ucontent").val()
			};
			
			$.ajax({
// 				url : "${contextPath}/boards/1"
				url : "${contextPath}/boards/"+$("#ubno").val(),
				type : "PUT",
				contentType : "application/json",
				data : JSON.stringify(obj),
				success : function(data){
					alert("성공 : "+data);
				}
			});
		}); // U Click
		
		
		$("#btnD").click(function(){
			alert(" 게시판 글(1번) 삭제");
			
			//  /boards/100		DELETE
			$.ajax({
				url : "${contextPath}/boards/1",
				type : "delete",
				success : function(data){
					alert(" 성공 : "+data);
				}			
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
   	
   	<h2> 특정 글의 내용 가져오기 
   	      /boards/100		GET
   	</h2>
   	
   	<input type="button" value="100번 글 가져오기" id="btnR">
   	
   	<div id="divR" ></div>
   	
   	<hr>
   	
   	
   	<h2> 특정 글의 내용 수정하기 </h2>
   	
   	<input type="hidden" id="ubno" >
	제목 : <input type="text" id="utitle"> <br>
   	이름 : <input type="text" id="uwriter"> <br>
   	내용 : <input type="text" id="ucontent"> <br>
   	
   	<input type="button" id="btnU" value="글 수정하기">
   	<hr>
   	
   	<h2> 특정  글(1번) 삭제하기 </h2>
   		
   		<input type="button" id="btnD" value="글 삭제하기">
   	
   	<hr>
   	
   	
   	
   	

</body>
</html>