<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ceshi.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  
  <head>
    <title>My Uploadify Implementation</title>
    <link rel="stylesheet" type="text/css" href="uploadify2/uploadify.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="uploadify2/jquery.uploadify.min.js"></script>
<script type="text/javascript">
            $(function() {
                $("#upload_org_code").uploadify({
                    'height'        : 27,
                    'width'         : 80, 
                    'buttonText'    : '选择图片',
                    'swf'           : '<%=basePath%>/uploadify/uploadify.swf',
                    'uploader'      : 'uploadIMGSerlet',
                    'auto'          : true,
                    'multi'         : false,
                    'removeCompleted':false,
                    'cancelImg'     : '<%=basePath%>/uploadify/uploadify-cancel.png',
                    'fileTypeExts'  : '*.jpg;*.jpge;*.gif;*.png',
                    'fileSizeLimit' : '2MB',
                    'onUploadSuccess':function(file,data,response){
                        $('#' + file.id).find('.data').html('');
                        $("#upload_org_code_name").val(data);
                        $("#upload_org_code_img").attr("src","${pageContext.request.contextPath}/getImg?file="+data);  
                        $("#upload_org_code_img").show();
                    },
                    //加上此句会重写onSelectError方法【需要重写的事件】
                    'overrideEvents': ['onSelectError', 'onDialogClose'],
                    //返回一个错误，选择文件的时候触发
                    'onSelectError':function(file, errorCode, errorMsg){
                        switch(errorCode) {
                            case -110:
                                alert("文件 ["+file.name+"] 大小超出系统限制的" + jQuery('#upload_org_code').uploadify('settings', 'fileSizeLimit') + "大小！");
                                break;
                            case -120:
                                alert("文件 ["+file.name+"] 大小异常！");
                                break;
                            case -130:
                                alert("文件 ["+file.name+"] 类型不正确！");
                                break;
                        }
                    },
                });
            });
         
</script>
</head>
<body>
<%=basePath%>
<input type="file" name="file_upload" id="file_upload" />
</body>
</html>
