<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
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
.div{ margin:0 auto; border:1px solid #F00} 
</style>
</head>
<body>
	<div style="text-align:center;" >
 		<table id="table" class="div"> 
 			 	<tr>
 			 		<th>系统名称</th>
 			 		<th>标题</th> 
 			 		<th>接受时间</th>
 			 		<th>等待时间</th> 
 			 	</tr>
 			 	<c:forEach items="${list }" var="list">
 			 	<tr>
 			 		<td>  
 			 			${list.enName }
 			 		</td>
 			 		<td> 
 			 			<a href="${list.todoUrl }?title=${list.title }&enName=${list.enName }&receiveTime=${list.receiveTime }&pendingName=${list.pendingName }" target="_blank">${list.title }</a>
 			 		</td>
 			 		<td> 
 			 			${list.receiveTime }
 			 		</td>
 			 		<td> 
 			 			${list.pendingName }
 			 			
 			 		</td>
 			 	</tr>
 			 	</c:forEach>
 			 	 
 		</table>
 	</div>
</body>
</html>