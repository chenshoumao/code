<%@ page import="com.ibm.workplace.wcm.api. *"%>
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
com.ibm.workplace.wcm.api.query.QueryService.FilterOperation"%>
<%
	Principal currentUser = request.getUserPrincipal();
	Workspace workspace = WCM_API.getRepository().getWorkspace(
			currentUser);
	DocumentLibrary library = workspace.getDocumentLibrary("extranet");
	workspace.setCurrentDocumentLibrary(library);

	String contentPath = "extranet/Authoring Templates/template";

	QueryService queryService = workspace.getQueryService();
	Query folder = queryService.createQuery(Folder.class);
	folder.addSelectors(Selectors.typeIn(DocumentTypes.Folder
			.getApiType()));
	folder.addSelectors(Selectors.nameEquals("template"));
	folder.addSelector(Selectors.libraryEquals(workspace
			.getCurrentDocumentLibrary()));
	ResultIterator it = queryService.execute(folder);
	out.println(it.getSize() + ",vvv,<br>");
	while (it.hasNext()) {
		Folder ff = (Folder) it.next();
		ResultIterator result = ff.getChildren();
		out.println(result.getSize() + ",,<br>");
		while (result.hasNext()) {
			AuthoringTemplate template = (AuthoringTemplate) result
					.next();
			out.println(template.getTitle() + ",ttt,<br>");
		}

	}
%>