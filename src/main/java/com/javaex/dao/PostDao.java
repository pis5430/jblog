package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//post 작성
	public int insertPost(PostVo postVo) {
		
		System.out.println("postDao insertPost postVo : " + postVo);		
		return sqlSession.insert("post.postInsert" , postVo);
	}
	
	//post List 조회하기(카테고리 별로 표시되어야함)
	public List<PostVo> postList(int cateNo) {
		System.out.println("postDao postList cateNo :" + cateNo);
		
		return sqlSession.selectList("post.postList", cateNo);
	}
	
	//포스트 1개
	public PostVo postSelect(int postNo) {
		System.out.println("postDao postSelect postNo :" +postNo);
		
		return sqlSession.selectOne("post.postSelect", postNo);
	}
	
	

}
