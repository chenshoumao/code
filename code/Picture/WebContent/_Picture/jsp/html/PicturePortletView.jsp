<%@page session="false" contentType="text/html" pageEncoding="GB18030" import="java.util.*,javax.portlet.*,com.ibm.picture.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	com.ibm.picture.PicturePortletSessionBean sessionBean = (com.ibm.picture.PicturePortletSessionBean)renderRequest.getPortletSession().getAttribute(com.ibm.picture.PicturePortlet.SESSION_BEAN);
%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<div class="left_six">

 <iframe src="/wps/myportal/up/servlet/ShowImg?isadmin=<%=sessionBean.getAdmin() %>" frameborder="0" scrolling="no" width="348" height="422"></iframe>
<%--   <iframe src="http://127.0.0.1:8080/Up/servlet/ShowImg?isadmin=<%=sessionBean.getAdmin() %>" frameborder="0" scrolling="no" width="348" height="422"></iframe> --%>

</div>