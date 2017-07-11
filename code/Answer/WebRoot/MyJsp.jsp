<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
    String path = request.getContextPath(); 
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE> ZTREE DEMO - Simple Data</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/demo.css" type="text/css">
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
	<!--  <script type="text/javascript" src="../../../js/jquery.ztree.excheck-3.5.js"></script>
	  <script type="text/javascript" src="../../../js/jquery.ztree.exedit-3.5.js"></script>-->
	<SCRIPT type="text/javascript">
		<!--
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"父节点1 - 展开", open:true},
			{ id:11, pId:1, name:"父节点11 - 折叠"},
			{ id:111, pId:11, name:"叶子节点111"},
			{ id:112, pId:11, name:"叶子节点112"},
			{ id:113, pId:11, name:"叶子节点113"},
			{ id:114, pId:11, name:"叶子节点114"},
			{ id:12, pId:1, name:"父节点12 - 折叠"},
			{ id:121, pId:12, name:"叶子节点121"},
			{ id:122, pId:12, name:"叶子节点122"},
			{ id:123, pId:12, name:"叶子节点123"},
			{ id:124, pId:12, name:"叶子节点124"},
			{ id:13, pId:1, name:"父节点13 - 没有子节点", isParent:true},
			{ id:2, pId:0, name:"父节点2 - 折叠"},
			{ id:21, pId:2, name:"父节点21 - 展开", open:true},
			{ id:211, pId:21, name:"叶子节点211"},
			{ id:212, pId:21, name:"叶子节点212"},
			{ id:213, pId:21, name:"叶子节点213"},
			{ id:214, pId:21, name:"叶子节点214"},
			{ id:22, pId:2, name:"父节点22 - 折叠"},
			{ id:221, pId:22, name:"叶子节点221"},
			{ id:222, pId:22, name:"叶子节点222"},
			{ id:223, pId:22, name:"叶子节点223"},
			{ id:224, pId:22, name:"叶子节点224"},
			{ id:23, pId:2, name:"父节点23 - 折叠"},
			{ id:231, pId:23, name:"叶子节点231"},
			{ id:232, pId:23, name:"叶子节点232"},
			{ id:233, pId:23, name:"叶子节点233"},
			{ id:234, pId:23, name:"叶子节点234"},
			{ id:3, pId:0, name:"父节点3 - 没有子节点", isParent:true}
		];

		$(document).ready(function(){
			var nds;
			$.ajax({
				url:"<%=basePath%>GroupLdap/showsAllGroup.action",
				type:'post',
				dataType:"json",
				success:function(map){
					nds = map.tree;
					$.fn.zTree.init($("#treeDemo"), setting, nds);
				}
			});
			//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//$.fn.zTree.init($("#treeDemo"), setting, nds);
		});
		//-->
	</SCRIPT>
 </HEAD>

<BODY>
<h1>最简单的树 -- 简单 JSON 数据</h1>
<h6>[ 文件路径: core/simpleData.html ]</h6>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	 
</div>
</BODY>
</HTML>