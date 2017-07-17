package com.solar.tech.dao;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

import javax.ejb.Local;
import javax.imageio.ImageIO;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.portlet.PortletFileUpload;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.openjpa.jdbc.kernel.exps.ToUpperCase;
import org.apache.wink.common.categories.Categories;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import antlr.collections.impl.LList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.db2.jcc.a.f;
import com.ibm.db2.jcc.am.id;
import com.ibm.db2.jcc.am.wp;
import com.ibm.dtfj.corereaders.NewElfDump;
import com.ibm.oti.reflect.Method;
import com.ibm.portal.ListModel;
import com.ibm.portal.PagedIterator;
import com.ibm.portal.resolver.helper.streams.FileDataSource;
import com.ibm.websphere.security.auth.WSSubject;
import com.ibm.websphere.security.cred.WSCredential;
import com.ibm.websphere.sib.SIApiConstants;
import com.ibm.workplace.wcm.api.AuthoringTemplate;
import com.ibm.workplace.wcm.api.ChildPosition;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.ContentComponent;
import com.ibm.workplace.wcm.api.ContentLink;
import com.ibm.workplace.wcm.api.ContentPrototype;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentType;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.FileComponent;
import com.ibm.workplace.wcm.api.Folder;
import com.ibm.workplace.wcm.api.Identity;
import com.ibm.workplace.wcm.api.ImageComponent;
import com.ibm.workplace.wcm.api.Item;
import com.ibm.workplace.wcm.api.LibraryImageComponent;
import com.ibm.workplace.wcm.api.OptionSelectionComponent;
import com.ibm.workplace.wcm.api.OptionType;
import com.ibm.workplace.wcm.api.RichTextComponent;
import com.ibm.workplace.wcm.api.SaveOption;
import com.ibm.workplace.wcm.api.SaveOptions;
import com.ibm.workplace.wcm.api.ShortTextComponent;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.TargetableContentComponent;
import com.ibm.workplace.wcm.api.TextComponent;
import com.ibm.workplace.wcm.api.WCMApiObject;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.AuthorizationException;
import com.ibm.workplace.wcm.api.exceptions.ComponentNotFoundException;
import com.ibm.workplace.wcm.api.exceptions.DocumentDeleteException;
import com.ibm.workplace.wcm.api.exceptions.DocumentRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.IllegalDocumentTypeException;
import com.ibm.workplace.wcm.api.exceptions.OperationFailedException;
import com.ibm.workplace.wcm.api.exceptions.PropertyRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.QueryServiceException;
import com.ibm.workplace.wcm.api.exceptions.ServiceNotAvailableException;
import com.ibm.workplace.wcm.api.query.AccessFilter;
import com.ibm.workplace.wcm.api.query.Conjunction;
import com.ibm.workplace.wcm.api.query.Disjunction;
import com.ibm.workplace.wcm.api.query.HistorySelectors;
import com.ibm.workplace.wcm.api.query.PageIterator;
import com.ibm.workplace.wcm.api.query.ProfileSelectors;
import com.ibm.workplace.wcm.api.query.Query;
import com.ibm.workplace.wcm.api.query.QueryDepth;
import com.ibm.workplace.wcm.api.query.QueryService;
import com.ibm.workplace.wcm.api.query.QueryService.FilterOperation;
import com.ibm.workplace.wcm.api.query.QueryStructureException;
import com.ibm.workplace.wcm.api.query.ResultIterator;
import com.ibm.workplace.wcm.api.query.Selector;
import com.ibm.workplace.wcm.api.query.Selectors;
import com.ibm.workplace.wcm.api.query.Sort;
import com.ibm.workplace.wcm.api.query.SortDirection;
import com.ibm.workplace.wcm.api.query.Sorts;
import com.ibm.workplace.wcm.api.query.WorkflowSelectors;
import com.solar.tech.bean.AreaEntity;
import com.solar.tech.bean.AuthArea;
import com.solar.tech.bean.PageInfo;
import com.solar.tech.bean.SimpleArea;
import com.solar.tech.bean.SiteAreaContent;
import com.solar.tech.bean.SolarNewsPortletSessionBean;
import com.solar.tech.bean.WebLibrary;
import com.solar.tech.bean.WorkFlowConfig;
import com.solar.tech.util.ContentUtil;
import com.solar.tech.util.ReadConfigXML;
import com.solar.tech.util.WCMUtils;
import com.tivoli.pd.jasn1.map_acl_to_pobj_t;
import com.ibm.workplace.wcm.api.security.Access;
import com.ibm.workplace.wcm.api.query.WorkflowSelectors;
import com.ibm.workplace.wcm.api.query.WorkflowSelectors.Status;
import com.ibm.ws.batch.BatchJobExecutionEnvironmentConfig.beans.name;
import com.ibm.ws.batch.BatchJobExecutionEnvironmentConfig.beans.tempDirectory;
import com.ibm.ws.batch.xJCL.beans.returnCodeExpression;
import com.ibm.ws.http.logging.AccessLog.Format;
import com.ibm.ws.webservices.xml.wassysapp.systemApp;
import com.ibm.ws.wssecurity.wssobject.interfaces.WSSObject;

@Repository
public class SolarNewsPortletDao {
	public static final String Content_TITLE = "title";// 标题
	public static final String Content_SUMMARY = "summary";// 简介
	public static final String Content_SOURCE = "source";// 来源
	public static final String Content_WORKFLOW = "workflow";// 内容审核流程
	public static final String Content_CONTENT = "wcmcontent";// 内容正文

	private String Content = "content";// 与内容演示模板进行对应的字段
	public static final String Content_FILE = "uploadfile";

	public static final String UploadfileDirct = "UploadFile";

	private String is_Create = "false";// 是否已经创建了内容

	private String ConfigFilePath = "";// 配置文件路径
	private List<WebLibrary> listlibrary = null;// 配置内容库的集合
	private List<SimpleArea> listArea = null;//
	List<WorkFlowConfig> listFlowConfig = null;// 工作流程集合
	private String treeStr = "";// 树形结构的集合
	private List<Content> listcontent = null;// 我的待办的集合。

	private List<SiteArea> selectListArea = null;// 需要选中的站点

	private String isApprovalPage = "approvalPage";

	// 配置文件的属性
	private String fileConfigName = "SolarNewsConfig.xml";

	public List<WorkFlowConfig> getWorkFlow(HttpServletRequest request,
			String selectName) {
		// TODO Auto-generated method stub
		List<WorkFlowConfig> listFlowConfig = new ArrayList<WorkFlowConfig>();
		ConfigFilePath = request.getServletContext().getRealPath("/WEB-INF/")
				+ fileConfigName;

		try {
			// 取出所有的内容库配置信息
			// listlibrary=ReadConfigXML.getAllConfig(ConfigFilePath);
			listFlowConfig = ReadConfigXML.getAllWorkflowsConfig(
					ConfigFilePath, selectName);
			System.out.println("加载工作流程配置完成！！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listFlowConfig;
	}

	public Map<String, Object> getApprovar(HttpServletRequest request,
			HttpSession session, int firstPage) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> contentMap = new HashMap<String, Object>();

		Map<String, Object> photoMap = new HashMap<String, Object>();

		//获取待审核的图片的所属相册
		photoMap = this.getPhotoDraftForApprover(request, session, firstPage);
		int photoPageSum = (int) ((PageInfo) photoMap.get("page")).getPageSum();
		// int photoSum = (int)
		// ((PageInfo)photoMap.get("page")).getSumOfResult();
		List<Map<String, Object>> list = (List<Map<String, Object>>) photoMap
				.get("list");
		int photoNum = list.size();
		System.out.println(photoNum + "," + photoPageSum);
		PageInfo pageInfo = (PageInfo) photoMap.get("page");
		System.out.println("photo sum " + pageInfo.getSumOfResult());
		if (firstPage > photoPageSum) {
			contentMap = this.getApprovarConfigInfo(request, session, firstPage
					- photoPageSum);

			if (photoNum != pageInfo.getPerPage() && photoNum != 0) {
				int difference = pageInfo.getPerPage() - photoNum;

				Map<String, Object> map2 = this.getApprovarConfigInfo(request,
						session, firstPage - photoPageSum + 1);
				List<Map<String, Object>> list1 = (List<Map<String, Object>>) contentMap
						.get("list");
				List<Map<String, Object>> list2 = (List<Map<String, Object>>) map2
						.get("list");
				System.out.println(list1.size() + "," + list2.size());
				for (int i = 0, j = 0; list1.size() > 0 && j < difference; j++) {
					list1.remove(i);
					if (list2.size() > j)
						list1.add(list2.get(j));
				}
				contentMap.put("list", list1);
			}
			System.out.println("content sum " + (int) ((PageInfo) contentMap.get("page"))
					.getSumOfResult());
			pageInfo.setSumOfResult((int) pageInfo.getSumOfResult()
					+ (int) ((PageInfo) contentMap.get("page"))
							.getSumOfResult());

			contentMap.put("page", pageInfo);
			return contentMap;
		} else if (firstPage == photoPageSum) {
			if (photoNum == pageInfo.getPerPage()) {
				contentMap = this.getApprovarConfigInfo(request, session, 1);
				int contentSum = (int) ((PageInfo) contentMap.get("page"))
						.getSumOfResult();
				pageInfo.setSumOfResult(contentSum + pageInfo.getSumOfResult());
				photoMap.put("page", pageInfo);
				return photoMap;
			} else {
				contentMap = this.getApprovarConfigInfo(request, session, 1);
				int difference = pageInfo.getPerPage() - photoNum;
				List<Map<String, Object>> list1 = (List<Map<String, Object>>) contentMap
						.get("list");

				for (int i = 0; i < difference && i < list1.size(); i++) {
					list.add(list1.get(i));
				}

				// pageInfo = (PageInfo)photoMap.get("page");
				int contentSum = (int) ((PageInfo) contentMap.get("page"))
						.getSumOfResult();
				pageInfo.setSumOfResult(contentSum + pageInfo.getSumOfResult());
				photoMap.put("page", pageInfo);
				photoMap.put("list", list);
			}
			return photoMap;
		} else {
			contentMap = this.getApprovarConfigInfo(request, session, 1);
			int contentSum = (int) ((PageInfo) contentMap.get("page"))
					.getSumOfResult();
			pageInfo.setSumOfResult(contentSum + pageInfo.getSumOfResult());
			photoMap.put("page", pageInfo);
		}
		// map.put("selectListArea", selectListArea);

		return photoMap;
	}

	/****
	 * 读取配置审核页面文件的方法
	 * 
	 * @author chenshoumao
	 * @date 2016年7月15日
	 */
	private Map<String, Object> getApprovarConfigInfo(
			HttpServletRequest request, HttpSession session, int firstPage) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Principal currentUser = request.getUserPrincipal();

		Workspace workspace;
		try {
			workspace = WCM_API.getRepository().getWorkspace(currentUser);

			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");

			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary library = workspace
					.getDocumentLibrary(libName);
			workspace.setCurrentDocumentLibrary(library);
			QueryService queryservice = workspace.getQueryService();
			Query query = queryservice.createQuery(Content.class);
			query.addSelectors(Selectors.libraryEquals(library));//
			// query.addSelector(Selectors.typeEquals(DocumentTypes.Content));

			List<DocumentId> ll = new ArrayList<DocumentId>();
			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					"Review Stage").next());
			query.addSelectors(WorkflowSelectors.stageIn(ll));
			 

