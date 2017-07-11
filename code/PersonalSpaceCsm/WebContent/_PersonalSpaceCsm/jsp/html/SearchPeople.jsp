<%@page session="false" contentType="text/html" pageEncoding="UTF-8" import="java.util.*,javax.portlet.*,com.ibm.personalspacecsm.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
 
<%
    String path = request.getContextPath(); 
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String basePath2 = path + "/";
	String userName = request.getUserPrincipal().getName();
%>
<script type="text/javascript">
$(function(){
	$.ajaxSettings.async = false;
$.getJSON("http://10.161.2.72:10039/AppData/PersonalSpace/show.action",function(result){
		 
		 var json = eval(result);
		 var map = json.user;
		 $("#myname").val(map.name);
		 $("#mytel").val(map.tel);
		 $("#mymobile").val(map.mobile);
		 $("#mypostalCode").val(map.postalCode);
		 $("#mymail").val(map.mail);
		 $("#myaddress").val(map.address);
		 $("#mystaffNumber").val(map.staffNumber);
		 $("#mydepartment").val(map.department);
		 $("#myprofession").val(map.profession);
		 $("#mytitle").val(map.title);
		});
$.ajaxSettings.async = true; 
});

function upimg(){
$("#photoForm").submit();
}

</script>
<style type="text/css">
	.personal_left{float: left;width: 155px;position: relative;}
	input{border: 1px solid #ffffff;text-indent: 8px;}
	/*a  upload */
	.a-upload {
	    padding: 4px 10px;
	    height: 20px;
	    line-height: 20px;
	    position: absolute;
	    cursor: pointer;
	    color: #888;
	    background: rgba(255, 255, 255, 0.76);
	    border: 1px solid #ddd;
	    border-radius: 4px;
	    overflow: hidden;
	    display: inline-block;
	    *display: inline;
	    *zoom: 1;
	    top: -120px;
    	left: 10px;
    	visibility:hidden;
    	display: none;
	}

	.a-upload  input {
	    position: absolute;
	    font-size: 100px;
	    right: 0;
	    top: 0;
	    opacity: 0;
	    filter: alpha(opacity=0);
	    cursor: pointer
	}
	.fileerrorTip{
		top: 170px;
	    width: 100%;
	    left: 0px;
	    text-align: center;
	    display: inline-block;
	    position: absolute;
	}
	.personal_left:hover.a-upload{
		visibility: visible;
	}
	.showpage input{
	background:#f6f6f6;
	    cursor: pointer;
	}
	.wptheme1Col .wpthemeCol {
    width: 1100px;
	}
	.wpthemeOverflowAuto {
    overflow: hidden;
	}
	.myedit{
	    width: 100px;
	    height: 35px;
	    border: 0px solid #fafafa;
	    background-color: #f6f6f6;
	    cursor: pointer;
	    outline: none;
	    }
	.mysearch{
	width: 100px;
	height: 35px;
	border: 0px solid #fafafa;
	background-color: #f6f6f6;
	outline: none;
	cursor: pointer;
	}
</style>
		<div class="stations_left">
			<h1 class="wholeh1">
				<span class="blue"><img src="<%=basePath%>/images/q5.png" class="wholeimg">个人空间</span>
			</h1>
			<ul class="showpage">
<!-- 			<li><span>·</span><a href="javascript:;" id="mymessage">个人信息编辑</a></li> -->
<!-- 			<li><span>·</span><a href="javascript:;" id="mysearch">人员查找</a></li> -->
				<li>
					<INPUT value="个人信息编辑" type="submit" class="myedit"/> 
				</li>
				<li>
					<INPUT value="人员查找" type="submit" class="mysearch"/> 
				</li>
			</ul>
		</div>
		
		<!-- 右边内容 -->
		<div class="stations_right">
<%-- 			<form action="<portlet:actionURL><portlet:param name="requestType" value="update"/></portlet:actionURL>" id="updateForm"> --%>
			<h1 class="wholeh1">
				<span class="blue"><img src="<%=basePath%>/images/q6.png" class="wholeimg">编辑修改个人信息</span>
			</h1>
			<p class="personal">个人信息</p>
			<div class="personal_left">
				<img src="<%=basePath %>/images/information3.png" id="myphoto" width="152" height="158">
				<form action="<portlet:actionURL><portlet:param name="requestType" value="upImg"/></portlet:actionURL>" method="POST" enctype="multipart/form-data" id="photoForm">
				<a href="javascript:;" class="a-upload" id="daye">
				    <input type="file" name="file">点击这里上传照片
				</a>
				</form>
				<span class="fileerrorTip"></span>
			</div>
			<div class="personal_right">
				<span style="margin-left: 27px;">姓名：</span><input type="text" disabled="disabled" name="name" id="myname"><span>联系电话：</span><input type="text" style="margin-right: 0;" disabled="disabled"  name="tel" id="mytel"><br>
				<span>手机号码：</span><input type="text" disabled="disabled"   name="mobile" id="mymobile"><br>
				<span>邮政编码：</span><input type="text" disabled="disabled"  name="postalCode" id="mypostalCode">电子邮箱：<input type="text" style="margin-right: 0;" disabled="disabled"  name="mail" id="mymail"><br>
				<span>通讯地址：</span><input type="text" disabled="disabled"  name="address" id="myaddress">
			</div>
			<br clear="both">
			<p class="department">部门信息</p>
			<span style="margin-left: 25px;">员工工号：</span><input type="text" disabled="disabled"  name="staffNumber" id="mystaffNumber"><span>所属部门：</span><input type="text" disabled="disabled"  name="department" id="mydepartment"><br>
			<span style="margin-left: 53px;">职业：</span><input type="text" disabled="disabled"  name="profession" id="myprofession"><span>岗位名称：</span><input type="text" disabled="disabled"  name="title" id="mytitle"><br>
			<a href="javascript:void(0)" class="data" onclick="check()" id="hehe">编辑</a>
<!-- 			<a href="javascript:;" class="data" onclick="update()">保存资料</a> -->
<!-- 			</form> -->
		</div>
	<script type="text/javascript">
	var inputlist=document.getElementsByTagName("input");
	window.onload=function(){
		document.getElementById('hehe').onclick=function(){
			var check = document.getElementById('hehe').innerHTML;
			if(check=='编辑'){
				for(i=0;i<inputlist.length;i++){
				inputlist[i].disabled=false;
				inputlist[i].style.border="1px solid #dcdcdc";
				}
				document.getElementById('hehe').innerHTML='保存';
				document.getElementById('daye').style.display="block";
			}
			
			if(check=='保存'){
			
			var name = $("#myname").val();
			var tel = $("#mytel").val();
			var mobile = $("#mymobile").val();
			var postalCode = $("#mypostalCode").val();
			var mail = $("#mymail").val();
			var address = $("#myaddress").val();
			var staffNumber = $("#mystaffNumber").val();
			var department = $("#mydepartment").val();
			var profession = $("#myprofession").val();
			var title = $("#mytitle").val();
			 $.ajax({
			 type: "get",
			  url: "http://10.161.2.72:10039/AppData/PersonalSpace/update.action",
			     data: {name:name, homePhone:tel,mobile:mobile,postalCode:postalCode,mail:mail,address:address,staffNumber:staffNumber,department:department,profession:profession,title:title},
             dataType: "json",
             success: function(data){
            	 alert(data.state);
              //upimg();
             },
             error:function(data){
              upimg();
             }
			}); 
			}
			
		}
	}

	$(".a-upload").on("change","input[type='file']",function(){
	    var filePath=$(this).val();
	    if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
	        $(".fileerrorTip").html("").show();
	        var arr=filePath.split('\\');
	        var fileName=arr[arr.length-1];
	        $(".fileerrorTip").html(fileName);
	    }else{
	        $(".fileerrorTip").html("");
	        $(".fileerrorTip").html("您未上传文件，或者您上传文件类型有误！").show();
	        return false 
	    };
	});

	$(".personal_left").mouseenter(function(e) {
		$(".a-upload").css({"visibility": "visible"});
		var nima = e.pageY-550;
		document.getElementById('daye').style.top=nima+"px"
		if (nima > 125) {
			document.getElementById('daye').style.top=125+"px"
		};
		if (nima < 0) {
			document.getElementById('daye').style.top=0+"px"
		};
	})
	$(".personal_left").mouseleave(function() {
		$(".a-upload").css({"visibility": "hidden"});
	})
	/*document.getElementById('nima').onmousemove=function(event){
		var divheight=this.offsetHeight;
		var y=event.clientY-300;
		document.getElementById('daye').style.top=y+"px";
	}*/
</script>
	
