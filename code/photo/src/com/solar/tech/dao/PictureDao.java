package com.solar.tech.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.db2.jcc.a.f;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.Folder;
import com.ibm.workplace.wcm.api.LibraryImageComponent;
import com.ibm.workplace.wcm.api.SaveOption;
import com.ibm.workplace.wcm.api.SaveOptions;
import com.ibm.workplace.wcm.api.WCMApiObject;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.AuthorizationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.OperationFailedException;
import com.ibm.workplace.wcm.api.exceptions.QueryServiceException;
import com.ibm.workplace.wcm.api.exceptions.ServiceNotAvailableException;
import com.ibm.workplace.wcm.api.query.PageIterator;
import com.ibm.workplace.wcm.api.query.Query;
import com.ibm.workplace.wcm.api.query.QueryDepth;
import com.ibm.workplace.wcm.api.query.QueryService;
import com.ibm.workplace.wcm.api.query.ResultIterator;
import com.ibm.workplace.wcm.api.query.Selector;
import com.ibm.workplace.wcm.api.query.Selectors;
import com.ibm.workplace.wcm.api.query.SortDirection;
import com.ibm.workplace.wcm.api.query.Sorts;
import com.ibm.workplace.wcm.api.query.WorkflowSelectors;
import com.ibm.workplace.wcm.api.query.WorkflowSelectors.Status;
import com.solar.tech.bean.Image;
import com.solar.tech.bean.Img;
import com.solar.tech.bean.Pager;
import com.solar.tech.dbutil.StringUtil;
import com.solar.tech.util.Constant;
import com.solar.tech.util.RandomNum;
import com.solar.tech.util.WCMUtils;
import com.tivoli.pd.jasn1.boolean32;

@Repository
public class PictureDao {
	private static final int MAX_NUM = 20;
	private static final int PAGE_SIZE = 4;

	@Autowired
	private HttpServletRequest request;

	private String description = "";

	public Map<String, Object> show(String username, String pageNum,
			String pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); // 用来返回数据

		boolean admin = false;// 是否是管理员.,wpsadmins 是管理员

		ObjectMapper mapper = new ObjectMapper();

		// 此段代码是用来判断用户是否是管理员

		admin = true;
		System.out.println(admin);
		// 判断完毕

		String urlpath = this.request.getServletContext()
				.getRealPath("/config");
		System.out.println(urlpath);
		List<String> urlList = Constant.urlList(urlpath);// 读取配置文件imageConfig.xml获取值
		List<String> pathList = testFileIsExit(urlList);
		int PageNum = pageNum == null ? Constant.DEFAULT_PAGE_NUM : Integer
				.parseInt(pageNum);
		int PageSize = pageSize == null ? Constant.DEFAULT_PAGE_SIZE_FIRST
				: Integer.parseInt(pageSize);
		// pageSize = Constant.DEFAULT_PAGE_SIZE_FIRST;
		// 循环获取相册
		Pager<Img> result = ReadFiles.photo(pathList, urlList, PageNum,
				PageSize);
		map.put("result", result);
		map.put("admin", admin);
		System.out.println(map.get("admin"));

