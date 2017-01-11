
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath =path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 

<head>
<script type="text/javascript" src="<%=basePath%>scripts/js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.js"></script>
<title>历史轨迹查询</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/styles.css">
<script type="text/javascript">
	var curIndex = 2;
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
				var map = list[0];
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
		<input id="url2" type="hidden"  value="<%=basePath %>track/show.action"/><br>
	 
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">用户主键</label>
			<div class="col-sm-3">
				<input   class="form-control" id="uid2" name="uid2"
					placeholder="uid">
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">设备编号</label>
			<div class="col-sm-3">
				<input   class="form-control" id="deviceid2" name="deviceid2"
					placeholder="deviceid">
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">设备类型</label>
			<div class="col-sm-3">
				<input   class="form-control" id="devicetype2" name="devicetype2"
					placeholder="devicetype">
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">查询日期</label>
			<div class="col-sm-3">
				<input   class="form-control" id="parDate2" name="parDate2"
					placeholder="parDate">
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">开始时间</label>
			<div class="col-sm-3">
				<input   class="form-control" id="timeStart2" name="timeStart2"
					placeholder="timeStart">
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">结束时间</label>
			<div class="col-sm-3">
				<input   class="form-control" id="timeEnd2" name="timeEnd2"
					placeholder="timeEnd">
			</div>
		</div>
		 <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="button" id="btnConfirm" value="sure" onclick="submit()">
			</div>
		</div> 
	</div>
	<table class="table table-bordered" id="table">
		 
		</table>
	</div>
</body>
</html>