			AccessFilter filter = queryservice.createAccessFilter(
					Access.REVIEWER, FilterOperation.ANY_USER,
					currentUser.getName());
			query.setAccessFilter(filter);
			query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
			//long sum = queryservice.count(query);
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurrentPage(firstPage);
			//pageInfo.setSumOfResult(sum);

			resultMap.put("page", pageInfo);

			PageIterator iterator;
			iterator = queryservice.execute(query, pageInfo.getPerPage(),
					firstPage);
			if (iterator.hasNext()) {

				ResultIterator it = iterator.next();
				pageInfo.setSumOfResult(it.getSize());
				while (it.hasNext()) {
					WCMApiObject obj = (WCMApiObject) it.next();
					Content content = (Content) obj;
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("title", content.getTitle());
					map.put("name", content.getName());
					map.put("type", "content");
					map.put("fromPeople", content.getAuthors()[0]);
					try {
						map.put("time", transferToDate(content.getDateEnteredStage()));
					} catch (PropertyRetrievalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					map.put("read",
							"/wps/myportal/wcmAuthoring?wcmAuthoringAction=read&docid=com.ibm.workplace.wcm.api.WCM_Conten/"
									+ content.getId().getId());
					map.put("approve",
							"/wps/myportal/wcmAuthoring?wcmAuthoringAction=approve&docid=com.ibm.workplace.wcm.api.WCM_Conten/"
									+ content.getId().getId());
					map.put("decline",
							"/wps/myportal/wcmAuthoring?wcmAuthoringAction=decline&docid=com.ibm.workplace.wcm.api.WCM_Conten/"
									+ content.getId().getId());
					list.add(map);
				}

			}
			resultMap.put("list", list);

		} catch (ServiceNotAvailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OperationFailedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (QueryServiceException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryStructureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;

	}

	/***
	 * 根据当前用户和web内容库的名称查询所有的待办
	 * 
	 * @author simon
	 * @date 2016年7月29日
	 * @param request
	 *            用户请求
	 * @param weblibrary
	 *            web内容库
	 */
	@SuppressWarnings("deprecation")
	public List getMySelfApproval(HttpServletRequest request, String weblibrary) {
		List list = new ArrayList<Content>();
		System.out.println("=====================getMySelfApproval:");
		listcontent = new ArrayList<>();
		try {
			Principal currentUser = request.getUserPrincipal();
			Workspace workspace = WCM_API.getRepository().getWorkspace(
					currentUser);
			System.out.println(weblibrary);
			DocumentLibrary library = workspace.getDocumentLibrary(weblibrary);
			workspace.setCurrentDocumentLibrary(library);

			System.out.println("Current_Content_Library:==="
					+ library.getName());
			QueryService queryservice = workspace.getQueryService();
			Query query = queryservice.createQuery(Content.class);
			query.addSelectors(Selectors.libraryEquals(library));
			// Following selector is faked .waitingMyApproval()
			List<DocumentId> ll = new ArrayList<DocumentId>();
			System.out.println("go go go !!!!newsContentOneAction");
			// ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
			// "newsContentOneAction").next());
			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					"newsContentOneAction").next());

			query.addSelectors(WorkflowSelectors.stageIn(ll),
					WorkflowSelectors.statusEquals(Status.DRAFT));

			// query.addSelector(arg0);
			// workspace.useDistinguishedNames(true);
			AccessFilter filter = queryservice.createAccessFilter(
					Access.MANAGER, FilterOperation.ALL_USERS,
					currentUser.getName());
			query.setAccessFilter(filter);
			System.out.println("AccessFilter--------Come on-----Go------");
			ResultIterator iterator = queryservice.execute(query);
			while (iterator.hasNext()) {
				WCMApiObject obj = (WCMApiObject) iterator.next();
				Content content = (Content) obj;
				System.out
						.println("content.getName():-----" + content.getName()
								+ "," + content.getId()
								+ content.getAuthors().length
								+ content.getAuthors()[0]);
				String id = content.getId().toString();
				list.add(content.getTitle());
				list.add(id);
				System.out.println("添加数据成功！！！");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}
		return list;

	}

