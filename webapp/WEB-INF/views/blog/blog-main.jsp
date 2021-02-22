<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<!-- 기본이미지 -->
					<c:choose>
						<c:when test="${blogVo.logoFile eq null}">
							<!-- 기본이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
						</c:when>
						<c:otherwise>
							<!-- 사용자업로드 이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}">
						</c:otherwise>
					</c:choose>					
					
					<div id="nick">${blogVo.blogTitle}</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${cList}" var="cateVo">
							<li><a href="$}">${cateVo.cateName}</a></li>
				      	</c:forEach>	
				      	<!-- 
						<li><a href="$}">카테고리5</a></li>
						<li><a href="$}">카테고리4</a></li>
						<li><a href="$}">카테고리3</a></li>
						<li><a href="$}">카테고리2</a></li>
						<li><a href="$}">카테고리1</a></li>
						<li><a href="$}">미분류</a></li>
						 -->
						
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				<c:choose>
					<c:when test="${empty tMap.postVo}"> 
						<!-- 글이 없는 경우 -->				
						<div id="postBox" class="clearfix">
									<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
									<div id="postDate" class="text-left"><strong></strong></div>
									<div id="postNick"></div>
						</div>
					    
						<div id="post" >
						
						</div>
						
						<!-- //post -->
					</c:when>
		 			<c:otherwise>	
						<!-- 글이 있는경우 * 해당 카테고리에 제일 최근글-->
						<div id="postBox" class="clearfix">
								<div id="postTitle" class="text-left"><strong>${tMap.postVo.postTitle}</strong></div>
								<div id="postDate" class="text-left"><strong>${tMap.postVo.regDate}</strong></div>
								<div id="postNick">${blogVo.userName}(${blogVo.id})님</div>
						</div>
						<!-- //postBox -->
					
						<div id="post" >
							${tMap.postVo.postContent}
						</div>
					</c:otherwise>
				</c:choose>
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						<!-- postList출력 -->
						<c:forEach items="${postList}" var="postList">
							<tr>
								<td class="text-left"><a href="${pageContext.request.contextPath}/${blogVo.id}?cateNo=${postList.cateNo}&postNo=${postList.postNo}">${postList.postTitle}</a></td>
								<td class="text-right">${postList.regDate}</td>
							</tr>
						</c:forEach>
						
						
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>
</html>