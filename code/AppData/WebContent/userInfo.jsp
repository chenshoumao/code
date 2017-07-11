<%@page import="com.ibm.db2.jcc.am.wo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*,
java.util.*,com.ibm.portal.um.PumaHome,com.ibm.portal.um.PumaProfile,com.ibm.portal.um.User,
java.security.Principal,
com.ibm.workplace.wcm.api.Workspace,
com.ibm.workplace.wcm.api.WCM_API,
com.ibm.workplace.wcm.api.DocumentLibrary,
com.ibm.workplace.wcm.api.query.*,
com.ibm.workplace.wcm.api.Content,
com.ibm.workplace.wcm.api.*,
com.ibm.workplace.wcm.api.query.WorkflowSelectors.Status,
com.ibm.workplace.wcm.api.security.Access,
com.ibm.workplace.wcm.api.query.QueryService.FilterOperation,
com.ibm.workplace.wcm.api.DocumentId,
com.solar.tech.util.WCMUtils,
com.ibm.portal.um.PumaHome,
com.ibm.portal.um.PumaProfile,
com.ibm.portal.um.User,
com.ibm.portal.um.exceptions.PumaException,
javax.naming.NamingException"%>

<h4>
	<a href="javascript:display('drafts')">My Drafts</a>
</h4>
<div id="drafts" style="display: block">
	<table>
		<%
		String username = "";
		PumaHome pumaHome;
		javax.naming.Context ctx;
		try {
			ctx = new javax.naming.InitialContext();
			pumaHome = (PumaHome) ctx.lookup(PumaHome.JNDI_NAME);
			PumaProfile profile = pumaHome.getProfile();
			User cur_user;

			cur_user = (User) profile.getCurrentUser();
			out.println(cur_user.toString()+"...");
			
			List attributeList = new ArrayList();
			attributeList.add("sn");	out.println(cur_user.toString()+"...");
			 
			attributeList.add("givenName");
			attributeList.add("uid");

			Map valuse = profile.getAttributes(cur_user, attributeList);
			username = (String) valuse.get("uid");
			
			

		} catch (PumaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		%>
	</table>
</div>

