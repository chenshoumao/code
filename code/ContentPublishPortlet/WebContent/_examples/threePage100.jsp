<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
 
<%
String path1 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

String a = request.getParameter("a");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP </title>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<style>
		#btnBox{list-style-type:none;margin:0px;padding:0px;}
		#btnBox li{float:left; width:70px; height:30px; line-height:30px;background-color:blue;color:#fff; margin-left:10px; padding:10px;}
		#tableBox{width:500px;}
		#tableBox th{background-color:blue;color:#fff;}
	</style>
	
	<script>
		$(function(){
			alert("<%=a%>");
			loadMoren();
		});
		
		//默认加载的数据
		function loadMoren(){
			$.ajax({
                    url:'/wps/myportal/AppData/SolarNewsPortlet/getApprovar.action',
                    type:'post',
                    dataType:'json', 
					data:{ 
                            'firstPage':1 
                    },
                    success:function(resultMap){
			   			var list = resultMap['list'];
                        var table = $("#tableBox");
                        table.empty();
                        var tr = '<tr><th>标题</th><th>时间</th><th>操作</th></tr>';
                        table.append(tr);
                        for(var i = 0; i < list.length;i++){
                                var map = list[i];
                                tr = '<tr>'
                                                            +'<td>' + map['title'] + '</td>'
                                                            +'<td>'+map['time']+'</td>'
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
		
		//分页的方法
		function changePageInfo(page,func){
			$(".pages").empty();
			var pageOfFoot =
				'<p>'
			+'<span>共'+page.sumOfResult+'条记录，'+page.currentPage +'/'+page.pageSum +'页</span>'; 
			var currentPage = page.currentPage;
			var pageSum = page.pageSum;
			var perPage = page.perPage;
			var mid = (perPage + 1) / 2;
			var cha = (perPage - 1) / 2;
			var end = pageSum;
			var start = 1;
			if(pageSum <= perPage || currentPage <= mid){ 
				if(currentPage <= mid){
					start = 1;
					end = pageSum > perPage? perPage:pageSum;
				}
				for(; start <= end;start++){
					if(start == currentPage){
						pageOfFoot += ' <a style="margin-left: 5px;" class="now">'+start+'</a>';
					}
					else{
						pageOfFoot += ' <a onclick="'+func+'(this,'+pageSum+')">'+start+'</a>';
					}
				}
			}
			else{ 
				if(pageSum - currentPage < cha){
					start = pageSum - perPage + 1;
					end = pageSum;
				}
				else if(pageSum - currentPage >=  cha){
					start = currentPage - cha;
					end = currentPage + cha;
				}
				
				for(;start <= end;start++){
					if(start == currentPage){
						pageOfFoot += ' <a style="margin-left: 5px;" class="now">'+start+'</a>';
					}
					else{
						pageOfFoot += ' <a onclick="'+func+'(this,'+pageSum+')">'+start+'</a>';
					}
				}
			}
			pageOfFoot += 
				' <span>到第<input type="text" id="tt">页</span>'
				+' <a class="got now" onclick="'+func+'(this,'+pageSum+',1)">前往</a>'
				+'</p>';
			$(".pages").append(pageOfFoot);
		}
	</script>
</head>
<body>
	<ul id="btnBox">
		<li><a>发布内容</a></li>
		<li><a>我的草稿</a></li>
		<li><a>我的发布</a></li>
		<div style="clear:both;"></div>
	</ul>
	<table id="tableBox">
		<tr>
			<th>标题</th><th>时间</th><th>操作</th>
		</tr>
	</table>
	<div class="pages"></div>
</body>
</html>