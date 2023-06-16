<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<!-- 본문 내용 작성 -->

<h1>http://localhost:8088/board/read 호출</h1>
<h1>/board/read.jsp</h1>

<%-- ${vo } --%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판 본문(내용)</h3>
	</div>
		
		<!-- 수정(get-post)/삭제(post) 정보 전달용 -->
	<form role="form" id="fr">	
			<input type="hidden" name="bno" value="${vo.bno }">
		

		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">번   호</label>
				 <input	type="text" name="title" class="form-control" id="exampleInputEmail1"
					readonly value="${vo.bno }">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">제  목</label>
				 <input	type="text" name="title" class="form-control" id="exampleInputEmail1"
					 value="${vo.title }">
			</div>
			
			<div class="form-group">
				<label for="exampleInputPassword1">이  름</label>
				<input type="text" name="writer" class="form-control" id="exampleInputPassword1"
					 value="${vo.writer }">
			</div>
			
			<div class="form-group">
				<label>내  용</label>
				<textarea class="form-control" name="content" 
				rows="3" >${vo.content }</textarea>
			</div>
		
		</div>
		

		<div class="box-footer">
		<!-- 	<button type="button" class="btn btn-success"  
			         onclick=" location.href='/board/listALL';"
			>목록으로</button> -->
			<button type="submit" class="btn btn-danger">수정하기</button>
			<button type="reset" class="btn btn-warning">취소하기</button>
		</div>
</div>

		</form>


<!-- 본문 내용 작성 -->

<%@ include file="../include/footer.jsp"%>
