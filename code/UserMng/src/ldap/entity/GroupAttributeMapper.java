package ldap.entity;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import org.springframework.ldap.core.AttributesMapper;

public class GroupAttributeMapper
  implements AttributesMapper
{
  public Object mapFromAttributes(Attributes attrs)
    throws NamingException
  {
    Group gg = new Group();
    Attribute attr = attrs.get("cn");
    if (attr != null)
      gg.setCn((String)attr.get());
    return gg;
  }
}