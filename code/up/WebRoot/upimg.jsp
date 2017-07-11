<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
        <script src="bootstrap/js/jquery-2.0.3.min.js"></script>
        <script src="bootstrap/js/fileinput.js" type="text/javascript"></script>
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="bootstrap/js/fileinput_locale_zh.js" type="text/javascript"></script>
    </head>
  <body>
        <div class="container kv-main">
            
            <br>
            <form enctype="multipart/form-data">
                
                <div class="form-group">
                    <input id="file-1" type="file" multiple class="file" data-overwrite-initial="false" data-min-file-count="2">
                </div>
                <div>
                相册名称：<input type="text"  id="photo">
                </div>
            </form>
        </div>
    </body>
	<script>
// 	 $.ajax({
// 		   type:"post",//请求方式
// 		  url:"servlet/CreatePhoto",//发送请求地址
// 		   data:{
// 			   description:$("#description").val(),
// 	           content:$("#content").val()
// 		   },
// 		   请求成功后的回调函数有两个参数
// 		   success:function(){
// 		   }
// 		   });
    	 $("#file-1").fileinput({
    		 
    	        uploadUrl: '<%=basePath%>servlet/Upload', // you must set a valid URL here else you will get an error
    	        allowedFileExtensions : ['jpg', 'png','gif'],
    	        overwriteInitial: false,
    	        maxFileSize: 1000,
    	        maxFilesNum: 10,
//     	        allowedFileTypes: ['image', 'video', 'flash'],
    	        slugCallback: function(filename) {
    	        	alert("success");
    	            return filename.replace('(', '_').replace(']', '_');
    	        }
    		});
    	
   
   
	</script>
</html>