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
			    <h2> result : ${result }</h2>
		<hr>
		 ${pm }
		<hr>
		
		
		<!-- 모달창  -->
		<div class="modal modal-info fade" id="modal-info"
			style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title">ITWILL 페이지 정보</h4>
					</div>
					<div class="modal-body">
						<p>One fine body…</p>
					</div>
					<div class="modal-footer">
<!-- 						<button type="button" class="btn btn-outline pull-left" -->
<!-- 							data-dismiss="modal">Close</button> -->
<!-- 						<button type="button" class="btn btn-outline">확인</button> -->
						<button type="button" class="btn btn-outline" 
						           data-dismiss="modal">확인2</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 모달창  -->	

<script>
	
	$(document).ready(function(){
		// 디비-서비스-컨트롤러 -> 뷰(jsp) -> JS -> AJAX 
		var data = "${result }";
	
		if (data == "CREATEOK") {
			//alert(" 글쓰기 성공! ");
// 			$(".modal-body").html("결과 : "+data);
			callModal("글쓰기 성공! ");
		}
		if(data == "MODOK"){
			callModal("글 수정 성공! ");
		}
		if(data == "DELOK"){
			callModal("글 삭제 성공! ");
			
		}
		
		function callModal(txt){
			$(".modal-body").html(txt);
			$("#modal-info").modal("show");
		}
	});
	
	
	
</script>
	    
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
									<td>
										<a href="/board/read?bno=${vo.bno }">${vo.title }</a>
									</td>
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
				</div><!-- div box-body -->
			
				<div class="box-footer clearfix">
					<!-- 페이징처리(하단부) -->
					<ul class="pagination pagination-sm no-margin pull-right">
						
						<c:if test="${pm.prev }">						
							<li><a href="/board/listPage?page=${pm.startPage-1 }">«</a></li>
						</c:if>
						
						<c:forEach begin="${pm.startPage }" 
						           end="${pm.endPage}" step="1"
						           var="idx">						
							<li  
							   <c:out value="${pm.pageVO.page == idx? 'class=active':''}"/>
							>
								<a href="/board/listPage?page=${idx }">${idx }</a>
							</li>
						</c:forEach>
						
						<c:if test="${pm.next && pm.endPage > 0 }">
							<li><a href="/board/listPage?page=${pm.endPage + 1}">»</a></li>
						</c:if>
					</ul>
				</div>

			</div> <!-- div  box -->

<%@ include file="../include/footer.jsp" %>
