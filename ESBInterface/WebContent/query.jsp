<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=basePath%>scripts/jquery1.8.3.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css">
<script type="text/javascript">
	var curIndex = 1;
	var keyList = ['','devices','positions','listalarms','listdevices','listdevices','listdevices'];
	$(function(){
		for(var i = 2;i <= 6;i++){
			$('#'+i).hide();
		}
		$("table").hide();
	})
	function selectChange() {
		var value = $('#select').val();
		$('#'+curIndex).hide();
		$("table").hide();
		//$("#table").remove();
		$('#'+value).show();
		curIndex = value;
	}
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
				var key = keyList[curIndex];
				var list = data[key];
				//$("table").show();
				var tr = "<tr>";
				var map = list[0];
				$.each(map,function(key,values){ 
					console.log(key+values); 
					tr += '<td>'+key+'</td>';
				})
				tr += '</tr>';
				console.log(tr); 
				$("table").append(tr);
				
				
				for(var i = 0;i < list.length;i++){
					var map = list[i];
					var td = '<tr>';
					$.each(map,function(key,values){ 
						console.log(key+values);
						td += '<td>'+values+'</td>';
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
	<div id="query">
		<select id="select" onchange="selectChange()">
			<option value="1">设备位置查询</option>
			<option value="2">历史轨迹查询</option>
			<option value="3">报警信息查询</option>
			<option value="4">用户设备查询</option>
			<option value="5">在线设备查询</option>
			<option value="6">离线设备查询</option>
		</select>
		<div id="1">
			<jsp:include page="DeviceActionQuery.jsp" />
		</div>
		<div id="2">
			<jsp:include page="TrackActionQuery.jsp" />
		</div>
		<div id="3">
			<jsp:include page="AlarmActionShow.jsp" />
		</div>
		<div id="4">
			<jsp:include page="DeviceListQuery.jsp" />
		</div>
		<div id="5">
			<jsp:include page="DeviceOnQuery.jsp" />
		</div>
		<div id="6">
			<jsp:include page="DeviceOffQuery.jsp" />
		</div>
	</div style="margin-top: 70px;">
		<table class="table table-bordered" id="table"> 
	</table>
</body>
</html>