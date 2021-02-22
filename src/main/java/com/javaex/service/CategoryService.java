package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> cateList(String id){
		System.out.println("cateService cateList id" + id);

		return categoryDao.getCateList(id);
	}
	
	public CategoryVo cateInsert(CategoryVo categoryVo) {
		System.out.println("Service cateResultVo categoryVo :" + categoryVo);
		
		//글 저장
		System.out.println("Service: dao.insertSelectKey 실행전");
		categoryDao.insertSelectKey(categoryVo);
		
		System.out.println("Service: dao.insertSelectKey 실행후");
		int no = categoryVo.getCateNo();
		
		System.out.println(no);
		
		//글1개 가져오기
		return categoryDao.selectOne(no);
		
	}	
	
	// 카테고리 삭제
	public int cateDelete(CategoryVo categoryVo) {
		System.out.println("Service cateDelete()");
		
		return categoryDao.cateDelete(categoryVo);
	}

}
