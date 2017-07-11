<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" 	src="<%=basePath%>scripts/js/pdfobject.js"></script>
	<script type="text/javascript" 	src="<%=basePath%>scripts/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			 alert(123);
			  //  var url = "<c:url value='<%=basePath%>/scan/showWord.action'/>";

			    var x = (screen.availWidth - 1000) / 2;
			    var y = (screen.availHeight - 600) / 2;
			    var sw = 1000;      
			    var sh = 600;
			    var win = window.open('<%=basePath%>scan/showWord.action', '预览', 'width='+sw+',height='+sh+',top='+y+',left='+x+',location=no,scrollbars=yes,resizable=no,allwaysRaised');
			    win.focus(); 
		})
	 
	</script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
