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
	<!-- <link rel="stylesheet" href="<%=basePath%>css/reset.css">-->
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
		 
	}
	</style>
	<script type="text/javascript">
	//alert("<%=path1%>,<%=basePath%>,<%=path%>");
       // $("#nav").hide();

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

	
	function beforeClick(treeId, treeNode, clickFlag) { 
		return (treeNode.click != false);
	} 

	function onClick(event, treeId, treeNode, clickFlag) { 
		 
		$("#address").html(treeNode.name);
		$("#area").val(treeNode.t);	
		$("#areatitle").val(treeNode.name);
		siteArea = treeNode.t;
		 
		if(siteArea == "")
			window.location.reload();
		else{
			$("#draft").show();
			$("#approve").show();
			$("#publish").show(); 
			if(siteArea == "siteArea_gonggaolan")
				$("#summary").hide();
			else	$("#summary").show();
			getMyPublished();  
		}
	} 
	
	$(document).ready(function(){
         $("#draft").hide();
	 $("#approve").hide();	
         $("#publish").hide();$("#main").show();
		$("#scan").hide();
		 
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
	
	 function filter(treeId, parentNode, childNodes) {
		 
         if (!childNodes) return null; 
         for (var i=0, l=childNodes.length; i<l; i++) { 
         	
             childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
         }
         return childNodes;
     }
	
	function getMyDraft(firstPage,pageSum,buttonChange){
                  
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
		//<%=path%>wps/wcm/myconnect/' + map['preview'] + '&isDraft=true)
		 $.ajax({
                               url:'/wps/myportal/AppData/SolarNewsPortlet/getMyDraft.action',
                               type:'post',
                               dataType:'json',
			       data:{
                               	'selectLib':'guanweihui',
                               	'siteArea':siteArea,   
                                 'firstPage':start 
                               } ,
                               success:function(resultMap){
             			var list = resultMap['list'];
                                   var table = $("#table");
                                       table.empty();
                                       var tr = '<tr><th>标题</th><th>时间</th><th>操作</th></tr>';
                                       table.append(tr);
                                       for(var i = 0; i < list.length;i++){
                                               var map = list[i];
					       var prev = map['preview'];
                                                    tr = '<tr>'
                                                               +'<td>' + map['title'] + '</td>'
                                                               +'<td>'+map['time']+'</td>'
                                                             <!--  +'<td><a target="_blank" href="<%=path%>wps/wcm/myconnect/' + map['preview'] + '&isDraft=true)">浏览</a>'+'<a target="_blank" href="'+map['read']+'">阅读</a>'+'<a target="_blank" href="'+map['edit']+'">编辑</a>'-->
                                                               +'<td>'
									+'<a class="'+map['name']+'" onclick="ScanContent(this)"><input type="button" class="btn btn-primary mar btn-sm" value="浏览"/></a>'
									+'<a class="'+map['name']+'" onclick="deleteContent(this)"><input type="button" class="btn btn-primary mar btn-sm" value="删除"/></a>'
									+'<a url="'+map['approve']+'" onclick="commitContent(this)"><input type="button" class="btn btn-primary btn-sm" value="提交"/></a>'
							       +'</td>'
                                                               +'</tr>';
                                                       table.append(tr);
                                       }
                                  var page = resultMap['page']; 
		   		  var func = "getMyDraft";
                                  changePageInfo(page,func);
                               }
               })
		 
	       
	}


	function deleteContent(a){
		var name = $(a).attr("class");
		console.log(name);
		$.ajax({
                               url:'/wps/myportal/AppData/SolarNewsPortlet/deleteContent.action',
                               type:'post',
                               dataType:'json',
			       data:{  
                                'contentName':name
                               } ,
                               success:function(resultMap){
				 var state = resultMap['state'];
				 if(state){
					alert("删除成功!!");
				 }
				 else{
					alert("删除失败...");
				 }
             			 getMyDraft();
                               }
               }) 
		
	}
	
	function deletePublishContent(a){
		var name = $(a).attr("class");
		console.log(name);
		$.ajax({
                               url:'/wps/myportal/AppData/SolarNewsPortlet/deleteContent.action',
                               type:'post',
                               dataType:'json',
			       data:{  
                                'contentName':name
                               } ,
                               success:function(resultMap){
				 var state = resultMap['state'];
				 if(state){
					alert("删除成功!!");
				 }
				 else{
					alert("删除失败...");
				 }
             			 getMyPublished();
                               }
               }) 
		
	}
	
	function commitContent(a){ 
		var contentName = $(a).parent().siblings().eq(0).html();
		 
		$.ajax({
                               url:'/wps/myportal/AppData/SolarNewsPortlet/commitContent.action',
                               type:'post',
                               dataType:'json',
			       data:{ 
                               	'siteArea':siteArea,   
                                'contentName':contentName
                               } ,
                               success:function(resultMap){
				 var state = resultMap['state'];
				 if(state){
					alert("提交成功!!");
				 }
				 else{
					alert("提交失败...");
				 }
             			 getMyDraft();
                               }
               }) 
	       
	}

        function ajaxCommitFunction(a){
		//log.console(a);
		
		 $.ajax({
                               url:$(a).attr('url'), 
                               success:function(resultMap){
					alert("提交成功!");
					getMyDraft();
					
			       }
		})
	}


	function getMyPublished(firstPage,pageSum,buttonChange){
		
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
		 $.ajax({
                               url:'/wps/myportal/AppData/SolarNewsPortlet/getMyPubulished.action',
                               type:'post',
                               dataType:'json',
			       data:{
                               		'selectLib':'guanweihui',
                               		'siteArea':siteArea,   
                                    	'firstPage':start 
                               } ,
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
                                                               +'<td>'
							       +'<a class="'+map['name']+'" onclick="deletePublishContent(this)"><input type="button" class="btn btn-primary mar btn-sm" value="删除"/></a>'
							       +'<a target="_blank" href="<%=path%>wps/wcm/myconnect/' + map['preview'] + '&isDraft=true"><input type="button" class="btn btn-primary btn-sm" value="浏览"/></a>'
                                                              
                                                               +'</td>'
                                                               +'</tr>';
                                                       table.append(tr);
                                       }
                                  var page = resultMap['page']; 
				  var func = "getMyPublished";
                                  changePageInfo(page,func);
                               }
		 
               })
	}	
	
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
                                            var map = list[i];//alert(map['name']);
                                                 tr = '<tr>'
                                                            +'<td>' + map['title'] + '</td>'
                                                            +'<td>'+map['time']+'</td>'
                                                            +'<td><a class="'+map['name']+'" onclick="ScanReviewContent(this)"><input type="button" class="btn btn-primary mar btn-sm" value="查看"/></a>'
                                                            +'<a  url="'+map['approve']+'" onclick="ajaxApproveFunction(this);"><input type="button" class="btn btn-primary mar btn-sm" value="通过"/></a>'
                                                            +'<a  url="'+map['decline']+'" onclick="ajaxRejectFunction(this);"><input type="button" class="btn btn-primary mar btn-sm" value="退回"/></a></td>'
                                                            +'</tr>';
                                                    table.append(tr);
                                    }
                                var page = resultMap['page']; 
				var func = "getApprove";
                                changePageInfo(page,func); 
                            }
            })
	}
	

	function ScanReviewContent(a){
		var contentName = $(a).attr("class");
		 
		$("#portletParam").val(contentName);
		$("#pageInfo").val("ScanReviewContent");
		$("#form5").submit();
	}

	function ajaxApproveFunction(a){
		 
		var url = $(a).attr('url');
		$.ajax({
			url:url,
			success:function(data){
				alert("通过成功!");
				getApprove();
			}
		})
	}
	function ajaxRejectFunction(a){
		 
		var url = $(a).attr('url');
		$.ajax({
			url:url,
			success:function(data){
				alert("退回成功!");
				getApprove();
			}
		})
	}
	
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
	

	function getTemplate(){
		
		$("#form").children().eq(7).nextAll().remove();
		if(siteArea == 'intra_yuangongxinxi')
			$("#intra_yuangongxinxi").show();
		else
			$("#intra_yuangongxinxi").hide();

		if(siteArea == 'extra_Investment')
			$("#extra_Investment").show();
		else
			$("#extra_Investment").hide();
		$.ajax({
                            url:'/wps/myportal/AppData/SolarNewsPortlet/getTemplate.action',
                            type:'post',
                            dataType:'json', 
			    data:{ 
                            	'siteArea':siteArea 
                            },
                            success:function(list){
				var htmlTemp = '';
				var hasContent = false;
				 for(var i = 0; i < list.length;i++){
					
					var map = list[i];
					console.log(map['name'] + "," + map['type']);
					var type = map['type'];
					var name = map['name'];
					if(type == 'ImageComponent'){
						htmlTemp += ' <div class="form-group">'
							+'<label class="col-sm-2 control-label">图片</label><div class="col-sm-10"><input type="file" class="form-control" name="file"></div></div>';
					}
					else if(type == 'ShortTextComponent'){
						var text = '';
						if(name == 'source')	 text = '来源';
						else if(name == 'title') text = '标题';
						else if(name == 'title2') text = '详细标题';
						else if(name == 'number') text = '招商编号';
						  htmlTemp += '<div class="form-group">'
							+'<label class="col-sm-2 control-label">'+text+'</label><div class="col-sm-10"><input type="text" class="form-control" name="'+map['name']+'"></div></div>';
					}
					else if(type == 'RichTextComponent'){
						if(name == 'content'){ 
							hasContent = true;
							//htmltemp += $("#uedi").html();
						}
						else{
							 htmlTemp += '<div class="form-group">'
								+'<label class="col-sm-2 control-label">概要</label>'
								+'<textarea cols="60" rows="4" name="'+name+'"></textarea></div>';
						}
					} 
					 
				 }
					htmlTemp += ' <div class="form-group"><div class="col-sm-offset-2 col-sm-10">'
						+'<input type="button" onclick="checkContent();" class="btn btn-default" value="提交"></div></div>';
					$('#form').append(htmlTemp);
					if(hasContent){
						var length = $("#form").children().length -2;
						var uuedi = $("#uedi").clone(); 
						uuedi.insertAfter($("#form").children().eq(length)); 
						uuedi.show();
					} 
			    }
		}) 
        } 


	
	function publishContent(a){
		$("#main").show();
		$("#scan").hide();
	}
	
	</script>
 </head>
  
 <body>

	<FORM method="POST" action="<portlet:actionURL/>" id="form5" style="display:none;"> 
		<INPUT name="<%=com.ibm.contentpublishportlet.ContentPublishPortlet.FORM_TEXT%>" type="text" id="portletParam"/>
		<INPUT name="pageInfo" type="text" id="pageInfo"/>
		<INPUT name="<%=com.ibm.contentpublishportlet.ContentPublishPortlet.FORM_SUBMIT%>" value="Submit"/>
		 
	</FORM>

     
    <div class="layout" style="width:1100px;height:800px;" id="main" >
    <aside class="layout-side" style="margin-top:50px;">
        <ul id="treeDemo" class="ztree"></ul>
    </aside>
    <div class="layout-main">
        <ul class="tab-li">
	    <li  class="table-btn" onclick="getApprove()"><a>我的审核</a></li>
            <li id="publish" class="issue-btn" onclick="getTemplate()"><a>发布内容</a></li>
	    <li  id="draft" onclick="getMyDraft()" class="table-btn"><a>我的草稿</a></li>
	    <li  id="approve" onclick="getMyPublished()" class="table-btn"><a>我的发布</a></li>
        </ul>
        <form class="layout-form" style="display:block" id="form2">
        <table class="layout-tab" id="table">
        </table>
        <div class="pages"> 
        </div>
        </form>
        <div class="list-tab">
        <!--<form class="art-new" id="form" action="/wps/myportal/AppData/SolarNewsPortlet/createContent.action" method="post" enctype="multipart/form-data">
	     <div class="list-item" style="display:none;">
                <label>发布地址</label><input type="text" name="selectLib" value="guanweihui">
             </div>
             <div class="list-item">
                <label>发布位置</label><label id="address" style="display:none;"></label>
		<input type="text" name="area" id="area" style="display:none;">
		<input type="text" name="areatitle" id="areatitle" disabled="true">
            </div>
             <div class="list-item" style="display:none;">
                <label>工作流程</label>
                <input type="text" name="workflow" value="newsReviewAndPublish">
             </div>
	    <div class="list-item" style="display:none;">
                <label>内容</label>
		<input type="text" id="contentcc" name="content"> 
            </div> 
	    <div class="list-item" style="display:none;" id="intra_yuangongxinxi">
                <label>发布类型</label>
		<select name="zhaopin_categoryName">
			<option value=""></option>
			<option value="neibuzhaopin">内部招聘</option>
			<option value="xinyuangongruzhi">新员工入职</option>
			<option value="renshirenmian">人事任免</option>		
		</select> 
            </div>
	    <div class="list-item" style="display:none;" id="extra_Investment">
                <label>发布类型</label>
		<select name="touzi_categoryName">
			<option value=""></option>
			<option value="touzixiangmu">投资项目</option>
			<option value="touzizhengce">投资政策</option>
			<option value="touzixiangdao">投资向导</option>
			<option value="touzihuanjing">投资环境</option>		
		</select> 
            </div>
	    <div class="list-item" style="display:none;">
                <label>内容</label>
		<input type="text" id="contentcc" name="content"> 
            </div> 
	    <div>
	    	<label>是否置顶</label>
		<input type="text" name="stick" id="stick" value="0" style="display:none;"> 
		<input type="button" onclick="stick_1()" value="是">
		<input type="button"  onclick="stick_0()" value="否">
	    </div>
	   
	   
        </form> -->
 
