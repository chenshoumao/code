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
			Principal currentUser = request.getUserPrincipal();
			Workspace workspace = WCM_API.getRepository().getWorkspace(
					currentUser);
			DocumentLibrary lib = WCMUtils.getWCMLibrary("guanweihui",
					currentUser);
			workspace.setCurrentDocumentLibrary(lib);
			// Get draft workflow stage
			DocumentIdIterator draftIt = workspace.findByName(
					DocumentTypes.WorkflowStage, "Publish Stage");
			DocumentId draftSt = null;
			if (draftIt.hasNext()) {
				draftSt = (DocumentId) draftIt.next();
			}

			if (draftIt != null) {

				// Get all the content in this stage
				//DocumentId[] arraySt= new DocumentId[];
				DocumentId[] arraySt = new DocumentId[1];
				arraySt[0] = draftSt;
				String[] arrayUsers = new String[1];
				arrayUsers[0] = request.getUserPrincipal().getName();
				DocumentIdIterator drafts = workspace
						.findContentByWorkflowStage(arraySt, arrayUsers,
								Workspace.ROLE_AUTHOR);
				boolean found = false;
				while (drafts.hasNext()) {
					found = true;
					DocumentId draft = (DocumentId) drafts.next();
					Content content = (Content) workspace.getById(draft);
					//content.approve(false, false, "cscsm");
					out.println("<tr>");
					out.println(content.getTitle() + "," + content.getId());
					if(content.getTitle().equals(""))
					//workspace.delete(content.getId());
					out.println("</tr>");
				}
				if (!found) {
					out.println("There are no drafts currently available");
				}
			}
		%>
	</table>
</div>
