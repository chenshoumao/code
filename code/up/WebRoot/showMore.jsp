<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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


<link rel="stylesheet" type="text/css" href="myjs/reset.css">
<link rel="stylesheet" type="text/css" href="myjs/index_me.css">
<script type="text/javascript" src="myjs/jquery1.8.3.min.js"></script>
<!-- <script type="text/javascript" src="myjs/HeaderFooter.js"></script> -->
<!-- <script src="myjs/responsiveslides.min.js"></script> -->
<!-- banner导航条 -->


<link href="css/pagination.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="Uploadify/uploadify.css">
<!-- <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script> -->
<script type="text/javascript" src="Uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#stuForm").attr("action", "servlet/ShowImgMore?isadmin=1&pageNum=" + (new_page_index+1));
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
<style>
.images_1_of_4 {
    width: 20.8%;
    padding: 1.5%;
    position: relative;
}
.grid_1_of_4 {
    display: block;
    float: left;
    margin: 1% 0 1% 1.6%;
    background: #FFF;
    box-shadow: 0px 0px 5px #999;
}
.images_1_of_4 img {
    max-width: 100%;
    display: block;
    outline: none;
}
.grid_1_of_4:nth-child(4n+1) {
    margin-left: 0;
}
.grid_1_of_4 p{height: 20px;text-align: center;line-height: 35px;}
#pageGro {
    width: 294px;
    height: 25px;
    margin: 0px auto;
    padding-top: 30px;
}
.cb {
    clear: both;
}
#pageGro .pageUp {
    text-indent: 23px;
    background: url(./images/pageUp.png) 5px 7px no-repeat;
}
#pageGro div, #pageGro div ul li {
    font-size: 12px;
    color: #999;
    line-height: 23px;
    float: left;
    margin-left: 5px;
}
#pageGro div ul li.on {
    color: #fff;
    background: #005BC1;
    border: 1px solid #005BC1;
}
#pageGro div ul li {
    width: 22px;
    text-align: center;
    border: 1px solid #999;
    cursor: pointer;
}
#pageGro .pageDown {
    text-indent: 5px;
    background: url(images/pageDown.png) 46px 6px no-repeat;
}
#pageGro .pageUp, #pageGro .pageDown {
    width: 63px;
    border: 1px solid #999;
    cursor: pointer;
}
</style>
</head>

<body>
     <div>
		<font color="red">${errorMsg }</font>
		<form action="servlet/ShowImg?isadmin=0" id="stuForm" method="post">
			<input type="text" name="imgName" id="imgName"
				style="width:120px;display:none;"  />
		</form>
	</div>

	<!-- hender 
	<div id="header"></div>-->
	<!-- banner 
	<div id="banner"></div>-->
	<!-- container -->
	<div class="container">
	    <!-- nav 
		<div id="nav"></div>-->
		<!-- main -->
			<div class="main">
			
				<c:if test="${fn:length(result.dataList) gt 0 }">
					<c:forEach items="${result.dataList }" var="Img">
					<div class="grid_1_of_4 images_1_of_4">
						<a class="fancybox" href="servlet/showDetailPage?imgName=${Img.photo}&isadmin=0&url=${Img.saveFile}" data-fancybox-group="gallery"> <img alt="${Img.imgName}"
							src="${Img.imgUrl}" width="229px" height="143px">
							</a>
							<a href="servlet/showDetailPage?imgName=${Img.photo}&isadmin=0&url=${Img.saveFile}"><font color="blue"><p>${Img.photo}<p></font></a>
					</div>
					</c:forEach>
				</c:if>
	
			</div>
			<div id="pageGro" class="cb">
			   <div id="News-Pagination"></div>
			 
			</div>
		</div>
		<br clear="both">
	<div id="footer"></div>
		  <a href="">上传</a>
		
	

</body>
</html>
