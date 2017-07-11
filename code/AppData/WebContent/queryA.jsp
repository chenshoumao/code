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

	<%
			Principal currentUser = request.getUserPrincipal();
			out.println("user is " + currentUser);
			Workspace workspace = WCM_API.getRepository().getWorkspace(
					currentUser);
			DocumentLibrary library = workspace.getDocumentLibrary("extranet");
			workspace.setCurrentDocumentLibrary(library);
			QueryService queryservice = workspace.getQueryService();
			Query query = queryservice.createQuery(Content.class);  
			query.addSelectors(Selectors.libraryEquals(library));// query.addSelector(Selectors.typeEquals(DocumentTypes.Content));
			query.addSelector(Selectors.typeEquals(Content.class));

			List<DocumentId> ll = new ArrayList<DocumentId>();
			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					"Review Stage").next());
			query.addSelectors(WorkflowSelectors.stageIn(ll),
					WorkflowSelectors.statusEquals(Status.DRAFT));
			
			AccessFilter filter = queryservice.createAccessFilter(
					Access.MANAGER, FilterOperation.ALL_USERS,
					currentUser.getName());
			query.setAccessFilter(filter);
			
				ResultIterator iterator = queryservice.execute(query);
				
				out.println("count is " + iterator.getSize());
				out.println("<br>");
			 	while (iterator.hasNext()) {
					WCMApiObject obj = (WCMApiObject) iterator.next();
					Content content = (Content) obj;
					out.println(content.getTitle());
					out.println("<br>");
					 
				} 
				out.println("hell111o world");out.println("<br>"); 
			 
		%>

</div>

