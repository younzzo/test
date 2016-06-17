<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="false"%>

<%@include file="./include/header.jsp"%>

<div class="container">
	<h1>Board List</h1>
	<table class="table table-hover">
		<tr>
			<th>No.</th>
			<th>Title</th>
			<th>Content</th>
			<th>User_name</th>
			<th>날짜</th>
		</tr>
		<c:forEach items="${list}" var="vo">
			<tr>
				<td>${vo.board_no}</td>
				<td><a href="/rest/${vo.board_no}">${vo.title} [${vo.reply_cnt}]</a></td>
				<td>${vo.content}</td>
				<td>${vo.user_name}</td>
				<td>${vo.regdate}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@include file="./include/footer.jsp"%>