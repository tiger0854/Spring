<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   (include/header.jsp 페이지에 저장)
    --%>

<%@ include file="../include/header.jsp" %>
	<!-- 본문내용 -->
		<h1>http://localhost:8088/board/listALL  호출</h1>
		<h1>/board/listALL.jsp </h1>
		
	<%-- 	${boardList }		 --%>
	
	<script>
	var data = "${result}";
	if(data == "CREATEOK"){
		alert("글쓰기 성공!");
	}
	
	</script>
	
	
	    <h2> result : ${result }</h2>
	<!-- 본문내용 -->

			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">Bordered Table</h3>
				</div>
			
				<div class="box-body">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th style="width: 60px">번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th style="width: 60px">조회수</th>
							</tr>
							<c:forEach var="vo" items="${boardList }">
								<tr>
									<td>${vo.bno }</td>
									<td>${vo.title }</td>
									<td>${vo.writer }</td>
									<td>${vo.regdate }</td>
									<td>
										<span class="badge bg-maroon">
											${vo.viewcnt }
										</span>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			
			
			</div>

<%@ include file="../include/footer.jsp" %>
