<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
      <head>
        <base href="<%=basePath%>">
        <title>Uploadify</title>
        <link href="css/uploadify.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
        <script type="text/javascript" src="js/swfobject.js"></script>
        <script type="text/javascript" src="js/jquery.uploadify.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
            $("#uploadify").uploadify({
                'swf'       : 'uploadify.swf',
                'uploader'         : 'servlet/Upload',
               // 'cancelImg'      : 'images/uploadify-cancel.png',
                'folder'         : 'uploads',
                'fileDataName'   : 'uploadify',//
                fileSizeLimit   : 1024,//KB,文件大小
                'queueID'        : 'fileQueue',
                'auto'           : false,//是否选取文件后自动上传
                'multi'          : true,//是否支持多文件上传
                'simUploadLimit' : 20,//每次最大上传文件数
                'buttonText'     : '选择文件',
                'displayData'    : 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比 
                // 'fileDesc'       : '支持格式:jpg或gif', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 
            });
        });
        
        function up(){
        	alert(11);
        	jQuery('#uploadify').uploadify('upload','*');
        	alert(33);
        }
        </script>
    </head>
    <body>
        <div id="fileQueue"></div>
        <input type="file" name="uploadify" id="uploadify" />
        <p>
        <a href="javascript:up();">开始上传</a>&nbsp;
        <a href="javascript:jQuery('#uploadify').uploadify('cancel','*')">取消所有上传</a>
        </p>
    </body>
</html>