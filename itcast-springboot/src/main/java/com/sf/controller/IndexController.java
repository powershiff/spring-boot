package com.sf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sf.base.BaseController;


@Controller
public class IndexController extends BaseController{

	@RequestMapping("/")
	public  ModelAndView  index(){
		return  redirect("/user/list");
	}
}
