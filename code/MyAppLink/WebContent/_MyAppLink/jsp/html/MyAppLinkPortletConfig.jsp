<%@page session="false" contentType="text/html" pageEncoding="GB18030" import="javax.portlet.*,com.ibm.myapplink.*,java.util.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init>  
 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String basePath2 = path+"/";
	Map<String,String> appMap = (HashMap<String,String>)request.getAttribute("appMap");
 %>
<script type="text/javascript" src="<%=path%>/js/jquery1.8.3.min.js"></script>
<style type="text/css">
	#config {
		border: 1px solid #cccccc;
		 border-collapse: collapse;
		 height: 100%;
		 width: 100%;
	}
	#config tr td{
		border: 1px solid #cccccc;
		text-align: center;
	}
</style>
<script type="text/javascript">
	$(function(){
		$.getJSON("<%= basePath2 %>AppLink.json",function(result){
			var $table = $("#config");
			if(null!=result&&result.length>0){
			 for(var i=0;i<result.length;i++){
			    var obj=result[i];
				$table.append("<tr>"+
								"<td><input type='checkbox' name='appLink' value='"+obj.appKey+"'/></td>"+
								"<td>"+obj.appName+"</td>"+
								"<td>"+obj.appURL+"</td>"+
								"<td>"+obj.appKey+"</td>"+
							  "</tr>");
			  }	
			}else{
			      $table.append("<tr>"+
								"<td  colspan=\"4\" aligin=\"center\">暂无数据</td>"+
				"</tr>");
			}			
		});
		
		$('#loginform').submit(function(e){
			return false;
		});
		
		
		
		//弹出层调用语句
		/*
		$('.modaltrigger').leanModal({
			top:110,
			overlay:0.45,
			closeButton:".hidemodal"
		});
		*/
		$(".update-App").click(function(){
			//panpuan 
			var Text = $(this).text();
			if(Text=='新增'){
				console.log(Text);
				$('#submitBtn-App').val('保存');
				$('#myForm-App').attr('action','<portlet:actionURL><portlet:param name="save" value="save"/></portlet:actionURL>');
				$("#appName").val("");
				$("#appURL").val("http://");
				$("#appKey").val("").removeAttr("readonly");
				console.log($("#myForm-App").attr('action'));
				$("#dialog-App").show();
			}
			if(Text=='修改'){
				console.log(Text);
				var $collectID = $("input[name='appLink']:checked");
				if($collectID.length>1){
					alert('只能选择一个选项！');
				}else if($collectID.length==1){
					$('#submitBtn-App').val('修改');
					$('#myForm-App').attr('action','<portlet:actionURL><portlet:param name="update" value="update"/></portlet:actionURL>');
					var $all = $collectID.parent().siblings();
					$("#appName").val($all.eq(0).html());
					$("#appURL").val($all.eq(1).html());
					$("#appKey").val($collectID.val()).attr("readonly","readonly");
					$("#dialog-App").show();
				}else {
					alert("请选中一条记录！");				
				}
			}
		});
		
		});
	
	function removeApp(){
		var rows = $("input[name='appLink']:checked");
		if(rows.length>0){
			var flag = confirm("您确定删除所选的记录吗？");
			if(flag){
				$("#config-fm").submit();
			}
		}else{
			alert("请先勾选一个选项");
		}
	}
	function cancelApp(){
      	$("#dialog-App").hide();
    }
    function submitApp(){
    	var btnVal = $("#submitBtn-App").val();
	    var url = $("#appURL").val().trim();
   		console.log(btnVal);
    	if(btnVal=="保存"){
	    	var name = $("#appKey").val().trim();
	    	if(url!=null&&url!=''&&url!='http://'){
		    	if(name!=null&&name!=''){
		    		var $appName = $("#config input");
			   		var flag = true;
			   		for(var i=0;i<$appName.length;i++){
			   			if($appName[i].value==name){
			   				flag = false;
			   			}
			   		}
			   		console.log(flag);
			   		if(flag){
			    		$("#myForm-App").submit();
			   		}else{
			   			alert("标识已存在");
			   		}
		    	}else {
		    		alert("标识不能为空！");
		    	}
	    	}else{
	    		alert("地址不能为空！");
	    	}
    	}
    	if(btnVal=="修改"){
    		console.log("修改后提交！")
    		if(url!=null && url!=''&&url!='http://'){
	    		$("#myForm-App").submit();
    		}else{
    			alert("地址不能为空！");
    		}
    	}
    
    }
</script>
<div>
	<a href="#dialog-App" class="update-App" id="add">新增</a>
	<a href="#dialog-App" class="update-App" id="update">修改</a>
	<a href="javascript:removeApp()" id="removeApp">删除</a>
</div>
<DIV style="margin: 6px">
	<form method="post" action="<portlet:actionURL><portlet:param name='remove' value='remove'/></portlet:actionURL>" id="config-fm">
		<table id="config">
			<tr>
				<td width="5px"></td>
				<td>名称</td>
				<td>地址</td>
				<td>系统标识</td>
			</tr>
		</table>
	</form>
	
</DIV>
<div id="dialog-App" style="display:none;">
	<form id="myForm-App" method="post" action="<portlet:actionURL></portlet:actionURL>">
<!-- 	       <div> -->
<!-- 				<input type="radio" name="image" id="" value="favorites1.png"> -->
<%-- 				<img src="<%= basePath %>images/favorites1.png"> --%>
<!-- 			</div> -->
<!-- 			<div> -->
<!-- 				<input type="radio" name="image" id="" value="favorites2.png"> -->
<%-- 				<img src="<%= basePath %>images/favorites2.png"> --%>
<!-- 			</div> -->
<!-- 			<div> -->
<!-- 				<input type="radio" name="image" id="" value="favorites3.png"> -->
<%-- 				<img src="<%= basePath %>images/favorites3.png"> --%>
<!-- 			</div> -->
<!-- 			<div> -->
<!-- 				<input type="radio" name="image" id="" value="favorites4.png"> -->
<%-- 				<img src="<%= basePath %>images/favorites4.png"> --%>
<!-- 			</div> -->
<!-- 			<div> -->
<!-- 				<input type="radio" name="image" id="" value="favorites5.png"> -->
<%-- 				<img src="<%= basePath %>images/favorites5.png"> --%>
<!-- 			</div> -->
<!-- 			<div> -->
<!-- 				<input type="radio" name="image" id="" value="favorites6.png"> -->
<%-- 				<img src="<%= basePath %>images/favorites6.png"> --%>
<!-- 			</div> -->
		<label>名称:</label>
		<input type="text" name="appName" class="txtfield" tabindex="1" id="appName"/>
	
		<label>地址:</label>
		<input type="text" name="appURL" class="txtfield" tabindex="2" value="http://" id="appURL"/>
		<label>唯一标识:</label>
		<input type="text" name="appKey" class="txtfield" tabindex="2" id="appKey"/>
	</form>
	<div class="center">
		<input type="button" id="submitBtn-App" class="flatbtn-blu hidemodal" value="保存" tabindex="3" onclick="submitApp()"/>
		<input type="button"id="cancelBtn" class="flatbtn-blu hidemodal" value="取消" tabindex="3" onclick="cancelApp()" />
	</div>
</div>