<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<script type="text/javascript" src="scripts/js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<script type="text/javascript">
	var curIndex = 1;
	var keyList = ['','devices','positions','listalarms','listdevices','listdevices','listdevices'];
	$(function(){
		for(var i = 2;i <= 6;i++){
			$('#'+i).hide();
		}
		$("table").hide();
	})
	function changeIndex(a) {
		var value = $(a).attr('class'); 
		alert(value);
		$('#'+curIndex).hide();
		//$("table").hide();
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
<body class="">
	<c:import url="main_left.jsp"></c:import>
	<div class="content">
		<div id="query">
			<div id="1">
				<jsp:include page="DeviceActionQuery.jsp" />
			</div>
			<div id="2" style="hidden">
				<jsp:include page="TrackActionQuery.jsp" />
			</div>
			<div id="3" style="hidden">
				<jsp:include page="AlarmActionShow.jsp" />
			</div>
			<div id="4" style="hidden">
				<jsp:include page="DeviceListQuery.jsp" />
			</div>
			<div id="5" style="hidden">
				<jsp:include page="DeviceOnQuery.jsp" />
			</div>
			<div id="6" >
				<jsp:include page="DeviceOffQuery.jsp" />
			</div>
		</div>
		 
		<table class="table table-bordered" id="table">
		 
		</table>
	</div>
</body>
</html>


