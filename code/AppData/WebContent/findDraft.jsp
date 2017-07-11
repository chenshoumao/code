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
 DocumentLibrary lib = WCMUtils.getWCMLibrary("extranet", currentUser);
 workspace.setCurrentDocumentLibrary(lib);
 
 QueryService queryservice = workspace.getQueryService();
				Query query = queryservice.createQuery(Content.class);
				query.addSelectors(Selectors.libraryEquals(lib));
				
				 
				//query.addSelectors(Selectors.authorsContain("uid=wpsadmin,o=defaultWIMFileBasedRealm"));

				List<DocumentId> ll = new ArrayList<DocumentId>();

				ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
						"Review Stage").next());
				
				query.addSelectors(WorkflowSelectors.stageIn(ll),
						WorkflowSelectors.statusEquals(Status.DRAFT));
				query.addSelectors(Selectors.nameEquals("3323r11e"));
				

				ResultIterator it = queryservice.execute(query);

				if (it.hasNext()) {

					WCMApiObject object = (WCMApiObject) it.next();
					Content content = (Content) object;
					String[] str = content.getComponentNames();

					for (int i = 0; i < str.length; i++) {
					        out.println(str[i] + ",");
						ContentComponent cc = content.getComponent(str[i]);
						out.println(cc.getName() + "<br>");
						if (cc instanceof RichTextComponent) {
							out.println(((RichTextComponent)cc).getName() + ",");
							out.println((cc.getContainer().getTitle())+",");
							out.println((cc.getContainer().getTitle()));
							out.println("rich : " + ((RichTextComponent) cc).getRichText() + "<br>"); 
						}
						else if (cc instanceof ShortTextComponent) {
							out.println("short: " + ((ShortTextComponent)cc).getName()+",");
						//	out.println((cc.getContainer().getTitle())+",");
							out.println(((ShortTextComponent)cc).getContainer().getName());
							out.println(((ShortTextComponent) cc).getText()+"<br>");
						}else if(cc instanceof FileComponent){
							out.println("file" + "<br>"); 
						}
					}
				}

 
 %> 
<!--<img src="/wps/wcm/myconnect/89c5a57e-a4b7-4c88-b81f-1c5cbb0d4a91/timg.jpeg?MOD=AJPERES&cache=none"></img>-->

	</table>
</div>
