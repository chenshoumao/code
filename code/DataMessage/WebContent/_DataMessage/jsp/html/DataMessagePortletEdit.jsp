<%@page session="false" contentType="text/html" pageEncoding="GB18030" import="java.util.*,javax.portlet.*,com.ibm.datamessage.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>                 
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init>         
        

<DIV style="margin: 6px">
<H3 style="margin-bottom: 3px">欢迎使用！</H3>
这是样本 编辑方式 页。您已通过编辑来定制此页面，以供您自己使用。<BR>
<H3 style="margin-bottom: 3px">写入 portlet 首选项</H3>
这是用来演示在 portlet 处于 编辑方式 时如何更改 portlet 首选项的样本表单。
<DIV style="margin: 12px; margin-bottom: 36px">
<% /******** Start of sample code ********/ %>
<% 
  PortletPreferences preferences = renderRequest.getPreferences();
  if( preferences!=null ) {
    String value = (String)preferences.getValue(com.ibm.datamessage.DataMessagePortlet.EDIT_KEY,"");
%> 
  <FORM ACTION="<portlet:actionURL/>" METHOD="POST">
    <LABEL for="<%=com.ibm.datamessage.DataMessagePortlet.EDIT_TEXT%>">新建值</LABEL><BR>
    <INPUT name="<%=com.ibm.datamessage.DataMessagePortlet.EDIT_TEXT%>" value="<%=value%>" type="text"/><BR>
    <INPUT name="<%=com.ibm.datamessage.DataMessagePortlet.EDIT_SUBMIT%>" value="保存" type="submit"/>
  </FORM>
<%
  }
else {
  %>错误：portlet 首选项为 NULL。<%
  }
%>
<% /******** End of sample code *********/ %>
</DIV>


<FORM ACTION='<portlet:renderURL portletMode="view"/>' METHOD="POST">
	<INPUT NAME="back" TYPE="submit" VALUE="返回视图方式">
</FORM>
</DIV>