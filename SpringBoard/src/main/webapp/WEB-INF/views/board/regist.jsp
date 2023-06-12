<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<%@ include file="../include/header.jsp"%>


<!-- http://localhost:8088/board/regist -->

<!-- 	본문내용 작성 -->

<!-- <h1>regist.jsp</h1> -->
<%-- <P>The time on the server is ${serverTime}.</P> --%>



	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">ITWILL 게시판 글쓰기</h3>
		</div>
		
		<form role="form">
			<div class="box-body">
				<div class="form-group">
					<label for="title">제목</label> 
					<input
						type="text" class="form-control" id="title"
						placeholder="제목을 입력하세요">
				</div>
				<div class="form-group">
					<label for="name">이름</label> 
					<input
						type="text" class="form-control" id="name"
						placeholder="이름을 입력하세요">
				</div>
					<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="3" placeholder="내용을 입력하세요"></textarea>
					</div>
				
			<div class="box-footer">
				<button type="submit" class="btn btn-success">Submit</button>
			</div>
		</form>
	</div>

<!-- 본문내용 작성 -->

<%@ include file="../include/footer.jsp"%>