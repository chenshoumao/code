package ldap.dao.impl;


import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.PagedResultsControl;
import javax.naming.ldap.PagedResultsResponseControl;
 















import ldap.dao.UserDao;
import ldap.entity.PersonAttributeMapper;
import ldap.entity.User;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.control.PagedResultsCookie;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.control.PagedResultsRequestControl;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.AttributesMapperCallbackHandler;
import org.springframework.ldap.core.CollectingNameClassPairCallbackHandler;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.NameAwareAttributes;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.core.support.LdapOperationsCallback;
import org.springframework.ldap.core.support.SingleContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

 

 


 


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import antlr.StringUtils;
 
 

@Repository
public class UserDaoImpl implements UserDao,Serializable{
	@Resource
    private LdapTemplate ldapTemplate; 
	
	@Autowired(required = true)
	@Qualifier(value = "contextSource")
	private ContextSource contextSource;
	
	private User user;
	private Map<String, Object> userMap = new HashMap<String, Object>();
	 
	public List<User> getPersonList(User user) { 
        List<User> list = new ArrayList<User>();  
        //查询过滤条件  
        AndFilter andFilter = new AndFilter();  
        andFilter.and(new EqualsFilter("objectclass", "person"));  
          
          
        if (user.getUid() != null  
                && user.getUid().length() > 0) {  
            andFilter.and(new EqualsFilter("uid", user.getUid()));  
        }  
   
        
        //search是根据过滤条件进行查询，第一个参数是父节点的dn，可以为空，不为空时查询效率更高  
        list = ldapTemplate.search("cn=users1", andFilter.encode(),  
                new PersonAttributeMapper());  
//        list = this.ldapTemplate.search("cn=users", andFilter.encode(),
//              SearchControls.SUBTREE_SCOPE, new AbstractContextMapper() {
//          @Override
//          protected Attributes doMapFromContext(DirContextOperations adapter) {
//              return adapter.getAttributes();
//          }
//      });
        return list;  
    }
	
//	public List<User> getAllUsers() {
//		SearchControls controls = new SearchControls();
//		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//	      return ldapTemplate.search(query().where("objectclass").is("top"),  new PersonAttributeMapper());
//	}
//	//有bug
//	public List<User> findAllUsers(final int page, int rows)
//    {
//
//        final SearchControls searchControls = new SearchControls();
//		  searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//
//		  final PagedResultsDirContextProcessor processor =
//		        new PagedResultsDirContextProcessor(rows);
//
//		  return SingleContextSource.doWithSingleContext(    contextSource, new LdapOperationsCallback<List<User>>() {
//
//		      @Override
//		      public List<User> doWithLdapOperations(LdapOperations operations) {
//			   Map<String, Object> map = new HashMap<String, Object>();
//		        List<User> result = new LinkedList<User>();
//                int i=1;
//		        do {
//		          List<User> oneResult = operations.search("cn=users", "(&(objectclass=person))", searchControls, new PersonAttributeMapper() , processor);
//		          map.put(""+i,oneResult);
//		          i++;
//		        } while(processor.hasMore());
//		        result=(List<User>) map.get(""+ page);
//		        return result ;
//		      }
//		  });
//		 
//       
//    }  
	
	
//	public boolean addUser(User vo) {
//	    try {
//	        // 基类设置
//		BasicAttribute ocattr = new BasicAttribute("objectClass");
//		ocattr.add("top");
//		ocattr.add("person");
////		ocattr.add("uidObject");
//		ocattr.add("inetOrgPerson");
//		ocattr.add("organizationalPerson");
//		// 用户属性
//		Attributes attrs = new BasicAttributes();
//		attrs.put(ocattr);
//		attrs.put("cn",vo.getCn());
//		attrs.put("sn",vo.getSn());
////		attrs.put("displayName", StringUtils.trimToEmpty(vo.getRealname()));
////		attrs.put("mail", StringUtils.trimToEmpty(vo.getEmail())); 
//		attrs.put("description",vo.getDescription());
//	 
//		ldapTemplate.bind("uid=" + vo.getCn().trim(), null, attrs);
//		return true;
//	    } catch (Exception ex) {
//		ex.printStackTrace();
//		return false;
//	    }
//	}
	
