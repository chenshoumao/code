<%@page session="false" contentType="text/html" pageEncoding="GB18030" import="java.util.*,javax.portlet.*,com.ibm.datamessage.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>                 
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init>         
        

<DIV style="margin: 6px">
<H3 style="margin-bottom: 3px">��ӭʹ�ã�</H3>
�������� �༭��ʽ ҳ������ͨ���༭�����ƴ�ҳ�棬�Թ����Լ�ʹ�á�<BR>
<H3 style="margin-bottom: 3px">д�� portlet ��ѡ��</H3>
����������ʾ�� portlet ���� �༭��ʽ ʱ��θ��� portlet ��ѡ�����������
<DIV style="margin: 12px; margin-bottom: 36px">
<% /******** Start of sample code ********/ %>
<% 
  PortletPreferences preferences = renderRequest.getPreferences();
  if( preferences!=null ) {
    String value = (String)preferences.getValue(com.ibm.datamessage.DataMessagePortlet.EDIT_KEY,"");
%> 
  <FORM ACTION="<portlet:actionURL/>" METHOD="POST">
    <LABEL for="<%=com.ibm.datamessage.DataMessagePortlet.EDIT_TEXT%>">�½�ֵ</LABEL><BR>
    <INPUT name="<%=com.ibm.datamessage.DataMessagePortlet.EDIT_TEXT%>" value="<%=value%>" type="text"/><BR>
    <INPUT name="<%=com.ibm.datamessage.DataMessagePortlet.EDIT_SUBMIT%>" value="����" type="submit"/>
  </FORM>
<%
  }
else {
  %>����portlet ��ѡ��Ϊ NULL��<%
  }
%>
<% /******** End of sample code *********/ %>
</DIV>


<FORM ACTION='<portlet:renderURL portletMode="view"/>' METHOD="POST">
	<INPUT NAME="back" TYPE="submit" VALUE="������ͼ��ʽ">
</FORM>
</DIV>