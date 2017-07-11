<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Detail.jsp' starting page</title>
    
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
  xiangxi
   <c:forEach var="Img" items="${listImg}">
      <tr>
           <td><img alt="${Img.imgName}" src="${Img.imgUrl}" width="300px" height="300px"></td>
           <td>图片名称：${Img.imgName}</td>
<!--            <td>相册名称：${Img.photo}</td> -->
           <td><a href="servlet/DeleteImg?imgName=${Img.imgName}&photo=${Img.photo}" onclick="if(confirm('确定删除?')==false)return false;">删除</a></td>
          
      </tr>
     
</c:forEach>
<br/><br/><br/><br/>
<a href="servlet/ShowImg">返回相册</a>
  </body>
</html>
