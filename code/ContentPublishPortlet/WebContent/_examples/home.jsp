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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="stylesheet" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" href="<%=basePath%>css/index.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>lang/zh-cn/zh-cn.js"></script>
	<!--<script type="text/javascript" src="<%=basePath%>scripts/common/jquery-easyui/jquery.min.js"></script>-->
	<script type="text/javascript" src="<%=basePath%>scripts/common/jquery-easyui/jquery.easyui.min.js"></script>
	<script  type="text/javascript">
		//自定义左边的树数据
		var zNodes =[  
            { id:1, pId:0, name:"内网",t:"intranet",level:1,isParent:true},  
            { id:2, pId:0, name:"外网",t:"extranet",level:1,isParent:true}
        ];
		
		$(document).ready(function(){
			 $("#draft").hide();
			 $("#approve").hide();	
			 $("#publish").hide();
			 
			$.fn.zTree.init($("#treeDemo"), setting, zNodes); 
			/*	$.ajax({
				url:'SolarNewsPortlet/getChileSite.action',
				type:'post',
				dataType:'json',
				data:{'t':'guanweihui',"id":"1"} ,
				success:function(list){
					var zNodes ='[{ id:1, pId:0, name:"外网门户", t:"", open:true},';
					for(var i = 0; i < list.length;i++){
						var map = list[i]; 
						zNodes += '{id:'+map['id']+', pId:'+map['pId']+', name:"'+map['name']+'",t:"'+map['t']+'",isParent:'+map['open']+'},';
					}
					zNodes +=']';
					zNodes = eval(zNodes);
	//				alert(zNodes);
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				}
				})  */
			getApprove(1);
		
		});
		
		function getApprove(firstPage,pageSum,buttonChange){
			var start = $(firstPage).html();
			if(start == undefined)
				start = 1;
			else if(buttonChange != undefined){ 
				var buttonChangeTo = $("#tt").val();
				if(buttonChangeTo > pageSum){
					alert("不能超过 " + pageSum + "页数！");
					return;
				}
				else
					start = $("#tt").val();
			}
			//获取带审批列表
			$.ajax({
								url:'/wps/myportal/AppData/SolarNewsPortlet/getApprovar.action',
								type:'post',
								dataType:'json', 
								data:{ 
									'firstPage':start 
								},
								success:function(resultMap){
									var list = resultMap['list'];
									var table = $("#table");
										table.empty();
										var tr = '<tr><th>标题</th><th>时间</th><th>操作</th></tr>';
										table.append(tr);
										for(var i = 0; i < list.length;i++){
												var map = list[i];
													 tr = '<tr>'
																+'<td>' + map['title'] + '</td>'
																+'<td>'+map['time']+'</td>'
																 <!--   +'<td><a target="_blank" href="'+map['read']+'">查看</a>'-->
																+'<td><a  url="'+map['approve']+'" onclick="ajaxApproveFunction(this);">通过 ;</a>'
																+'<a  url="'+map['decline']+'" onclick="ajaxRejectFunction(this);">退回</a></td>'
																+'</tr>';
														table.append(tr);
										}
									var page = resultMap['page']; 
					var func = "getApprove";
									changePageInfo(page,func); 
								}
				})
		}
	</script>
 </head>
 
 <body>
 <div class="layout" style="width:1100px;height:800px;">
    <aside class="layout-side" style="margin-top:50px;">
        <ul id="treeDemo" class="ztree"></ul>
    </aside>
</div>
 </body>
 </html>