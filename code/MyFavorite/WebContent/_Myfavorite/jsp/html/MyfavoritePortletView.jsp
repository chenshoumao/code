<%@page session="false" contentType="text/html" pageEncoding="GB18030" import="java.util.*,javax.portlet.*,com.ibm.myfavorite.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	com.ibm.myfavorite.MyfavoritePortletSessionBean sessionBean = (com.ibm.myfavorite.MyfavoritePortletSessionBean)renderRequest.getPortletSession().getAttribute(com.ibm.myfavorite.MyfavoritePortlet.SESSION_BEAN);
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String userName = request.getUserPrincipal().getName();
	String basePath2 = path+"/";
%>
				<div class="left_two">
					<span class="wholeh1">
					<span class="blue"><img src="./images/collection.png" class="wholeimg">�ղؼ�</span>
					<a href="javascript:void(0);" class="modify" id="delete">ɾ��</a>
					<a href="javascript:void(0);" class="modify" id="change">�޸�&nbsp&nbsp|</a>
					<a href="javascript:void(0);" class="modify" id="added">����&nbsp&nbsp|</a>
					</span>
					<form id="deleteForm" method="post" 
						action='<portlet:actionURL><portlet:param name="requestType" value="remove"/></portlet:actionURL>'>
					</form>
				</div>
				
				
				
				<div id="dialog" style="display:none;">
	<form action="">
	<table class="eject">
	   <tr>
	       <td>��&nbsp;&nbsp�ƣ�</td>
	       <td><input type="text" /></td>
	   </tr>
	   <tr>
	        <td>��&nbsp;&nbsp;ַ��</td>
	        <td><input type="text" /></td>
	    </tr>
	 </table>
   </form>
	<div class="center">
		<input type="button" name="loginbtn" id="submitBtn" class="flatbtn-blu hidemodal" value="����" tabindex="3" onclick="toSubmit()"/>
		<input type="button" name="close" id="close" class="flatbtn-blu hidemodal" value="ȡ��" tabindex="3" onclick="cancle()"/>
	</div>
</div>

<!-- ���ƣ�<input type="text" id="myname"><br/> -->
<!-- url:<input type="text" id="myurl"><br/> -->
<input type="text" id="myimage" value="favorites1.png" style="display:none">
<script type="text/javascript">
// alert("in");
				// �ղؼ�����
		$(function() {
		function checkURL(URL){
		var str=URL;
		//�ж�URL��ַ��������ʽΪ:http(s)?://([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?
		//����Ĵ�����Ӧ����ת���ַ�"\"���һ���ַ�"/"
		var Expression= "^((https|http)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.com|net|cn|cc (:s[0-9]{1-4})?/$";
		var objExp=new RegExp(Expression);
		if(objExp.test(str)==true){
		return true;
		}else{
		return false;
		}
		} 
		
		
		$.ajaxSettings.async = false;
		$.getJSON("<%= basePath2%><%=userName%>.json",function(result){
			var $form = $('#deleteForm');
			for(var i=0;i<result.length;i++){
				var $div = $("<div class='favorites'></div>");
				var $input = $('<input type="checkbox" name="collect" id="collect"/>').val(result[i].id);
				var $img = $('<img>').attr("src","<%= basePath %>images/"+result[i].image).attr("name",result[i].image);
				var $a = $('<a target="_blank"></a>').attr("href",result[i].url).append($img);
				var $p = $("<p></p>").text(result[i].name);
				$div.append($input).append($a).append($p);
				$form.append($div);
			}
		});
		$.ajaxSettings.async = true;
		
		
		    // �ղؼ�����
			/*var zhi;*/
			$(".favorites").mousemove(function(event) {

				$(this).find('input').show();
			});

			$('.favorites').find('input[type=checkbox]').bind('click', function() {
				$('.favorites').find('input[type=checkbox]').not(this).attr("checked", false);
				/*zhi = $(this).prop("checked");*/
				$('.favorites').find('input[type=checkbox]').not(this).hide();
			});

			$(".favorites").mouseout(function(event) {
				var zhi;
				zhi =$(this).find('input[type=checkbox]').prop("checked");
				/*alert(zhi);*/
				
				if(zhi == true) {
					$(this).find('input').show();
					
				} else if(zhi == false){
					$(this).find('input').hide();
				}

			});

		});
		
				// ������

		var $1 = function() {
			return document.getElementById(arguments[0]);
		};

		var btnFn = function(e) {
			alert(e.target);
			return false;
		};
		// �����ղ�
		$1('added').onclick = function() {
			addBtn =  function(){
				var myimage = $("#myimage").val();
				var in_name = $("#in_name").val();
				var in_url = $("#in_url").val();
				var Expression=/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
				var objExp=new RegExp(Expression);
			 	if(!(new RegExp("\\S+")).test(in_name)){
			 	alert("���Ʋ���Ϊ�ա���");
			 	return false;
			 	}
// 				 if(in_name.replace(/(^s*)|(s*$)/g, "").length ==0){
// 					  alert("���Ʋ���Ϊ�ա���");
// 					  alert(in_name);
// 					    return false;
// 					  	}
						if(objExp.test(in_url)==false){
						  alert("�ղص�ַ���淶���磺http://www.baidu.com");
						  return false;
						}
					 
  					$.ajax({
			             type: "post",
			             url: "<portlet:actionURL><portlet:param name="requestType" value="add"/></portlet:actionURL>",
			             data: {"image":myimage,"name":in_name,"url":in_url},
			             dataType: "json",
			             async: false,
			             success: function(data){
			            window.location.reload();
			             },
			             error:function(data){
			             window.location.reload();
			             }
			             });
					};
			easyDialog.open({
				container: {
					header: '�����ղ�',
					content: '<form action="<portlet:actionURL><portlet:param name="requestType" value="add"/></portlet:actionURL>" id="addForm"><ul class="imgSelect"><li class><img src="<%= basePath %>/images/favorites1.png" width="53px" height="53px"></li><li class><img src="<%= basePath %>/images/favorites2.png" width="53px" height="53px"></li><li class><img src="<%= basePath %>/images/favorites3.png" width="53px" height="53px"></li><li class><img src="<%= basePath %>/images/favorites4.png" width="53px" height="53px"></li><li class><img src="<%= basePath %>/images/favorites5.png" width="53px" height="53px"></li><li class><img src="<%= basePath %>/images/favorites6.png" width="53px" height="53px"></li></ul><table class="eject"><tr><td>��&nbsp;&nbsp�ƣ�</td><td><input type="text" name="name" id="in_name" /></td></tr><tr><td>��&nbsp;&nbsp;ַ��</td><td><input type="text" name="url" id="in_url"/></td></tr></table></form>',
					yesFn: addBtn,
					noFn: true
				},
				fixed: false
			});
		};
		
