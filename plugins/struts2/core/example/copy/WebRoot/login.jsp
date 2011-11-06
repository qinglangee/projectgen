<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
	  
	<body>
		<!-- 提交请求参数的表单 -->  
		<form action="login.action" method="post">  
		    <table align="center">  
		    <caption><h3>用户登录</h3></caption>  
		        <tr>  
		            <td>用户名：<input type="text" name="username"/></td>  
		        </tr>  
		        <tr>  
		            <td>密码：<input type="text" name="password"/></td>  
		        </tr>  
		        <tr align="center">  
		            <td colspan="2"><input type="submit" value="登录"/><input type="reset" value="重填"/></td>  
		        </tr>  
		    </table>  
		</form>  
	</body>
</html>
