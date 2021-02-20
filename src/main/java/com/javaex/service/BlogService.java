package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	
	//블로그 진입(주소에 쓸 id 필요)
	public BlogVo blogSelectOne(String id) {
		
		System.out.println("blog서비스 id : " + id);	
		
		return blogDao.blogSelectOne(id);
	}

}