	public boolean updateUser(User vo) {
		System.out.println("121211111111111111111111111111111111111212111111111111111111111111111111111112121111111111111111111111111111111111");
	    try {
		ldapTemplate.modifyAttributes("uid=" + vo.getUid().trim()+",cn=users1", new ModificationItem[] {
		  //new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("cn", vo.getCn())),
		  //new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("sn", vo.getSn())),
			new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mobile", vo.getMobile())),
			new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("postalCode", vo.getPostalCode())),
			new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("address", vo.getAddress())),
			new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("homePhone", vo.getHomePhone())),
			new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mail", vo.getMail())),
			new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("staffNumber", vo.getStaffNumber())),
			new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("duty", vo.getDuty())),
			new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("department", vo.getDepartment())),
			 	    //new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("address", vo.getAddress().trim())),
		    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("title", vo.getTitle())),
		    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("imageUrl", vo.getImageUrl()))
		    
		});
		return true;
	    } catch (Exception ex) {
		ex.printStackTrace();
		return false;
	    }
	}
//	
//	public boolean deleteUser(String username) {
//	    try {
//		ldapTemplate.unbind("uid=" + username.trim());
//		return true;
//	    } catch (Exception ex) {
//		ex.printStackTrace();
//		return false;
//	    }
//	}
//
//	
//
//	  public User getPersonDetail(String cn) {  
//	        User ua = (User)   
//			    ldapTemplate.lookup( cn,  
//			            new PersonAttributeMapper());  
//			return ua;  
//	    } 
	
	 
	@Override
	public Map<String, Object> search(User user,final int curPage,int pageSize) {
		// TODO Auto-generated method stub
		 List<User> list = new ArrayList<User>();  
		 System.out.println(user.getUid());
		 System.out.println(curPage);
		 System.out.println(pageSize);
		 this.user = user;
		 System.out.println(this.user.getUid());
	        //查询过滤条件  
	        AndFilter andFilter = new AndFilter();  
	        andFilter.and(new EqualsFilter("objectclass", "person"));   
	        
	        final SearchControls searchControls = new SearchControls();
			  searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			  final PagedResultsDirContextProcessor processor =
			        new PagedResultsDirContextProcessor(pageSize);

			   SingleContextSource.doWithSingleContext(    contextSource, new LdapOperationsCallback<List<User>>() {

			      @Override
			      public List<User> doWithLdapOperations(LdapOperations operations) {
				   Map<String, Object> map = new HashMap<String, Object>();
			        List<User> result = new LinkedList<User>();
	              int i=1;
			        do {contextSource.equals("");
			        	 AndFilter andFilter = new AndFilter();  
			             andFilter.and(new EqualsFilter("objectclass", "person"));
			             if (UserDaoImpl.this.user.getTitle() != null  
			 	                && UserDaoImpl.this.user.getTitle().length() > 0) {  
			 	            andFilter.and(new WhitespaceWildcardsFilter("title",UserDaoImpl.this.user.getTitle()));  
			 	        }  
			 	        if (UserDaoImpl.this.user.getMobile() != null  
			 	                && UserDaoImpl.this.user.getMobile().length() > 0) {  
			 	            andFilter.and(new WhitespaceWildcardsFilter("mobile",UserDaoImpl.this.user.getMobile()));  
			 	        }  
			 	  
			 	        if (UserDaoImpl.this.user.getUid() != null  
			 	                && UserDaoImpl.this.user.getUid().length() > 0) { 
			 	        	System.out.println(UserDaoImpl.this.user.getUid() + "ddddddd");
			 	            andFilter.and(new WhitespaceWildcardsFilter("uid", UserDaoImpl.this.user.getUid()));  
			 	        } 
			 	        if (UserDaoImpl.this.user.getMail() != null  
			 	                && UserDaoImpl.this.user.getMail().length() > 0) {  
			 	            andFilter.and(new WhitespaceWildcardsFilter("mail", UserDaoImpl.this.user.getMail()));  
			 	        } 
			              
			          List<User> oneResult = operations.search("cn=users1", andFilter.toString(), searchControls, new PersonAttributeMapper() , processor);
			          map.put(""+i,oneResult);
			          i++;
			        } while(processor.hasMore());
			       // result=(List<User>) map.get(""+ curPage);
			        
			       userMap = map;
			       return null;
			      }
			  }); 
			  
			  return userMap;
	}

