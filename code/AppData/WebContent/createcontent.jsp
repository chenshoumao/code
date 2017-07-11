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
	<table>
		<%
		String libName = "extranet";// 已选中的库名
		String areaId = "Work_Instructions";// 站点区域id
		String authTemplateName = "banshizhiyin_jiaotongyunshuju";// 模板名称
		String workflowId = "Four State Work";// 工作流程
		AuthoringTemplate authTemplate = null;// 编写模板
		
		DocumentLibrary lib = null;// web内容库
		Principal currentUser = request.getUserPrincipal();
		lib = WCMUtils.getWCMLibrary(libName, currentUser);
		
		// 拿编写模板的id
		authTemplate = WCMUtils.getAuthTemplate(lib, authTemplateName,
				currentUser);
		Workspace wcmspace = null;// 声明一个WCM工作空间
		wcmspace = WCMUtils.getWCMWorkspace(currentUser);
		DocumentIdIterator iterator = wcmspace.findByName(
				DocumentTypes.SiteArea, areaId);
		System. out.println(iterator.getCount());
		
		Content content = wcmspace.createContent(authTemplate.getId(),
				iterator.next(), null, ChildPosition.END);
		
		content.setWorkflowId(wcmspace.findByName(DocumentTypes.Workflow,
				workflowId).next());
		System. out.println(1);
		// 设置内容名称
		content.setName("000title");
		// 设置内容标题
		content.setTitle("000title");
		// 设置内容描述
		content.setDescription("000title");
		
		// 设置关键字
		content.setKeywords(new String[] { currentUser.toString() });
		System. out.println(2);
		OptionSelectionComponent optionElement = (OptionSelectionComponent) content.getComponent("test");
		System. out.println(3);
		// DocumentIdIterator iter = wcmspace.findAllByPath("extranet/testleibie", DocumentTypes.Category, 
		//		 Workspace.WORKFLOWSTATUS_ALL,new DocumentLibrary[]{lib});
		 System. out.println(4);
		 
		 DocumentIdIterator caIdIterator = 
				 wcmspace.findByName(DocumentTypes.Category, "testCa");
		 DocumentId iddc = null;
		//	Identity iddc = null;
			out.println(" count is  : "  + caIdIterator.getCount() + "<br>");
			while(caIdIterator.hasNext()){ 
				iddc = caIdIterator.next();
				out.println("hre find id : "  + iddc.getID()+ "<br>");
			}
		 
		 
		 DocumentId[] categories = new DocumentId[] {iddc};
		 optionElement.setOptionType(OptionType.USE_TAXONOMY);
         optionElement.setCategorySelections(categories);
         content.setComponent("test", optionElement);
		
		 wcmspace.save(content);
		%>
	</table>
</div>