// 		function addFn(){
// 		//ͼƬ����
// 		var myimage = $("#myimage").val();
// 		var in_name = $("#in_name").val();
// 		var in_url = $("#in_url").val();
// 		//�ǿ���֤
		
		
// 		 $.ajax({
//              type: "post",
//              url: "<portlet:actionURL><portlet:param name="requestType" value="add"/></portlet:actionURL>",
//              data: {"image":myimage,"name":in_name,"url":in_url},
//              dataType: "json",
//              async: false,
//              success: function(data){
//             window.location.reload();
//              },
//              error:function(data){
//              window.location.reload();
//              }
//              });
// 		}
		
		
		// �޸��ղ�
		$1('change').onclick = function() {
		var $collectID = $("input[name='collect']:checked");
				if($collectID.length>1||$collectID.length==0){
					easyDialog.open({
					  container : {
					    header : '������ʾ',
					    content : '��ѡ���޸����'
					  },
					  overlay : false
					});
				}
				var $href = $collectID.next("a").attr("href");
				var $name = $collectID.next("a").find("img").attr("name");
				var $img  = $collectID.nextAll("p").html();
				var $id   = $collectID.attr("value");
				
				var updateBtn = function(){
				var up_img = $("#up_img").val();
				var up_href = $("#up_href").val();
					  if(up_img.replace(/(^s*)|(s*$)/g, "").length ==0||up_href.replace(/(^s*)|(s*$)/g, "").length ==0){
					  alert("����Ϊ�ա���");
					    return;
					  }
  					  $("#updateForm").submit();
					  return true;
					};
				if($collectID.length==1){
				easyDialog.open({
				container: {
					header: '�޸��ղ�',
					content: '<form action="<portlet:actionURL><portlet:param name="requestType" value="update"/></portlet:actionURL>" id="updateForm"><table class="eject"><tr><td>��&nbsp;&nbsp�ƣ�</td><td> <input type="hidden" name="id" id="collectID" value='+$id+'>	 <input type="text" name="name" value='+$img+' id="up_img"></td></tr><tr><td>��&nbsp;&nbsp;ַ��</td><td><input type="text" name="url" value='+$href+' id="up_href" ></td></tr></table></form>',
					yesFn: updateBtn,
					noFn: true
				},
				fixed: false
			});
				
				}
			
		};
		
		
       
		// ɾ��
		function delFn(){
			var $collectID = $("input[name='collect']:checked");
// 			if(confirm("��ȷ��ɾ����ѡ�е�ѡ�")){
				document.getElementById('deleteForm').submit();
// 			}
		}
		$1('delete').onclick = function() {
		var $collectID = $("input[name='collect']:checked");
		if($collectID.length>0){
		easyDialog.open({
				container: {
					header: 'ɾ���ղ�',
					content: 'ȷ��ɾ��ѡ���ղأ�',
					yesFn:delFn,
					noFn: true
				},
				fixed: false
			});
		}else{
			easyDialog.open({
					  container : {
					    header : '������ʾ',
					    content : '��ѡ��ɾ�����'
					  },
					  overlay : false
					});
		
		   }
		};
		
		
// 
		$(".imgSelect li").live('click', function(event) {
			/* Act on the event */
			$(this).addClass('selectPic').siblings().removeClass('selectPic');
			var $src = $(this).children("img");
			var address = $src.attr("src");
			var name = address.substring(address.length-14,address.length);
			$("#myimage").val(name);
			//$(this).append("<input type='text' style='display:none' name='image' value="+name+">");
		});		
		
	</script>