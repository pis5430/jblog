package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private PostDao postDao;
	
	
	//블로그 진입(주소에 쓸 id 필요)
	public BlogVo blogSelectOne(String id) {	
		System.out.println("blog서비스 id : " + id);			
		return blogDao.blogSelectOne(id);
	}
	
	//내 블로그 기본설정 수정
	public int blogBasicUpdate(BlogVo blogVo, MultipartFile file) {	
		System.out.println("blog서비스 기본설정 수정service blogVo : " + blogVo);	
		System.out.println(file.getOriginalFilename());
		
		//db에 저장할 정보수집//
		String saveDir = "C:\\javaStudy\\upload";
		
		//오리지널 파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName : " + orgName);
		
		//확장자 -->확장자만 남기기 위해 
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName : " + exName);	
		
		//서버 저장파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("seveName : " + saveName);		
		
		//서버 파일패스 --> 저장경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath : " + filePath);
		
		//파일 사이즈		
		long fileSize = file.getSize();
		System.out.println("fileSize : " + fileSize);
		
		blogVo.setLogoFile(saveName);
		System.out.println(blogVo.getLogoFile());
		
		//서버 하드 디스크 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		System.out.println("블로그서비스 blogBasicUpdate blogVo"+ blogVo);
		return blogDao.blogBasicUpdate(blogVo);
	}
	
	//post에 글쓰기
	public int blogWrite(PostVo postVo) {	
		System.out.println("블로그 서비스  blogWrite postVo" + postVo);
		return postDao.insertPost(postVo);
	}
	
	
	//블로그 post 데이터 조회
	public Map<String, Object> postList(PostVo postVo) {
		System.out.println("blogService.blogList() postVo" +postVo );
		
		Map<String, Object> tMap = new HashMap<String, Object>();
		
		int cateNo = postVo.getCateNo();
		
		//해당 카테고리에 연결된 postList
		List<PostVo> postList = postDao.postList(cateNo);
		tMap.put("postList", postList);
		
		//post 갯수가 0이 아니면 포스트 가져오기
		if(postList.size() != 0) {			
			int postNo = postVo.getPostNo();
			
			if(postNo == 0) {
				postNo = postList.get(0).getPostNo();
			}
			
			tMap.put("postVo",postDao.postSelect(postNo) );
			
		}
		
		
		return tMap;
	}
	
	
	

}
