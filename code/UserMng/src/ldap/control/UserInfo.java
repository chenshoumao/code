package ldap.control;

import java.io.PrintStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import ldap.entity.ReturnUser;
import ldap.entity.User;
import ldap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/UserInfo")
public class UserInfo
{

  @Autowired
  private UserService service;

  @RequestMapping(value={"/findByDn"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<User> findByDn(User user)
  {
    List list = this.service.getPersonDetail(user);
    return list;
  }
  @RequestMapping(value="/getUserInfo", method= RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> getUserInfo(@RequestBody User user) {
    Map map = new HashMap();
    List list = this.service.getPersonDetail(user);
    if (list.size() > 1) {
      map.put("state", Boolean.valueOf(false));
      map.put("reason", "用户数据过多");
    }
    else {
      User userReturn = (User)list.get(0);
      map.put("state", Boolean.valueOf(true));
      map.put("user", userReturn);
    }

    return map;
  }

  @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Map<String, Object> search(User user, int curPage, int pageSize) {
    System.out.println(user.getUid());
    Map result = new HashMap();
    List list = null;
    Map map = this.service.search(user, curPage, pageSize);
    list = (List)map.get(curPage);
    result.put("result", list);

    int num = (map.size() - 1) * pageSize + ((List)map.get(map.size())).size();
    result.put("total", Integer.valueOf(num));
    System.out.println(111111111);
    System.out.println(num);
    return result;
  }

  @RequestMapping(value={"/updateUser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Map<String, Object> updateUser(User user)
  {
    Map map = new HashMap();
    try {
      boolean state = this.service.updateUser(user);
      map.put("state", Boolean.valueOf(state));
    }
    catch (Exception e) {
      map.put("state", Boolean.valueOf(false));
    }

    return map;
  }

  @RequestMapping(value={"/updatePassword"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Map<String, Object> updatePassword(ReturnUser user, HttpServletRequest request)
  {
    Map map = new HashMap();
    try {
      user.setLoginName(request.getUserPrincipal().getName());
      boolean state = this.service.updatePassword(user);
      map.put("state", Boolean.valueOf(state));
    }
    catch (Exception e) {
      map.put("state", Boolean.valueOf(false));
    }

    return map;
  }

  @RequestMapping(value={"/test"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Map<String, Object> test(@RequestBody User user)
  {
    Map map = new HashMap();
    System.out.println(user.getCn());
    return map;
  }
}