	/***
	 * 拿到所有的栏目信息的方法
	 * 
	 * @author simon
	 * @date 2016年7月25日
	 */
	private List<SiteAreaContent> loadAllArea(List<WebLibrary> listweb,
			HttpServletRequest request) throws Exception {
		List<SiteAreaContent> list = new ArrayList<SiteAreaContent>();
		System.out.println("拿到所有的栏目信息的方法 start");
		Principal currentUser = request.getUserPrincipal();
		selectListArea = new ArrayList<>();
		//
		List<AreaEntity> listAreaEntity = new ArrayList<AreaEntity>();
		Map<String, String> mapstr = ReadConfigXML
				.getFilterSiteConfig(ConfigFilePath);
		// treeStr="[";
		// SolarNewsPortletSessionBean sessionBean =
		// (SolarNewsPortletSessionBean)
		// session.getAttribute("SolarNewsPortletSessionBean");
		if (null != listweb && !listweb.isEmpty()) {
			System.out.println("listweb.size() : " + listweb.size());
			for (int i = 0; i < listweb.size(); i++) {
				System.out.println(i);
				// 1.拿WCM的工作空间
				Workspace sp = WCMUtils.getWCMWorkspace(currentUser);
				String libName = listweb.get(i).getUnqiueName();// 判断是外网还是内网
				// 2.拿到当前内容库中的
				DocumentLibrary weblib = WCMUtils.getWCMLibrary(libName,
						currentUser);
				// 3.设置当前工作空间为当前的内容库
				sp.setCurrentDocumentLibrary(weblib);
				// 4.创建查询服务
				QueryService queryservice = sp.getQueryService();
				// 5.创建查询器
				Query query = queryservice.createQuery();
				// 6.查询当前内容库的所有的栏目
				query.addSelectors(Selectors.libraryEquals(weblib));
				query.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea
						.getApiType()));
				// 只搜索配置库中的栏目
				// query.addSelectors(Selectors.libraryEquals(weblib));
				query.addSelector(Selectors.nameLike("innerWe%"));
				// 排序
				// query.addSort(Sorts.byPublishDate(SortDirection.DESCENDING));
				// 7.执行查询，
				ResultIterator iterator = queryservice.execute(query);

				try {
					int count = 0;
					// 8.处理查询结果
					while (iterator.hasNext()) {
						System.out.println("count is : " + count++);
						WCMApiObject apiobj = (WCMApiObject) iterator.next();
						SiteArea areaobj = (SiteArea) apiobj;
						SiteAreaContent siteAreaContent = new SiteAreaContent();
						System.out.println("areaobj.getName() : "
								+ areaobj.getName());
						System.out.println(areaobj.getTitle());
						Query queryChild = queryservice.createQuery();
						queryChild.addSelectors(Selectors
								.typeIn(DocumentTypes.SiteArea.getApiType()));
						queryChild.addParentId(areaobj.getId(),
								QueryDepth.CHILDREN);
						ResultIterator it = queryservice.execute(queryChild);
						while (it.hasNext()) {
							WCMApiObject aobj = (WCMApiObject) it.next();
							SiteArea area = (SiteArea) aobj;
							System.out.println(area.getTitle());
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
			}

		}
		System.out.println("拿到所有的栏目信息的方法 end!");
		return list;
		// 8.保存当前使用的存储库
		// sessionBean.setCurrentLibrary(weblib);
	}

	public String getParentSite(HttpServletRequest request) throws Exception {

		// Content contentWithMyElement;
		// QueryService queryService = workspace.getQueryService();
		// Query query = queryService.createQuery(Content.class);
		// query.addParentId(parentSiteArea.getId(), QueryDepth.CHILDREN);
		// try
		// {
		// ResultIterator resultIterator = queryService.execute(query);
		// if (resultIterator.hasNext())
		// {
		// Content childContent = (Content) resultIterator.next();
		// while (childContent.hasComponent("myElement"))
		// {
		// contentWithMyElement = childContent;
		// break;
		// }
		// }
		// }
		// catch (QueryServiceException e)
		// {
		// // Handle exception
		// }

		return null;
	}

	/***
	 * 获取该站点域的完整路径
	 * 
	 * @param areaobj
	 * @return
	 */
	private String getSiteAreaPath(SiteArea areaobj, Principal currentUser)
			throws Exception {
		String name = "";
		if (areaobj.getParentId() != null) {
			System.out.println("areaobj.getParentId() :"
					+ areaobj.getParentId());
			DocumentId parentobj = areaobj.getParentId();
			Workspace sp = WCMUtils.getWCMWorkspace(currentUser);
			sp.setCurrentDocumentLibrary(areaobj.getOwnerLibrary());
			/**
			 * fdsv 优化之前使用的方法 SiteArea
			 * parentArea=WCMUtils.getSiteAreaByName(areaobj.getOwnerLibrary(),
			 * currentUser, parentobj.getName());*
			 */
			// 优化之后使用的方法
			SiteArea parentArea = (SiteArea) sp.getById(parentobj);
			name = areaobj.getTitle();
			System.out.println("SiteArea-Name::::" + name);
			if (null != parentArea.getParentId()) {
				return getSiteAreaPath(parentArea, currentUser) + ">" + name;
			} else {
				name = parentArea.getTitle() + ">" + areaobj.getTitle();
			}
		} else {
			name += areaobj.getTitle();
		}
		System.out.println("Nnnname : " + name);
		return name;
	}

	public Map<String, Object> createContentPage(HttpSession session,
			HttpServletRequest reqeust, String upload, String create) {
		// TODO Auto-generated method stub
		SolarNewsPortletSessionBean sessionBean = (SolarNewsPortletSessionBean) session
				.getAttribute("SolarNewsPortletSessionBean");
		Map<String, Object> map = new HashMap<String, Object>();
		if (sessionBean == null) {
			map.put("result", "NO PORTLET SESSION YET");
			return map;
		}
		if (null != sessionBean.getIsApprovalPage()
				&& sessionBean.getIsApprovalPage().length() > 0) {
			isApprovalPage = sessionBean.getIsApprovalPage();
		}
		listcontent = new ArrayList<Content>();
		System.out.println("isApprovalPage:" + isApprovalPage);

		if (null != isApprovalPage
				&& isApprovalPage.equals("createContentPage")) {
			if (upload != null && "uploadPage".equals(upload)) {
				// 上传文件页面和添加内容
				sessionBean.setUpload(true);
				sessionBean.setImagePath("");
				// 加载内容到审核页面
			} else if (upload == null && !sessionBean.getImagePath().equals("")) {
				sessionBean.setUpload(true);
			} else {
				sessionBean.setUpload(false);
			}
			// 已经是保存了数据了的
			if (sessionBean.isSaveed()) {
				sessionBean.setUpload(false);
				sessionBean.setSaveed(false);
				sessionBean.setImagePath("");
			}
			if (!sessionBean.isUpload()) {
				String SelectName = sessionBean.getSelectLibraryName();

				System.out.println("");
				// 读取配置文件的方法reqeust
				getCreateConfigFileInfo(reqeust, SelectName);
				// 保存栏目
				// request.setAttribute("listArea", listArea);
				map.put("create", "true");
				// request.setAttribute("treeStr", treeStr);
				map.put("listFlowConfig", listFlowConfig);
				// 保存所有的配置文件信息
				sessionBean.setListarea(listlibrary);
				map.put("listcontent", listcontent);
			}
			session.setAttribute("SolarNewsPortletSessionBean", sessionBean);
			map.put("SolarNewsPortletSessionBean", sessionBean);
		}

		return map;
	}

	/****
	 * 读取配置文件的方法
	 * 
	 * @author simon
	 * @date 2016年7月15日
	 */
	private void getCreateConfigFileInfo(HttpServletRequest request,
			String SelectName) {
		// listArea=new ArrayList<SimpleArea>();
		listFlowConfig = new ArrayList<WorkFlowConfig>();
		// 如果内容没有的时候就加载
		// if(null==listlibrary||listlibrary.isEmpty()){
		// 解析配置文件
		ConfigFilePath = request.getServletContext().getRealPath("/WEB-INF/")
				+ fileConfigName;
		try {
			// 取出所有的内容库配置信息
			// listlibrary=ReadConfigXML.getAllConfig(ConfigFilePath);
			listFlowConfig = ReadConfigXML.getAllWorkflowsConfig(
					ConfigFilePath, SelectName);
			System.out.println("加载工作流程配置完成！！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
		// Principal currentUser=request.getUserPrincipal();
		// 拿到所有的模板
		/*
		 * for (int i = 0; i < listlibrary.size(); i++) { WebLibrary
		 * libobj=listlibrary.get(i); try { List<SiteArea> listarea=
		 * WCMUtils.getAreaByLibrary
		 * (WCMUtils.getWCMLibrary(libobj.getUnqiueName(
		 * ),currentUser),currentUser);
		 * listArea.addAll(WCMUtils.getConfigAreaList(listarea,libobj)); } catch
		 * (Exception e) { // TODO 自动生成 catch 块 e.printStackTrace(); } }
		 */
		// ////////////////////////////////////

		/*
		 * try { //加载所需的所有的内容库 loadAllArea(listlibrary,request) ; //查询所有待办的集合
		 * for (int i = 0; i < listlibrary.size(); i++) { WebLibrary library =
		 * listlibrary.get(i); //library.getUnqiueName()
		 * //getAllMyApproval(request, library.getUnqiueName());
		 * getMySelfApproval(request,library.getUnqiueName()); } } catch
		 * (Exception e) { // TODO 自动生成 catch 块 e.printStackTrace(); }
		 */

	}

	public Map<String, Object> commit(HttpSession session,
			HttpServletRequest request, String upload, String isApprovalPage,
			String selectedSite, String create, String selectedlib,
			String title, String summary, String source, String workflow,
			String wcmcontent) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		SolarNewsPortletSessionBean sessionBean = (SolarNewsPortletSessionBean) session
				.getAttribute("SolarNewsPortletSessionBean");
		Principal currentUser = request.getUserPrincipal();
		if (null != isApprovalPage && isApprovalPage.length() > 0) {
			this.isApprovalPage = isApprovalPage;
			sessionBean.setIsApprovalPage(this.isApprovalPage);
		}
		if (null != selectedSite && selectedSite.length() > 0) {

			String[] strs = selectedSite.split("&solar&");
			sessionBean.setSelectedSiteId(selectedSite);
			String libName = strs[0];
			sessionBean.setSelectLibraryName(libName);
			SiteArea tempobj = null;
			try {

				System.out
						.println("*************get SiteArea Name start****************");
				tempobj = WCMUtils.getSiteAreaBySelectedName(currentUser,
						selectedSite);
				System.out.println("get SiteArea Name-------"
						+ tempobj.getName());
				sessionBean.setSelectedSitePath(getSiteAreaPath(tempobj,
						currentUser));

				System.out
						.println("*************get SiteArea Name end****************");
				System.out
						.println("***********************tempobj----*****************************");
				System.out
						.println("*************selectedSite:****************::"
								+ selectedSite);

			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}
		if (null != sessionBean) {
			if (null != isApprovalPage
					&& "createContentPage".equals(isApprovalPage)) {
				Workspace wcmspace = null;// 声明一个WCM工作空间
				String reulst[] = null;// 内容处理数组
				boolean isUpload = upload != null
						&& "uploadPage".equals(upload.toString()) ? true
						: false;
				System.out.println("isUpload================" + isUpload);
				// String
				// approvalPageId=request.getParameter("approval_Page");//审核内容页面

				if (!isUpload) {
					if (!isUpload) {
						// 如果是创建内容
						if (create != null && "true".equals(create.toString())) {
							// 1.拿到选择了的内容库
							System.out
									.println("create***********************create来了！");
							String[] libauth = selectedlib.split("&solar&");
							sessionBean.setSelectLibraryName(libauth[0]);// 取得所选中的web内容库
							sessionBean.setSelectAreaId(libauth[1]);// 取得所选中的栏目id
							System.out.println("数据进来了！！！");
							try {
								wcmspace = WCMUtils
										.getWCMWorkspace(currentUser);
								// 1.拿到表单数据
								List<String> listcomponect = sessionBean
										.getListComponent();
								// 2.取出内容库和模板、工作流程和站点区域（栏目）
								String libName = sessionBean
										.getSelectLibraryName();// 已选中的库名
								String authTemplateName = "";// 模板名称
								String workflowId = "";// 工作流程
								String areaId = sessionBean.getSelectAreaId();// 站点区域id
								DocumentLibrary lib = null;// web内容库
								AuthoringTemplate authTemplate = null;// 编写模板
								workflowId = workflow;// 取得工作流程id
								// 拿到所有配置文件中的配置信息
								List<WebLibrary> weblibrary = sessionBean
										.getListarea();
								for (int i = 0; i < weblibrary.size(); i++) {
									WebLibrary libobj = weblibrary.get(i);
									String libobjName = libobj.getUnqiueName()
											.toLowerCase();
									if (libobjName
											.equals(libName.toLowerCase())) {
										List<AuthArea> listarea = libobj
												.getAuthAreaList();
										for (int j = 0; j < listarea.size(); j++) {
											AuthArea objarea = listarea.get(j);
											if (objarea.getAreaId().equals(
													areaId)) {
												authTemplateName = objarea
														.getAuthTemplateId();// 取得模板id
												break;
											}
										}
										if (null == authTemplateName
												|| authTemplateName.length() <= 0) {
											for (int j = 0; j < listarea.size(); j++) {
												AuthArea objarea = listarea
														.get(j);
												if (objarea.getAreaId().equals(
														"*")) {
													authTemplateName = objarea
															.getAuthTemplateId();// 取得模板id
													break;
												}
											}
										}
										break;// 结束循环
									}
								}
								System.out
										.println("取得的模板名称为：authTemplateName___:"
												+ authTemplateName);
								// 拿当前web内容库
								lib = WCMUtils.getWCMLibrary(libName,
										currentUser);
								System.out.println("内容库对象为：" + libName);
								wcmspace.setCurrentDocumentLibrary(lib);
								// 拿编写模板的id
								authTemplate = WCMUtils.getAuthTemplate(lib,
										authTemplateName, currentUser);
								System.out.println("查询出模板的对象为：authTemplate——:"
										+ authTemplate);
								// 拿到栏目的id
								DocumentIdIterator iterator = wcmspace
										.findByName(DocumentTypes.SiteArea,
												areaId);
								System.out.println("iterator对象为：" + iterator);
								// 创建内容
								Content content = wcmspace.createContent(
										authTemplate.getId(), iterator.next(),
										null, ChildPosition.END);
								System.out.println("内容对象为：" + content);
								System.out.println("workflowId对象为："
										+ workflowId);
								// 内容添加工作流程
								content.setWorkflowId(wcmspace.findByName(
										DocumentTypes.Workflow, workflowId)
										.next());
								System.out.println("配置文件中拿到的工作流程id为workflowId："
										+ workflowId);
								ContentPrototype prototype = authTemplate
										.getPrototype();
								// 拿到所有的元素名称的集合
								String[] componentNames = prototype
										.getComponentNames();
								for (int i = 0; i < componentNames.length; i++) {
									String componectName = componentNames[i];
									ContentComponent contentComponect = content
											.getComponent(componectName);
									// 简短文本的判断
									if (contentComponect instanceof ShortTextComponent) {
										String contents = "";
										if (componectName
												.equalsIgnoreCase(title)) {
											// 内容标题
											contents = request
													.getParameter(title);
										} else if (componectName
												.equalsIgnoreCase(source)) {
											// 内容来源
											contents = source;
										}
										((ShortTextComponent) contentComponect)
												.setText(contents);
										// 文本元素的判断
									} else if (contentComponect instanceof TextComponent) {
										// 内容标题
										((TextComponent) contentComponect)
												.setText(title);
										// 富文本框的判断
									} else if (contentComponect instanceof RichTextComponent) {
										String contents = "";
										if (componectName
												.equalsIgnoreCase(Content)) {
											// 内容正文
											contents = wcmcontent;
										} else {// 内容简介
											contents = summary;
										}
										((RichTextComponent) contentComponect)
												.setRichText(contents);
									}
									content.setComponent(componectName,
											contentComponect);
								}
								// 设置内容名称
								content.setName(SureUnquitName(lib, currentUser));
								// 设置内容标题
								content.setTitle(title);
								// 设置内容描述
								content.setDescription(title);
								// 保存内容
								reulst = wcmspace.save(content);
								if (null != reulst && reulst.length > 0) {
									System.out.println("处理的结果为：" + reulst[0]);
								} else {
									System.out.println("内容添加成功！！");
								}
							} catch (Exception e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
							}
							map.put("create", false);
							// request.setAttribute("create", false);
							sessionBean.setUpload(false);
							sessionBean.setSaveed(true);
						}
						// 如果是上传图上或者文件
					} else {
						// System.out.println("***********************上传的更来了！");
						// 路径
						String path = request.getServletContext()
								.getRealPath("/").toString()
								+ "/" + UploadfileDirct;
						// 判断文件夹路径存不存在，如果不存在就创建一个文件夹
						File filedict = new File(path);
						if (!filedict.isDirectory()) {
							filedict.mkdir();// 创建一个文件夹
						}

						// 上传图片功能
						DiskFileItemFactory dfif = new DiskFileItemFactory();
						dfif.setSizeThreshold(4096);
						PortletFileUpload sfu = new PortletFileUpload(dfif);
						sfu.setSizeMax(10485760L);
						List fileList = null;
						try {
							fileList = sfu.parseRequest(request);
						} catch (FileUploadException e1) {
							e1.printStackTrace();
						}
						Iterator iter = fileList.iterator();
						while (iter.hasNext()) {
							// 表单内容
							FileItem item = (FileItem) iter.next();
							if (item.isFormField()) {
								String name = item.getFieldName();

								String value = item.getString("utf-8");
								if ("upload".equals(name)) {
									sessionBean.setUpload("uploadPage"
											.endsWith(value));
								}
							} else {
								String filename = item.getName();
								String filepath = path + "/" + filename;
								File uploadedFile = new File(filepath);
								try {
									// 把文件保存到服务器本地
									item.write(uploadedFile);
									sessionBean.setImagePath(filename);
									sessionBean.setUpload(true);
								} catch (Exception e) {
									// TODO 自动生成 catch 块
									e.printStackTrace();
								}
							}

						}
					}

				} else {
					sessionBean.setImagePath("");
					sessionBean.setUpload(true);
				}
			}
		}
		session.setAttribute("SolarNewsPortletSessionBean", sessionBean);
		map.put("SolarNewsPortletSessionBean", sessionBean);
		return map;
	}

	/***
	 * 生成唯一的字符标识
	 * 
	 * @author simon
	 * @date 2016年7月15日
	 * @param library
	 * @return
	 * @throws Exception
	 */
	private String SureUnquitName(DocumentLibrary library, Principal currentUser)
			throws Exception {
		String unqitName = "";
		while (true) {
			unqitName = getContentUnquitName();
			boolean isExit = WCMUtils.getCreateNewContentName(library,
					unqitName, currentUser);
			if (!isExit) {
				break;
			}
		}
		return unqitName;
	}

	/***
	 * 生成16位随机码
	 * 
	 * @author simon
	 * @date 2016年7月15日
	 * @return
	 */
	private String getContentUnquitName() {
		String UnquitName = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		StringBuilder df = new StringBuilder();
		Random rd = new Random();
		for (int i = 0; i < 16; i++) {
			int number = rd.nextInt(UnquitName.length());
			df.append(UnquitName.charAt(number));
		}
		return df.toString();
	}

	public List<SiteAreaContent> getColumn(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<SiteAreaContent> list = new ArrayList<SiteAreaContent>();

		if (null == listlibrary || listlibrary.isEmpty()) {
			// 解析配置文件
			// ConfigFilePath 为配置文件所在的war应用在服务器中的绝对位置
			ConfigFilePath = request.getServletContext().getRealPath(
					"/WEB-INF/")
					+ fileConfigName;
			try {
				// 取出所有的内容库配置信息
				listlibrary = ReadConfigXML.getAllConfig(ConfigFilePath);
				System.out.println(1);
				System.out.println(selectListArea.size());
			} catch (Exception e) {
				System.out.println("error");
				System.out.println(e);
				e.printStackTrace();
			}
		}

		try {
			list = loadAllArea(listlibrary, request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<Map<String, Object>> getChildSite(int id, String name,
			int level, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 获取登录者的信息
		Principal currentUser = request.getUserPrincipal();
		System.out.println("user is : " + currentUser);
		try {
			Workspace sp = WCMUtils.getWCMWorkspace(currentUser);
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");

			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary lib = WCMUtils.getWCMLibrary(libName,
					currentUser);
			sp.setCurrentDocumentLibrary(lib);
			// 创建查询服务
			QueryService queryservice = sp.getQueryService();
			// 创建查询器
			Query query = queryservice.createQuery();
			// 设置查询类型为 站点域 ，即sitearea
			query.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea
					.getApiType()));
			// 查找站点域
			query.addSelector(Selectors.nameEquals(name));
			
			query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));

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
					System.out.println("areaobj.getName() : "
							+ areaobj.getName());
					System.out.println(areaobj.getTitle());
					list = getChildList(areaobj, sp, id, name, level,
							currentUser);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;
	}

	public List<Map<String, Object>> getChildList(SiteArea areaobj,
			Workspace sp, int id, String parentPath, int level,
			Principal currentUser) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		System.out.println("this level is : " + level);
		try {
			// 创建查询服务
			QueryService queryservice = sp.getQueryService();
			// 再次构建查询类，用来查询子类
			Query queryChild = queryservice.createQuery();
			// 设定查询类型 SiteArea
			queryChild.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea
					.getApiType()));

			queryChild.addParentId(areaobj.getId(), QueryDepth.CHILDREN);

			// 部门
			// String group = "guanweihuibangongshi";
			String group = getUserGroup(currentUser.getName());
			String[] groupStr = group.split(",");
			ObjectMapper mapper = new ObjectMapper();
			// List<Map<String, Object>> listMap = mapper.readValue(group,
			// ArrayList.class);
			List<Identity> listIds = new ArrayList<Identity>();
			for (int j = 0; j < groupStr.length; j++) {
				String groupName = groupStr[j];
				DocumentIdIterator caIdIterator = sp.findByName(
						DocumentTypes.Category, groupName);
				if (caIdIterator.hasNext()) {
					Identity iddc = null;
					iddc = caIdIterator.next();
					System.out.println("hre find id : " + iddc.getID());
					listIds.add(iddc);
				}
			}
			queryChild
					.addSelector(ProfileSelectors.categoriesContains(listIds));
			ResultIterator it = queryservice.execute(queryChild);

			// 遍历结果
			int count = 0;
			while (it.hasNext()) {
				WCMApiObject aobj = (WCMApiObject) it.next();
				if (aobj instanceof SiteArea) {
					SiteArea area = (SiteArea) aobj;
					// area.getCate
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("pId", id);
					map.put("id", id + 10 + count++);
					map.put("t", area.getName());
					map.put("name", area.getTitle());
					map.put("level", level + 1);
					map.put("isParent",
							level < 1 ? (area.getName().equals(
									"extra_public_Information") ? false
									: hasChileSiteArea(sp, area)) : false);
					list.add(map);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public String getGroup(String username) {
		String urlParam = "http://10.161.2.68:9080/UserMng/GroupLdap/queryGroup.action";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		StringBuffer resultBuffer = null;
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(urlParam);

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> elem = iterator.next();
			list.add(new BasicNameValuePair(elem.getKey(), String.valueOf(elem
					.getValue())));
		}
		BufferedReader br = null;
		try {
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
						"utf-8");
				httpPost.setEntity(entity);
			}
			HttpResponse response = client.execute(httpPost);

			resultBuffer = new StringBuffer();
			br = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			String temp;
			while ((temp = br.readLine()) != null) {
				resultBuffer.append(temp);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				}
			}
		}
		System.out.println(resultBuffer.toString());
		return resultBuffer.toString();
	}