		return map;
	}

	/**
	 * 判断文件是否存在
	 */
	public List<String> testFileIsExit(List<String> urlList) {
		List<String> pathList = new ArrayList<String>();
		ResourceBundle resource = ResourceBundle.getBundle("url");
		String savePath = resource.getString("imageSavePath");
		for (int i = 0; i < urlList.size(); i++) {
			String path = savePath + urlList.get(i);// 上传图片路径
			System.out.println("path : " + path);
			pathList.add(path);
			// 判断上传upUrl该文件是否存在
			File f1 = new File(path);
			if (!f1.exists()) {
				f1.mkdirs();
			}
		}
		return pathList;
	}

	public Map<String, Object> remove(HttpServletRequest request2,
			String albumId) {
		// TODO Auto-generated method stub

		Map<String, Object> map = new HashMap<String, Object>();
		Workspace wcmspace = null;// 声明一个WCM工作空间
		Principal currentUser = request.getUserPrincipal();
		try {
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);
			DocumentLibrary lib = null;// web内容库
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
			lib = WCMUtils.getWCMLibrary(libName, currentUser);
			wcmspace.setCurrentDocumentLibrary(lib);

			Folder folder = (Folder) wcmspace.getById(wcmspace.createDocumentId(albumId));

			wcmspace.delete(folder.getId());
			map.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", false);
		}
		return map;
		/*
		 * try { ResourceBundle resource = ResourceBundle.getBundle("url");
		 * String savePath = resource.getString("imageSavePath"); String
		 * PhotoPath = savePath + url + "/" + photo; // 转为物理路径 File file = new
		 * File(PhotoPath); if (file.exists()) {// 判断文件是否存在 if (file.isFile())
		 * {// 判断是否是文件 file.delete();// 删除文件 } else if (file.isDirectory()) {//
		 * 否则如果它是一个目录 File[] files = file.listFiles();// 声明目录下所有的文件 files[]; for
		 * (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件 //
		 * this.delete(files[i]);//把每个文件用这个方法进行迭代 files[i].delete(); }
		 * file.delete();// 删除文件夹 } return "success"; } else {
		 * System.out.println("所删除的文件不存在"); return "failed"; } } catch
		 * (Exception e) { // TODO: handle exception return "failed"; }
		 */
	}

	public Map<String, Object> edit(String photo, String nphoto, String url) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Workspace wcmspace = null;// 声明一个WCM工作空间
		Principal currentUser = request.getUserPrincipal();
		try {
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);
			DocumentLibrary lib = null;// web内容库
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
			lib = WCMUtils.getWCMLibrary(libName, currentUser);
			wcmspace.setCurrentDocumentLibrary(lib);

			Folder folder = getFolderByName(wcmspace, photo);
			folder.setTitle(nphoto);
			wcmspace.save(folder);
			map.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", false);
		}
		return map;
		/*
		 * ResourceBundle resource = ResourceBundle.getBundle("url"); String
		 * savePath = resource.getString("imageSavePath");
		 * 
		 * String PhotoPath = savePath + url + "/" + photo; // 转为物理路径 String
		 * PhotoPathn = savePath + url + "/" + nphoto; File file = new
		 * File(PhotoPath); File f = null; File f1 = null; boolean bool = false;
		 * try { f = new File(PhotoPath); f1 = new File(PhotoPathn); bool =
		 * f.renameTo(f1); return "success"; } catch (Exception e) {
		 * 
		 * System.out.println(e); e.printStackTrace();
		 * 
		 * return "failed"; }
		 */

	}

	public Map<String, Object> add(MultipartFile file) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		// System.out.println("开始");
		// String description = "";
		// description = this.description;
		// ResourceBundle resource = ResourceBundle.getBundle("url");
		// String savePath = resource.getString("imageSavePath");
		//
		// String urlpath =
		// request.getServletContext().getRealPath("/config");//配置上传图片路径的配置文件路径
		// String url = Constant.upUrl(urlpath);//读取配置文件imageConfig.xml获取值
		// // String path =request.getServletContext().getRealPath(url);//上传图片路径
		// savePath = savePath + url+"/"+description+"/";
		// File f1 = new File(savePath);
		// // System.out.println(savePath);
		// if (!f1.exists()) {
		// f1.mkdirs();
		// }
		// String name = file.getOriginalFilename();
		// name = RandomNum.getRandomString(5) + name;
		// File targetFile = null;
		// do {
		// targetFile = new File(savePath, name);
		// name = RandomNum.getRandomString(5) + name;
		// } while (targetFile.exists());
		//
		// // 保存
		// try {
		// file.transferTo(targetFile);//上传
		// map.put("success", true);
		// map.put("fileUrl", savePath+"\\"+name);//保存文件路径
		// System.out.println(name);
		// } catch (Exception e) {
		// e.printStackTrace();
		// map.put("success", false);
		// map.put("msg", e.getMessage());//保存错误信息
		// }
		// map.put("fileName", name);//保存文件名
		return map;
	}

	public Map<String, Object> addFirst(String description) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.description = description;
			map.put("state", "success");
		} catch (Exception e) {
			// TODO: handle exception
			map.put("state", "failed");
		}
		return map;
	}

	public Map<String, Object> showDetail(HttpServletRequest request2,
			HttpServletResponse response, String isadmin, String url,
			String pageNum, String pageSize, String imgName, String photo,
			String newImg) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		// 校验pageNum参数输入合法性

		if (pageNum != null && !StringUtil.isNum(pageNum)) {
			request.setAttribute("errorMsg", "参数传输错误");
			// request.getRequestDispatcher("/showPage.jsp").forward(request,
			// response);
			return null;
		}

		int pageNumber = Constant.DEFAULT_PAGE_NUM; // 显示第几页数据
		if (pageNum != null && !"".equals(pageNum.trim())) {
			pageNumber = Integer.parseInt(pageNum);
		}

		// int pageSize = Constant.DEFAULT_PAGE_SIZE; // 每页显示多少条记录
		int pageSizeNumber = 1000;
		// String pageSizeStr = request.getParameter("pageSize");
		// String name = request.getParameter("imgName");//相册名称
		// String photo = request.getParameter("photo");//相册名称
		if (photo != null && !photo.equals("")) {
			imgName = photo;
		}
		if (pageSize != null && !"".equals(pageSize.trim())) {
			pageSizeNumber = Integer.parseInt(pageSize);
		}
		// String newImg = request.getParameter("newImg");
		// 调用service 获取查询结果
		FindImg findImg = new FindImg();
		ResourceBundle resource = ResourceBundle.getBundle("url");

		String savePath = resource.getString("imageSavePath") + url;// 相册存储路径
		Pager<Img> result = findImg.findImg(newImg, imgName, savePath, url,
				pageNumber, pageSizeNumber);

		// 返回结果到页面
		System.out.println("::::::::::result::::::::");
		if (null != result) {
			System.out.println("result::::::::" + result.getTotalRecord());
		}
		// request.setAttribute("result", result);
		// request.setAttribute("url", url);
		map.put("result", result);
		map.put("url", url);
		System.out.println("::::::::::url::::::::" + url);
		if (isadmin != null) {
			if (isadmin.equals("1")) {
				System.out.println("::::::::::showPage::::::::");
				// request.getRequestDispatcher("/showPage.jsp").forward(request,
				// response);

			} else {
				System.out.println("::::::::::showPageOpen::::::::");
				// request.getRequestDispatcher("/showPageOpen.jsp").forward(request,
				// response);
			}
		} else {
			System.out.println("::::::::::isadmin------showPageOpen::::::::");
			// request.getRequestDispatcher("/showPageOpen.jsp").forward(request,
			// response);
		}
		return map;
	}

	public Map<String, Object> uploadImage(HttpServletRequest request,
			MultipartFile file) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
		if (!(file.getSize() > 0)) {
			map.put("success", false);
			map.put("reason", "没有上传图片= <> =");
			return map;
		}

		Workspace wcmspace = null;// 声明一个WCM工作空间
		Principal currentUser = request.getUserPrincipal();
		try {
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);

			DocumentLibrary lib = null;// web内容库
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
			lib = WCMUtils.getWCMLibrary(libName, currentUser);
			wcmspace.setCurrentDocumentLibrary(lib);

			LibraryImageComponent image = wcmspace.createImageComponent();
			// image.setTitle( this.description);
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddhhmmssSSSSS");
			Date date = new Date();
			image.setName(getContentUnquitName());
			//description 即为相册的名字
			map = validateFolder(wcmspace, this.description);
			// 检验是否程序已经报错，如果是，就直接不运行下面的代码
			String state = (String) map.get("state");
			if (state.equals("success")) {
				Folder ff = (Folder) map.get("folder");

				String fileName = file.getOriginalFilename();// 文件原名称
				String path = "/root/test" + "/" + fileName;
				file.transferTo(new File(path));
				BufferedImage bufferedImage = ImageIO.read(new File(path));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "gif", baos);
				byte[] imageByte = baos.toByteArray();
				// byte[] imageByte = file.getBytes();

				image.setBorder("0");
				image.setHeight(String.valueOf(bufferedImage.getHeight()));
				image.setWidth(String.valueOf(bufferedImage.getWidth()));
				image.setAltText("THIS IS THE ALT TEXT FOR THE IMAGE FROM API");
				image.setImage(fileName, imageByte);
				image.setTitle(fileName);

				// set workflow
				image.setWorkflowId(wcmspace.findByName(DocumentTypes.Workflow,
						"newsReviewAndPublish").next());
				baos.close();
				wcmspace.save(image, ff.getId(),
						SaveOptions.createOptions(SaveOption.MOVE));
				map.put("success", true);
			} else {
				return map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put("success", false);
			System.out.println(e);
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test1", 111);
		map.put("gggg", 222);
		map.put("23423411", 222);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Map.Entry<String, Object> entry : map.entrySet()) {

			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());

		}
	}

	public Map<String, Object> validateFolder(Workspace wcmspace,
			String albumName) {
		DocumentLibrary lib = null;// web内容库
		Map<String, Object> map = new HashMap<String, Object>();
		QueryService queryService = wcmspace.getQueryService();
		Query folder = queryService.createQuery(Folder.class);
		folder.addSelectors(Selectors.typeIn(DocumentTypes.Folder.getApiType()));
		// 寻找相册的文件夹
		folder.addSelectors(Selectors.titleEquals(albumName));
		folder.addSelector(Selectors.libraryEquals(wcmspace
				.getCurrentDocumentLibrary()));
		ResultIterator it;

		try {
			it = queryService.execute(folder);
			int count = it.getSize();
			if (count == 1) {
				Folder ff = (Folder) it.next();
				map.put("state", "success");
				map.put("folder", ff);
			}
			// 如果相册不存在 那么就在总相册下面创建一个新的相册
			else {
				Query parentfolder = queryService.createQuery(Folder.class);
				parentfolder.addSelectors(Selectors.typeIn(DocumentTypes.Folder
						.getApiType()));
				// 寻找相册的文件夹
				parentfolder.addSelectors(Selectors.nameEquals("Album"));
				parentfolder.addSelector(Selectors.libraryEquals(wcmspace
						.getCurrentDocumentLibrary()));
				ResultIterator parentIt = queryService.execute(parentfolder);

				if (parentIt.hasNext()) {
					Folder ff = (Folder) parentIt.next();

					Folder folderdd = wcmspace.createFolder(ff.getId());
					folderdd.setTitle(albumName);
					folderdd.setName(getContentUnquitName());

					wcmspace.save(folderdd);
					map.put("state", "success");
					map.put("folder", folderdd);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put("state", "fail");
			e.printStackTrace();
		}
		return map;
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

	public Map<String, Object> getImage(HttpServletRequest request, Pager pager) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List list = new ArrayList();

		Workspace wcmspace = null;// 声明一个WCM工作空间
		Principal currentUser = request.getUserPrincipal();
		try {
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);

			DocumentLibrary lib = null;// web内容库
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
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

			if (parentIt.hasNext()) {
				Folder parentFolder = (Folder) parentIt.next();
				Query queryChild = queryService.createQuery();
				// 设定查询类型 SiteArea
				queryChild.addSelectors(Selectors.typeIn(DocumentTypes.Folder
						.getApiType()));
				queryChild.setSorts(Sorts
						.byDateCreated(SortDirection.DESCENDING));
				queryChild.addParentId(parentFolder.getId(),
						QueryDepth.CHILDREN);
				ResultIterator totalIterator = queryService.execute(queryChild);
				int totalRecord = totalIterator.getSize();

				// 总共多少页
				int totalPage = totalRecord > 0 ? ((totalRecord - 1)
						/ this.PAGE_SIZE + 1) : 0;

				boolean isadmin = getUserGroup(currentUser.getName()).indexOf(
						"album") > 0;

				resultMap.put("isadmin", isadmin);
				resultMap.put("totalPage", totalPage);
				PageIterator<ResultIterator> page = queryService.execute(
						queryChild, this.PAGE_SIZE, pager.getCurrentPage());
				if (page.hasNext()) {
					ResultIterator it = page.next();

					while (it.hasNext()) {
						Folder childFolder = (Folder) it.next();
						String albumName = childFolder.getTitle();
						System.out.println("albumName is :" + albumName);
						List imgList = getImageByFolder(wcmspace, queryService,
								childFolder, 1, 1);
						String key = childFolder.getId().getId() + "," + childFolder.getTitle().toString();
						Map<String, Object> map = new HashMap<String, Object>();
//						map.put(childFolder.getTitle(), imgList);
//						map.put("albumId", childFolder.getId().getId());
						map.put(key, imgList);
						list.add(map);
						// LibraryImageComponent image = (LibraryImageComponent)
						// it.next();
					}
				}
				resultMap.put("list", list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	public List getImageByFolder(QueryService queryService, Folder folder) {
		List list = new ArrayList();
		Query imageQuery = queryService.createQuery();
		imageQuery.addSelectors(Selectors
				.typeIn(DocumentTypes.LibraryImageComponent.getApiType()));
		imageQuery.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
		imageQuery.addParentId(folder.getId(), QueryDepth.CHILDREN);
		ResultIterator it;
		try {
			it = queryService.execute(imageQuery);
			while (it.hasNext()) {
				LibraryImageComponent image = (LibraryImageComponent) it.next();
				String imageUrl = image.getResourceURL();
				String imageName = image.getTitle();
				Img img = new Img();
				img.setImgName(imageName);
				img.setImgUrl(imageUrl);
				list.add(img);
				// LibraryImageComponent image = (LibraryImageComponent)
				// it.next();
			}
		} catch (QueryServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Folder getFolderByName(Workspace wcmspace, String folderName) {
		Folder returnFolder = null;
		QueryService queryService = wcmspace.getQueryService();
		Query folder = queryService.createQuery(Folder.class);
		folder.addSelectors(Selectors.typeIn(DocumentTypes.Folder.getApiType()));
		// 寻找相册的文件夹
		folder.addSelectors(Selectors.titleEquals(folderName));
		folder.addSelector(Selectors.libraryEquals(wcmspace
				.getCurrentDocumentLibrary()));
		ResultIterator it = null;
		try {
			it = queryService.execute(folder);
			if (it.hasNext())
				returnFolder = (Folder) it.next();
		} catch (QueryServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnFolder;

	}

	public List getImageByFolder(Workspace wcmspace, QueryService queryService,
			Folder folder, int pageSize, int currentPage) {
		List list = new ArrayList();
		Query imageQuery = queryService.createQuery();
		imageQuery.addSelectors(Selectors
				.typeIn(DocumentTypes.LibraryImageComponent.getApiType()));
		imageQuery.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
		imageQuery.addParentId(folder.getId(), QueryDepth.CHILDREN);

		List ll = new ArrayList();

		ll.add(wcmspace
				.findByName(DocumentTypes.WorkflowStage, "Publish Stage")
				.next());
		imageQuery.addSelectors(new Selector[] {
				WorkflowSelectors.stageIn(ll),
				WorkflowSelectors
						.statusEquals(WorkflowSelectors.Status.PUBLISHED) });
		try {
			PageIterator<ResultIterator> page = queryService.execute(
					imageQuery, pageSize, currentPage);
			if (page.hasNext()) {
				ResultIterator it = page.next();

				while (it.hasNext()) {
					LibraryImageComponent image = (LibraryImageComponent) it
							.next();
					Folder ffffFolder = wcmspace.getById(image.getParentId());
					String imageUrl = image.getResourceURL();
					String imageName = image.getTitle();
					String id = image.getId().getId();
					Image img = new Image();
					img.setImgName(imageName);
					img.setImgUrl(imageUrl);
					System.out.println("id is :" + id);
					img.setImgNumId(id);
					System.out.println("id is :" + id);
					list.add(img);
					// LibraryImageComponent image = (LibraryImageComponent)
					// it.next();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// 查看自己的有未提交的草稿的相册里面图片
	/**
	 * @author chenshoumao
	 * @param wcmspace
	 * @param queryService
	 * @param folder
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List getMyDraftImageByFolder(HttpServletRequest request,
			Workspace wcmspace, QueryService queryService, Folder folder,
			int pageSize, int currentPage,String stage) {
		List list = new ArrayList();
		Query imageQuery = queryService.createQuery();
		String workFlowStage = stage.equals("draft") ? "Draft":stage.equals("review") ?"Review":"Publish";
		// 作者的信息
		ResourceBundle resource = ResourceBundle.getBundle("url");
		String userDN = resource.getString("userDN");
		imageQuery.addSelectors(Selectors.authorsContain("uid="
				+ request.getUserPrincipal().getName() + "," + userDN));

		imageQuery.addSelectors(Selectors
				.typeIn(DocumentTypes.LibraryImageComponent.getApiType()));
		imageQuery.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
		imageQuery.addParentId(folder.getId(), QueryDepth.CHILDREN);

		List ll = new ArrayList();

		ll.add(wcmspace.findByName(DocumentTypes.WorkflowStage, workFlowStage + " Stage")
				.next());
		imageQuery
				.addSelectors(new Selector[] {
						WorkflowSelectors.stageIn(ll)});
		try {
			PageIterator<ResultIterator> page = queryService.execute(
					imageQuery, pageSize, currentPage);
			if (page.hasNext()) {
				ResultIterator it = page.next();

				while (it.hasNext()) {
					LibraryImageComponent image = (LibraryImageComponent) it
							.next();
					Folder ffffFolder = wcmspace.getById(image.getParentId());
					String imageUrl = image.getResourceURL();
					String imageName = image.getTitle();
					String id = image.getId().getId();
					Image img = new Image();
					img.setImgName(imageName);
					img.setImgUrl(imageUrl);
					System.out.println("id is :" + id);
					img.setImgNumId(id);
					System.out.println("id is :" + id);
					list.add(img);
					// LibraryImageComponent image = (LibraryImageComponent)
					// it.next();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Map<String, Object> getMyDraftImageByFolder(
			HttpServletRequest request, String albumId,String stage) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Workspace wcmspace = null;// 声明一个WCM工作空间
		Principal currentUser = request.getUserPrincipal();
		List list = new ArrayList();
		try {
			wcmspace = WCMUtils.getWCMWorkspace(currentUser);

			DocumentLibrary lib = null;// web内容库
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
			lib = WCMUtils.getWCMLibrary(libName, currentUser);
			wcmspace.setCurrentDocumentLibrary(lib);
			QueryService queryService = wcmspace.getQueryService();

			DocumentId documentId = wcmspace.createDocumentId(albumId);
			// Folder folder = getFolderByName(wcmspace, albumName);
			Folder folder = wcmspace.getById(documentId);
			list = getMyDraftImageByFolder(request, wcmspace, queryService,
					folder, MAX_NUM, 1,stage);
			resultMap.put("list", list);
//			boolean isadmin = getUserGroup(currentUser.getName()).indexOf(
//					"album") > 0;
			resultMap.put("isadmin", stage.equals("draft")? true:false);
			resultMap.put("albumName", folder.getTitle());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	public void downLoadImage(HttpServletRequest request,
			HttpServletResponse response, String id) {
		// TODO Auto-generated method stub
		try {
			Workspace wcmspace = WCM_API.getRepository().getWorkspace();
			Principal currentUser = request.getUserPrincipal();
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary lib = WCMUtils.getWCMLibrary(libName, currentUser);
			wcmspace.setCurrentDocumentLibrary(lib);

			DocumentId doId = wcmspace.createDocumentId(id);
			Document document = wcmspace.getById(doId);
			if (document instanceof LibraryImageComponent) {
				LibraryImageComponent image = (LibraryImageComponent) document;
				InputStream reader = null;
				response.setHeader(
						"content-disposition",
						"attachment;fileName="
								+ URLEncoder.encode(image.getTitle(), "UTF-8"));
				OutputStream out = null;
				byte[] bytes = new byte[1024];
				int len = 0;
				reader = image.getImageStream();
				out = response.getOutputStream();
				while ((len = reader.read(bytes)) != -1) {
					out.write(bytes, 0, len);
				}
				if (reader != null) {
					reader.close();
				}
				if (out != null)
					out.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map<String, Object> deleteImage(HttpServletRequest request2,
			HttpServletResponse response, String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		try {
			Workspace wcmspace = WCM_API.getRepository().getWorkspace();
			Principal currentUser = request.getUserPrincipal();
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary lib = WCMUtils.getWCMLibrary(libName, currentUser);
			wcmspace.setCurrentDocumentLibrary(lib);

			DocumentId doId = wcmspace.createDocumentId(id);
			Document document = wcmspace.getById(doId);
			if (document instanceof LibraryImageComponent) {
				LibraryImageComponent image = (LibraryImageComponent) document;
				wcmspace.delete(image.getId());
				map.put("result", true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", false);
		}
		return map;
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

	public Map<String, Object> commitDraftImageByAlbum(
			HttpServletRequest request2, String albumId,String stage) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		 
		Principal currentUser = request.getUserPrincipal();
		Workspace workspace;
		String workFlowStage = stage.equals("draft") ? "Draft":"Review";
		System.out.println(workFlowStage);
		try {
			workspace = WCM_API.getRepository().getWorkspace(currentUser);
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary lib = workspace.getDocumentLibrary(libName);
			workspace.setCurrentDocumentLibrary(lib);
			// 作者的信息
			ResourceBundle resource = ResourceBundle.getBundle("url");
			String userDN = resource.getString("userDN");
			QueryService queryService = workspace.getQueryService();
			Query imageQuery = queryService.createQuery();
			//如果是起初草稿状态 就匹配作者
			if(workFlowStage.equals("Draft"))
				imageQuery.addSelectors(Selectors.authorsContain("uid="
					+ request.getUserPrincipal().getName() + "," + userDN));

			imageQuery.addSelectors(Selectors
					.typeIn(DocumentTypes.LibraryImageComponent.getApiType()));
			imageQuery.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
			DocumentId documentId = workspace.createDocumentId(albumId); 

			imageQuery.addParentId(documentId, QueryDepth.CHILDREN);

			List ll = new ArrayList();

			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					workFlowStage + " Stage").next());
			imageQuery.addSelectors(new Selector[] {
					WorkflowSelectors.stageIn(ll) });

			ResultIterator it = queryService.execute(imageQuery);
			int successCount = 0;
			int failCount = 0;
			ArrayList failList = new ArrayList();
			System.out.println(it.getSize());
			while (it.hasNext()) {
				LibraryImageComponent image = null;
				try {
					image = (LibraryImageComponent) it.next();
					//下一个工作阶段   草稿 --> 待审核， 待审核 --> 发布
					image.nextWorkflowStage(true, true,null); 
					successCount++;
				} catch (Exception e) {
					// TODO: handle exception
					failCount++;
					failList.add(image.getTitle());
				} 
			}
			
			if(successCount > 0 && failCount == 0){
				map.put("state", true);
			}else{
				map.put("state", "NoAllSuccess");
				map.put("failList", failList);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch blocke
			System.out.println(e);
			e.printStackTrace();
		}

		return map;
	}

	public Map<String, Object> moveDraftImageToPrevWF(
			HttpServletRequest request2, String albumId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		 
		Principal currentUser = request.getUserPrincipal();
		Workspace workspace;
		try {
			workspace = WCM_API.getRepository().getWorkspace(currentUser);
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("property");
			String libName = resourceBundle.getString("rootLibrary");
			DocumentLibrary lib = workspace.getDocumentLibrary(libName);
			workspace.setCurrentDocumentLibrary(lib);
			 
			QueryService queryService = workspace.getQueryService();
			Query imageQuery = queryService.createQuery();
			 
			imageQuery.addSelectors(Selectors
					.typeIn(DocumentTypes.LibraryImageComponent.getApiType()));
			imageQuery.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
			DocumentId documentId = workspace.createDocumentId(albumId); 

			imageQuery.addParentId(documentId, QueryDepth.CHILDREN);

			List ll = new ArrayList();

			ll.add(workspace.findByName(DocumentTypes.WorkflowStage,
					"Review Stage").next());
			imageQuery.addSelectors(new Selector[] {
					WorkflowSelectors.stageIn(ll) });

			ResultIterator it = queryService.execute(imageQuery);
			int successCount = 0;
			int failCount = 0;
			ArrayList failList = new ArrayList();
			while (it.hasNext()) {
				LibraryImageComponent image = null;
				try {
					image = (LibraryImageComponent) it.next();
					//滚回上一个工作阶段   待审核 -->  草稿
					image.previousWorkflowStage(true,null); 
					successCount++;
				} catch (Exception e) {
					// TODO: handle exception
					failCount++;
					failList.add(image.getTitle());
				} 
			}
			
			if(successCount > 0 && failCount == 0){
				map.put("state", true);
			}else{
				map.put("state", "NoAllSuccess");
				map.put("failList", failList);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

}
