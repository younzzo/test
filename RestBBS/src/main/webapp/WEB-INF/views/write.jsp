<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="utf-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>게시판 등록</title>
</head>
<body>
<h1>게시판 등록</h1>
	<form action="/rest/" method="post">	
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" ></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><input type="text" name="content" ></td>
			</tr>
			<tr>
				<th>사용자 이름</th>
				<td><input type="text" name="user_name" ></td>
			</tr>
		</table>
			<input type="submit" value="등록">
	</form>
	
</body>
</html>
