<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page  import="java.util.*,com.ibm.portal.um.PumaHome,com.ibm.portal.um.PumaProfile,com.ibm.portal.um.User,
java.security.Principal,
com.ibm.workplace.wcm.api.Workspace,
com.ibm.workplace.wcm.api.WCM_API,
com.ibm.workplace.wcm.api.DocumentLibrary,
com.ibm.workplace.wcm.api.query.*,
com.ibm.workplace.wcm.api.Content,
com.ibm.workplace.wcm.api.*,
com.ibm.workplace.wcm.api.query.WorkflowSelectors.Status,
com.ibm.workplace.wcm.api.security.Access,
com.ibm.workplace.wcm.api.query.QueryService.FilterOperation
"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
 	try {
			Principal currentUser = request.getUserPrincipal();
			Workspace workspace = WCM_API.getRepository().getWorkspace(
					currentUser);
			//System.out.println("");
			DocumentLibrary library = workspace.getDocumentLibrary("csm");
			workspace.setCurrentDocumentLibrary(library);
			System.out.println("Current_Content_Library:==="
					+ library.getName());
			QueryService queryservice = workspace.getQueryService();
			Query query = queryservice.createQuery(Content.class);
			query.addSelectors(Selectors.libraryEquals(library));
			// Following selector is faked .waitingMyApproval()
			List<DocumentId> ll = new ArrayList<DocumentId>();
			System.out.println("go go go !!!!");
//			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
//					"newsContentOneAction").next());
			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					"Draft Stage").next());
			System.out.println(111);
			query.addSelectors(WorkflowSelectors.stageIn(ll),
					WorkflowSelectors.statusEquals(Status.DRAFT));
			System.out.println(222);
			// workspace.useDistinguishedNames(true);
			AccessFilter filter = queryservice.createAccessFilter(
					Access.REVIEWER, FilterOperation.ALL_USERS,
					currentUser.getName());
			System.out.println(333);
			query.setAccessFilter(filter);
			System.out.println(444);
			System.out.println("AccessFilter--------Come on-----Go------");
			ResultIterator iterator = queryservice.execute(query);
			while (iterator.hasNext()) {
				WCMApiObject obj = (WCMApiObject) iterator.next();
				Content content = (Content) obj;
				System.out.println("content.getName():-----"
						+ content.getName() + "," + content.getId());
				//listcontent.add(content);
				System.out.println("添加数据成功！！！");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
		}

 %>

</body>
</html>