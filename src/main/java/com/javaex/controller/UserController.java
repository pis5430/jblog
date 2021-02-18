package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//회원가입 폼
	@RequestMapping(value="/joinForm" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String joinForm() {
		System.out.println("/user/joinForm");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/join" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		
		System.out.println("/user/join");
		System.out.println(userVo);
		
		int count = userService.join(userVo);// dao에 바로 연결하지 않고 서비스로
		
		return "user/joinSuccess";
	}
	
	//로그인 폼
	@RequestMapping(value="/loginForm" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String loginForm() {
		System.out.println("/user/loginForm");
		
		return "user/loginForm";
	}
	
	

}
