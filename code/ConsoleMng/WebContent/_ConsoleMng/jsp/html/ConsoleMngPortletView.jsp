<%@page session="false" contentType="text/html" pageEncoding="GB18030" import="java.util.*,javax.portlet.*,com.solar.consolemng.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	com.solar.consolemng.ConsoleMngPortletSessionBean sessionBean = (com.solar.consolemng.ConsoleMngPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.solar.consolemng.ConsoleMngPortlet.SESSION_BEAN);
%>
<iframe src="/wps/myportal/MonitorMng/mainpage.do"  style="width:100%;min-height:800px;"></iframe>