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

}
