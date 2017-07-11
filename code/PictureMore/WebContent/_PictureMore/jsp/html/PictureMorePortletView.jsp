<%@page session="false" contentType="text/html" pageEncoding="GB18030" import="java.util.*,javax.portlet.*,com.solar.picturemore.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	com.solar.picturemore.PictureMorePortletSessionBean sessionBean = (com.solar.picturemore.PictureMorePortletSessionBean)renderRequest.getPortletSession().getAttribute(com.solar.picturemore.PictureMorePortlet.SESSION_BEAN);
%>
 <div style="margin-top: 5px;border: 1px solid #a9cfe5;overflow: hidden;">
 <iframe src="/wps/myportal/up/servlet/ShowImgMore?isadmin=<%=sessionBean.getAdmin() %>" frameborder="0" scrolling="no" width="100%" height="100%" style="min-height: 900px;"></iframe>
</div>