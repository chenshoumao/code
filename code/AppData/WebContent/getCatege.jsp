<%@ page import="com.ibm.workplace.wcm.api. *"%>
<%@ page import="java.util.*,
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
com.solar.tech.util.WCMUtils"%>
<%

			Principal currentUser = request.getUserPrincipal();
			Workspace sp = WCMUtils.getWCMWorkspace(currentUser);
			DocumentLibrary lib = WCMUtils.getWCMLibrary("extranet",
					currentUser);
			sp.setCurrentDocumentLibrary(lib);
			 
			DocumentIdIterator caIdIterator = 
					sp.findByName(DocumentTypes.Category, "testCa");
			Identity iddc = null;
			out.println(" count is  : "  + caIdIterator.getCount() + "<br>");
			while(caIdIterator.hasNext()){ 
				iddc = caIdIterator.next();
				out.println("hre find id : "  + iddc.getID()+ "<br>");
			}
			 
%>
