<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="java.security.Principal,
com.ibm.workplace.wcm.api.Workspace,
com.solar.tech.util.WCMUtils,
com.ibm.workplace.wcm.api.DocumentLibrary,
com.ibm.workplace.wcm.api.query.Query,
com.ibm.workplace.wcm.api.query.Selectors,
com.ibm.workplace.wcm.api.DocumentTypes,
com.ibm.workplace.wcm.api.query.ResultIterator,
com.ibm.workplace.wcm.api.WCMApiObject,
com.ibm.workplace.wcm.api.SiteArea,
com.ibm.workplace.wcm.api.query.ProfileSelectors,
com.ibm.workplace.wcm.api.DocumentId,com.ibm.workplace.wcm.api.exceptions.QueryServiceException,
com.ibm.workplace.wcm.api.query.Disjunction,
com.ibm.workplace.wcm.api.query.Conjunction,
com.ibm.workplace.wcm.api.query.QueryService,com.ibm.workplace.wcm.api.Content"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%
	Principal currentUser = request.getUserPrincipal();
	Workspace sp = WCMUtils.getWCMWorkspace(currentUser);
	QueryService queryService = sp.getQueryService();
    Query que = queryService.createQuery();
    que.addSelector(Selectors.typeIn(DocumentTypes.SiteArea
			.getApiType())); 
    que.addSelector(Selectors.nameLike("news%")); 
    
    try
    {
       ResultIterator resultIterator = queryService.execute(que);
       while (resultIterator.hasNext())
       {
          Content content = (Content) resultIterator.next();
          System.out.println(content.getTitle());
          // Process Content result
       }
    }
    catch (QueryServiceException e)
    {
       // Handle exception
    }

%>
</body>
</html>