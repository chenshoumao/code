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
		Workspace wcmspace = null;// 声明一个WCM工作空间
		Principal currentUser = request.getUserPrincipal();
	 
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);

			DocumentLibrary lib = null;// web内容库
			lib = WCMUtils.getWCMLibrary("extranet", currentUser);
			wcmspace.setCurrentDocumentLibrary(lib);
			/*
			 * Content content = null; content =
			 * wcmspace.createContent(authTemplate.getId(), iterator.next(),
			 * null, ChildPosition.END);
			 */
			AuthoringTemplate authTemplate = null;// 编写模板
			QueryService queryService = wcmspace.getQueryService();
			Query folder = queryService.createQuery(Folder.class);
			folder.addSelectors(Selectors.typeIn(DocumentTypes.Folder
					.getApiType()));
			folder.addSelectors(Selectors.nameEquals("photo"));
			folder.addSelector(Selectors.libraryEquals(wcmspace
					.getCurrentDocumentLibrary()));
			ResultIterator it = queryService.execute(folder);

			while (it.hasNext()) {
				Folder ff = (Folder) it.next();
				ResultIterator result = ff.getChildren();
				out.println("count is:" + result.getSize() + "<br>");
				while (result.hasNext()) {
					LibraryImageComponent img = (LibraryImageComponent)result.next();
					 out.println(img.getResourceURL());
				}

			}
			 
 
		 
		%>
	</table>
</div>
