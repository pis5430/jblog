package com.javaex.vo;

public class CategoryVo {
	
	private int cateNo; //식별번호PK
    private String id;// 회원번호FK
    private String cateName; //카테고리명
    private String description; //카테고리설명
    private String regDate; //등록일
    
    private int postCount; //포스트 갯수
    
    
	public CategoryVo() {
		super();
	}

	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate, int postCount) {
		super();
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
		this.postCount = postCount;
	}




	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public int getCateNo() {
		return cateNo;
	}


	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCateName() {
		return cateName;
	}


	public void setCateName(String cateName) {
		this.cateName = cateName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName + ", description=" + description
				+ ", regDate=" + regDate + ", postCount="+ postCount+"]";
	}
	
	
    
    
    
	
	

}
