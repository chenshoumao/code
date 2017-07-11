<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath%>scripts/js/jquery1.8.3.min.js"></script>
<style type="text/css">
table {
	border-right: 1px solid red;
	border-bottom: 1px solid red;
	border-collapse: collapse;
}

th,td {
	border-left: 1px solid red;
	border-top: 1px solid red;
}
</style>
<script type="text/javascript">
	$(function(){
		$("input").hide();
		$("input[name=systemName]").show();
	})
	 function sure(){ 
		 $('input[name="systemName"]:checked').each(function(){  
		     var item = $(this).parent().siblings(); 
		     var data = "";
		     var key = "";
		     for(var i = 0;i < item.length;i++){
		    	 key += item.eq(i).find("input").attr("name") + ",";
		    	 data +=  item.eq(i).find("input").val() + ",";
		     }
		     data = data.split(",");
		     console.log($(this).next().next().val());
		     $.ajax({
		    	url:'<%=basePath%>adapter/edit.action',
		    	type:'post',
		    	dataType:'json',
		    	data:{
		    		'systemName':$(this).next().next().val(),
		    		'oldSystemName':$(this).val(),
		    		'EnName':data[0],
		    		'CnName':data[1],
		    		'sysUrl':data[2],
		    		'jsonroot':data[3],
		    		'jsondata':data[4],
		    		'Title':data[5],
		    		'receiveTime':data[6],
		    		'PendingName':data[7],
		    		'todoUrl':data[8],
		    		'sysCount':data[9],
		    		'sysListUrl':data[10] 
		    	},
		    	success:function(data){
		    		alert(data.result);
		    		window.location.reload();
		    	}
		     }) 
		}); 
	 }
	 function addNode() {
		 var item = '<tr>'
			 +'<td><input type="checkbox" name="systemName">'
			 + '<input></td>'
			 +'<td><input name="EnName"></td>'
			 +'<td><input name="CnName"></td>'
			 +'<td><input name="sysUrl"></td>'
			 +'<td><input name="jsonroot"></td>'
			 +'<td><input name="jsondata"></td>'
			 +'<td><input name="Title"></td>'
			 +'<td><input name="receiveTime"></td>'
			 +'<td><input name="PendingName"></td>'
			 +'<td><input name="todoUrl"></td>'
			 +'<td><input name="sysCount"></td>'
			 +'<td><input name="sysListUrl"></td></tr>';
			 $("#table").append(item);
			  
	 }
	 
	 function deleteNode() {
		 $('input[name="systemName"]:checked').each(function(){ 
				//	 alert($(this).val()); 
				     $.ajax({
					    	url:'<%=basePath%>adapter/remove.action',
					    	type:'post',
					    	dataType:'json',
					    	data:{
					    		'oldSystemName':$(this).val(),
					    	},
					    	success:function(data){ 
					    		alert(data.result);
					    		window.location.reload();
					    	} 
					}); 
				});  
	}
	 function editNode(){
		 $('input[name="systemName"]:checked').each(function(){ 
			 var item = $(this).parent().siblings();  
		     for(var i = 0;i < item.length;i++){
		    	// console.log(item.eq(i).find("input").attr('type','show') + i);
		    	//item.eq(i).find("input").show();
		    	//item.eq(i).find("input").removeAttr("type");
		    	item.eq(i).find("input").css('display','block');
		    	item.eq(i).find("span").hide();
		    	item.eq(i).find("a").hide();
		     }
				     
		});  
	 }
</script>
</head>
<body>
	<div>
 		<table id="table"> 
 			 	<tr>
 			 		<th rowspan="2"></th>
 			 		<th rowspan="2">EnName</th>
 			 		<th rowspan="2">CnName</th>
 			 		<th rowspan="2">sysUrl</th>
 			 		<th rowspan="2">jsonroot</th>
 			 		<th rowspan="2">jsondata</th>  
 			 		<th colspan="4">todomap</th>  
 			 		<th colspan="2">countmap </th>
 			 	</tr> 
 			 	<tr> 
 			 		<th>Title</th>
 			 		<th>receiveTime</th>
 			 		<th>PendingName</th>
 			 		<th>todoUrl</th>
 			 		<th>sysCount</th>
 			 		<th>sysListUrl</th>
 			 	</tr> 
 			 	 	
				<c:forEach items="${map}" var="firstloop">
					<tr> 	
						<td>
						<input type="checkbox" name="systemName" value="${firstloop.key }"> 
						<span>${firstloop.key }</span>
						<input  name="${firstloop.key }" value="${firstloop.key }"></td>
					<c:forEach items="${firstloop.value }" var="secondloop"> 
						<c:choose>
							<c:when
								test="${secondloop.key == 'todomap' || secondloop.key == 'countmap'}"> 
								<c:forEach items="${secondloop.value }" var="thridloop"> 
									<td><span>${thridloop.value }</span>
										<input   name="${thridloop.key }"
										value="${thridloop.value }">
									</td> 
								</c:forEach>
							</c:when>
							<c:when
								test="${secondloop.key == 'sysUrl'}">  
									<td><a href="${secondloop.value }">${secondloop.value }</a>
										<input   name="${secondloop.key }"
										value="${secondloop.value }">
									</td>  
							</c:when>
							<c:otherwise>
								<td>
								<span>${secondloop.value }</span>
								<input   name="${secondloop.key }"
									value="${secondloop.value }"></td>
								 
							</c:otherwise>
						</c:choose> 
					</c:forEach>
					</tr>
				</c:forEach> 
 		</table>
 		
 		<button onclick="sure()">提交</button>
 		<button onclick="deleteNode()">删除</button>
 		<button onclick="addNode()">新增</button>
 		<button onclick="editNode()">编辑</button>
 	</div>
	
 	 
 	
 	
 	
</body>
</html>