package com.javaex.vo;

public class BlogVo {
	
	private String id; //식별번호
    private String blogTitle; //블로그제목
    private String logoFile; //블로그이미지경로
    
    
	public BlogVo() {
		super();
	}


	public BlogVo(String id, String blogTitle, String logoFile) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getBlogTitle() {
		return blogTitle;
	}


	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}


	public String getLogoFile() {
		return logoFile;
	}


	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}


	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + "]";
	}
	
	
	
	

}
