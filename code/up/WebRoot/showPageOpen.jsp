<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>图片展示</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="myjs/reset.css">
	<link rel="stylesheet" type="text/css" href="myjs/index_me.css">
	<!-- 相册样式 -->
	<link href="myjs/blue.css" rel="stylesheet" type="text/css" media="all" />
	<link href="myjs/grids.css" rel="stylesheet" type="text/css" media="all" />
	<link href="myjs/album.css" rel="stylesheet" type="text/css" media="all" />
	<link href="myjs/base.css" rel="stylesheet" type="text/css" media="all" />
	<!-- 脚本 -->
	<script type="text/javascript" src="myjs/jquery1.8.3.min.js"></script>
<!-- 	<script type="text/javascript" src="myjs/HeaderFooter.js"></script> -->
<!--	<script src="myjs/responsiveslides.min.js"></script>  banner导航条 -->
	<link href="myjs/pure-min.css" rel="stylesheet" type="text/css" media="all" />

<link href="css/pagination.css" rel="stylesheet" type="text/css" />
<!-- <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script> -->
<script type="text/javascript" src="js/jquery.pagination.js"></script>
</head>
<body>
	 
	<div class="container">
	 
		

<div class="album" id="album">
	 <div class="album-image-md" id="album-image-md">
		  <p class="album-image-bd"><img src="" class="album-image" id="album-image" alt="相册图片-示例图片（1）" width="674px" height="750px" /></p>
		  		  <a href="servlet/ShowImgMore?isadmin=0" class="album-download" style="left:8px;"><span>返回相册</span></a> 	  
		 		  <a class="album-download" id="album-download" href="images/01.jpg" target="_blank" onclick="ceshi(this);return false;" ><span>下载图片</span></a>
		  <ul class="album-image-nav hide" id="album-image-nav">
		  	  <li class="album-image-nav-left-block" id="album-image-nav-left-block"><a href="#prev-image" class="album-image-btn-prev" id="album-image-btn-prev">‹</a></li>
			  <li class="album-image-nav-right-block" id="album-image-nav-right-block"><a href="#next-image" class="album-image-btn-next" id="album-image-btn-next">›</a></li>
		  </ul>
		  <p class="album-image-loading-overlay hide" id="album-image-loading-overlay"><img src="images/loading.gif" alt="loading..." width="100" height="100" /></p>
	 </div>
	 <div class="album-carousel" id="album-carousel">
	 	  <a href="#prev-group" class="album-carousel-btn-prev" id="album-carousel-btn-prev">‹</a>
		  <div class="album-carousel-zone" id="album-carousel-zone">
		  <ul class="album-carousel-list" id="album-carousel-list">

			<c:if test="${fn:length(result.dataList) gt 0 }">
				<c:forEach items="${result.dataList }" var="Img">
					<li class="album-carousel-thumb"><a href="${Img.imgUrl }"><img src="${Img.imgUrl }" alt="${Img.imgUrl }" width="230" height="144" /></a>
					<span class="imageName" style=display:none;'>${Img.imgName}</span>
					<span class="photo" style=display:none;'>${Img.photo}</span>
					</li>
				</c:forEach>
			</c:if>
		  </ul>
		  </div>
	 	  <a href="#next-group" class="album-carousel-btn-next" id="album-carousel-btn-next"></a>
	 </div>
</div>

	</div>
	<br clear="both">
	<div style=display:none;'>cehshi</div>
	<div id="footer"></div>
   
</body>
<script type="text/javascript" src="myjs/carousel.js"></script>
<script type="text/javascript" src="myjs/album.js"></script>
<script type="text/javascript">
function ceshi(){
	var tohref= $("#album-download").attr("href");
	window.open("servlet/DownImg?url="+tohref);
// 	window.location.href("servlet/DownImg?url="+tohref);
}
var Album = new jQuery.Album();
</script>

</html>
