package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	
	//블로그 main(header에서 블로그로 집입할때 id가져오기)
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("블로그 컨트롤러 main , id : " +id);
		
		//main페이지에 표시될 정보
		model.addAttribute("blogVo", blogService.blogSelectOne(id));

		return "blog/blog-main";
	}
	
	//내 블로그 관리 - 기본설정
	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogAdmin(@PathVariable("id") String id, Model model) {
		System.out.println("내 블로그 관리 - 기본설정");
		
		//header에 표시될 정보
		model.addAttribute("blogVo", blogService.blogSelectOne(id));
		
		return "blog/admin/blog-admin-basic";
		
	}
	
	//내블로그 관리 - 기본설정 수정(블로그 제목, 로고파일)
	@RequestMapping(value = "/basicUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogBasicUpdate(@ModelAttribute BlogVo blogVo, @RequestParam("file") MultipartFile file) {
		
		System.out.println(blogVo); //id ,블로그 제목 , 로고파일(보충필요)
		
		//로고파일 보충하기 (이미지 업데이트되게 )
		System.out.println("블로그 컨트롤러 file 확인 :"+file);
		
		
		//기본설정 수정하기 
		blogService.blogBasicUpdate(blogVo,file);
		
		
		return "redirect:/"+blogVo.getId()+"/admin/basic"; //기본수정화면으로 리다리렉트
	}
	

}
