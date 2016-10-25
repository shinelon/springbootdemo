package com.spring.web.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.domain.user.User;
import com.spring.realm.UserRealm;
import com.spring.service.sys.UserService;
@Controller
@RequestMapping("/user")
public class UserContorller {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRealm userRealm;
	
	@RequestMapping("/getuser")
	@ResponseBody
	public User getUserByName(){	
		User u=userService.findByUsername("admin");
		System.out.println(u);
		return u;
	}
	@RequestMapping("/getrealm")
	@ResponseBody
	public User getRealm(){	
		return null;
	}
	@RequestMapping("/get")
	public User selectByPrimaryKey(){	
		User u=userService.selectByPrimaryKey(1l);
		System.out.println(u);
		return u;
	}
	
	
}
