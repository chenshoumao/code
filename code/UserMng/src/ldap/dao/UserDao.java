package ldap.dao;

import java.util.List;
import java.util.Map;
import ldap.entity.ReturnUser;
import ldap.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface UserDao
{
  public abstract List<User> getPersonList(User paramUser);

  public abstract boolean updateUser(User paramUser);

  public abstract Map<String, Object> search(User paramUser, int paramInt1, int paramInt2);

  public abstract List search(int paramInt1, int paramInt2);

  public abstract boolean updatePassword(ReturnUser paramReturnUser);
}