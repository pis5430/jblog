package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	//블로그 생성(주소 id , 블로그명 name ,로고파일)
	public int blogInsert(BlogVo blogVo) {	
		System.out.println("blogDao blogInset blogVo :" + blogVo);		
		return sqlSession.insert("blog.blogInsert", blogVo);
	}
	
	
	//블로그 진입(주소 id , 블로그명 name ,로고파일)
	public BlogVo blogSelectOne(String id) {		
		System.out.println("blogDao blogSelectOne id :" + id);		
		return sqlSession.selectOne("blog.blogSelectOne", id);
		
	}
	
	//내 블로그 기본정보 수정 
	public int blogBasicUpdate(BlogVo blogVo) {		
		System.out.println("blogDao blogBasicUpdate blogVo :" + blogVo);		
		return sqlSession.update("blog.basicUpdate", blogVo);
		
	}
	
	
	
	
	
	
	
}
