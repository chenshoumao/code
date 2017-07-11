<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
 
<%
String path1 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP </title>

	 <link rel="stylesheet" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" href="<%=basePath%>css/index.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/common/jquery-easyui/jquery.easyui.min.js"></script>
	<script  type="text/javascript">
		//自定义左边的树数据的参数
		var siteArea = ""; 
		var setting = {
			data: {
				key: {
					title:"t"
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			},
			async: {
				enable: true,
				url:"/wps/myportal/AppData/SolarNewsPortlet/getChileSite.action",
				autoParam:["id", "t","level"],
				dataFilter: filter
			}
		};
		var zNodes =[  
            { id:1, pId:0, name:"内网",t:"intranet",level:1,isParent:true},  
            { id:2, pId:0, name:"外网",t:"extranet",level:1,isParent:true}
        ];
		
		//开始执行生成数
		$(document).ready(function(){
			$("#draft").hide(); //默认隐藏草稿按钮
			$("#approve").hide();	//默认隐藏已发布按钮
			$("#publish").hide();   //默认隐藏 发布内容 按钮
			$.fn.zTree.init($("#treeDemo"), setting, zNodes); 
		});
		
		//这个方法与生成树有关
		function beforeClick(treeId, treeNode, clickFlag) { 
			return (treeNode.click != false);
		} 
		
		//这个方法与生成树有关
		function onClick(event, treeId, treeNode, clickFlag) { 
			$("#address").html(treeNode.name);
			$("#area").val(treeNode.t);	
			$("#areatitle").val(treeNode.name);
			siteArea = treeNode.t;
			alert(siteArea);
			if(siteArea == "")
				window.location.reload();
			else{ 
				changeIframeSrc(2);
			}
		}
		
		//这个方法与生成树有关
		function filter(treeId, parentNode, childNodes) {
			 if (!childNodes) return null; 
			 for (var i=0, l=childNodes.length; i<l; i++) { 
				
				 childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			 }
			 return childNodes;
		}
		
		//根据不同的参数改变不同的iframe的src
		function changeIframeSrc(a){
			var iframeMoth = $("#iframeBox");
			if(a=="1"||a==1){
				iframeMoth.attr("src","PE_5.jsp");
			}else if(a==2||a=="2"){
				iframeMoth.attr("src","PF_2.jsp?shh=9023");
			}
		}
		
	</script>
 </head>
 
 <body>
 <div class="layout" style="width:1100px;height:800px;">
    <aside class="layout-side" style="margin-top:50px;min-height:800px;">
        <ul id="treeDemo" class="ztree"></ul>
    </aside>
	<div class="layout-main">
		<iframe id="iframeBox" src="firstPage100.jsp" style="width:1200px; height:500px;"></iframe>
	</div>
</div>
 </body>
 </html>