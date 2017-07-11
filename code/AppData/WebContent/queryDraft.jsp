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
//			query.addSelectors(Selectors.authorsContain("wpsadmin"));//匹配作者. wpsadmin
			query.addSelector(ProfileSelectors.keywordsContain("mykeyword"));
			// Following selector is faked .waitingMyApproval()
			AuthoringTemplate template = null;
			template = WCMUtils.getAuthTemplate(library, "zhengcefagui_transport", currentUser);
		
			query.addSelectors(Selectors.authoringTemplateEquals(template.getId()));
			List<DocumentId> ll = new ArrayList<DocumentId>();
			System.out.println("go go go !!!!newsContentOneAction");
			// ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
			// "newsContentOneAction").next());
			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					"Draft Stage").next());

			query.addSelectors(WorkflowSelectors.stageIn(ll),
					WorkflowSelectors.statusEquals(Status.DRAFT));
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
				
				
				
				PageIterator it = queryservice.execute(query,1,1);
				out.println(it.getPageSize());out.println("<br>");
				int count = 0;
				if(it.hasNext()){
					out.println("count is : "  + count++ + "<br>");
					ResultIterator re =  it.next();
					while (re.hasNext()) {
						WCMApiObject obj = (WCMApiObject) re.next();
						Content content = (Content) obj;
						out.println(content.getTitle());
						out.println("<br>");
						 
					} 
				}
				
				out.println("<br>");
				out.println("sum is : ");
				long sum = queryservice.count(query);
				out.println(sum);
			 
		%>

</div>

