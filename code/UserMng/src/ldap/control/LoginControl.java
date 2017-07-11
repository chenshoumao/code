package ldap.control;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; 
import ldap.entity.ReturnUser;
import ldap.service.impl.LoginServiceImpl;

@Controller
@RequestMapping("/login")
public class LoginControl {
	 
	
	@Autowired
	private LoginServiceImpl loginService;

	@RequestMapping(value = "/login", method= RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> login(@RequestBody ReturnUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean loginState = loginService.login(user.getLoginName(), user.getPassword());
		map.put("state", loginState);
		return map;
	}
	
	@RequestMapping(value = "/login2")  
	@ResponseBody
	public Map<String, Object> login2(ReturnUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean loginState = loginService.login(user.getLoginName(), user.getPassword());
		map.put("state", loginState);
		return map;
	}
	
}
