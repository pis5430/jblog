package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getCateList(String id){
		System.out.println("dao getCateList() id :" +id);
		
		System.out.println();
		
		return sqlSession.selectList("cate.cateList" , id);		
		
	}
	
	//post 갯수
	//public int catePostCount(String id) {		
	//	System.out.println("dao catePostCount id :" + id);	
	//	
	//	return sqlSession.selectOne("cate.postCount", id );
		
	//}
	
	

}
