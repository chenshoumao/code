<%@page session="false" contentType="text/html" pageEncoding="GB18030" import="java.util.*,javax.portlet.*,com.ibm.datamessage.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>
</portlet-client-model:init>
<%
	String path = request.getScheme()+"://"+request.getServerName();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	 
	String userName = request.getUserPrincipal().getName();
%>   
<%
	com.ibm.datamessage.DataMessagePortletSessionBean sessionBean = (com.ibm.datamessage.DataMessagePortletSessionBean)renderRequest.getPortletSession().getAttribute(com.ibm.datamessage.DataMessagePortlet.SESSION_BEAN);
%>
 
<%
 String pendNum = sessionBean.getPend();
 String haveNum = sessionBean.getHava();
 String meetingNum = sessionBean.getMeeting();
 %>
<script type="text/javascript">
	$(function(){
		daiban();getWCMPublishReview();
	})
	function getWCMPublishReview(){
		$.ajax({
			url:'/wps/myportal/AppData/SolarNewsPortlet/getApprovar.action',
			type:'post',
			dataType:'json',
			data:{'firstPage':'1'},
			success:function(data){
				var page = data['page'];
				var list = data['list'];
				 
				var sumOfResult = page.sumOfResult;
				//alert(sumOfResult);
			}
			
		});
	}
	function daiban(a){
		var curPage = $(a).html();
		if(curPage == undefined)
			curPage = '1';
		 
		$.ajax({
			url:'/wps/myportal/AppData/SolarNewsPortlet/getApprovar.action',
			data:{'firstPage':curPage},
			type:'post',
			dataType:'json',
			success:function(data){ 
				$("#daiban").empty();
				var page = data['page'];
				var pageSum = page.pageSum;
				var sumOfResult = page.sumOfResult;
				var list = data['list']; 

				$(".daiban_sum").html(sumOfResult);
				for(var i = 0;i < list.length;i++){
					var bean = list[i]; 
					var title = bean.title;
					var receiveTime = bean.time;
					var todoUrl = '/wps/myportal/cmOperation';
					var pendingPeople = bean.fromPeople; 
					var item =  '<a href="'+todoUrl+'" target="_blank">'
					 +' <li>'
					 +' <p>数据来源'
					 +' <span class="z">'+pendingPeople+'</span>'
					 +' </p>'
					 +'  <b>'+title+'</b>'
					 +'  <span style="margin-left:-70px;">'+receiveTime+'</span>'
					 +'</li>'
					 +' </a>'
					 $("#daiban").append(item);
				}
				
				$(".daiban_page").empty();
				for(var j = 1;j <= pageSum;j++){
					var pageItem = '<li><a onclick="daiban(this)">'+j+'</a></li>';
					$(".daiban_page").append(pageItem);
				}
			}
		});
	}
</script>
 <!-- 我的待办 -->
	<div class="title">
		<span class="title_switch"><img src="/wps/search/images/q2.png" class="wholeimg">我的待办</span>
		<a href="#"><span class="span_gray2"></span></a>
	</div>
<div class="flet_one">
	<div class="tabPanel2">
		<ul>
			<li class="hit">待办<span class="unread_red daiban_sum">2</span></li>
			<li>已办</li>
			<li>会议<span class="unread_red"><%=meetingNum %></span></li>
       		</ul>
		
	<div id="pane1">
		<div class="numbers7">
			<div class="number">							
				<ul id="daiban">
				<!--	 <% 
					 List<Waiting> list = sessionBean.getListWaiting();
					  for(int i=0;i<list.size();i++){
					  %>
					  <a href="<%=list.get(i).getUrl().replace("pm.cmzd.cmhk.com", "10.160.1.2:81")%>" target="_blank">
					  <li>
					  <p>数据来源
					  <span class="z">王XX</span>
					  </p>
					   <b><%=list.get(i).getTitle() %></b>
					   <span>2017-04-06</span>
					  </li>
					  </a>
					  <%
					  }
					 %> -->
				 </ul>
			</div>

			 
			<div class="page"> 
				<ul class="numberUl daiban_page">
					 
				</ul>
			</div>
		</div></div>
	 <div id="pane2" style="display:none;">
			<div class="numbers8">
				<div class="number">							
					<ul>
					<%
					 List<WaitingFinish> listWaitingFinish = sessionBean.getListWaitingFinish();
  						for(int i=0;i<listWaitingFinish.size();i++){
					  %>
					  <a href="http://10.161.2.67/wps/myportal/AppData/todo/turnToDetail.action?title=<%=listWaitingFinish.get(i).getTitle() %>&status=已审核&cnName=<%=listWaitingFinish.get(i).getFrom()%>&receiveTime=<%=listWaitingFinish.get(i).getDatatime() %>&pendingName=<%=listWaitingFinish.get(i).getOriginator() %>" target="_blank">
						<li>
							<p><%=listWaitingFinish.get(i).getFrom()%>
								<span class="z"><%=listWaitingFinish.get(i).getOriginator() %></span>
							</p> 	
								<b><%=listWaitingFinish.get(i).getTitle() %></b>
							<span>[<%=listWaitingFinish.get(i).getDatatime() %>]</span>
						</li>
					  </a>
					  <%
					  }
					 %>
					</ul>
				</div>
			 
	               <!-- <div class="page">
				<ul class="numberUl">
					<li class="number_shu"><a>1</a></li> 
				</ul>
			</div>-->
	 	</div>
	
	 </div>
	<div  id="pane3" style="display:none;">
		<div class="numbers9" >
			<div class="number">							
				<ul>
					<%
					  List<Meeting> listMeeting = sessionBean.getListMeeting();
						 for(int i=0;i<listMeeting.size();i++){
					  %>
					  <a href="http://10.161.2.67/wps/myportal/AppData/todo/turnToDetail.action?title=<%=listMeeting.get(i).getTitle() %>&status=会议&cnName=<%=listMeeting.get(i).getFrom()%>&receiveTime=<%=listMeeting.get(i).getDatatime() %>&pendingName=<%=listMeeting.get(i).getOriginator() %>" target="_blank">
					  <li><p><%=listMeeting.get(i).getFrom() %>
						<span class="z"><%=listMeeting.get(i).getOriginator() %></span>
						</p> 
			
						 <b><%=listMeeting.get(i).getTitle() %></b>

						<span>[<%=listMeeting.get(i).getDatatime() %>]</span></li>
						</a>
					  <%
					  }
					 %>	
				</ul>
			</div>
		</div>
	 </div>
		
		 
	</div>
</div>
<script type="text/javascript">	
	$(".tabPanel2 > ul li").click(function(){
		var index = $(this).index();	
		// alert($(this).index());
		//alert($(".tabPanel2").children().length);
		//$(".tabPanel2").children().eq(index+1).show().siblings().hide();
		//$("#pane1").hide();
		for(var i = 0;i < 3;i++){
			if(i == index)
				$("#pane"+(i+1)).show();
			else
				$("#pane"+(i+1)).hide();
		}
		 //$("#pane"+$(this).index()+1).show();
	})
	function test1(){
		 
	}
	 
</script>



