<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath =path + "/";
%>
<!DOCTYPE html>

<html>
<head>
<script type="text/javascript"
	src="<%=basePath%>scripts/js/jquery-1.11.0.min.js"></script>
<script src="<%=basePath%>scripts/js/bootstrap.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="css/styles.css">
<style type="text/css">
	body{
		background: #999999;
	}
	.sidebar-nav .nav-header{
		background: #99CC99;
	}
	.sidebar-nav .nav-header:HOVER{
		background: #999966;
	}
</style>
</head>
<body>
	
	  
	 
   
    <div class="sidebar-nav">
    	  
     <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>北斗<span class="caret" style="position: relative;left: 115px"></span></a>
        <ul id="dashboard-menu" class="nav nav-list collapse">
            <li><a href="<%=basePath%>DeviceActionQuery.jsp">&nbsp;&nbsp;&nbsp;&nbsp;设备位置查询</a></li>
            <li ><a href="<%=basePath%>TrackActionQuery.jsp">&nbsp;&nbsp;&nbsp;&nbsp;历史轨迹查询</a></li>
            <li ><a href="<%=basePath%>AlarmActionShow.jsp">&nbsp;&nbsp;&nbsp;&nbsp;报警信息查询</a></li>
            <li ><a href="<%=basePath%>DeviceListQuery.jsp">&nbsp;&nbsp;&nbsp;&nbsp;用户设备查询</a></li>
            <li ><a href="<%=basePath%>DeviceOnQuery.jsp">&nbsp;&nbsp;&nbsp;&nbsp;在线设备查询</a></li>
            <li ><a href="<%=basePath%>DeviceOffQuery.jsp">&nbsp;&nbsp;&nbsp;&nbsp;离线设备查询</a></li>
        </ul>     

	 <!-- <a href="<%=basePath%>driver/queryDriver.do?nextPage=1" class="nav-header" ><i class="icon-comment"></i>司机资料管理</a> --> 
	   
    </div>
</body>
</html>


