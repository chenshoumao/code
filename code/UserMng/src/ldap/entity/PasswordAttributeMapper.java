package ldap.entity;

import java.io.PrintStream;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import org.springframework.ldap.core.AttributesMapper;

public class PasswordAttributeMapper
  implements AttributesMapper
{
  public Object mapFromAttributes(Attributes attrs)
    throws NamingException
  {
    ReturnUser user = new ReturnUser();
    Attribute attr = attrs.get("userPassword");

    if (attr != null) {
      Object o = attr.get();
      byte[] bytes = (byte[])o;
      System.out.println(new String(bytes));
      user.setPassword(new String(bytes));
    }

    return user;
  }
}