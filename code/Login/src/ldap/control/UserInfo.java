package ldap.control;

import java.util.HashMap;
import java.util.List;

 



 

 




import java.util.Map;

import ldap.entity.User;
import ldap.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

 


@Controller
@RequestMapping("/UserInfo")
public class UserInfo {
	@Autowired
	private UserService service;
	
	 
	@RequestMapping(value = "/findByDn",method= RequestMethod.POST)
	@ResponseBody
	public List<User> findByDn(User user){
		List<User> list = service.getPersonDetail(user);
		return list;
	} 
	
	@RequestMapping(value = "/search",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> search(User user,int curPage,int pageSize){
		System.out.println(user.getUid());
		Map<String, Object> result = new HashMap<String,Object>();
		List<User> list = null;
		Map<String, Object> map= service.search(user,curPage,pageSize);
		list = (List<User>)map.get(""+curPage);
		result.put("result", list);
		 
		int num =  (map.size() - 1) * pageSize + ((List<User>)map.get(""+(map.size()))).size();
		result.put("total", num);
		System.out.println(111111111);
		System.out.println(num);
		return result;
	} 
	 
	//List list = dao.getPersonList(person);
	//System.out.println(list);
	
	@RequestMapping(value = "/updateUser",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUser(User user){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			boolean state = service.updateUser(user);
			map.put("state", state);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("state", false);
		}
		
		return map;
	} 
	
	@RequestMapping(value = "/updatePassword",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePassword(User user){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			boolean state = service.updatePassword(user);
			map.put("state", state);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("state", false);
		}
		
		return map;
	} 
	
	
	@RequestMapping(value = "/forgetPassword",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forgetPassword(User user){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			boolean state = service.forgetPassword(user);
			map.put("state", state);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("state", false);
		}
		
		return map;
	} 
	
	 
	
	
}
