<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- release v4.1.8, copyright 2014 - 2015 Kartik Visweswaran -->
<html lang="en">
    <head>
     <base href="<%=basePath%>">
        <meta charset="UTF-8"/>
        <title>Krajee JQuery Plugins - &copy; Kartik</title>
        <link href="<%=basePath%>bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=basePath%>bootstrap/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
        <script src="<%=basePath%>bootstrap/js/jquery-2.0.3.min.js"></script>
        <script src="<%=basePath%>bootstrap/js/fileinput.js" type="text/javascript"></script>
         <script src="<%=basePath%>bootstrap/js/fileinput_locale_zh.js" type="text/javascript"></script>
        <script src="<%=basePath%>bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
    <%=basePath%>
        <div class="container kv-main">
            
            <br>
            <form enctype="multipart/form-data">
                
                <div class="form-group">
                    <input id="file-1" type="file" multiple class="file" data-overwrite-initial="false" data-min-file-count="2">
                </div>
                
            </form>
        </div>
    </body>
	<script>
  
    $("#file-1").fileinput({
        uploadUrl: '<%=basePath%>servlet/Upload', // you must set a valid URL here else you will get an error
        allowedFileExtensions : ['jpg', 'png','gif'],
        overwriteInitial: false,
        maxFileSize: 1000,
        maxFilesNum: 10,
        //allowedFileTypes: ['image', 'video', 'flash'],
        slugCallback: function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
	});
   
	</script>
</html>