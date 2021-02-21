package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	
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

}