	public boolean hasChileSiteArea(Workspace sp, SiteArea steArea) {
		boolean state = false;
		try {
			// 创建查询服务
			QueryService queryservice = sp.getQueryService();
			// 再次构建查询类，用来查询子类
			Query queryChild = queryservice.createQuery();
			// 设定查询类型 SiteArea
			queryChild.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea
					.getApiType()));

			queryChild.addParentId(steArea.getId(), QueryDepth.CHILDREN);
			ResultIterator it = queryservice.execute(queryChild);
			if (it.hasNext()) {
				state = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}

	public Map<String, Object> createContent(HttpServletRequest request,
			MultipartFile file, String selectLib, String area,
			SiteAreaContent siteAreaContent) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();

		Principal currentUser = request.getUserPrincipal();
		Workspace wcmspace = null;// 声明一个WCM工作空间
		String reulst[] = null;// 内容处理数组
		//
		// String selectedlib= lib;
		try {
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);
			// 2.取出内容库和模板、工作流程和站点区域（栏目）
			String libName = selectLib;// 已选中的库名
			String areaId = area;// 站点区域id

			String workflowId = "";// 工作流程
			DocumentLibrary lib = null;// web内容库
			AuthoringTemplate authTemplate = null;// 编写模板
			workflowId = siteAreaContent.getWorkflow();// 取得工作流程id

			// 拿当前web内容库
			lib = WCMUtils.getWCMLibrary(libName, currentUser);
			System.out.println("内容库对象为：" + libName);
			wcmspace.setCurrentDocumentLibrary(lib);
			// 拿编写模板的id
			QueryService queryService = wcmspace.getQueryService();
			Query folder = queryService.createQuery(Folder.class);
			folder.addSelectors(Selectors.typeIn(DocumentTypes.Folder
					.getApiType()));
			folder.addSelectors(Selectors.nameEquals(area));
			folder.addSelector(Selectors.libraryEquals(wcmspace
					.getCurrentDocumentLibrary()));
			ResultIterator it = queryService.execute(folder);
			if (it.hasNext()) {
				Folder ff = (Folder) it.next();
				ResultIterator result = ff.getChildren();
				if (result.hasNext()) {
					AuthoringTemplate template = (AuthoringTemplate) result
							.next();
					authTemplate = template;
				}
			}
			System.out.println("now the template is :"
					+ authTemplate.getTitle());

