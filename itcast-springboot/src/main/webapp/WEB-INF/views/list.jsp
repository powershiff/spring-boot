<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<%@ taglib uri='/WEB-INF/tld/c.tld' prefix='c'%>
<title>学生列表</title>
 <link rel="icon" type="image/x-icon" href="/static/image/favicon.ico">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="/static/css/bootstrap.min.css" rel="stylesheet">
<link href="/static/css/public.css" rel="stylesheet">
<style>
.addBtn{ display:block; text-align:right; padding:20px 0;}
</style>
</head>

<body>

	<div class="index_con container">
	<h3 align="center">学生名单</h3> 
	<a href="/user/addUser" class="addBtn"> 新增</a>
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>姓名</th>
				<th>年龄</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="u">
				<tr>
					<td>${u.id }</td>
					<td>${u.username}</td>
					<td>${u.age}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>
