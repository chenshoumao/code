<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src=js/jquery1.8.3.min.js></script>
<script type="text/javascript" src=js/jquery-heartbeat.js></script>
<script type="text/javascript">
$(document).ready(function(){
	/* $.jheartbeat.set({
		 url: "login/login2.action?loginName=leader1&password=passw0rd,http://www.baidu.com", // The URL that jHeartbeat will retrieve
		 delay: 2000, // How often jHeartbeat should retrieve the URL
		 div_id: "test_div", 
	 	 
	 });
	var result = beat("login/login2.action?loginName=leader1&password=passw0rd,http://www.baidu.com");
	 alert("here " + result);
	$.each(result,function(idx){
		alert(result[idx]);
	})
	
	var d = runAsync();
	d.then(function(data){
	    console.log("123");
	  //  return runAsync2();
	}) */
	
	 
	setInterval("testFun()",2000);
	
	
});
function testFun(){
	$.ajax({
		url: "http://10.161.2.67/kingtop/sys/login_login"
		}).done(function(){showLi(1);} ).fail(function(){ hideLi(1); } );
	$.ajax({
		url: "http://www.baidu.com"
		}).done(function(){showLi(2);} ).fail(function(){ hideLi(2); } );
}

 function showLi(i){
	 $("#"+i).show();
 }
 function hideLi(i){
	 $("#"+i).hide();
 }

function runAsync(){
    var def = $.Deferred();
    //做一些异步操作
    setTimeout(function(){
        console.log('执行完成');
        def.resolve('随便什么数据');
    }, 2000);
    return def.promise(); //就在这里调用
}


function beat(url){
	var arr = url.split(",");
	var result = new Array(); 
	for(var idx = 0;idx < arr.length;idx++){ 
		console.log(arr[idx] +"," + idx);//idx为数组下标
		 $.ajax({
			url: arr[idx],
			dataType: "html",
			type: "GET",
			error: function(e)   { 
				console.log(arr[idx] + ",,,Error Requesting Data");
				result[idx] = arr[idx] + ",,,Error Requesting Data";
				//$('#'+ $.jheartbeat.options.div_id).append("Error Requesting Data"); 
			},
			success: function(data){ 
				console.log(arr[idx] + ",,," + data);
				result[idx] = arr[idx] + ",,," + data;
				//$('#'+ $.jheartbeat.options.div_id).html(data); 
			}
		 });
	}
	var t=setTimeout("alert('1 seconds!')",5000);
	 
	return result;
	
	/*$.each(arr,function(idx){
	 console.log(arr[idx] +"," + idx);//idx为数组下标
	 $.ajax({
		url: arr[idx],
		dataType: "html",
		type: "GET",
		error: function(e)   { 
			console.log(arr[idx] + ",,,Error Requesting Data");
			result[idx] = arr[idx] + ",,,Error Requesting Data";
			//$('#'+ $.jheartbeat.options.div_id).append("Error Requesting Data"); 
		},
		success: function(data){ 
			console.log(arr[idx] + ",,," + data);
			result[idx] = arr[idx] + ",,," + data;
			//$('#'+ $.jheartbeat.options.div_id).html(data); 
		}
	 });
});*/
}
	 
</script>
</head>
<body>
	 
	<ol>
		<li id="1">1</li>
		<li id="2">2</li>
	</ol>
</body>
</html>