			System.out.println("查询出模板的对象为：authTemplate——:" + authTemplate);
			// 拿到栏目的id
			DocumentIdIterator iterator = wcmspace.findByName(
					DocumentTypes.SiteArea, areaId);
			System.out.println("iterator对象为：" + iterator);
			// 创建内容
			Content content = null;
			System.out.println("内容对象为：" + content);
			System.out.println("workflowId对象为：" + workflowId);
			// 内容添加工作流程
			try {
				System.out.println("area is " + area + ",areaId is :" + areaId);
				System.out.println("file is " + file);
				// System.out.println("file size " + file.getSize());
				System.out.println(iterator.hasNext());
				System.out.println("template id : " + authTemplate.getId());
				// System.out.println(iterator.next());
				content = wcmspace.createContent(authTemplate.getId(),
						iterator.next(), null, ChildPosition.END);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			content.setWorkflowId(wcmspace.findByName(DocumentTypes.Workflow,
					workflowId).next());
			System.out.println("配置文件中拿到的工作流程id为workflowId：" + workflowId);
			ContentPrototype prototype = authTemplate.getPrototype();
			// 拿到所有的元素名称的集合

			String[] componentNames = prototype.getComponentNames();
			for (int i = 0; i < componentNames.length; i++) {
				String componectName = componentNames[i];
				ContentComponent contentComponect = content
						.getComponent(componectName);
				String contents = "";

				Class cls = siteAreaContent.getClass();
				Field[] f = cls.getDeclaredFields();
				if (f != null) {
					for (int j = 0; j < f.length; j++) {
						String name = f[j].getName();
						if (componectName.equals(name)) {
							contents = getFieldValueByName(name,
									siteAreaContent).toString();
							System.out.println(componectName + "," + contents);
							break;
						}
					}
				}

				// 简短文本的判断
				if (contentComponect instanceof ShortTextComponent) {
					((ShortTextComponent) contentComponect).setText(contents);
					// 文本元素的判断
				} else if (contentComponect instanceof TextComponent) {
					// 内容标题
					((TextComponent) contentComponect).setText(contents);
					// 富文本框的判断
				} else if (contentComponect instanceof RichTextComponent) {
					((RichTextComponent) contentComponect)
							.setRichText(contents);

				} else if (contentComponect instanceof ImageComponent
						&& file.getSize() > 0) {
					System.out.println("href we come!!");
					String fileName = file.getOriginalFilename();// 文件原名称
					String path = "/root/test" + "/" + fileName;
					file.transferTo(new File(path));
					BufferedImage bufferedImage = ImageIO.read(new File(path));
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(bufferedImage, "gif", baos);
					byte[] imageByte = baos.toByteArray();
					// byte[] imageByte = file.getBytes();
					System.out.println("imageByte : " + imageByte);
					((ImageComponent) contentComponect).setBorder("0");
					((ImageComponent) contentComponect).setHeight(String
							.valueOf(bufferedImage.getHeight()));
					((ImageComponent) contentComponect).setWidth(String
							.valueOf(bufferedImage.getWidth()));
					((ImageComponent) contentComponect)
							.setAltText("THIS IS THE ALT TEXT FOR THE IMAGE FROM API");
					((ImageComponent) contentComponect).setImage(fileName,
							imageByte);
					baos.close();
					// 富文本框的判断
				}
				content.setComponent(componectName, contentComponect);
			}
			// 设置内容名称
			content.setName(SureUnquitName(lib, currentUser));
			// 设置内容标题
			content.setTitle(siteAreaContent.getTitle());
			// 设置内容描述
			content.setDescription(siteAreaContent.getTitle());
			// 设置关键字
			int stick = siteAreaContent.getStick();
			if (stick == 1)
				content.setKeywords(new String[] { "stick" });

			/*
			 * 如有需要设置类别
			 */
			// System.out.println(siteAreaContent.getZhaopin_categoryName() +
			// ",11," + siteAreaContent.getTouzi_categoryName());
			String categoryName = siteAreaContent.getZhaopin_categoryName()
					.length() > 0 ? siteAreaContent.getZhaopin_categoryName()
					: (siteAreaContent.getTouzi_categoryName().length() > 0 ? siteAreaContent
							.getTouzi_categoryName() : "");
			// System.out.println("catefotyName is : " + categoryName);
			// System.out.println("catefotyName is  empty ? : " +
			// (categoryName.length() > 0));
			if (categoryName.length() > 0) {
				DocumentIdIterator caIdIterator = wcmspace.findByName(
						DocumentTypes.Category, categoryName);

				DocumentId iddc = null;
				// Identity iddc = null;
				// System.out.println(" count is  : " + caIdIterator.getCount()
				// + "<br>");
				while (caIdIterator.hasNext()) {
					iddc = caIdIterator.next();
					// System.out.println("hre find id : " + iddc.getID() +
					// "<br>");
				}
				OptionSelectionComponent optionElement = (OptionSelectionComponent) content
						.getComponent("option");
				DocumentId[] categories = new DocumentId[] { iddc };
				optionElement.setOptionType(OptionType.USE_TAXONOMY);
				optionElement.setCategorySelections(categories);
				content.setComponent("option", optionElement);
			}

			// 保存内容
			reulst = wcmspace.save(content);
			if (null != reulst && reulst.length > 0) {
				System.out.println("处理的结果为：" + reulst[0]);
				map.put("result", reulst[0]);
			} else {
				System.out.println("内容添加成功！！");
				map.put("result", "内容添加成功！！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return map;
	}

	/*
	 * creatby 陈守貌 time 2017-05-16 更新内容项
	 */

	public Map<String, Object> updateContent(HttpServletRequest request,
			MultipartFile file, SiteAreaContent siteAreaContent) {
		// TODO Auto-generated method stub

		Map<String, Object> map = new HashMap();

		Principal currentUser = request.getUserPrincipal();

		Workspace wcmspace = null;// 声明一个WCM工作空间

		try {
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);

			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");

			String libName = resourceBundle.getString("rootLibrary");

			DocumentLibrary lib = WCMUtils.getWCMLibrary(libName, currentUser);

			wcmspace.setCurrentDocumentLibrary(lib);

			String contentName = siteAreaContent.getName();

			DocumentIdIterator caIdIterator = wcmspace.findByName(
					DocumentTypes.Content, contentName);

			while (caIdIterator.hasNext()) {

				DocumentId contentId = null;
				contentId = (DocumentId) caIdIterator.next();
				Content content = (Content) wcmspace.getById(contentId);

				String[] str = content.getComponentNames();
				for (int i = 0; i < str.length; i++) {
					ContentComponent contentComponect = content
							.getComponent(str[i]);
					if (contentComponect instanceof ShortTextComponent) {

						if (str[i].equals("title")
								&& siteAreaContent.getTitle() != null) {// 标题
							((ShortTextComponent) contentComponect)
									.setText(siteAreaContent.getTitle());
						} else if (str[i].equals("title2")
								&& siteAreaContent.getTitle2() != null) {// 二级标题
							((ShortTextComponent) contentComponect)
									.setText(siteAreaContent.getTitle2());
						}
						// else if(str[i].equals("source")){//来源
						// ((ShortTextComponent)
						// contentComponect).setText(siteAreaContent.getSource());
						// }
						else if (str[i].equals("number")
								&& siteAreaContent.getNumber() != null) {// 招商编号
							((ShortTextComponent) contentComponect)
									.setText(siteAreaContent.getNumber());
						}
					} else if (contentComponect instanceof RichTextComponent) {
						System.out.println(6);
						if (str[i].equals("summary")
								&& siteAreaContent.getSummary() != null) {// 简介
							((RichTextComponent) contentComponect)
									.setRichText(siteAreaContent.getSummary());
						} else if (str[i].equals("content")
								&& siteAreaContent.getContent() != null) {// 内容
							((RichTextComponent) contentComponect)
									.setRichText(siteAreaContent.getContent());
						}
					} else if (contentComponect instanceof ImageComponent
							&& file.getSize() > 0) {
						System.out.println(7);
						System.out.println("href we come!!");
						String fileName = file.getOriginalFilename();// 文件原名称
						String path = "/root/test" + "/" + fileName;
						file.transferTo(new File(path));
						BufferedImage bufferedImage = ImageIO.read(new File(
								path));
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(bufferedImage, "gif", baos);
						byte[] imageByte = baos.toByteArray();
						// byte[] imageByte = file.getBytes();
						System.out.println("imageByte : " + imageByte);
						((ImageComponent) contentComponect).setBorder("0");
						((ImageComponent) contentComponect).setHeight(String
								.valueOf(bufferedImage.getHeight()));
						((ImageComponent) contentComponect).setWidth(String
								.valueOf(bufferedImage.getWidth()));
						((ImageComponent) contentComponect)
								.setAltText("THIS IS THE ALT TEXT FOR THE IMAGE FROM API");
						((ImageComponent) contentComponect).setImage(fileName,
								imageByte);
						baos.close();
					}
					content.setComponent(str[i], contentComponect);
				}

				// 设置容器标题
				content.setTitle(siteAreaContent.getTitle());
				// 保存，存在的话则更新
				wcmspace.save(content);

				map.put("result", "success");
			}
		} catch (Exception e) {
			map.put("result", "failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	// public static void main(String[] args) {
	// ResourceBundle resource = ResourceBundle.getBundle("author");
	// String authInfo = resource.getString("Traffic bureau");
	// System.out.println(authInfo);
	// }

	// public List<Map<String, Object>> getContent(String siteArea,
	// HttpServletRequest request) {
	// // TODO Auto-generated method stub
	// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	//
	// Workspace workspace = null;// 声明一个WCM工作空间
	//
	// // get the workspace for current user
	// try {
	// workspace = WCMUtils.getWCMWorkspace(request.getUserPrincipal());
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// DocumentLibrary library = workspace.getDocumentLibrary("extranet");
	// workspace.setCurrentDocumentLibrary(library);
	// // 获取组用户所在组 暂时写死
	// // String group = getUserGroup(request.getUserPrincipal().toString());
	// String group = "Traffic bureau";
	//
	// // define authoring template 获取编写模板的信息
	// List authorTemplateList = getAuthorTemplate(group,selectLib,siteArea);
	//
	// for (int i = 0; i < authorTemplateList.size(); i++) {
	// list.addAll(getContentAction(workspace, siteArea,
	// authorTemplateList.get(i).toString()));
	// }
	//
	// return list;
	// }

	public List getContentAction(Workspace workspace, String siteArea,
			String authoringTemplateName) {
		List list = new ArrayList();

		DocumentIdIterator contentIterator = null;
		DocumentId contentId = null;
		// find authoring template by name
		DocumentIdIterator authoringTemplateIterator = workspace.findByName(
				DocumentTypes.AuthoringTemplate, authoringTemplateName);
		DocumentId authoringTemplateId = null;
		if (authoringTemplateIterator.hasNext()) {
			authoringTemplateId = authoringTemplateIterator.nextId();
		}
		// define siteare
		DocumentId siteAreaId = null;
		DocumentIdIterator contentIterator1 = null;
		// find sitearea by name //获取站点信息
		System.out.println(" siteArea " + siteArea);
		if (siteArea != null && siteArea.length() > 0) {
			System.out.println("siteArea 非空");
			DocumentIdIterator siteAreaIterator = workspace.findByName(
					DocumentTypes.SiteArea, siteArea);
			if (siteAreaIterator.hasNext()) {
				siteAreaId = siteAreaIterator.nextId();
			}
			// 根据站点以及编写模板来获取内容的信息
			contentIterator1 = workspace.contentSearch(authoringTemplateId,
					new DocumentId[] { siteAreaId }, null, null, true);
		} else {
			// 根据站点以及编写模板来获取内容的信息
			contentIterator1 = workspace.contentSearch(authoringTemplateId,
					null, null, null, true);
		}

		while (contentIterator1.hasNext()) {
			contentId = (DocumentId) contentIterator1.next();
			try {
				Content content = (Content) workspace.getById(contentId);

				String path = "";

				SiteArea parentArea = (SiteArea) workspace.getById(content
						.getParentId());
				path = parentArea.getName() + "/" + path;
				while (parentArea.getParentId() != null) {
					parentArea = (SiteArea) workspace.getById(parentArea
							.getParentId());
					path = parentArea.getName() + "/" + path;
				}

				String url = contentId.getContainingLibrary().getName() + "/"
						+ path + content.getName() + "?id=" + contentId.getId();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("url", url);
				map.put("name", content.getTitle());
				map.put("time", transferToDate(content.getCreationDate()));
				list.add(map);

			} catch (DocumentRetrievalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AuthorizationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// WCMApiObject aobj = (WCMApiObject) contentIterator1.next();

		}

		return list;
	}

	/**
	 * 
	 * @param args
	 *            时间戳换算
	 */
	public String transferToDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		// long lt = new Long(time);
		Date dd = date;
		String res = simpleDateFormat.format(date);
		System.out.println(res);
		return res;
	}

	// public List getAuthorTemplate(String group,
	// String siteArea) {
	// List list = new ArrayList();
	// String authoringTemplateName = "";
	// ResourceBundle resource = ResourceBundle.getBundle("Path");
	// ObjectMapper mapper = new ObjectMapper();
	// String url = resource.getString("json");
	// System.out.println(url);
	//
	// try {
	// File jsonFile = new File(url, "author.json");
	// JsonNode rootNode = null;
	// if (jsonFile.exists()) {
	// rootNode = mapper.readTree(jsonFile);
	//
	// if (siteArea != null && siteArea.length() > 0) {
	// JsonNode chlidNode = rootNode.findPath(siteArea);
	// System.out.println(11);
	// System.out.println(chlidNode.path(group).asText());
	// authoringTemplateName = chlidNode.path(group).asText();
	// list.add(authoringTemplateName);
	// } else {
	//
	// Iterator<String> keys = rootNode.fieldNames();
	// while (keys.hasNext()) {
	// String fieldName = keys.next();
	// JsonNode chlidNode = rootNode.path(fieldName);
	// Iterator<String> childKeys = chlidNode.fieldNames();
	// while (childKeys.hasNext()) {
	// String keyName = childKeys.next();
	// JsonNode chlidChild = chlidNode.path(keyName);
	// authoringTemplateName = chlidChild.path(group).asText();
	// System.out.println(authoringTemplateName);
	// list.add(authoringTemplateName);
	// }
	//
	//
	// }
	// }
	// }
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	//
	// }
	// return list;
	// }

	public List getAuthorTemplate(String siteArea) {
		List list = new ArrayList();
		ResourceBundle resource = ResourceBundle.getBundle("Path");
		ObjectMapper mapper = new ObjectMapper();
		String url = resource.getString("json");
		System.out.println(url);
		try {
			File jsonFile = new File(url, "author.json");
			JsonNode rootNode = null;
			if (jsonFile.exists()) {
				rootNode = mapper.readTree(jsonFile);
				JsonNode chlidNode = rootNode.findPath(siteArea);
				String text = chlidNode.asText();
				if (text == "") {// 暂时这样写，没有设置组
					list.add(chlidNode.path("Traffic bureau").asText());
				} else
					list.add(text);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return list;
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", 123);
		map.put("1", 1233);
		List list = new ArrayList();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//
//		List list2 = new ArrayList();
//		list2.add(11);
//		list2.add(22);
//	//	list2.add(33);
//
//		list.addAll(list2);
		
		list.removeAll(list);
		System.out.println(list.size());
		//list2 = list.subList(3, list.size());
		 for(int  j = 0;j < list.size();j++){
			 System.out.println(list.get(j));
		 }
//		for (int k = 0; k <= 1 && k < list.size(); k++) {
//			System.out.println(list.get(k));
//		}
		// System.out.println(map.get("1") + "," + map.size());
	}

	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			java.lang.reflect.Method method = o.getClass().getMethod(getter,
					new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	public String getUserGroup(String username) {
		String group = "";
		try {
			String input1 = username;
			System.out.println("Username entered is: " + input1);

			// Retrieves the default InitialContext for this server.
			javax.naming.InitialContext ctx = new javax.naming.InitialContext();

			// Retrieves the local UserRegistry object.
			com.ibm.websphere.security.UserRegistry reg = (com.ibm.websphere.security.UserRegistry) ctx
					.lookup("UserRegistry");

			// Retrieves the registry uniqueID based on the userName that is
			// specified
			// in the NameCallback.
			String uniqueID = reg.getUniqueUserId(input1);
			System.out.println("uniqueID is: " + uniqueID);

			// Strip the realm name and get real uniqueID
			String uid = com.ibm.wsspi.security.token.WSSecurityPropagationHelper
					.getUserFromUniqueID(uniqueID);
			System.out.println("Real uniqueID is: " + uid);

			// Retrieves the security name from the user registry based on the
			// uniqueID.
			String securityName = reg.getUserSecurityName(uid);
			System.out.println("securityName is: " + securityName);

			// Get user registry name
			String userDisplayName = reg.getUserDisplayName(input1);
			System.out.println("User Registry display name is: "
					+ userDisplayName);

			// Get list of groups for user
			java.util.List groupList = reg.getGroupsForUser(input1);
			ListIterator litr = groupList.listIterator();
			while (litr.hasNext()) {
				String element = (String) litr.next();
				System.out.println("Group List is: " + element);
				group += element + ",";
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

		return group;
	}

	public Map<String, Object> getMyDraftIncludeContentImage(HttpServletRequest request,
			 int firstPage) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> contentMap = new HashMap<String, Object>(); 
		
		Map<String, Object> imageMap = this.getMyAlbum(request, firstPage,"draft"); 
	 
		int photoPageSum = (int) ((PageInfo) imageMap.get("page")).getPageSum();
		// int photoSum = (int)
		// ((PageInfo)photoMap.get("page")).getSumOfResult();
		List<Map<String, Object>> list = (List<Map<String, Object>>) imageMap
				.get("list");
		int photoNum = list.size();
		System.out.println(photoNum + "," + photoPageSum);
		PageInfo pageInfo = (PageInfo) imageMap.get("page");
		if (firstPage > photoPageSum) {
			contentMap = this.getMyDraft(request,  firstPage
					- photoPageSum);

			if (photoNum != pageInfo.getPerPage() && photoNum != 0) {
				int difference = pageInfo.getPerPage() - photoNum;

				Map<String, Object> map2 = this.getMyDraft(request,  firstPage
						- photoPageSum + 1);
				List<Map<String, Object>> list1 = (List<Map<String, Object>>) contentMap
						.get("list");
				List<Map<String, Object>> list2 = (List<Map<String, Object>>) map2
						.get("list");
				System.out.println(list1.size() + "," + list2.size());
				for (int i = 0, j = 0; list1.size() > 0 && j < difference; j++) {
					list1.remove(i);
					if (list2.size() > j)
						list1.add(list2.get(j));
				}
				contentMap.put("list", list1);
			}
			pageInfo.setSumOfResult((int) pageInfo.getSumOfResult()
					+ (int) ((PageInfo) contentMap.get("page"))
							.getSumOfResult());

			contentMap.put("page", pageInfo);
			return contentMap;
		} else if (firstPage == photoPageSum) {
			if (photoNum == pageInfo.getPerPage()) {
				contentMap = this.getMyDraft(request, 1);
				int contentSum = (int) ((PageInfo) contentMap.get("page"))
						.getSumOfResult();
				pageInfo.setSumOfResult(contentSum + pageInfo.getSumOfResult());
				imageMap.put("page", pageInfo);
				return imageMap;
			} else {
				contentMap = this.getMyDraft(request,  1);
				int difference = pageInfo.getPerPage() - photoNum;
				List<Map<String, Object>> list1 = (List<Map<String, Object>>) contentMap
						.get("list");

				for (int i = 0; i < difference && i < list1.size(); i++) {
					list.add(list1.get(i));
				}

				// pageInfo = (PageInfo)photoMap.get("page");
				int contentSum = (int) ((PageInfo) contentMap.get("page"))
						.getSumOfResult();
				pageInfo.setSumOfResult(contentSum + pageInfo.getSumOfResult());
				imageMap.put("page", pageInfo);
				imageMap.put("list", list);
			}
			return imageMap;
		} else {
			contentMap = this.getMyDraft(request,  1);
			int contentSum = (int) ((PageInfo) contentMap.get("page"))
					.getSumOfResult();
			pageInfo.setSumOfResult(contentSum + pageInfo.getSumOfResult());
			imageMap.put("page", pageInfo);
		}
		return imageMap;
		
	}
	
	public Map<String, Object> getMyDraft(HttpServletRequest request, int firstPage) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Principal currentUser = request.getUserPrincipal();
		try {
			Workspace workspace;

			workspace = WCM_API.getRepository().getWorkspace(currentUser);

			ResourceBundle resource = ResourceBundle.getBundle("url");
			String userDN = resource.getString("userDN");

			
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");

			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary library = workspace.getDocumentLibrary(libName);
			workspace.setCurrentDocumentLibrary(library);
			QueryService queryservice = workspace.getQueryService();
			Query query = queryservice.createQuery(Content.class);
			query.addSelectors(Selectors.libraryEquals(library));

			query.addSelectors(Selectors.authorsContain("uid="
					+ currentUser.toString() + "," + userDN));
 

			List<DocumentId> ll = new ArrayList<DocumentId>();
			System.out.println("go go go !!!!newsContentOneAction");

			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					"Draft Stage").next());

			query.addSelectors(WorkflowSelectors.stageIn(ll),
					WorkflowSelectors.statusEquals(Status.DRAFT));
			query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
			long sum = queryservice.count(query);

			PageInfo pageInfo = new PageInfo();

			pageInfo.setSumOfResult(sum);
			pageInfo.setCurrentPage(firstPage);
			resultMap.put("page", pageInfo);

			PageIterator<ResultIterator> page = queryservice.execute(query,
					pageInfo.getPerPage(), firstPage);

			if (page.hasNext()) {
				ResultIterator it = page.next();

				while (it.hasNext()) {
					Map<String, Object> map = new HashMap<String, Object>();
					WCMApiObject object = (WCMApiObject) it.next();
					Content content = (Content) object;
					map.put("title", content.getTitle());
					map.put("name", content.getName());
					map.put("time", transferToDate(content.getCreationDate()));
					map.put("type", "content");
					String path = "";

					SiteArea parentArea;

					parentArea = (SiteArea) workspace.getById(content
							.getParentId());

					path = parentArea.getName() + "/" + path;
					while (parentArea.getParentId() != null) {
						parentArea = (SiteArea) workspace.getById(parentArea
								.getParentId());
						path = parentArea.getName() + "/" + path;
					}

					String url = libName + "/" + path + content.getName()
							+ "?id=" + content.getId().getId();

					map.put("preview", url);

					map.put("read",
							"/wps/myportal/wcmAuthoring?wcmAuthoringAction=read&docid=com.ibm.workplace.wcm.api.WCM_Conten/"
									+ content.getId().getId());
					map.put("edit",
							"/wps/myportal/wcmAuthoring?wcmAuthoringAction=edit&docid=com.ibm.workplace.wcm.api.WCM_Conten/"
									+ content.getId().getId());
					map.put("approve",
							"/wps/myportal/wcmAuthoring?wcmAuthoringAction=approve&docid=com.ibm.workplace.wcm.api.WCM_Conten/"
									+ content.getId().getId());
					list.add(map);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultMap.put("list", list);

		return resultMap;

	}
	
	public Map<String, Object> getMyPubulished(HttpServletRequest request,
			String selectLib, String siteArea, int firstPage) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> contentMap = new HashMap<String, Object>(); 
		
		Map<String, Object> imageMap = this.getMyAlbum(request, firstPage,"publish"); 
	 
		int photoPageSum = (int) ((PageInfo) imageMap.get("page")).getPageSum();
		// int photoSum = (int)
		// ((PageInfo)photoMap.get("page")).getSumOfResult();
		List<Map<String, Object>> list = (List<Map<String, Object>>) imageMap
				.get("list");
		int photoNum = list.size();
		System.out.println(photoNum + "," + photoPageSum);
		PageInfo pageInfo = (PageInfo) imageMap.get("page");
		if (firstPage > photoPageSum) {
			contentMap = this.getMyPubulishedContent(request,  firstPage
					- photoPageSum);

			if (photoNum != pageInfo.getPerPage() && photoNum != 0) {
				int difference = pageInfo.getPerPage() - photoNum;

				Map<String, Object> map2 = this.getMyPubulishedContent(request,  firstPage
						- photoPageSum + 1);
				List<Map<String, Object>> list1 = (List<Map<String, Object>>) contentMap
						.get("list");
				List<Map<String, Object>> list2 = (List<Map<String, Object>>) map2
						.get("list");
				System.out.println(list1.size() + "," + list2.size());
				for (int i = 0, j = 0; list1.size() > 0 && j < difference; j++) {
					list1.remove(i);
					if (list2.size() > j)
						list1.add(list2.get(j));
				}
				contentMap.put("list", list1);
			}
			pageInfo.setSumOfResult((int) pageInfo.getSumOfResult()
					+ (int) ((PageInfo) contentMap.get("page"))
							.getSumOfResult());

			contentMap.put("page", pageInfo);
			return contentMap;
		} else if (firstPage == photoPageSum) {
			if (photoNum == pageInfo.getPerPage()) {
				contentMap = this.getMyPubulishedContent(request, 1);
				int contentSum = (int) ((PageInfo) contentMap.get("page"))
						.getSumOfResult();
				pageInfo.setSumOfResult(contentSum + pageInfo.getSumOfResult());
				imageMap.put("page", pageInfo);
				return imageMap;
			} else {
				contentMap = this.getMyPubulishedContent(request,  1);
				int difference = pageInfo.getPerPage() - photoNum;
				List<Map<String, Object>> list1 = (List<Map<String, Object>>) contentMap
						.get("list");

				for (int i = 0; i < difference && i < list1.size(); i++) {
					list.add(list1.get(i));
				}

				// pageInfo = (PageInfo)photoMap.get("page");
				int contentSum = (int) ((PageInfo) contentMap.get("page"))
						.getSumOfResult();
				pageInfo.setSumOfResult(contentSum + pageInfo.getSumOfResult());
				imageMap.put("page", pageInfo);
				imageMap.put("list", list);
			}
			return imageMap;
		} else {
			contentMap = this.getMyPubulishedContent(request,  1);
			int contentSum = (int) ((PageInfo) contentMap.get("page"))
					.getSumOfResult();
			pageInfo.setSumOfResult(contentSum + pageInfo.getSumOfResult());
			imageMap.put("page", pageInfo);
		}
		return imageMap;
	}
	 

	public Map<String, Object> getMyPubulishedContent(HttpServletRequest request,
			int firstPage) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Principal currentUser = request.getUserPrincipal();
		try {
			Workspace workspace;
			workspace = WCM_API.getRepository().getWorkspace(currentUser);
			ResourceBundle resourceBundle = ResourceBundle.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary library = workspace.getDocumentLibrary(libName);
			workspace.setCurrentDocumentLibrary(library);
			QueryService queryservice = workspace.getQueryService();
			Query query = queryservice.createQuery(Content.class);
			query.addSelectors(Selectors.libraryEquals(library));

			List<DocumentId> ll = new ArrayList<DocumentId>();
			System.out.println("go go go !!!!newsContentOneAction");

			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					"Publish Stage").next());

			query.addSelectors(WorkflowSelectors.stageIn(ll),
					WorkflowSelectors.statusEquals(Status.PUBLISHED));
			query.setSorts(Sorts.byDateModified(SortDirection.DESCENDING));
			ResourceBundle resource = ResourceBundle.getBundle("url");
			String userDN = resource.getString("userDN");
			query.addSelectors(Selectors.authorsContain("uid="
					+ currentUser.getName() + "," + userDN));
			long sum = queryservice.count(query);

			PageInfo pageInfo = new PageInfo();

			pageInfo.setSumOfResult(sum);
			pageInfo.setCurrentPage(firstPage);
			resultMap.put("page", pageInfo);

			PageIterator<ResultIterator> page = queryservice.execute(query,
					pageInfo.getPerPage(), firstPage);

			if (page.hasNext()) {
				ResultIterator it = page.next();

				while (it.hasNext()) {
					Map<String, Object> map = new HashMap<String, Object>();
					WCMApiObject object = (WCMApiObject) it.next();
					Content content = (Content) object;

					map.put("title", content.getTitle());
					map.put("name", content.getName());
					// map.put("time",
					// transferToDate(content.getCreationDate()));
					map.put("time", transferToDate(content.getPublishedDate()));

					String path = "";

					SiteArea parentArea;

					parentArea = (SiteArea) workspace.getById(content
							.getParentId());

					path = parentArea.getName() + "/" + path;
					while (parentArea.getParentId() != null) {
						parentArea = (SiteArea) workspace.getById(parentArea
								.getParentId());
						path = parentArea.getName() + "/" + path;
					}

					String url = libName + "/" + path + content.getName()
							+ "?id=" + content.getId().getId();

					map.put("preview", url);

					map.put("read",
							"/wps/myportal/wcmAuthoring?wcmAuthoringAction=read&docid=com.ibm.workplace.wcm.api.WCM_Conten/"
									+ content.getId().getId());
					map.put("edit",
							"/wps/myportal/wcmAuthoring?wcmAuthoringAction=edit&docid=com.ibm.workplace.wcm.api.WCM_Conten/"
									+ content.getId().getId());
					map.put("approve",
							"/wps/myportal/wcmAuthoring?wcmAuthoringAction=approve&docid=com.ibm.workplace.wcm.api.WCM_Conten/"
									+ content.getId().getId());
					list.add(map);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultMap.put("list", list);

		return resultMap;
	}

	public Map<String, Object> getSiteArea(HttpServletRequest request,
			String selectLib, String area) {
		Map<String, Object> map = new HashMap();
		Principal currentUser = request.getUserPrincipal();
		System.out.println("current user is : " + currentUser.getName());
		Workspace wcmspace = null;// 声明一个WCM工作空间

		try {
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);
			// 2.取出内容库和模板、工作流程和站点区域（栏目）
			String libName = selectLib;// 已选中的库名
			String areaId = area;// 站点区域id

			DocumentLibrary lib = null;// web内容库
			// 拿当前web内容库
			lib = WCMUtils.getWCMLibrary(libName, currentUser);
			System.out.println("内容库对象为：" + libName);
			wcmspace.setCurrentDocumentLibrary(lib);

			DocumentIdIterator siteAreaIdIterator = wcmspace.findByName(
					DocumentTypes.SiteArea, areaId);
			System.out.println("iterator对象为：" + siteAreaIdIterator);
			// 创建内容
			Content content = null;
			// 内容添加工作流程

			System.out.println("area is " + area + ",areaId is :" + areaId);
			System.out.println(siteAreaIdIterator.hasNext());
			map.put("result", siteAreaIdIterator.hasNext());
			map.put("test", "test");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return map;
	}

	public List<Map<String, Object>> getTemplate(String siteArea,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Workspace wcmspace = null;// 声明一个WCM工作空间
		Principal currentUser = request.getUserPrincipal();
		try {
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);

			DocumentLibrary lib = null;// web内容库
			lib = WCMUtils.getWCMLibrary("guanweihui", currentUser);
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
			folder.addSelectors(Selectors.nameEquals(siteArea));
			folder.addSelector(Selectors.libraryEquals(wcmspace
					.getCurrentDocumentLibrary()));
			ResultIterator it = queryService.execute(folder);

			while (it.hasNext()) {
				Folder ff = (Folder) it.next();
				ResultIterator result = ff.getChildren();

				while (result.hasNext()) {
					AuthoringTemplate template = (AuthoringTemplate) result
							.next();
					authTemplate = template;
				}

			}
			System.out.println("now the template name is : "
					+ authTemplate.getTitle());

			ContentPrototype prototype = authTemplate.getGenericPrototype();

			String[] componentNames = prototype.getComponentNames();
			for (int i = 0; i < componentNames.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				String componectName = componentNames[i];
				ContentComponent contentComponect = prototype
						.getComponent(componectName);

				String type = contentComponect.getDocumentType().getApiType()
						.getName();
				type = type.substring(type.lastIndexOf(".") + 1);
				map.put("name", componectName);
				map.put("type", type);

				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Map<String, Object> commitContent(HttpServletRequest request,
			String contentName, String siteArea) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", false);
		Principal currentUser = request.getUserPrincipal();
		Workspace workspace;
		try {
			workspace = WCM_API.getRepository().getWorkspace(currentUser);
			DocumentLibrary lib = workspace.getDocumentLibrary("guanweihui");

			workspace.setCurrentDocumentLibrary(lib);
			QueryService queryservice = workspace.getQueryService();
			Query query = queryservice.createQuery(Content.class);

			query.addSelectors(Selectors.libraryEquals(lib));

			ResourceBundle resource = ResourceBundle.getBundle("url");
			String userDN = resource.getString("userDN");
			query.addSelectors(Selectors.authorsContain("uid="
					+ currentUser.toString() + "," + userDN));

			List<DocumentId> ll = new ArrayList<DocumentId>();
			System.out.println("go go go !!!!newsContentOneAction");

			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					"Draft Stage").next());
			query.addSelectors(WorkflowSelectors.stageIn(ll),
					WorkflowSelectors.statusEquals(Status.DRAFT));
			query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
			ResultIterator it = queryservice.execute(query);
			while (it.hasNext()) {
				WCMApiObject object = (WCMApiObject) it.next();
				Content content = (Content) object;
				if (content.getTitle().equals(contentName)) {
					content.approve(false, false, "");
					map.put("state", true);
					break;
				}

			}

		} catch (ServiceNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	public Map<String, Object> deleteContent(HttpServletRequest request,
			String contentName) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", false);
		Principal currentUser = request.getUserPrincipal();
		Workspace workspace;
		try {
			workspace = WCM_API.getRepository().getWorkspace(currentUser);
			DocumentLibrary lib = workspace.getDocumentLibrary("guanweihui");

			workspace.setCurrentDocumentLibrary(lib);
			QueryService queryservice = workspace.getQueryService();
			Query query = queryservice.createQuery(Content.class);
			query.addSelectors(Selectors.libraryEquals(lib));
			ResourceBundle resource = ResourceBundle.getBundle("url");
			String userDN = resource.getString("userDN");
			query.addSelectors(Selectors.authorsContain("uid="
					+ currentUser.toString() + "," + userDN));
			// List<DocumentId> ll = new ArrayList<DocumentId>();
			System.out.println("go go go !!!!newsContentOneAction");
			/*
			 * ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
			 * "Draft Stage").next());
			 * query.addSelectors(WorkflowSelectors.stageIn(ll),
			 * WorkflowSelectors.statusEquals(Status.DRAFT));
			 */
			query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
			ResultIterator it = queryservice.execute(query);
			while (it.hasNext()) {
				WCMApiObject object = (WCMApiObject) it.next();
				Content content = (Content) object;
				if (content.getName().equals(contentName)) {
					workspace.delete(content.getId());
					map.put("state", true);
					break;
				}

			}

		} catch (ServiceNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentDeleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	public Map<String, Object> searchContentByName(HttpServletRequest request,
			String contentName, String siteArea, String state) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Principal currentUser = request.getUserPrincipal();
		Workspace workspace;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("property");
		String libName = resourceBundle.getString("rootLibrary");
		try {
			workspace = WCM_API.getRepository().getWorkspace(currentUser);
			DocumentLibrary lib;
			lib = WCMUtils.getWCMLibrary(libName, currentUser);
			workspace.setCurrentDocumentLibrary(lib);

			QueryService queryservice = workspace.getQueryService();
			Query query = queryservice.createQuery(Content.class);
			query.addSelectors(Selectors.libraryEquals(lib));

			List<DocumentId> ll = new ArrayList<DocumentId>();

			ll.add(workspace.findByName(DocumentTypes.WorkflowStage, state)
					.next());

			query.addSelectors(WorkflowSelectors.stageIn(ll),
					WorkflowSelectors.statusEquals(Status.DRAFT));

			query.addSelectors(Selectors.nameEquals(contentName));
			ResultIterator it = queryservice.execute(query);

			if (it.hasNext()) {
				WCMApiObject object = (WCMApiObject) it.next();
				Content content = (Content) object;
				String[] str = content.getComponentNames();

				for (int i = 0; i < str.length; i++) {

					ContentComponent cc = content.getComponent(str[i]);
					map.put(cc.getName(), getValue(cc));
				}
				map.put("createTime", transferToDate(content.getCreationDate()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}

	public String getValue(ContentComponent cc) {
		if (cc instanceof RichTextComponent) {
			return ((RichTextComponent) cc).getRichText();
		} else if (cc instanceof ShortTextComponent) {
			return ((ShortTextComponent) cc).getText();
		} else if (cc instanceof ImageComponent) {
			return ((ImageComponent) cc).getResourceURL();
		} else if (cc instanceof FileComponent) {
			return ((FileComponent) cc).getResourceURL();
		}
		return null;
	}

 
	
	
	public Map<String, Object> getPhotoDraftForApprover(
			HttpServletRequest request, HttpSession session, int firstPage) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List list = new ArrayList();
		try {
			Principal currentUser = request.getUserPrincipal();
			Workspace wcmspace = WCM_API.getRepository().getWorkspace(
					currentUser);
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");

			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary library = wcmspace.getDocumentLibrary(libName);
			wcmspace.setCurrentDocumentLibrary(library);

			QueryService queryService = wcmspace.getQueryService();
			Query imageQuery = queryService.createQuery();
			List ll = new ArrayList();
			ll.add(wcmspace.findByName(DocumentTypes.WorkflowStage,
					"Review Stage").next());
			AccessFilter filter = queryService.createAccessFilter(
					Access.REVIEWER, QueryService.FilterOperation.ANY_USER,
					new String[] { currentUser.getName() });

			imageQuery.addSelectors(new Selector[] { Selectors
					.typeIn(new Class[] { DocumentTypes.LibraryImageComponent
							.getApiType() }) });
			imageQuery.setSorts(new Sort[] { Sorts
					.byPublishDate(SortDirection.DESCENDING) });

			imageQuery.addSelectors(new Selector[] { WorkflowSelectors
					.stageIn(ll) });
			imageQuery.setAccessFilter(filter);
			ResultIterator imageit;

			imageit = queryService.execute(imageQuery);
			List folderNameList = new ArrayList();
			 
			while (imageit.hasNext()) {
				LibraryImageComponent image = (LibraryImageComponent) imageit
						.next();
				 
				DocumentId parentId = image.getParentId();
				
				if (folderNameList.indexOf(parentId.getId()) == -1) {
					Folder parentFolder = wcmspace.getById(image.getParentId());
					String folderName = parentFolder.getTitle(); 
					Map<String, Object> map = new HashMap<String, Object>(); 
					map.put("title", folderName);
					map.put("folderId", parentId.getId());
					map.put("time", transferToDate(parentFolder.getModifiedDate()));
					map.put("type", "folder");
					map.put("fromPeople", image.getAuthors()[0]);
					list.add(map);
					folderNameList.add(parentId.getId());
				}
			}
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurrentPage(firstPage);
			pageInfo.setSumOfResult(list.size());
			
			int pageSum = (int) pageInfo.getPageSum();
			if(pageSum > firstPage){
				list = list.subList((firstPage -1) *  pageInfo.getPerPage(),firstPage * pageInfo.getPerPage());
			}
			else if (pageSum == firstPage) {
				list = list.subList((firstPage -1) *  pageInfo.getPerPage(),list.size());
			}
			else{
				int size = list.size();
				int remain = size % pageInfo.getPerPage();
				list.subList(0,size-remain);
			}
			resultMap.put("page", pageInfo);
			resultMap.put("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	public Map<String, Object> getMyAlbum(HttpServletRequest request,
			int firstPage,String stage) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List list = new ArrayList();
		String workFlowStage = stage.equals("draft")? "Draft":"Publish";
		try {
			Principal currentUser = request.getUserPrincipal();
			Workspace wcmspace = WCM_API.getRepository().getWorkspace(
					currentUser);
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");

			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary library = wcmspace.getDocumentLibrary(libName);
			wcmspace.setCurrentDocumentLibrary(library);

			QueryService queryService = wcmspace.getQueryService();
			Query imageQuery = queryService.createQuery();
			List ll = new ArrayList();
			ll.add(wcmspace.findByName(DocumentTypes.WorkflowStage,
					workFlowStage + " Stage").next());
			ResourceBundle resource = ResourceBundle.getBundle("url");
			String userDN = resource.getString("userDN");
			imageQuery.addSelectors(Selectors.authorsContain("uid="
					+ currentUser.toString() + "," + userDN));
			imageQuery.addSelectors(WorkflowSelectors.stageIn(ll));
//			imageQuery.addSelectors(WorkflowSelectors.stageIn(ll),
//					WorkflowSelectors.statusEquals(Status.DRAFT));

			imageQuery.addSelectors(new Selector[] { Selectors
					.typeIn(new Class[] { DocumentTypes.LibraryImageComponent
							.getApiType() }) });
			imageQuery.setSorts(new Sort[] { Sorts
					.byPublishDate(SortDirection.DESCENDING) });

			imageQuery.addSelectors(new Selector[] { WorkflowSelectors
					.stageIn(ll) });
			 
			ResultIterator imageit;

			imageit = queryService.execute(imageQuery);
			List folderNameList = new ArrayList();
			while (imageit.hasNext()) {
				LibraryImageComponent image = (LibraryImageComponent) imageit
						.next();
				DocumentId parentId = image.getParentId();

				if (folderNameList.indexOf(parentId.getId()) == -1) {
					Folder parentFolder = wcmspace.getById(image.getParentId());
					String folderName = parentFolder.getTitle();

					Map<String, Object> map = new HashMap<String, Object>();

					map.put("title", folderName);
					map.put("folderId", parentId.getId());
					map.put("time", transferToDate(parentFolder.getModifiedDate()));
					map.put("type", "folder");
					list.add(map);
					folderNameList.add(parentId.getId());
				}
			}
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurrentPage(firstPage);
			pageInfo.setSumOfResult(list.size());
			
			int pageSum = (int) pageInfo.getPageSum();
			if(pageSum > firstPage){
				list = list.subList((firstPage -1) *  pageInfo.getPerPage(),firstPage * pageInfo.getPerPage());
			}
			else if (pageSum == firstPage) {
				list = list.subList((firstPage -1) *  pageInfo.getPerPage(),list.size());
			}
			else{
				int size = list.size();
				int remain = size % pageInfo.getPerPage();
				list.subList(0,size-remain);
			}
			
			resultMap.put("list", list);
			resultMap.put("page", pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

}
