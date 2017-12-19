<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>学生管理系统-新增学生</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="/static/css/public.css" rel="stylesheet">
<link href="/static/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
	<div class="index_con container">
		<div class="formBox">
			<a style="margin: 23px;"  href="Javascript:history.go(-1);void(0);" >返回</a>
			<form class="form-horizontal" role="form" action="/user/saveuser"
				method="post">
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="firstname"
							name="username" placeholder="请输入姓名">
					</div>
				</div>
				<div class="form-group">
					<label for="lastname" class="col-sm-2 control-label"> 密码</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="lastname"
							placeholder="请输入密码">
					</div>
				</div>
				<div class="form-group">
					<label for="lastname" class="col-sm-2 control-label">年龄</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="lastname" name="age"
							placeholder="请输入年龄">
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10" style="text-align: center;">
						<button  type="submit" class="btn btn-default">提交</button>
					</div>
				</div>
			</form>
		</div>

	</div>

</body>
</html>
