package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
@RequestMapping(value="/api/cate")
public class ApiCategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//카테고리 리스트 가져오기 
	@ResponseBody 
	@RequestMapping(value = "/cateList")
	public List<CategoryVo> cateList(@RequestParam("id") String id) {
		System.out.println("ApiCategoryController /cateList id" + id);
		
		return categoryService.cateList(id);
	}
	
	//카테고리 추가
	@ResponseBody 
	@RequestMapping(value = "/cateInsert")
	public CategoryVo cateInsert(@ModelAttribute CategoryVo categoryVo) {
		System.out.println("ApiCategoryController /cateInsert  categoryVo" +  categoryVo);
		
		return categoryService.cateInsert(categoryVo);
	}
	
	

}
