package ldap.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import ldap.dao.UserDao;
import ldap.entity.PasswordAttributeMapper;
import ldap.entity.PersonAttributeMapper;
import ldap.entity.ReturnUser;
import ldap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapOperationsCallback;
import org.springframework.ldap.core.support.SingleContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl
  implements UserDao, Serializable
{

  @Resource
  private LdapTemplate ldapTemplate;

  @Autowired(required=true)
  @Qualifier("contextSource")
  private ContextSource contextSource;
  private User user;
  private Map<String, Object> userMap = new HashMap();

  public List<User> getPersonList(User user) {
    List list = new ArrayList();

    AndFilter andFilter = new AndFilter();
    andFilter.and(new EqualsFilter("objectclass", "person"));
    if ((user.getUid() != null) && 
      (user.getUid().length() > 0)) {
      andFilter.and(new EqualsFilter("uid", user.getUid()));
    }

    list = this.ldapTemplate.search("cn=users1", andFilter.encode(), 
      new PersonAttributeMapper());

    return list;
  }

  public boolean updateUser(User vo)
  {
    System.out.println("121211111111111111111111111111111111111212111111111111111111111111111111111112121111111111111111111111111111111111");
    try {
      this.ldapTemplate.modifyAttributes("uid=" + vo.getUid().trim() + ",cn=users1", new ModificationItem[] { 
        new ModificationItem(2, new BasicAttribute("mobile", vo.getMobile())), 
        new ModificationItem(2, new BasicAttribute("postalCode", vo.getPostalCode())), 
        new ModificationItem(2, new BasicAttribute("address", vo.getAddress())), 
        new ModificationItem(2, new BasicAttribute("homePhone", vo.getHomePhone())), 
        new ModificationItem(2, new BasicAttribute("mail", vo.getMail())), 
        new ModificationItem(2, new BasicAttribute("staffNumber", vo.getStaffNumber())), 
        new ModificationItem(2, new BasicAttribute("duty", vo.getDuty())), 
        new ModificationItem(2, new BasicAttribute("department", vo.getDepartment())), 
        new ModificationItem(2, new BasicAttribute("title", vo.getTitle())), 
        new ModificationItem(2, new BasicAttribute("imageUrl", vo.getImageUrl())) });

      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
    }return false;
  }

  public Map<String, Object> search(User user, int curPage, int pageSize)
  {
    List list = new ArrayList();
    System.out.println(user.getUid());
    System.out.println(curPage);
    System.out.println(pageSize);
    this.user = user;
    System.out.println(this.user.getUid());

    AndFilter andFilter = new AndFilter();
    andFilter.and(new EqualsFilter("objectclass", "person"));

    final SearchControls searchControls = new SearchControls();
    searchControls.setSearchScope(2);

    final PagedResultsDirContextProcessor processor = 
      new PagedResultsDirContextProcessor(pageSize);

    SingleContextSource.doWithSingleContext(this.contextSource, new LdapOperationsCallback()
    {
      public List<User> doWithLdapOperations(LdapOperations operations)
      {
        Map map = new HashMap();
        List result = new LinkedList();
        int i = 1;
        do { UserDaoImpl.this.contextSource.equals("");
          AndFilter andFilter = new AndFilter();
          andFilter.and(new EqualsFilter("objectclass", "person"));
          if ((UserDaoImpl.this.user.getTitle() != null) && 
            (UserDaoImpl.this.user.getTitle().length() > 0)) {
            andFilter.and(new WhitespaceWildcardsFilter("title", UserDaoImpl.this.user.getTitle()));
          }
          if ((UserDaoImpl.this.user.getMobile() != null) && 
            (UserDaoImpl.this.user.getMobile().length() > 0)) {
            andFilter.and(new WhitespaceWildcardsFilter("mobile", UserDaoImpl.this.user.getMobile()));
          }

          if ((UserDaoImpl.this.user.getUid() != null) && 
            (UserDaoImpl.this.user.getUid().length() > 0)) {
            System.out.println(UserDaoImpl.this.user.getUid() + "ddddddd");
            andFilter.and(new WhitespaceWildcardsFilter("uid", UserDaoImpl.this.user.getUid()));
          }
          if ((UserDaoImpl.this.user.getMail() != null) && 
            (UserDaoImpl.this.user.getMail().length() > 0)) {
            andFilter.and(new WhitespaceWildcardsFilter("mail", UserDaoImpl.this.user.getMail()));
          }

          List oneResult = operations.search("cn=users1", andFilter.toString(), searchControls, new PersonAttributeMapper(), processor);
          map.put(i, oneResult);
          i++; }
        while (processor.hasMore());

        UserDaoImpl.this.userMap = map;
        return null;
      }
    });
    return this.userMap;
  }

  public boolean updatePassword(ReturnUser user)
  {
    String curPassword = user.getCurPassword();
    String confirmPassword = user.getConfirmPassword();
    String newPassword = user.getNewPassword();
    String password = "";

    List list = new ArrayList();

    AndFilter andFilter = new AndFilter();
    andFilter.and(new EqualsFilter("objectclass", "person"));
    if ((user.getLoginName() != null) && 
      (user.getLoginName().length() > 0)) {
      andFilter.and(new EqualsFilter("uid", user.getLoginName()));
    }

    list = this.ldapTemplate.search("cn=users1", andFilter.encode(), 
      new PasswordAttributeMapper());
    if (list.size() > 0) {
      password = ((ReturnUser)list.get(0)).getPassword();
    }
    try
    {
      if (!password.equals(curPassword))
        return false;
      if (!newPassword.equals(confirmPassword))
        return false;
      this.ldapTemplate.modifyAttributes("uid=" + user.getLoginName() + ",cn=users1", new ModificationItem[] { 
        new ModificationItem(2, 
        new BasicAttribute("userPassword", newPassword)) });

      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
    }return false;
  }

  public List search(final int curPage, int pageSize)
  {
    final SearchControls searchControls = new SearchControls();
    searchControls.setSearchScope(2);

    final PagedResultsDirContextProcessor processor = 
      new PagedResultsDirContextProcessor(pageSize);

    return (List)SingleContextSource.doWithSingleContext(this.contextSource, new LdapOperationsCallback()
    {
      public List<User> doWithLdapOperations(LdapOperations operations)
      {
        Map map = new HashMap();
        List result = new LinkedList();
        int i = 1;
        do {
          AndFilter andFilter = new AndFilter();
          andFilter.and(new EqualsFilter("objectclass", "person"));
          andFilter.and(new WhitespaceWildcardsFilter("uid", "test"));
          List oneResult = operations.search("cn=users", andFilter.toString(), searchControls, new PersonAttributeMapper(), processor);
          map.put(i, oneResult);
          i++;
        }while (processor.hasMore());
        result = (List)map.get(curPage);
        return result;
      }
    });
  }

  public String doGetMethod(String updataUrl)
  {
    String result = "";
    BufferedReader in = null;

    System.out.println(updataUrl);
    try
    {
      URLConnection con = new URL(updataUrl).openConnection();

      System.out.println("dataurl-----:" + updataUrl);

      con.setRequestProperty("accept", "*/*");
      con.setRequestProperty("connection", "Keep-Alive");
      con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

      System.out.println(11111);
      con.connect();
      System.out.println(22222);
      in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String line;
      while ((line = in.readLine()) != null)
      { 
        result = result +  line;
      }
      System.out.println(result);
      return result;
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}