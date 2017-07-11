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

<h> <a href="javascriptdisplay('approval')">View Content
	Requiring My Approval</a></h>
<div id="approval" style="">

	<table>
		<%
			Principal currentUser = request.getUserPrincipal();
			Workspace workspace = WCM_API.getRepository().getWorkspace(
					currentUser);
			// Get draft workflow stage
			DocumentLibrary lib = WCMUtils.getWCMLibrary("extranet",
					currentUser);
			workspace.setCurrentDocumentLibrary(lib);
			 
			DocumentIdIterator approvalIt = workspace.findByName(
					DocumentTypes.WorkflowStage, "Review Stage");
			DocumentId approvalSt = null;

			if (approvalIt.hasNext()) {
				approvalSt = (DocumentId) approvalIt.next();
			}
			out.println("aaa " + approvalSt);
			if (approvalSt != null) {
			 
				DocumentId[] arraySt = new DocumentId[1];
				QueryService queryservice = workspace.getQueryService();
				Query query = queryservice.createQuery(Content.class);
				query.addSelectors(Selectors.libraryEquals(lib));//
				
				
				 AccessFilter filter = queryservice.createAccessFilter(
						 Access.REVIEWER, FilterOperation.ANY_USER,
						 currentUser.getName());
				 
				 query.setAccessFilter(filter);
				 query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
				 List<DocumentId> ll = new ArrayList<DocumentId>();
					ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
							"Review Stage").next());
				 query.addSelectors(WorkflowSelectors.stageIn(ll));
				 ResultIterator it = queryservice.execute(query);
				 
				boolean found = false;
				while (it.hasNext()) {
					found = true;
					out.println("found it " + found);
					WCMApiObject obj = (WCMApiObject) it.next();
					Content content = (Content) obj;
					out.println("<tr>");
					out.println("<td>" + content.getTitle() + "</td>"); 
					out.println("</tr>");
				}
				if (!found) {
					out.println("There are no documents pending for approval");
				}
			} else {
				out.println("Draft or Approval stages couldn't be found");
			}
		%>
	</table>
</div>