<div class="container-fluid">
			<div class="row clearfix">
				<div class="col-md-8 column">
					<form class="form-horizontal art-new" role="form" id="form" action="/wps/myportal/AppData/SolarNewsPortlet/createContent.action" method="post" enctype="multipart/form-data">
  
						<div class="form-group" style="display:none;">
							<label for="inputEmail3" class="col-sm-2 control-label">发布地址</label>
							<input type="text" name="selectLib" value="guanweihui">
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">发布位置</label>
							<label id="address" style="display:none;"></label>
							<input type="text" name="area" id="area" style="display:none;">
							<div class="col-sm-10">
								<input type="text" class="form-control" name="areatitle" id="areatitle" disabled="true">
							</div>
						</div>
						 
						<div class="form-group" style="display:none;">
							<label  class="col-sm-2 control-label">工作流程</label>
							<input type="text" name="workflow" value="newsReviewAndPublish">
						</div>
						
						<div class="form-group" style="display:none;" id="intra_yuangongxinxi">
							<label  class="col-sm-2 control-label">发布类型</label>
							<select name="zhaopin_categoryName">
								<option value=""></option>
								<option value="neibuzhaopin">内部招聘</option>
								<option value="xinyuangongruzhi">新员工入职</option>
								<option value="renshirenmian">人事任免</option>		
							</select>
						</div>
						<div class="form-group" style="display:none;" id="extra_Investment">
							<label class="col-sm-2 control-label">发布类型</label>
							<select name="touzi_categoryName">
								<option value=""></option>
								<option value="touzixiangmu">投资项目</option>
								<option value="touzizhengce">投资政策</option>
								<option value="touzixiangdao">投资向导</option>
								<option value="touzihuanjing">投资环境</option>		
							</select> 
						</div>
  						<div class="form-group" style="display:none;">
							<label class="col-sm-2 control-label">内容</label>
							<input type="text" id="contentcc" name="content"> 
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否置顶</label>
							<input type="text" name="stick" id="stick" value="0" style="display:none;"> 
							<div class="col-sm-10">
								<div class="checkbox-inline">
									<label ><input type="checkbox" onclick="stick_1()"/>是</label>
								</div>
								<div class="checkbox-inline">
									<label ><input type="checkbox" onclick="stick_0()"/>否</label>
								</div>
							</div>
						</div>
						<!-- <div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">标题</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="inputEmail3" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">来源</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="inputEmail3" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">招商编号</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="inputEmail3" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">图片上传</label>
							<div class="col-sm-10">
								<input type="file" class="form-control" id="inputEmail3" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">概要</label>
							<div class="col-sm-10">
								<textarea class="form-control" rows="3"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">提交</button>
							</div>
						</div> -->
					</form>
				</div>
				<div class="col-md-4 column">
				</div>
			</div>
		</div>


        </div>
    </div>
    </div>
	
	<div id="scan" >
		<input type="button" class="btn btn-primary btn-sm" onclick="publishContent(this)" value="返回"/>
		<button class="btn btn-primary btn-sm" onclick="edit()">编辑</button>
		<button class="btn btn-primary btn-sm" onclick="read()">阅读</button>
		<button style="display:none;" id="subm" class="btn btn-primary btn-sm" onclick="commitEdit();">提交</button>
		 
		<form id="form3" action="/wps/myportal/AppData/SolarNewsPortlet/updateContent.action" method="post"  enctype="multipart/form-data" >
			<input type="hidden" id="name" name="name">
			<div class="article" style="height:1000px;">
				<!-- 标题 -->
				<span  class="span">
					<div id="title" class="contentTitle"></div>
					<div  style="display:none;" class="tes">
						<label>标题: </label> <input style="margin-left:45px;" name="title" value="">
					</div>
				</span>
				<h1>
					<span>
						<div id="source" class="tes"></div> 
					</span>
				</h1>
				<h1>
					<span class="span" style="display:none;" id="number">
						<div class="tes"></div>
						<div  style="display:none;" class="tes">
							<label>招商编号: </label> <input style="margin-left:10px;" name="number"  value="">
						</div>
					</span>
				</h1><br>
				<h1>
					<span class="span" id="summary" style="display:none;">
						<div class="tes"></div>
						<div style="display:none;" class="tes">
							<label>简介: </label> <input style="margin-left:45px;" name="summary"  value="">
						</div>
					</span>
				</h1><br>
				<h1>
					<span class="tes" id="image" style="display:none;">
						<div >
							<label style="margin-left:-50px;">图片:</label>  
							
							<input style="margin:10px  0px 0px 405px;" type="file" name="file"> 
						</div>
					</span>
					
				</h1> 
				
				
				<div class="span" id="contentBody">
					<input name="content" style="display:none;">
					<div id="contentText">
						 
					</div>
					<div class="list-item" style="display:none;"  id="ued1i"> 
						<script id="editor2" type="text/plain" style="width:1000px;height:100px;">
							 
						</script> 
          				</div> 
				</div> 
			</div>
		</form>
 
		
	<div>


