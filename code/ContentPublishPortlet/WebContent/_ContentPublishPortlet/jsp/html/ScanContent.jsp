<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
 
<%
String path1 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="stylesheet" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" href="<%=basePath%>css/index.css">
     <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css" type="text/css">
     <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery.ztree.core-3.5.js"></script>
   
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>lang/zh-cn/zh-cn.js"></script>
	<!--<script type="text/javascript" src="<%=basePath%>scripts/common/jquery-easyui/jquery.min.js"></script>-->
<script type="text/javascript" src="<%=basePath%>scripts/common/jquery-easyui/jquery.easyui.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
        <style type="text/css">
		.mar{
			margin-right: 20px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			 
		})
	 	
	</script>
 </head>
  
 <body>
     <iframe src="" id="myframe" style="height:800px;width:500px;"></iframe>
</body>
 	  
</script>
</html>

