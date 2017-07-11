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
com.solar.tech.util.WCMUtils"%>

<h4>
	<a href="javascript:display('drafts')">My Drafts</a>
</h4>
<div id="drafts" style="display: block">
	<table>
		<%
		String group = "";
		try {
			String input1 = request.getUserPrincipal().getName();
			System.out.println("Username entered is: " + input1);

			// Retrieves the default InitialContext for this server.
			javax.naming.InitialContext ctx = new javax.naming.InitialContext();

			// Retrieves the local UserRegistry object.
			com.ibm.websphere.security.UserRegistry reg = (com.ibm.websphere.security.UserRegistry) ctx
					.lookup("UserRegistry");

			// Retrieves the registry uniqueID based on the userName that is
			// specified
			// in the NameCallback.
			String uniqueID = reg.getUniqueUserId(input1);
			System.out.println("uniqueID is: " + uniqueID);

			// Strip the realm name and get real uniqueID
			String uid = com.ibm.wsspi.security.token.WSSecurityPropagationHelper
					.getUserFromUniqueID(uniqueID);
			System.out.println("Real uniqueID is: " + uid);

			// Retrieves the security name from the user registry based on the
			// uniqueID.
			String securityName = reg.getUserSecurityName(uid);
			System.out.println("securityName is: " + securityName);

			// Get user registry name
			String userDisplayName = reg.getUserDisplayName(input1);
			System.out.println("User Registry display name is: "
					+ userDisplayName);

			// Get list of groups for user
			java.util.List groupList = reg.getGroupsForUser(input1);
			ListIterator litr = groupList.listIterator();
			while (litr.hasNext()) {
				String element = (String) litr.next();
				System.out.println("Group List is: " + element);
				group = element;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		%>
	</table>
</div>

