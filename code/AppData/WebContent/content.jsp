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
com.ibm.workplace.wcm.api.query.QueryService.FilterOperation,
javax.naming.InitialContext"%>
<%
	InitialContext ctx = new InitialContext();
	Principal currentUser = request.getUserPrincipal();
	WebContentService webContentService = (WebContentService) 
		    ctx.lookup("portal:service/wcm/WebContentService");
	Workspace workspace = webContentService.getRepository().getWorkspace("wpsadmin", "wpsadmin");
	DocumentLibrary library = workspace.getDocumentLibrary("extranet");
	workspace.setCurrentDocumentLibrary(library);
	List<DocumentId> ll = new ArrayList<DocumentId>();
	DocumentId id = workspace.findByName(DocumentTypes.WorkflowStage,
			"Publish Stage").next();
	out.println("id is :" + id);
	ll.add(id);

	QueryService queryService = workspace.getQueryService();
	Query query = queryService.createQuery(Content.class);
	query.addSelectors(WorkflowSelectors.stageIn(ll),
			WorkflowSelectors.statusEquals(Status.PUBLISHED));
	query.setSorts(Sorts.byDateModified(SortDirection.DESCENDING)); 
	/*query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
	query.addSelectors(Selectors.authorsContain(request
			.getUserPrincipal().getName()));*/
		 
	//out.println("user is : " + requestquery.addSelectors(Selectors.authorsContain("uid="+currentUser.toString() + "," + userDN)); 
	//		.getUserPrincipal().toString());
	query.addSelectors(Selectors.authorsContain("uid=wpsadmin,o=defaultWIMFileBasedRealm")); 
	ResultIterator it = queryService.execute(query);
	out.println("count is : " + it.getSize());
	while (it.hasNext()) {
		WCMApiObject obj = (WCMApiObject) it.next();
		Content content = (Content) obj;
		out.println("name is : " + content.getTitle() +  " , " + content.getId() + "p t" + content.getPublishedDate());
	}
%>
