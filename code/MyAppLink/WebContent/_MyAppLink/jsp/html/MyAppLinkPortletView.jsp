<%@page session="false" contentType="text/html" pageEncoding="GB18030" import="java.util.*,javax.portlet.*,com.ibm.myapplink.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String basePath2 = path+"/";
	com.ibm.myapplink.MyAppLinkPortletSessionBean  sessionBean = (com.ibm.myapplink.MyAppLinkPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.ibm.myapplink.MyAppLinkPortlet.SESSION_BEAN);
    String name = sessionBean.getName(); 
%>
<%-- <script type="text/javascript" src="<%=path%>/js/jquery1.8.3.min.js"></script> --%>
 

</style>
<script type="text/javascript">
	//声明一个数据用以存储链接
	var arr = new Array();
	$(function(){	
	var name = "<%=name%>";
	var name_t = name.split(",");
	
	console.log('name : ' + name_t);
		$.getJSON("<%= basePath2 %>AppLink.json",function(result){
			if(result!=null&&result!=''){
				var $ul = $(".left_three");
				var selfconut=0;
				for(var i=0;i<result.length;i++){
				    	var obj=result[i];
						arr[i] = obj.appURL;
	                		if(name.indexOf(obj.appKey) > -1){
	                  		$ul.append("<div class='aimg'><div class='ssimg enter' onclick='issure(\""+obj.appURL+"\",\""+obj.appName+"\")'  name='"+obj.appName+"'></div>"+obj.appName+"</div></div>");
			                }else{
	        			          $ul.append("<div class='aimg'><div class='ssimg'></div>"+obj.appName+"</div></div>");
			                }

				    	if(selfconut==2){
						   selfconut=0;
						   $ul.append("<br clear=\"both\"/>");
					}else{
						   selfconut++;
					}
				}
				console.log("ip is,<%=request.getRemoteHost()%>");
			if("<%=request.getUserPrincipal().getName() %>" == "leader1" || '<%=request.getUserPrincipal().getName().indexOf("_user") %>' != -1)
				$ul.append("<div class='aimg' ><div class='ssimg enter' onclick='issure(\"/wps/myportal/cmOperation\",\"服务平台运维\")'  name='服务平台运维'></div>服务平台运维</div></div>");				

				//鼠标经过图片，颜色变换
		$(".aimg").hover(function() {
			if($(this).children('div').hasClass('enter')){
				$(this).find('.ssimg').css({
					"background-position-y": "-62px",
					"cursor":"pointer"
				})
				$(this).css({"color":"#005bc1"});
			}
		}, function() {
			$(this).find('.ssimg').css({
				"background-position-y": "0px"
			});
			$(this).css({"color":"#666"});

		    });
				//$ul.append("<li><a name='Others' target='_blank' href=''>其他</a></li>");
			}
			//心跳检测链接的状态，成功的话就显示，否则隐藏
			//setInterval("HeartBeat()",1000); 
		});
		
		

	});
	
	function HeartBeat(){
		$.each(arr,function(idx){
			console.log(arr[idx] +"," + idx);//idx为数组下标
			$.ajax({url: arr[idx]}).done(function(){$("#"+idx).show()} ).fail(function(){$("#"+idx).hide() } );
		});
	}
	
	function issure(url,appName){
	        $("#requestName").val(appName);
			//使用 jQuery异步提交表单
			jQuery.ajax({
				url:'<portlet:actionURL><portlet:param name="logsTag" value="logsTag"/></portlet:actionURL>',
				data:$("#logsTagForm").serialize(),
				type:"POST",
				beforeSend:function(){  
					//alert("submit before!");
				},
				success:function(){
					$("#requestName").val("");
				},
				error:function(){
					$("#requestName").val("");
				}
			});
		window.open(url);
	}
		
</script>


<div class="left_three">
	<span class="wholeh1">
	<span class="blue"><img src="/wps/search/images/collection.png" class="wholeimg">应用链接</span>
	<a href="#"><span class="span_gray"></span></a>
	</span>
	<!-- <div class="aimg"><div class="ssimg"></div>其他</div> -->
</div>
<form id="logsTagForm" name="logsTagForm" method="POST">
   <input type="hidden" value="requestName" name="requestName" id="requestName"/>
</form>