</body>
 	    <div class="list-item" style="display:none;" id="uedi"> 
				<script id="editor" type="text/plain" style="width:800px;height:500px;"></script>
            </div> 
<script type="text/javascript">

	function ScanContent(a){
		 
		  var contentName = $(a).attr("class");
		  console.log(contentName);
		  if(contentName != undefined)
		  	$("#name").val(contentName);
		   $.ajax({
		      	url:'/wps/myportal/AppData/SolarNewsPortlet/searchContentByName.action',
			data:{'contentName':$("#name").val()},
			dataType:'json',
			type:'post',
			success:function(data){ 
				 
				var title = data.title;
				var source = data.source;
				var imggeUrl = data.image;
				var summary = data.summary;
				var number = data.number;
				var content = data.content;  
				
				$("#title").html(title);
				$("#title").siblings().eq(0).find("input").val(title); 
				$("#source").html("发布日期："+data.createTime+" 来源 : " + source);  

				$.each(data,function(key,values){     
    					console.log(key);  
					if(key == "number"){
						//alert($("#number").children().length);
						$("#number").find("div").eq(0).html("招商编号: " + values);
						$("#number").find("div").eq(1).find("input").val(values);
						$("#number").show();
					}
					else if(key == "summary"){
						 
						$("#summary").find("div").eq(0).html("简介: " + values);
						$("#summary").find("div").eq(1).find("input").val(values);
						$("#summary").show();
					}
					else if(key == "image"){ 

						$("#image").find("div").find("label").append('<img src="'+imggeUrl+'" style="height:200;width:200px;"/>');
						
					}
					else if(key == "content"){ 
						$("#contentBody").show();
						$("#contentText").html(values);
						var editor = UE.getEditor('editor2');
    						editor.setContent(values);
					} 
    					    
 				}); 
			}
		  });
		$("#main").hide();
		$("#scan").show();
	}
	
	function edit(){ $("#image").show(); 
			$("#contentBody").css("margin-top","250px");
			$("#subm").show();
			$(".span").each(function(index,val){ 
				$(val).children("div").eq(0).hide();
				$(val).children("div").eq(1).show(); 
			})
				 
	}
	function read(){$("#image").hide();
			$("#contentBody").css("margin-top","0px");
			$("#subm").hide();
			 $(".span").each(function(index,val){ 
				$(val).children("div").eq(1).hide();
				$(val).children("div").eq(0).show(); 
			})
	}
	 

	var ue = UE.getEditor('editor');

	//初始化编辑功能的Ueditor插件
	var ue2 = UE.getEditor('editor2');

	function checkContent(){
		var hasContent = UE.getEditor('editor').hasContents();
		if(hasContent){ 
			var content = UE.getEditor('editor').getContent();		
			$("#contentcc").val(content);  
			//$("form").submit(); 
			 
        	$('#form').form('submit',{
        			        url: '/wps/myportal/AppData/SolarNewsPortlet/createContent.action',
        			        onSubmit: function(){
        			            return $(this).form('validate');
        			        },
        			        success: function(result){
					         var obj = jQuery.parseJSON(result); 
        			        	 alert(obj.result);
        			            	 window.location.reload();
        			        }
			});
        	 
		}
		else{
			alert("content could not be empty!");
			return;
		}
	}
	function commitEdit(){
		
		var hasContent = UE.getEditor('editor2').hasContents();
		if(hasContent){ 
			var content = UE.getEditor('editor2').getContent(); 
			$('input[name="content"]').val(content); 
			//$("form").submit(); 
			 
        	$('#form3').form('submit',{
        			        url: '/wps/myportal/AppData/SolarNewsPortlet/updateContent.action',
        			        onSubmit: function(){
        			            return $(this).form('validate');
        			        },
        			        success: function(result){
					         var obj = jQuery.parseJSON(result); 
        			        	 alert(obj.result);
        			            	 ScanContent();
						read();
        			        }
			});
        	 
		}
		else{
			alert("content could not be empty!");
			return;
		}
	}
    $(function(){
        var LH=$(document).height()+'px';
        $(".layout-side").css('min-height',LH);
        $(".issue-btn").click(function(event) { 
	  $(".issue-btn").css("background-color", "#6699FF").siblings().css("background-color","#005bc1");
          
          $(".list-tab").show().siblings('.layout-form').hide();
        });
         $(".table-btn").click(function(event) {
          $(".layout-form").show().siblings('.list-tab').hide();
    	$(this).css("background-color", "#6699FF").siblings().css("background-color","#005bc1");
        });
    })
	function stick_0(){
		$("#stick").val(0);
		return false;
	}
	function stick_1(){
		$("#stick").val(1);
		return false;
	}
</script>
</html>

