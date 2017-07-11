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
			Workspace wcmspace = WCM_API.getRepository().getWorkspace(
					currentUser);
			// Get draft workflow stage
			DocumentLibrary lib = WCMUtils.getWCMLibrary("extranet",
					currentUser);
			wcmspace.setCurrentDocumentLibrary(lib);
			 
			DocumentIdIterator caIdIterator = wcmspace.findByName(DocumentTypes.Content,"1122cc");
			while (caIdIterator.hasNext()) {
				DocumentId contentId = null;
				contentId = (DocumentId) caIdIterator.next();
				Content content = (Content) wcmspace.getById(contentId);
				
				out.println("find it");
				String[] str = content.getComponentNames();  
				for(int i = 0;i< str.length;i++){ 
					ContentComponent contentComponect = content.getComponent(str[i]);
					if (contentComponect instanceof ShortTextComponent) {
						if(str[i].equals("title")){//标题
							((ShortTextComponent) contentComponect).setText("csmTitle"); 
						}else if(str[i].equals("source")){//来源
							((ShortTextComponent) contentComponect).setText("csmsource");
						}
					}else if(contentComponect instanceof RichTextComponent) {
						if(str[i].equals("summary")){//简介
							((RichTextComponent)contentComponect).setRichText("csmrich");
						}else if(str[i].equals("content")){//内容
							((RichTextComponent)contentComponect).setRichText("csmtext");
						}
					}
					content.setComponent(str[i], contentComponect);
				}
				content.setTitle("fffff");
				wcmspace.save(content);
			}

		 
			
		%>
	</table>
</div>
