<%@page session="false" contentType="text/html" pageEncoding="UTF-8" import="java.util.*,javax.portlet.*,com.ibm.spacecatalog.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	com.ibm.spacecatalog.SpaceCatalogPortletSessionBean sessionBean = (com.ibm.spacecatalog.SpaceCatalogPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.ibm.spacecatalog.SpaceCatalogPortlet.SESSION_BEAN);
%> 

<div>
	<iframe url="http://10.160.131.2:8300/OneMapServer/rest/services/">
	</iframe>
</div>