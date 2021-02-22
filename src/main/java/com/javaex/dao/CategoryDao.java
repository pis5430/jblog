package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//전체 리스트 출력
	public List<CategoryVo> getCateList(String id){
		System.out.println("dao getCateList() id :" +id);
		
		return sqlSession.selectList("cate.cateList" , id);		
		
	}
	
	
	//ajax 등록 (selectkey)
	public int insertSelectKey(CategoryVo categoryVo) {
		System.out.println("dao insertSelectKey categoryVo :"+categoryVo );
		
		
		System.out.println("xml실행전 : " + categoryVo);
		sqlSession.insert("cate.insertSelectKey",categoryVo); 
		System.out.println("xml실행후 : " + categoryVo); //no값이 추가됨
		
		return categoryVo.getCateNo();
		
	}
	
	
	//글 한개 가져오기
	public CategoryVo selectOne(int no) {
		
		System.out.println("dao selectOne() no:" + no);
		
		return sqlSession.selectOne("cate.selectOne", no);	
	}
	
	
	//카테고리 삭제
	public int cateDelete(CategoryVo categoryVo) {
		System.out.println("dao cateDelete");
		
		return sqlSession.delete("cate.cateDelete", categoryVo);
	}

	
	

}