//	  //获取用户uid ；如 uid=test1
//	  public Name getPersonDn(String userId) throws Exception {
//	        AndFilter andFilter = new AndFilter();
//	        andFilter.and(new EqualsFilter("uid", userId));
//			List<Name> result = this.ldapTemplate.search("", andFilter.encode(),
//	                SearchControls.SUBTREE_SCOPE, new AbstractContextMapper() {
//	                    @Override
//	                    protected Name doMapFromContext(DirContextOperations adapter) {
//	                        return adapter.getDn();
//	                    }
//	                });
//	        if (null == result || result.size() != 1) {
//	            throw new Exception();
//	        } else {
//	        	Name str = result.get(0);
//	            return result.get(0);
//	        }
//
//	    }

	
//	@Override
//	public User queryPerson(String userId) {
//		 try { 
//			return (User) ldapTemplate.lookup(getPersonDn(userId),new PersonAttributeMapper());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//
//	}
//	
//	public void findFilter(){
//		SearchControls searchCtls = new SearchControls();  
//		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);  
//		String searchFilter = "(ou=*)";
//		this.ldapTemplate.find("cn=users1,DC=CMZD,DC=COM", searchFilter, searchCtls, clazz);
//	}
	 
	
//	public List<User> searchUser(String uid,String title,String mobile) {
//		List<User> listUser = new ArrayList<User>();
//		 AndFilter andFilter = new AndFilter();
//	      andFilter.and(new WhitespaceWildcardsFilter("uid", uid));
//	      andFilter.and(new WhitespaceWildcardsFilter("title", title));
//	      andFilter.and(new WhitespaceWildcardsFilter("mobile", mobile));
//		List list = this.ldapTemplate.search("", andFilter.encode(),
//                SearchControls.SUBTREE_SCOPE, new AbstractContextMapper() {
//            @Override
//            protected Attributes doMapFromContext(DirContextOperations adapter) {
//                return adapter.getAttributes();
//            }
//        });
//		try {
//			for(int i = 0;i < list.size();i++){
//			User user = new User();
//				
//			NameAwareAttributes nameAttr = (NameAwareAttributes) list.get(i);
//			 
//			String uidString = nameAttr.get("uid") == null?"":nameAttr.get("uid").toString();
//			if(uidString == ""){
//				user.setUid("");
//			}
//			else
//				user.setUid(uidString.substring(uidString.indexOf("[")+1, uidString.length() -1));
//			
//			String cn = nameAttr.get("cn") == null?"":nameAttr.get("cn").toString();
//			if(cn == ""){
//				user.setCn("");
//			}
//			else
//				user.setCn(cn.substring(cn.indexOf("[")+1, cn.length() -1));
//			
//			String snStr = nameAttr.get("sn") == null?"":nameAttr.get("sn").toString();
//			if(snStr == ""){
//				user.setSn("");
//			}
//			else
//				user.setSn(snStr.substring(snStr.indexOf("[")+1, snStr.length() -1));
//			
//			
//			
//			 
//			String mobileStr = nameAttr.get("mobile") == null ?"":nameAttr.get("mobile").toString(); 
//			if(mobileStr == ""){
//				user.setMobile("");
//			}
//			else
//				user.setUid(mobileStr.substring(mobileStr.indexOf("[")+1, mobileStr.length() -1));
//			 
//			
//			String mail = nameAttr.get("mail") == null ?"":nameAttr.get("mail").toString(); 
//			if(mail == ""){
//				user.setMail("");
//			}
//			else
//				user.setMail(mail.substring(mail.indexOf("[")+1, mail.length() -1));
//			 
//			
//			String department = nameAttr.get("department") == null ?"":nameAttr.get("department").toString(); 
//			if(department == ""){
//				user.setDepartment("");
//			}
//			else
//				user.setDepartment(department.substring(department.indexOf("[")+1, department.length() -1));
//			
//			String titleStr = nameAttr.get("title") == null ?"":nameAttr.get("title").toString(); 
//			if(titleStr == ""){
//				user.setTitle("");
//			}
//			else
//				user.setTitle(titleStr.substring(titleStr.indexOf("[")+1, titleStr.length() -1));
//			 
//			
//			listUser.add(user);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e);
//		}
//		
////		 
//		return listUser;
////	    
//	}
//
//	@Override
//	public void save(CSMUser csmUser) {
//		// TODO Auto-generated method stub
//		this.dao.save(csmUser);
//	}

