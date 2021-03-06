<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>相册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/pagination.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="Uploadify/uploadify.css">
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="Uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
    <script type="text/javascript">
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#stuForm").attr("action", "servlet/ShowImg?isadmin=${isadmin}&pageNum=" + (new_page_index+1));
	    $("#stuForm").submit();
	    return false;
	}
	$(function(){
		$("#News-Pagination").pagination(${result.totalRecord}, {
	        items_per_page:${result.pageSize}, // 每页显示多少条记录
	        current_page:${result.currentPage} - 1, // 当前显示第几页数据
	        num_display_entries:8, // 分页显示的条目数
	        next_text:"下一页",
	        prev_text:"上一页",
	        num_edge_entries:2, // 连接分页主体，显示的条目数
	        callback:handlePaginationClick
		});
		
	});
	</script>
<!-- 	<style type="text/css"> -->
<!-- /* 		#News-Pagination{margin:0 45%;text-align:center;width:1000px;} */ -->
<!-- 	</style> -->
  </head>
  
  <body>
  <div>
		<font color="red">${errorMsg }</font>
		<form action="servlet/ShowImg?isadmin=${isadmin }" id="stuForm" method="post">
			<input type="text" name="imgName" id="imgName"
				style="width:120px;display:none;"  />
		</form>
	</div>
  <!-- 相册页面 -->
		<c:if test="${fn:length(result.dataList) eq 0 }">
			<span style="display: block;text-align: center">查询的结果不存在</span>
		</c:if>
		<c:if test="${fn:length(result.dataList) gt 0 }">
		
			<c:forEach items="${result.dataList }" var="Img">
	           <a href="servlet/showDetailPage?imgName=${Img.photo}&isadmin=${isadmin }&url=${Img.saveFile}">
	           <img alt="${Img.imgName}" src="${Img.imgUrl}" width="240px" height="300px">
	           </a>
	           <a href="servlet/showDetailPage?imgName=${Img.photo}&isadmin=${isadmin }&url=${Img.saveFile}"><font color="blue">${Img.photo}</font></a>
	           　
<!-- 	           <a href="servlet/Delete?imgName=${Img.photo}&isadmin=${isadmin }&url=${Img.saveFile}" onclick="if(confirm('确定删除?')==false)return false;"><font color="red">删除</font></a> -->
	      
			</c:forEach>
		</c:if>
		<div id="News-Pagination"></div>
		<a href="">上传</a>
  </body>
</html>
