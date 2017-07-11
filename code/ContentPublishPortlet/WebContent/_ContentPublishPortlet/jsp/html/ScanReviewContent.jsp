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
String contentName = sessionBean.getFormText();
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
	 
    <link rel="stylesheet" href="<%=basePath%>css/index.css">
     <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css" type="text/css">
     <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery.ztree.core-3.5.js"></script>
   
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>lang/zh-cn/zh-cn.js"></script>
	<!--<script type="text/javascript" src="<%=basePath%>scripts/common/jquery-easyui/jquery.min.js"></script>-->
<script type="text/javascript" src="<%=basePath%>scripts/common/jquery-easyui/jquery.easyui.min.js"></script>
	<!--
	<l=ink rel="stylesheet" type="text/css" href="styles.css">
	-->
        <style type="text/css">
		.mar{
			margin-right: 20px;
		}
		 
	.article{
	margin-top: 10px;
	width: 96% !important;
	border: 1px solid #dcdcdc;
	text-align: center;
	padding: 30px 20px 50px 20px;
	color: #666666;
}
.article > span{
	font-size: 26px;
}
 
.article h1{
	margin:0px 0px 0px 0px;
	width: 100%;
	height: 30px;
	background-color: #fbfbfb;
	line-height: 28px;
}
.article h1 span{
	margin-right: 30px;
}
.article p{
	text-align: left;
	text-indent:2em;
	line-height: 20px;
	width: 965px;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 25px;
}
	.tes {
	 
    text-align: center;
    margin:-20px 0px 20px 0px;
    
    font-size: 18px;
}
	.contentTitle{
		text-align: center;
		margin-left: auto;
		margin-right: auto;
		display: block;
		font-size: 30px;
	}
 	.inputLeft{
		 
		margin-bottom: 50px;
		 
	}
	label{
		font-size: 20px ;
	}
	</style>

<script type="text/javascript">
	$(function(){
		var contentName = "<%=contentName%>";
		  console.log(contentName);
		   
		   $.ajax({
		      	url:'/wps/myportal/AppData/SolarNewsPortlet/getReviewContentByName.action',
			data:{'contentName':contentName},
			dataType:'json',
			type:'post',
			success:function(data){ 
				 
				var title = data.title;
				var source = data.source;
				var imggeUrl = data.image;
				var summary = data.summary;
				var number = data.number;
				var content = data.content;  
				
				$("#title").html("<h1>"+title+"</h1>");
				 
				$("#publishdate").html("发布日期："+data.createTime);  
				$("#source").html("来源 : " + source);  

				$.each(data,function(key,values){     
    					console.log(key);  
					if(key == "number"){
						//alert($("#number").children().length);
						$("#number").find("div").eq(0).html("招商编号: " + values);
						 
						$("#number").show();
					}
					else if(key == "summary"){
						 
						$("#summary").find("div").eq(0).html("简介: " + values);
						 
						$("#summary").show();
					}
					 
					else if(key == "content"){ 
						$("#contentBody").show();
						$("#contentText").html(values);
						 
					} 
    					    
 				}); 
			}
		  });
	})

	function back(a){
		$("#pageInfo").val("ContentPublishPortletView");
		$("#form5").submit();
	}
</script> 
<body>
	<FORM method="POST" action="<portlet:actionURL/>" id="form5" style="display:none;"> 
		<INPUT name="<%=com.ibm.contentpublishportlet.ContentPublishPortlet.FORM_TEXT%>" type="text" id="portletParam"/>
		<INPUT name="pageInfo" type="text" id="pageInfo"/>
		<INPUT name="<%=com.ibm.contentpublishportlet.ContentPublishPortlet.FORM_SUBMIT%>" value="Submit"/>
		 
	</FORM>
	<div id="scan" >
		<input type="button" class="btn btn-primary" onclick="back(this)" value="返回"/>  
		 
		<form id="form3" action="/wps/myportal/AppData/SolarNewsPortlet/updateContent.action" method="post"  enctype="multipart/form-data" >
			<input type="hidden" id="name" name="name">
			<div class="article" style="height:1000px;">
				<!-- 标题 -->
				<div class="row clearfix span"> 
						<div class="col-md-12 column" id="title">  
						</div> 
				</div>
				  
					
				 <div class="row clearfix span">
					<div class="col-md-12 column"> 
							<dt id="publishdate"></dt>
							<dt id="source"></dt> 
					</div>
				</div>
				 
				<div class="row clearfix span">
					 
					<div class="col-md-12 column">  
								<dt id="number"></dt>  
					</div> 
				</div>
				
				
				<div class="span" id="contentBody">
					<input name="content" style="display:none;">
					<div id="contentText">
						 
					</div>
					 
				</div> 
			</div>
		</form>
 
		
	<div>
</body>
</html>

	



