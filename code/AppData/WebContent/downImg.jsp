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
com.solar.tech.util.WCMUtils,
java.io.InputStream,
java.io.OutputStream,
java.net.URLEncoder"%>

<h4>
	<a href="javascript:display('drafts')">My Drafts</a>
</h4>
<div id="drafts" style="display: block">
	<table>
		<%
			Workspace wcmspace = WCM_API.getRepository().getWorkspace();
			Principal currentUser = request.getUserPrincipal();
			DocumentLibrary lib = WCMUtils.getWCMLibrary("extranet", currentUser);
			QueryService queryService = wcmspace.getQueryService();
			Query imageQuery = queryService.createQuery();
			imageQuery.addSelectors(Selectors.typeIn(DocumentTypes.LibraryImageComponent
					.getApiType()));
			
			imageQuery.addSelectors(Selectors.nameEquals("11211"));
			ResultIterator it = queryService.execute(imageQuery);
			while(it.hasNext()){
				LibraryImageComponent image = (LibraryImageComponent) it.next();
				
				DocumentId id = wcmspace.createDocumentId("04e19ea6-4949-4a1c-a118-a862b476853d");
				Document imgag = wcmspace.getById(id);
				if(imgag instanceof LibraryImageComponent){
					
					out.println(((LibraryImageComponent)imgag).getId().getId() + "<br>");
				}
				
				 
				out.println(image.getId() + "<br>");
				out.println(image.getResourceURL() + "<br>");
				InputStream reader = null;
				response.setHeader("content-disposition", "attachment;fileName="+URLEncoder.encode(image.getTitle(), "UTF-8"));
				OutputStream outS = null;
			    byte[] bytes = new byte[1024];
			    int len = 0;
			    reader = image.getImageStream();
			    outS = response.getOutputStream();
			    while ((len = reader.read(bytes)) != -1) {
			    	outS.write(bytes, 0, len);
	            }
			    if (reader != null) {
	                reader.close();
	            }
	            if (out != null)
	                out.close();
			}
			
			WCM_API.getRepository().endWorkspace();
			
			
			 
		%>
	</table>
</div>

