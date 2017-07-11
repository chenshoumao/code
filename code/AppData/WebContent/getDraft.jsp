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
	List<DocumentId> ll = new ArrayList<DocumentId>();
	DocumentId id = workspace.findByName(DocumentTypes.WorkflowStage,
			"Draft Stage").next();
	out.println("id is :" + id);
	ll.add(id);

	QueryService queryService = workspace.getQueryService();
	Query query = queryService.createQuery(Content.class);
	query.addSelectors(WorkflowSelectors.stageIn(ll),
			WorkflowSelectors.statusEquals(Status.DRAFT));
	/*query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
	query.addSelectors(Selectors.authorsContain(request
			.getUserPrincipal().getName()));*/
			 
	out.println("user is : " + request
			.getUserPrincipal().toString());
	query.addSelectors(Selectors.authorsContain("uid=wpsadmin,o=defaultWIMFileBasedRealm")); 
	ResultIterator it = queryService.execute(query);
	out.println("count is : " + it.getSize());
	while (it.hasNext()) {
		WCMApiObject obj = (WCMApiObject) it.next();
		Content content = (Content) obj;
		out.println("name is : " + content.getTitle());
	}
%>
