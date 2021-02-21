<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js" ></script>
</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/write">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
		
	
	
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">

	//시작되면 cateList 뿌려줘야함
	$("document").ready( function(){
		
	//진입 테스트
	console.log("ready")
		
	//리스트 출력
	fetchList();	
	
	});

	//카테고리 추가버튼 클릭할때
	$("#btnAddCate").on("click" , function(){
		console.log("카테고리 추가버튼 클릭")
		
		
	})
	
	
	//방명록 글 정도  + html조합하여 화면에 출력
	function render(categoryVo , updown){
		
		//문자열로 만듬 , 소문자만 인식가능 ""안에
  		var str = "";
		str += "<tr>";
		str += "	<td>"+categoryVo.cateNo+"</td>";
		str += "	<td>"+categoryVo.cateName+"</td>";
		str += "	<td></td>";
		str += "	<td>"+categoryVo.description+"</td>";
		str += "	<td class='text-center'>";
		str += "		<img class='btnCateDel' src='${pageContext.request.contextPath}/assets/images/delete.jpg'>";
		str += "	</td>";
		str += "</tr>";
		
		//<tr>
		//<td>2</td>
		//<td>오라클</td>
		//<td>5</td>
		//<td>오라클 설치와 sql문</td>
	    //<td class='text-center'>
	    //	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
	    //</td>
		//</tr>
		
		console.log(categoryVo);
			
		if(updown == 'down'){
			$("#cateList").append(str);
		}else if(updown == 'up'){
			$("#cateList").prepend(str);
		}else{
			console.log("방향 미지정");
		}		
		
	}
	
	
	//category 리스트 출력
	function fetchList(){
		
		$.ajax({
			//보낼때
			url : "${pageContext.request.contextPath}/api/cate/cateList",		
			type : "post",
			//contentType : "application/json",
			data : {id :'${authUser.id}'},

			//받을때
			dataType : "json",
			success : function(cateList){
				/*성공시 처리해야될 코드 작성*/
				console.log(cateList); //잘 들어갔는지 확인 

				//게스트 정보 출력
				for(var i=0; i<cateList.length; i++){
				render(cateList[i] , "down");
				}
			
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};
	




</script>
</html>