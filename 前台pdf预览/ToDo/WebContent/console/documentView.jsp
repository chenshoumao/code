<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线预览</title> 
<style type="text/css">
table {
	border-right: 1px solid silver;
	border-bottom: 1px solid silver;
	border-collapse: collapse;
} 
th,td {
	border-left: 1px solid silver;
	border-top: 1px solid silver;
	width:250px;
}
.div{ margin:0 auto; border:1px solid silver} 
</style>
      <script type="text/javascript" 	src="<%=basePath%>scripts/js/pdfobject.js"></script>
        
 
        <script type="text/javascript">
        window.onload = function (){
            var success = new PDFObject({ url: "<%=basePath%>file/111.pdf" }).embed("pdf");
     	};
      //http://blog.csdn.net/kek_kek/article/details/46235785
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
 			 	 
 			 	 <tr style="height:380px;" >
 			 	 	<td colspan="5" id="pdf"></td>
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