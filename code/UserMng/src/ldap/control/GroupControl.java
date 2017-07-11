package ldap.control;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchResult;
import ldap.dao.impl.GroupDao;
import ldap.entity.Group;
import ldap.entity.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/GroupLdap"})
public class GroupControl
{

  @Autowired
  private GroupDao dao;

  @RequestMapping(value={"/showsAllGroup.action"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Map<String, List> testFindAll()
  {
    Map map = new HashMap();
    try
    {
      NamingEnumeration name = this.dao.findAll();

      List list = printOrganization(name);
      map.put("tree", list);
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
    return map;
  }
 /* @RequestMapping(value={"/queryGroup.action"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public List<Group> findGroup(String username) {
    List list = this.dao.queryGroup(username);
    return list;
  }*/

  @RequestMapping({"/testAddGroup.action"})
  public void testAddGroup()
  {
    Attributes attrs = new BasicAttributes();
    attrs.put("cn", "testr");
    try
    {
      this.dao.addGroup(attrs);
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping({"/testDeleteGroup.action"})
  public void testDeleteGroup()
  {
    try {
      this.dao.deleteGroup("t1");
      this.dao.deleteGroup("t2");
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  private List printOrganization(NamingEnumeration answer)
    throws NamingException
  {
    List list = new ArrayList();
    int leavel1 = 1;
    int leavel2 = 9;
    int leavel3 = 99;
    int leavel4 = 999;
    int leavel5 = 9999;
    List containGroupName = new ArrayList();

    int state = 0;
    int totalResults = 0;
    Tree tree0 = new Tree(1, 0, "开发区公司", null, null, null, null, null, Boolean.valueOf(true), null);
    list.add(tree0);
    while (answer.hasMoreElements()) {
      SearchResult sr = (SearchResult)answer.next();
      String groupName = sr.getName();
      if (!groupName.contains("superman"))
      {
        boolean isCnExist = groupName.contains("cn");
        if (!isCnExist) {
          state = groupName.indexOf(",") > 0 ? 3 : 2;
        }
        Attributes Attrs = sr.getAttributes();

        if (Attrs != null) {
          if (!isCnExist) {
            if ((groupName != "") && (groupName != null) && (groupName.length() > 0))
            {
              String name = getValueByAttrbute(Attrs, "name");
              String organizationLevel = getValueByAttrbute(Attrs, "organizationLevel");
              String address = getValueByAttrbute(Attrs, "address");
              String postalCode = getValueByAttrbute(Attrs, "postalCode");
              String mobile = getValueByAttrbute(Attrs, "mobile");
              String stateValue = getValueByAttrbute(Attrs, "state");
              if (state == 2) {
                list.add(new Tree(++leavel2, leavel1, name, organizationLevel, address, postalCode, mobile, stateValue, 
                  null, Boolean.valueOf(true)));
              }
              else if (state == 3) {
                list.add(new Tree(++leavel3, leavel2, name, organizationLevel, address, postalCode, mobile, stateValue, 
                  null, Boolean.valueOf(true)));
                containGroupName.add(name);
              }
            }
          }
          else {
            System.out.println("111111");
            String[] str = groupName.split("=");
            String parentName = str[2].split(",")[0];
            int index = containGroupName.indexOf(parentName);
            String name = str[1].split(",")[0];

            list.add(new Tree(++leavel4, 100 + index, 
              name, null, null, null, null, null, null, Boolean.valueOf(true)));
            try
            {
              NamingEnumeration e;
              for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore(); 
                e.hasMore()){
                Attribute Attr = (Attribute)ne.next(); 
                e = Attr.getAll();  
                String user = e.next().toString();
                int userindex = user.indexOf("uid");
                String[] userstr = user.split(",");
                if ((userstr.length > 1) && (userindex != -1))
                {
                  String[] uu = userstr[0].split("=");
                  String username = uu[1];
                  System.out.println(username);
                  list.add(new Tree(++leavel5, leavel4, 
                    username, null, null, null, null, null, null, null));
                }
                totalResults++;
              }

            }
            catch (NamingException e)
            {
              System.err.println("Throw Exception : " + e);
            }

          }

        }

      }

    }

    return list;
  }

  public String getValueByAttrbute(Attributes Attrs, String attrbuteName) {
    try {
      int totalResults = 0;
      for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore(); ) {
        Attribute Attr = (Attribute)ne.next();
        String stringAttr = Attr.toString();
        if (stringAttr.indexOf(attrbuteName) >= 0)
          return Attr.getAll().next().toString();
      }
    }
    catch (NamingException e)
    {
      System.err.println("Throw Exception : " + e);
    }
    return null;
  }
}