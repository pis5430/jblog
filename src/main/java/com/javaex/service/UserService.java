package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//회원가입
	public int join(UserVo userVo) {
		
		System.out.println("userService join");
		return userDao.insert(userVo);
	}
	
	

}
