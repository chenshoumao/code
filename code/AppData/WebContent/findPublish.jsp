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
		String[] str = content.getComponentNames();  
		out.println("<br>");
		for(int i = 0;i< str.length;i++){ 
			out.println(str[i] + "," + "<br>");
			ContentComponent cc = content.getComponent(str[i]);
			 
			if (cc instanceof RichTextComponent) {
				out.println(((RichTextComponent)cc).getRichText());
				out.println(((RichTextComponent)cc));
				out.println(((RichTextComponent)cc).getName());
				out.println((cc.getContainer().getTitle()));
				out.println(((RichTextComponent)cc).getContainer());
			}
			
			else if(cc instanceof ImageComponent) {
				out.println("<img src=" + ((ImageComponent)cc).getResourceURL() + "></img>"); 
			}
			else if(cc instanceof FileComponent) {
				out.println(((FileComponent)cc).getFileName());
			}
			((ShortTextComponent)cc).getContainer().getName();
			out.println(((ShortTextComponent)cc).getName());
			out.println(((RichTextComponent)cc).getRichText());
			out.println(((ImageComponent)cc).getResourceURL());
			 
		} 
	}
%>
<img src="localhost:10039/wps/wcm/myconnect/89c5a57e-a4b7-4c88-b81f-1c5cbb0d4a91/timg.jpeg?MOD=AJPERES">
