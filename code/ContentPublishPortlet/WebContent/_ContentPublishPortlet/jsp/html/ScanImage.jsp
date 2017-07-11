<%@page session="false" contentType="text/html" pageEncoding="UTF-8" import="java.util.*,javax.portlet.*,com.ibm.contentpublishportlet.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 



 
<%
String path1 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
com.ibm.contentpublishportlet.ContentPublishPortletSessionBean sessionBean = (com.ibm.contentpublishportlet.ContentPublishPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.ibm.contentpublishportlet.ContentPublishPortlet.SESSION_BEAN);
String folderId = sessionBean.getFormText();
String stage = sessionBean.getStage();
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
        
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/index_me.css">
	<!-- 相册样式 -->
	<link href="<%=basePath%>css/blue.css" rel="stylesheet" type="text/css" media="all" />
	<link href="<%=basePath%>css/grids.css" rel="stylesheet" type="text/css" media="all" />
	<link href="<%=basePath%>css/album.css" rel="stylesheet" type="text/css" media="all" />
	<link href="<%=basePath%>css/base.css" rel="stylesheet" type="text/css" media="all" />
	<link rel="stylesheet" href="<%=basePath%>css/easydialog.css" />

	<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>Uploadify/uploadify.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>Uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/album.js"></script>
 	<script type="text/javascript" src="<%=basePath%>js/carousel.js"></script>
	<script src="<%=basePath%>js/easydialog.js"></script>
	<link href="<%=basePath%>css/pure-min.css" rel="stylesheet" type="text/css" media="all" />
	 

        

<script type="text/javascript">
	$(function(){
		console.log("<%=folderId%>"); 
		loadImage();
	})
	function loadImage(){
		
		$.ajax({
			url:'<%=path%>wps/myportal/AppData/picture/getImageByAlbum.action',
			data:{'albumId':"<%=folderId%>",'stage':'<%=stage%>'},
			type:'post',
			dataType:'json',
			success:function(data){
				if(!data['isadmin']){
					$("#delimg").hide();
				}
				$("#album-carousel-list").empty();
				var list = data['list'];
				for(var i = 0; i < list.length;i++){
					var img = list[i];
					var item = '<li class="album-carousel-thumb">'
					  +'<a href="'+img.imgUrl+'" id="'+img.imgNumId+'"><img src="'+img.imgUrl+'" alt="'+img.imgUrl+'" width="230" height="144" /></a>'
					  +'<span class="imageName"  style="display:none;">'+img.imgName+'</span>'
					  +'<span class="photo" style="display:none;" >123</span>'
					  +'</li>';
					 
					$("#album-carousel-list").append(item);
				} 
				var Album = new jQuery.Album();
				var $1 = function() {
					return document.getElementById(arguments[0]);
				};
				 
				
			}
			
		 }) 
	}
		

	function back(a){
		$("#pageInfo").val("ContentPublishPortletView");
		$("#form5").submit();
	}
	function downloadImage(){
		var id= $(".album-carousel-thumb-selected").find("a").attr("id");
		//window.open("servlet/DownImg?url="+tohref);
		
		window.open("<%=path%>wps/myportal/AppData/picture/downLoadImage.action?id="+id);
		   
		
	}
	function deleteImage(){
		var id= $(".album-carousel-thumb-selected").find("a").attr("id");
		//window.open("servlet/DownImg?url="+tohref);
		 
		$.ajax({
			url:'<%=path%>wps/myportal/AppData/picture/deleteImage.action',
			data:{'id':id},
			dataType:'json',
			type:'post',
			success:function(data){
				if(data.result)
					alert("删除成功");
				loadImage();
			}
		});
		
		   
		
	}

</script> 
<body>
	<FORM method="POST" action="<portlet:actionURL/>" id="form5" style="display:none;"> 
		<INPUT name="<%=com.ibm.contentpublishportlet.ContentPublishPortlet.FORM_TEXT%>" type="text" id="portletParam"/>
		<INPUT name="pageInfo" type="text" id="pageInfo"/>
		<INPUT name="<%=com.ibm.contentpublishportlet.ContentPublishPortlet.FORM_SUBMIT%>" value="Submit"/>
		 
	</FORM>
	 
	<div class="container"> 

		<div class="album" id="album">
	 		 <div class="album-image-md" id="album-image-md"  >
		  		<p class="album-image-bd">
					<img src="" class="album-image" id="album-image" alt="相册图片-示例图片（1）" width="674px" height="750px" />
				</p>
		  		<a href="" class="album-download" style="left:8px;"><span>返回</span></a> 
		        	<a href="" class="album-download" style="left:430px;" onclick="deleteImage();return false;" id="delimg">
					<span>删除本图</span>
				</a> 	  
		 		<a class="album-download" id="album-download"  target="_blank" onclick="downloadImage();return false;" >
					<span>下载图片</span>
				</a>
		  		<ul class="album-image-nav hide" id="album-image-nav">
		  	  		<li class="album-image-nav-left-block" id="album-image-nav-left-block"><a href="#prev-image" class="album-image-btn-prev" 						id="album-image-btn-prev">‹</a></li>
			  		<li class="album-image-nav-right-block" id="album-image-nav-right-block"><a href="#next-image" class="album-image-btn-next" 						id="album-image-btn-next">›</a></li>
		 		 </ul>
		 		 <p class="album-image-loading-overlay hide" id="album-image-loading-overlay">
					<img src="<%=basePath%>images/loading.gif" alt="loading..." width="100" height="100" />
				</p>
			 </div> 
	 
	 		<div class="album-carousel" id="album-carousel">
	 	  		<a href="#prev-group" class="album-carousel-btn-prev" id="album-carousel-btn-prev">‹</a>
		  		<div class="album-carousel-zone" id="album-carousel-zone">
		 			<ul class="album-carousel-list" id="album-carousel-list"> 
		  			</ul>
		  		</div>
	 	  		<a href="#next-group" class="album-carousel-btn-next" id="album-carousel-btn-next"></a>
	 		</div>  
		</div>

	</div>
	<br clear="both">
	<div style=display:none;'>cehshi</div>
</body>
</html>

	



