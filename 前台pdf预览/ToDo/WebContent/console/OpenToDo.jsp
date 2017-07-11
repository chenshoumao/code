<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath%>scripts/js/jquery1.8.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		var list = "${list}";
		var json = JSON.stringify(list);
		 
		 
		//var json = eval(list);
		// var length = json.size();
	 //   console.log(list.getSize());
	})
</script>
<style type="text/css">
table {
	border-right: 1px solid red;
	border-bottom: 1px solid red;
	border-collapse: collapse;
}

th,td {
	border-left: 1px solid red;
	border-top: 1px solid red;
}
</style>
</head>
<body>
	  
	<div>
 		<table id="table"> 
 			 	<tr>
 			 		<th rowspan="${list.size()+1 }">SystemName</th>
 			 		<th>title</th> 
 			 	</tr>
 			 	<c:forEach items="${list }" var="list">
 			 	<tr>
 			 		<td> 
 			 			<a href="${list.url }" target="_blank">${list.title }</a>
 			 		</td>
 			 	</tr>
 			 	</c:forEach>
 			 	 
 		</table>
 	</div>
</body>
</html>