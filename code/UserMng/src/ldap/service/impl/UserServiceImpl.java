package ldap.service.impl;

import java.util.List;
import java.util.Map;
import ldap.dao.UserDao;
import ldap.entity.ReturnUser;
import ldap.entity.User;
import ldap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
  implements UserService
{

  @Autowired
  private UserDao userDao;

  public List<User> getPersonDetail(User user)
  {
    return this.userDao.getPersonList(user);
  }

  public boolean updateUser(User user)
  {
    return this.userDao.updateUser(user);
  }

  public Map<String, Object> search(User user, int curPage, int pageSize)
  {
    return this.userDao.search(user, curPage, pageSize);
  }

  public List search(int curPage, int pageSize)
  {
    return this.userDao.search(curPage, pageSize);
  }

  public boolean updatePassword(ReturnUser user)
  {
    return this.userDao.updatePassword(user);
  }
}