<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-right: 1px solid silver;
	border-bottom: 1px solid silver;
	border-collapse: collapse;
} 
th,td {
	border-left: 1px solid silver;
	border-top: 1px solid silver;
}
.div{ margin:0 auto; border:1px solid silver} 
</style>
<script type="text/javascript">
	function downFile(){
	 	window.location.href = '<%=basePath %>todo/download.action?fileUrl=C:\\Users\\ibmchenshoumao\\Desktop\\url.properties&filename=文件.doc';
	 
	}
</script>
</head>
<body>
	<div style="text-align:center;" >
 		<table id="table" class="div"> 
 			 	<tr>
 			 		<th>系统名称</th> 
 			 		<th>标题</th> 
 			 		<th>当前状态</th>  
 			 		<th>申请时间</th>
 			 		<th>发送者</th> 
 			 	</tr>
 			 	 
 			 	 <tr>
 			 	 	<td>${todo.enName }</td>
 			 	 	<td>${todo.title }</td>
 			 	 	<td>待审核</td>
 			 	 	<td>${todo.receiveTime}</td>
 			 	 	<td>${todo.pendingName}</td>
 			 	 </tr>
 			 	 
 			 	 <tr style="height:180px;" >
 			 	 	<td colspan="5">${todo.enName }</td>
 			 	 </tr>
 			 	 <tr style="height:80px;" >
 			 	 	<td colspan="5">相关文件
 			 	 		<a onclick="downFile()">下载</a>
 			 	 	</td>
 			 	 </tr>
 		</table>
 	</div>
</body>
</html>