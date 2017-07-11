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
javax.naming.InitialContext,
com.solar.tech.util.WCMUtils"%>
<%
Workspace wcmspace = null;// 声明一个WCM工作空间
Principal currentUser = request.getUserPrincipal();
 
	wcmspace = WCMUtils.getWCMWorkspace(currentUser);

	DocumentLibrary lib = null;// web内容库
	 
	String libName =  "extranet";
	lib = WCMUtils.getWCMLibrary(libName, currentUser);
	wcmspace.setCurrentDocumentLibrary(lib);
	QueryService queryService = wcmspace.getQueryService();
	Query folder = queryService.createQuery(Folder.class);
	folder.addSelectors(Selectors.typeIn(DocumentTypes.Folder
			.getApiType()));
	// 寻找相册的文件夹
	folder.addSelectors(Selectors.nameEquals("Album"));
	folder.addSelector(Selectors.libraryEquals(wcmspace
			.getCurrentDocumentLibrary()));
	ResultIterator parentIt = queryService.execute(folder);
 
	if(parentIt.hasNext()){
		Folder parentFolder = (Folder) parentIt.next();
		Query queryChild = queryService.createQuery();
		// 设定查询类型 SiteArea
		queryChild.addSelectors(Selectors.typeIn(DocumentTypes.Folder
				.getApiType())); 
		queryChild.setSorts(Sorts.byDateCreated(SortDirection.DESCENDING));
		queryChild.addParentId(parentFolder.getId(), QueryDepth.CHILDREN);
		ResultIterator it = queryService.execute(queryChild);
		while(it.hasNext()){
			Folder childFolder = (Folder) it.next();
			String albumName = childFolder.getTitle();
			
			out.println(albumName + "<br>");
			 
		}
	 
	} 
%>