//	public void find(String str) {
//		// TODO Auto-generated method stub
//		List list = this.hdao.find(str);
//		System.out.println(list.size());
//	}

//	@Override
//	public boolean updatePassword(String eruid) {
//		// TODO Auto-generated method stub
//		String erpassword = "SHA-256:aGl1MmE5dHpudmZi:g1kdw8kJf3hcET5h5msuz82SGC7elPpTkg2877AM8Gs=";
//		try {
//			ldapTemplate.modifyAttributes("eruid=" + eruid, new ModificationItem[] {
//			  new ModificationItem(DirContext.REPLACE_ATTRIBUTE, 
//					  new BasicAttribute("erpassword", erpassword)),
//			});
//			return true;
//		    } catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		    }
//		 
//	}
	public  List search(final int curPage, int pageSize)  
    {  
       
        final SearchControls searchControls = new SearchControls();
		  searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		  final PagedResultsDirContextProcessor processor =
		        new PagedResultsDirContextProcessor(pageSize);

		  return SingleContextSource.doWithSingleContext(    contextSource, new LdapOperationsCallback<List<User>>() {

		      @Override
		      public List<User> doWithLdapOperations(LdapOperations operations) {
			   Map<String, Object> map = new HashMap<String, Object>();
		        List<User> result = new LinkedList<User>();
              int i=1;
		        do {
		        	 AndFilter andFilter = new AndFilter();  
		             andFilter.and(new EqualsFilter("objectclass", "person"));   
		             andFilter.and(new WhitespaceWildcardsFilter("uid", "test"));  
		          List<User> oneResult = operations.search("cn=users", andFilter.toString(), searchControls, new PersonAttributeMapper() , processor);
		          map.put(""+i,oneResult);
		          i++;
		        } while(processor.hasMore());
		        result=(List<User>) map.get(""+ curPage);
		        return result ;
		      }
		  });
         
    }
	
	@Override
	public boolean forgetPassword(User user) {
		// TODO Auto-generated method stub
		String url = "http://10.161.2.68:9080/itim_expi/ForgotPasswordServlet?j_username=" + user.getLoginName();
		try {
			String result = doGetMethod(url);
			if(result != "" && result != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		 
	}
	

	@Override
	public boolean updatePassword(User user) {
		// TODO Auto-generated method stub
		String url = "http://10.161.2.68:9080/itim_expi/ChangePasswordServlet?"
				+"logonID="
				+ user.getLoginName()
				+"&currentPassword="
				+ user.getCurPassord()
				+"&newPassword="
				+ user.getNewPassord()
				+"&confirmNewPassword="
				+ user.getConfirmPassword()
				+"&action=useSelfPasswordManager";
		try {
			String result = doGetMethod(url);
			if(result != "" && result != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	
	public String doGetMethod(String updataUrl){
		String result = "";
		BufferedReader in = null;  
		
		System.out.println(updataUrl);
		URLConnection con;
		
		try {
			con = new URL(updataUrl).openConnection();

			System.out.println("dataurl-----:" + updataUrl);

			 con.setRequestProperty("accept", "*/*");  
	            con.setRequestProperty("connection", "Keep-Alive");  
	            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
	            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
	            // 发送POST请求必须设置如下两行  
	          //  con.setDoOutput(true);  
	          //  con.setDoInput(true);  
			System.out.println(11111);
			con.connect();
			System.out.println(22222);
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
			return result;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
      
	}

	
	 
 
	 
}
