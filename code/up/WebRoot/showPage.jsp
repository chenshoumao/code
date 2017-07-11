<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath2 = path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/index_me.css">
	<!-- 相册样式 -->
	<link href="css/blue.css" rel="stylesheet" type="text/css" media="all" />
	<link href="css/grids.css" rel="stylesheet" type="text/css" media="all" />
	<link href="css/album.css" rel="stylesheet" type="text/css" media="all" />
	<link href="css/base.css" rel="stylesheet" type="text/css" media="all" />
	<link rel="stylesheet" href="css/easydialog.css" />

	<link href="css/pagination.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="Uploadify/uploadify.css">
	<script type="text/javascript" src="js/jquery1.8.3.min.js"></script>
	<script type="text/javascript" src="Uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="js/jquery.pagination.js"></script>
	<script type="text/javascript" src="js/carousel.js"></script>
    <script type="text/javascript" src="js/album.js"></script>
	<script src="js/easydialog.js"></script>
	<link href="css/pure-min.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript">
	var photo;
	/* $(function() {
		photo = $("#imgName").val();
		 var newImg = "<%=basePath2%>servlet/showDetailPage?newImg=all&imgName="+photo;
		 $('#uploadify').uploadify({
		        'swf'      : '<%=basePath%>uploadify.swf',
		        'uploader'         : '<%=basePath2%>servlet/Upload',
		        'cancelImg'      : '<%=basePath2%>Uploadify/uploadify-cancel.png',
		         'folder'         : 'uploads',
		         'fileDataName'   : 'uploadify',//
		         fileSizeLimit   : 1024,//KB,文件大小
		         'queueID'        : 'fileQueue',
		         'auto'           : false,//是否选取文件后自动上传
		         'multi'          : true,//是否支持多文件上传
		         'simUploadLimit' : 20,//每次最大上传文件数
		         'buttonText'     : '选择文件',
		         'buttonImg'      : 'images/uploadify-cancel.png',
		         'displayData'    : 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比 
		         'fileTypeExts' 		: '*.gif; *.jpg; *.png',
		         'onQueueComplete' : function(file, data, response) {
	       		 window.location = "<%=basePath2%>servlet/showDetailPage?newImg=all&imgName="+photo+"&isadmin=1&url=${url}";
	       	    }
		         
		    });
	
	});*/
    
    function up(){
		 $.ajax({
		   type:"post",//请求方式
		  url:"servlet/CreatePhoto",//发送请求地址
		   data:{//发送给数据库的数据
			   description:photo
		   },
		   //请求成功后的回调函数有两个参数
		   success:function(){
			   jQuery('#uploadify').uploadify('upload','*');
		   }
		   });
    }
    </script>

<script type="text/javascript">
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#stuForm").attr("action", "servlet/showDetailPage?url=${result.upurl}&isadmin=1&pageNum=" + (new_page_index+1));
	    $("#stuForm").submit();
	    return false;
	}
	

// 	function delimg(){
// 		alert("in");
// 		var tohref= $("#album-download").attr("href");
// 		alert(tohref);
// 		window.open(tohref);
// 		window.location.reload();
// 		$.ajax({
// 			type: "GET",
// 			url: tohref,
// 			 dataType: "json",
// 			 success: function(data){
// 				 window.location.reload();
// 			 },
// 			 error:function(data){
// 				 window.location.reload();
				 
// 			 }
// 		});
		
// 	}
	$(function(){
	    /*var Album=new jQuery.Album();
	    if(Album==undefined||Album==""){
	        Album = new jQuery.Album();
	    } */
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

  </head>
  
  <body>
   
	<!-- container -->
	<div class="container">
		<!-- main -->
		

<div class="album" id="album">
	 <div class="album-image-md" id="album-image-md">
		  <p class="album-image-bd"><img src="" class="album-image" id="album-image" alt="相册图片-示例图片（1）" width="674px" height="750px" /></p>
		  		   <a href="servlet/ShowImgMore?isadmin=1" class="album-download" style="left:8px;"><span>返回相册</span></a> 
		  		   <a href="javascript:void(0);"  class="album-download" style="left:430px;" id="delimg"><span>删除本图</span></a> 	  
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
</body>
<!-- <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script> -->
 

<script type="text/javascript">
function ceshi(){
	var tohref= $("#album-download").attr("href");
	window.open("servlet/DownImg?url="+tohref);
}

var Album = new jQuery.Album();
var $1 = function() {
	return document.getElementById(arguments[0]);
};
$("#delimg").bind("click",function(){
	var deFn = function(){
		var tohref= $("#album-download").attr("href");
		$.ajax({
			type: "GET",
			url: "servlet/DeleteImg?url="+tohref,
			 dataType: "json",
			 success: function(data){
				 window.location.reload();
			 },
			 error:function(data){
				 window.location.reload();
				 
			 }
		});
	}
	easyDialog.open({
		container: {
			header: '照片信息修改',
			content: '<p>你确定要删除这张图片吗？<p>',
			yesFn: deFn,
			noFn: true
		},
		fixed: false
	});
});
</script>

</html>
