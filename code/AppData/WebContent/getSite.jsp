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
			//创建查询服务
			QueryService queryservice = sp.getQueryService();
			// 创建查询器
			Query query = queryservice.createQuery();
			// 设置查询类型为 站点域 ，即sitearea
			query.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea
					.getApiType()));
			// 查找站点域
			query.addSelector(Selectors.nameEquals("extranet"));
			DocumentIdIterator caIdIterator = 
					sp.findByName(DocumentTypes.Category, "testCa");
			Identity iddc = null;
			while(caIdIterator.hasNext()){ 
				iddc = caIdIterator.next();
				out.println("hre find id : "  + iddc.getID());
			}
			query.addSelector(ProfileSelectors.categoriesContains(iddc));
			// query.addSelector(Selectors.nameEquals(current));
			// 排序10039/AppData/SolarNewsPortlet/getChileSite.action?current=内网门户
			// query.addSort(Sorts.byPublishDate(SortDirection.DESCENDING));
			// 执行查询，
			/*List<String> ll = new ArrayList<String>();
			ll.add(currentUser.getName());
			query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
			out.print("user is : " + currentUser.getName() + "<br>");
		    query.addSelector(Selectors.authorsContain(ll));*/
			ResultIterator iterator = queryservice.execute(query);
			// ResultIterator iterator = sp.findByName(
			// DocumentTypes.SiteArea, name);
			// 处理查询结果
			if (iterator.hasNext()) {
				// 声明相关应用类
				WCMApiObject apiobj = (WCMApiObject) iterator.next();
				if (apiobj instanceof SiteArea) {
					// 强转 成 SiteArea
					SiteArea areaobj = (SiteArea) apiobj; 
					// SiteAreaContent siteAreaContent = new SiteAreaContent();
					 out.println("areaobj.getName() : "
							+ areaobj.getName());
					 out.println(areaobj.getTitle() +  " <br>");
					 String[] authorList = areaobj.getAuthors();
					 for(int i = 0; i <authorList.length;i++){
						 out.println("author " + i + " is  :" + authorList[i] + ",");
					 }
					 out.println(" <br>");
				}
			}
%>
