package ldap.dao.impl;

import java.util.Hashtable;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;

import ldap.util.Config;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;


 

@Repository
public class GroupDao {
 
	static Config config= Config.getInstance();
	static DirContext ctx = null; 
	private static String userDn = "DC=CMZD,DC=COM"; 
	
	
	static  {
		 String account=config.getConfigValue("binduser");	
		 String password=config.getConfigValue("bindpwd");
		 Hashtable env = new Hashtable();
//		 该常量保存用来指定要使用的初始上下文工厂的环境属性名称�?该属性的值应该是将创建初始上下文的工厂类的完全限定类名称�?
		 env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//		 该常量保存用来指定要使用的服务提供�?配置信息的环境属性名称�?该属性的值应该包含一�?URL 字符串（例如 "ldap://somehost:389"）�?
		 env.put(Context.PROVIDER_URL, config.getConfigValue("LdapUrl") );	 
//		 该常量保存用来指定将使用的安全级别的环境属�?名称。该属�?的�?是下列字符串之一�?none"�?simple" �?"strong"�?
		 env.put(Context.SECURITY_AUTHENTICATION, "simple");
//		 该常量保存用来指定用于验证服务调用�?主体身份的环境属性名称�?主体格式取决于验证方案�?		 
		 env.put(Context.SECURITY_PRINCIPAL,  account);
//		 该常量保存用来指定用于验证服务调用�?主体证书的环境属性名称�?该属性�?取决于验证方�?
		 env.put(Context.SECURITY_CREDENTIALS, password);
		 
		 try{ 
			 ctx = new InitialDirContext(env);
			 //锟斤拷始锟斤拷锟斤拷锟斤拷锟斤拷
			 System.out.println(" ldap");
			 //锟斤拷锟斤拷锟斤拷愿某锟斤拷斐ｏ拷壮锟�锟�
		 }catch(javax.naming.AuthenticationException e){
			 System.out.println("ldap fail");
			 e.printStackTrace();
		 }catch(Exception e){
			 System.out.println("ldap fail"+e);
		 } 
		 }

	
	public void addGroup(Attributes attrs) throws NamingException {
		Attribute objectclass = new BasicAttribute("objectclass");
		objectclass.add("top");
		objectclass.add("groupOfNames");
		objectclass.add("ePerson");
	    objectclass.add("person");
		
		attrs.put(objectclass);
		attrs.put("member", "");
		String cn = attrs.get("cn").toString().substring(attrs.get("cn").toString().lastIndexOf(":")+1).trim();
		System.out.println(cn);
		this.userDn = "cn="+cn+","+ userDn;
		Context result = ctx.createSubcontext(userDn, attrs);
		result.close();
		ctx.close();
	}
	
	public void deleteGroup(String cn) throws NamingException{
		String _cn = "cn="+cn+","+this.userDn;
		ctx.destroySubcontext(_cn);
	}
	
	public NamingEnumeration findAll()throws NamingException {
		SearchControls searchCtls = new SearchControls();  
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);  
		String searchFilter = "(ou=*)";
		//String searchFilter = "cn=" + "wpsadmins";
		//csm add code
		 // String returnedAtts[] = {"member"};
       //   searchCtls.setReturningAttributes(returnedAtts);

		
		NamingEnumeration answer = ctx.search(this.userDn, searchFilter,searchCtls);  
		return answer;
		
	}
	
	
}
