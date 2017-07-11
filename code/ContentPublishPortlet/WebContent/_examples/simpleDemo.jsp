<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
 
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
									+'<a class="'+map['name']+'" onclick="deleteContent(this)">删除;</a>'
									+'<a url="'+map['approve']+'" onclick="commitContent(this)">提交</a>'
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
							       +'<a class="'+map['name']+'" onclick="deletePublishContent(this)">删除;</a>'
							       +'<a target="_blank" href="<%=path%>wps/wcm/myconnect/' + map['preview'] + '&isDraft=true">浏览</a>'
                                                              
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
						htmlTemp += ' <div class="list-item">'
							+'<label>图片</label><input type="file" name="file"></div>';
					}
					else if(type == 'ShortTextComponent'){
						var text = '';
						if(name == 'source')	 text = '来源';
						else if(name == 'title') text = '标题';
						else if(name == 'title2') text = '详细标题';
						else if(name == 'number') text = '招商编号';
						  htmlTemp += '<div class="list-item">'
							+'<label>'+text+'</label><input type="text" name="'+map['name']+'"></div>';
					}
					else if(type == 'RichTextComponent'){
						if(name == 'content'){ 
							hasContent = true;
							//htmltemp += $("#uedi").html();
						}
						else{
							 htmlTemp += '<div class="list-item">'
								+'<label>概要</label>'
								+'<textarea cols="30" rows="4" name="'+name+'"></textarea></div>';
						}
					} 
					else if(type == 'DateComponent'){
						var text = '发布日期';
						 htmlTemp += '<div class="list-item">'
							+'<label>'+text+'</label><input type="text" name="'+map['name']+'"></div>';
					}
				 }
					htmlTemp += ' <div class="list-item">'
						+'<input type="button" onclick="checkContent();" value="确定"></div>';
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

	
	 
	 
	</script>
 </head>
  
 <body>
     
    <div class="layout" style="width:1100px;height:800px;">
    <aside class="layout-side" style="margin-top:50px;">
        <ul id="treeDemo" class="ztree"></ul>
    </aside>
    <div class="layout-main">
        <ul class="tab-li">
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
        <form class="art-new" id="form" action="/wps/myportal/AppData/SolarNewsPortlet/createContent.action" method="post" enctype="multipart/form-data">
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
	    	<label>是否2置顶</label>
		<input type="text" name="stick" id="stick" value="0" style="display:none;"> 
		<input type="button" onclick="stick_1()" value="是">
		<input type="button"  onclick="stick_0()" value="否">
	    </div>
	   
	   
        </form> 
        </div>
    </div>
    </div>
</body>
 	    <div class="list-item" style="display:none;" id="uedi"> 
				<script id="editor" type="text/plain" style="width:800px;height:500px;"></script>
            </div> 
<script type="text/javascript">

	 
	

	var ue = UE.getEditor('editor');
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
    $(function(){
        var LH=$(document).height()+'px';
        $(".layout-side").css('min-height',LH);
        $(".issue-btn").click(function(event) {
          $(".list-tab").show().siblings('.layout-form').hide();
        });
         $(".table-btn").click(function(event) {
          $(".layout-form").show().siblings('.list-tab').hide();
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
