<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page  import="java.util.*,com.ibm.portal.um.PumaHome,com.ibm.portal.um.PumaProfile,com.ibm.portal.um.User;"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

   PumaHome pumaHome; 
  javax.naming.Context ctx = new javax.naming.InitialContext();
  pumaHome = (PumaHome) ctx.lookup(PumaHome.JNDI_NAME);
  PumaProfile profile = pumaHome.getProfile();
  User cur_user=(User)profile.getCurrentUser();
  List attributeList=new ArrayList();
   attributeList.add("sn");
   attributeList.add("givenName");
   attributeList.add("uid");
Map valuse = profile.getAttributes(cur_user, attributeList);
String uidmap=(String)valuse.get("uid");

//profile.getUserIdentifier(cur_user);
out.print("user:  "+uidmap);


 %>

</body>
</html>