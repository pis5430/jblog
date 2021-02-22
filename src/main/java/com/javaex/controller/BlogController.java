package com.javaex.controller;

import java.util.List;
import java.util.Map;

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
import com.javaex.service.CategoryService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;

	
	//블로그 main(header에서 블로그로 집입할때 id가져오기)
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogMain(@PathVariable("id") String id, Model model,
							@RequestParam(value="cateNo", required = false, defaultValue="-1") int cateNo,
							@RequestParam(value="postNo", required = false, defaultValue="-1") int postNo) {
		
		System.out.println("블로그 컨트롤러 main , id : " +id +" cateNo : " +cateNo + " postNo : " +postNo);
		
		//main페이지에 표시될 정보
		//model.addAttribute("blogVo", blogService.blogSelectOne(id));
		BlogVo blogVo = blogService.blogSelectOne(id);
		
		//카테고리 리스트 불러오기(제목)
		List<CategoryVo> categoryList = categoryService.cateList(id);
		model.addAttribute("cList", categoryList);
		
		
		//blog생성
		if(blogVo != null) {	
			Map<String, Object> tMap = blogService.postList(cateNo, postNo);
			
			model.addAttribute("blogVo", blogVo);
			System.out.println("blogVo : " + blogVo);
			model.addAttribute("tMap", tMap);
			System.out.println("tMap : " + tMap);
			
			return "blog/blog-main";
		} else { //아이디 정보가 없을 경우	
			return "error/403";
		}

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
	
	//내 블로그 관리 - 카테고리 폼
	@RequestMapping(value = "/{id}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogCategory(@PathVariable("id") String id, Model model) {
		System.out.println("내 블로그 관리 - 카테고리");
		
		//header에 표시될 정보
		model.addAttribute("blogVo", blogService.blogSelectOne(id));
	
		return "blog/admin/blog-admin-cate";
		
	}
	
	//내 블로그 관리 - 글작성 폼 (카테고리 제목 불러와야함)
	@RequestMapping(value = "/{id}/admin/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogWriteForm(@PathVariable("id") String id,Model model) {
		System.out.println("내 블로그 관리 -글작성 폼");
		
		//header에 표시될 정보
		model.addAttribute("blogVo", blogService.blogSelectOne(id));
		
		//카테고리 리스트 불러오기(제목)
		List<CategoryVo> categoryList = categoryService.cateList(id);
		model.addAttribute("cList", categoryList);
	
		return "blog/admin/blog-admin-write";
		
	}
	
	
	//내 블로그 관리 - 글작성(리다이렉트에id필요)
	@RequestMapping(value = "/{id}/admin/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogWrite(@PathVariable("id") String id, @ModelAttribute PostVo postVo) {
		System.out.println("내 블로그 관리 -글작성");
		
		//블로그 포스트에 글쓰기
		blogService.blogWrite(postVo);
		
		
		System.out.println("postVo : " + postVo + " id :" + id);
		
	
		return "redirect:/" + id + "/admin/writeForm"; //writeForm으로 리다이렉트
		
	}
	

}
