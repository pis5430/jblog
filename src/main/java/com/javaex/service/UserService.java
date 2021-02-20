package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	//회원가입
	public int join(UserVo userVo) {
		
		System.out.println("userService join userVo :" + userVo);
		
		//개인 블로그 정보 넣어주기(userVo 의 id와 name사용) 
		BlogVo blogVo = new BlogVo();
		
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getUserName()+"의 블로그입니다.");
		blogVo.setLogoFile("/assets/images/spring-logo.jpg"); //이미지는 기본설정
		
		//개인 블로그 생성
		userDao.insert(userVo);
		
		return blogDao.blogInsert(blogVo);
	}
	
	//회원가입 - 아이디 체크
	public String idcheck(String id) {
		System.out.println("userService idcheck id = " + id);
		
		
		UserVo userVo = userDao.selectOne(id);
		
		String result ="";
		
		if(userVo==null) {
			//사용할수있는 id
			result = "can";
		}else {
			//사용할수없는 id
			result = "cant";
		}
		
		return result;
		
	}
	
	//로그인 
	public UserVo login(UserVo userVo) {
		System.out.println("userService login");
		
		return userDao.selectUser(userVo);
	}
	
	

}
