<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath =  path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 

<head>
<script type="text/javascript" src="<%=basePath%>scripts/js/jquery-1.11.0.min.js"></script>
<script src="<%=basePath%>js/bootstrap.js"></script>
<title>设备位置查询</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/styles.css">
<script type="text/javascript">
	var curIndex = 1;
	var keyList = ['','devices','positions','listalarms','listdevices','listdevices','listdevices'];
	 
	 
	function submit() { 
	//	alert($('#url'+curIndex).val());
	 $("#table tr:not(:eq(10000))").remove();//清空
		$.ajax({
			url:$('#url'+curIndex).val(),
			data:{
				uid:$('#uid'+curIndex).val(),
				deviceid:$('#deviceid'+curIndex).val(),
				devicetype:$('#devicetype'+curIndex).val(),
				parDate:$('#parDate'+curIndex).val(),
				timeStart:$('#timeStart'+curIndex).val(),
				timeEnd:$('#timeEnd'+curIndex).val()},
			dataType:'json',
			type:'post',
			success:function(data){
				var title = data['title'];
				
				var key = keyList[curIndex];
				var list = data[key];
				//$("table").show();
				var tr = "<tr>";
				 
				$.each(title,function(key,values){ 
					console.log(key+values); 
					tr += '<td>'+values+'</td>';
				})
				tr += '</tr>';
				console.log(tr); 
				$("table").append(tr);
				
				
				for(var i = 0;i < list.length;i++){
					var map = list[i];
					var td = '<tr>';
					$.each(title,function(key,values){ 
						console.log(key+values);
						var temp = map[key];
						if(temp!= undefined)
							td += '<td>'+temp+'</td>';
						else
							td += '<td></td>';
					})
					td += '</tr>';
					console.log(td);
					$("table").append(td);
				}
				$("#query").hide();
				$("table").show();
			
			}
		})
		 
	}
	
	 
</script>
</head>
<body>
	<c:import url="main_left.jsp"></c:import>
	<div class="form-horizontal content"  >
		<div id="query">
		<input type="hidden" id="url1" value="<%=basePath %>device/show.action"/>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">用户主键</label>
			<div class="col-sm-3">
				<input   class="form-control" id="uid1" name="uid1"
					placeholder="uid">
			</div>
		</div>
		<!-- <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">devicetype</label>
			<div class="col-sm-3">
				<input   class="form-control" id="devicetype1" name="devicetype1"
					placeholder="devicetype">
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">deviceid</label>
			<div class="col-sm-3">
				<input   class="form-control" id="deviceid1" name="deviceid1"
					placeholder="deviceid">
			</div>
		</div>  -->
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" onclick="submit()" class="btn btn-default">确定</button>
			</div>
		</div>
		</div>
	<table class="table table-bordered" id="table">
		 
		</table>
	</div>
</body>
</html>