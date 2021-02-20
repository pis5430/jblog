package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	//아이디 중복체크
	@ResponseBody 
	@RequestMapping(value="/idcheck" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String idcheck(@RequestParam("id") String id) {
		
		System.out.println("/user/idcheck");
		System.out.println("chekid =" + id); //id값 확인하기
		//System.out.println("chekid =" + password);
		
		
		String result = userService.idcheck(id);
		System.out.println(result);
		
		return result; //@ResponseBody --> response 의 body 영역에 data 만 보낸다. (return값)
		
	}
	
	//로그인
	@RequestMapping(value="/login" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo , HttpSession session) {
		
		System.out.println("/user/login");		
		System.out.println("login userVo : "+userVo);
		
		UserVo authUser = userService.login(userVo);
		
		
		
		if(authUser == null) {//실패했을때
			//로그인폼 result = fail
			System.out.println("로그인실패");
			String loginResult = "fail";
			return loginResult;
			
		}else{//성공했을때			
			System.out.println("로그인 성공-->" + authUser.toString());	
			session.setAttribute("authUser", authUser); //로그인한 정보 header에 표시하기위에 정보 꺼내줘야함		
			return "redirect:/"; //메인으로 이동			
		}
		
		
		
	}
	
	
	//로그아웃
	@RequestMapping(value="/logout" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("/user/logout");
		
		session.removeAttribute("authUser"); //authUser 정보 지우기(세션 속성명이 name(authUser)인 것을 지운다)
		session.invalidate(); //현재 생성된 세션을 무효화 시킨다.
		
		return  "redirect:/";
	}
	
	

}
