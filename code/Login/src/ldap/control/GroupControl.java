package ldap.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import ldap.dao.impl.GroupDao;
import ldap.entity.Tree;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/GroupLdap")
public class GroupControl {
	@Autowired
	private GroupDao dao;

	@RequestMapping("/showsAllGroup.action")
	@ResponseBody
	public Map<String, List> testFindAll() {
		Map<String, List> map = new HashMap<String, List>();

		try {
			NamingEnumeration name = dao.findAll();
			// List<Tree> list = print(name);
			 List<Tree> list = printOrganization(name);
			map.put("tree", list);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/testAddGroup.action")
	public void testAddGroup() {
		Attributes attrs = new BasicAttributes();
		attrs.put("cn", "testr");
		// attrs.put("sn", "测试");
		try {
			dao.addGroup(attrs);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/testDeleteGroup.action")
	public void testDeleteGroup() {

		try {
			dao.deleteGroup("t1");
			dao.deleteGroup("t2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/showsAllGroup2.action")
	@ResponseBody
	public Map<String, List> showsAllGroup2() {
		Map<String, List> map = new HashMap<String, List>();
		Tree tree0 = new Tree(1, 0, "父节点1 - 展开", true, null);

		Tree tree = new Tree();
		tree.setId(11);
		tree.setpId(1);
		tree.setName("tree");
		tree.setOpen(true);
		tree.setIsParent(true);

		// Tree tree1 = new Tree();
		// tree1.setId(101);
		// tree1.setpId(11);
		// tree1.setName("tree1");
		// tree1.setOpen(null);
		// tree1.setIsParent(null);

		Tree tree2 = new Tree(102, 11, "tree2", null, null);

		List<Tree> list = new ArrayList<Tree>();
		list.add(tree0);
		list.add(tree);
		list.add(new Tree(102, 11, "tree3", null, null));
		list.add(tree2);
		map.put("tree", list);
		return map;
	}

	private List printOrganization(NamingEnumeration answer)
			throws NamingException {
		List<Tree> list = new ArrayList<Tree>();
		int leavel1 = 1;
		int leavel2 = 9;
		int leavel3 = 99;
		int leavel4 = 999;
		int leavel5 = 9999;
		List containGroupName = new ArrayList();
		 
		int state = 0;
		int totalResults = 0;
		Tree tree0 = new Tree(1, 0, "开发区公司", true, null);
		list.add(tree0);
		while (answer.hasMoreElements()) {
			SearchResult sr = (SearchResult) answer.next();
			String groupName = sr.getName();
			if(groupName.contains("superman"))
				continue;
			boolean isCnExist = groupName.contains("cn");
			if (!isCnExist) {
				state = groupName.indexOf(",") > 0 ? 3:2;
			}
			Attributes Attrs = sr.getAttributes();
			if (Attrs != null) {
				if (!isCnExist){ 
					if (groupName != "" && groupName != null && groupName.length() > 0) {
						String name = state == 2 ? groupName.split("=")[1] : (groupName.split("=")[1]).split(",")[0];
						if(state == 2){
							list.add(new Tree(++leavel2, leavel1, name,
									null, true));							 
						}
						else if(state == 3){
							list.add(new Tree(++leavel3, leavel2, name,
									null, true));
							containGroupName.add(name);
						}
					}
				}
				else{
					System.out.println("111111");
					String[] str = groupName.split("="); 
					String parentName = str[2].split(",")[0];  
					int index = containGroupName.indexOf(parentName);
					String name = str[1].split(",")[0];
				//	System.out.println(name.split(",")[0]);
					// 11 12 13 14 10 + (index + 1)
					list.add(new Tree(++leavel4, 100 + index,
							name, null, true)); 
					try {

							for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {
								Attribute Attr = (Attribute) ne.next();
								// System.out.println(Attr.getID().toString());
								// 读取属性值

//								int childId = id * 10;
								for (NamingEnumeration e = Attr.getAll(); e.hasMore(); totalResults++) {

									String user = e.next().toString(); // 接受循环遍历读取的userPrincipalName用户属性
								  //  System.out.println(user + 7777);
									int userindex = user.indexOf("uid");
									String[] userstr = user.split(",");
									if (userstr.length > 1 && userindex != -1) {

										String[] uu = userstr[0].split("=");
										String username = uu[1];
									    System.out.println(username);
										list.add(new Tree(++leavel5, leavel4,
												username, null, null));
										// System.out.println(uu[1]);
									}
								}
							}
						} catch (NamingException e) {
							System.err.println("Throw Exception : " + e);
						}
					 
					
				}
				
				
				
				
			}
		}
		return list;
	}
	/*private List printOrganization(NamingEnumeration answer)
			throws NamingException {
		int totalResults = 0;
		// return "redirect:showBusMain.do?nextPage="+1;

		Map<String, List<Tree>> map = new HashedMap();
		List<Tree> list = new ArrayList<Tree>();

		List containGroupName = new ArrayList();
		Tree tree0 = new Tree(1, 0, "开发区公司", true, null);
		list.add(tree0);
		int id = 10, pId = 1;
		int nameid = 1000;
		
		int state = 0; //级别
		
		int round = 0;
		int time = round;
		int count = round;
	//	System.out.println(count++);
		if (answer == null || answer.equals(null)) {
			System.out.println("answer is null");
		} else {
			System.out.println("answer not null");
		}
		while (answer.hasMoreElements()) {
			SearchResult sr = (SearchResult) answer.next();
			String groupName = sr.getName();
			if(groupName.contains("superman"))
				continue;
			boolean isCnExist = groupName.contains("cn");
			if (!isCnExist) {
				state = groupName.indexOf(",") > 0 ? 2:1;
				if(state == 2){
					time = 0;
				}
				else{
					round++;// 1 2
					time = 1; 
					count = round - 1;//0 1
				}
			} 
			 
			Attributes Attrs = sr.getAttributes();
			if (Attrs != null) {
				if (!isCnExist){
					if (groupName != "" && groupName != null && groupName.length() > 0) {
						String name = state == 1 ? groupName.split("=")[1] : (groupName.split("=")[1]).split(",")[0];
						list.add(new Tree((int)Math.pow(10, state) + count, 10 * ( state -1 ) + time, name,
								null, true));
						if(state == 2){
							count++; 
						}
					}
				}
				
			}
			id++;
		}
		 while (answer.hasMoreElements()) {
			SearchResult sr = (SearchResult) answer.next();
			System.out.println("************************************************");

			String groupName = sr.getName();
			if(groupName.contains("superman"))
				continue;
			System.out.println(groupName+1);
			boolean isCnExist = groupName.contains("cn");
			if (!isCnExist) {
				System.out.println(groupName);
			} else {
				System.out.println("111111");
				String[] str = groupName.split("="); cn=Cost_Control_Dept,ou=functional_department
				String parentName = str[2];
				int index = containGroupName.indexOf(parentName);
				String name = str[1];
			//	System.out.println(name.split(",")[0]);
				// 11 12 13 14 10 + (index + 1)
				list.add(new Tree(nameid++, 11 + (index),
						name.split(",")[0], null, true));

			}
			Attributes Attrs = sr.getAttributes();

			if (Attrs != null) {
				if (!isCnExist){
					if (groupName != "" && groupName != null && groupName.length() > 0) {
						list.add(new Tree(id, pId, groupName.split("=")[1],
								null, true));
						containGroupName.add(groupName.split("=")[1]);
					}
				}
				else {
						try {

							for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {
								Attribute Attr = (Attribute) ne.next();
								// System.out.println(Attr.getID().toString());
								// 读取属性值

								int childId = id * 10;
								for (NamingEnumeration e = Attr.getAll(); e.hasMore(); totalResults++) {

									String user = e.next().toString(); // 接受循环遍历读取的userPrincipalName用户属性
								  //  System.out.println(user + 7777);
									int index = user.indexOf("uid");
									String[] str = user.split(",");
									if (str.length > 1 && index != -1) {

										String[] uu = str[0].split("=");
										String username = uu[1];
									    System.out.println(username);
										list.add(new Tree(++childId, nameid-1,
												username, null, null));
										// System.out.println(uu[1]);
									}
								}
							}
						} catch (NamingException e) {
							System.err.println("Throw Exception : " + e);
						}
					}
			}
			id++;
		}
		return list;
	}*/

	private List print(NamingEnumeration answer) throws NamingException {
		int totalResults = 0;
		// return "redirect:showBusMain.do?nextPage="+1;

		Map<String, List<Tree>> map = new HashedMap();
		List<Tree> list = new ArrayList<Tree>();
		Tree tree0 = new Tree(1, 0, "开发区公司", true, null);
		list.add(tree0);
		int id = 11, pId = 1;

		if (answer == null || answer.equals(null)) {
			System.out.println("answer is null");
		} else {
			System.out.println("answer not null");
		}
		while (answer.hasMoreElements()) {
			SearchResult sr = (SearchResult) answer.next();
			System.out
					.println("************************************************");
			System.out.println(sr.getName());
			String groupName = sr.getName();
			Attributes Attrs = sr.getAttributes();
			if (groupName != "" && groupName != null && groupName.length() > 0) {
				list.add(new Tree(id, pId, groupName.split("=")[1], null, true));
			}
			if (Attrs != null) {
				try {

					for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {
						Attribute Attr = (Attribute) ne.next();
						System.out.println(Attr.getID().toString() + 11);
						// 读取属性值

						int childId = id * 10;
						for (NamingEnumeration e = Attr.getAll(); e.hasMore(); totalResults++) {

							String user = e.next().toString(); // 接受循环遍历读取的userPrincipalName用户属性
							int index = user.indexOf("uid");
							String[] str = user.split(",");
							if (str.length > 1 && index != -1) {

								String[] uu = str[0].split("=");
								String username = uu[1];

								list.add(new Tree(++childId, id, username,
										null, null));
								// System.out.println(uu[1]);
							}
						}
					}
				} catch (NamingException e) {
					System.err.println("Throw Exception : " + e);
				}
			}
			id++;
		}
		return list;
	}
}
