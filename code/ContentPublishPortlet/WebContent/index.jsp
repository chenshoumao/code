<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
    <style type="text/css">
        div{
            width:100%;
        }
    </style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="servlet1/test" method="post">
    	<div>
   			<h1>完整demo</h1>
   			<input name="content" id="content" style="display:none;">
   			 <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
		</div>
    	<input type="button" onclick="check('testT')" value="submitqw">
    	<input type="text" id="testT">
    </form>
    <script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    	var ue = UE.getEditor('editor');
    	function check(a){	
    		alert(a);
    		$("#" + a ).val(123);
    		//var content = UE.getEditor('editor').getContent();
    		/*$("#content").val(content);
    		alert(content);
    	   $("form").submit();*/
    	}
    </script>
  </body>
</html>
