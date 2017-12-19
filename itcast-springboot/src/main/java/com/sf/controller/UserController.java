package com.sf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sf.base.BaseController;
import com.sf.model.User;
import com.sf.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping("/getuser")
	@ResponseBody
	public User getuser() {
		User user = this.userService.queryUser();
		return user;
	}

	@RequestMapping("/addUser")
	public ModelAndView addUser() {

		return forword("addUser");
	}

	@RequestMapping("saveuser")
	public ModelAndView saveuser(User user) {
		userService.saveUser(user);
		return redirect("/user/list");
	}
	@RequestMapping("list")
	public ModelAndView list(){
		User use = new User();
		List<User> us = userService.queryUsers(use);
		return forword("list", "list", us);
	}
}
