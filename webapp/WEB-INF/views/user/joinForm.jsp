<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js" ></script>


<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		
	</div>

</body>

<script type="text/javascript">


//아이디 중복체크
$("#btnIdCheck").on("click" , function(){
	
	var uid = $("[name='id']").val(); //name값으로 가져오는 방법
	console.log(uid);
	
	//ajax 데이터만 받을때 쓰는 기술		
	//아이디 중복체크
	$.ajax({ //서버하고 통신하는 기술
		url : "${pageContext.request.contextPath}/user/idcheck",		
		type : "post",
		//contentType : "application/json",
		//data : {name: "홍길동"},
		data : {id: uid}, //url파라미터값을 만드는 다른 방법

		//dataType : "json",
		dataType : "text",
		success : function(result){
			console.log(result);
			
			/*성공시 처리해야될 코드 작성*/
			if(result == 'can'){
				console.log("사용할 수 있는 아이디 : can");
				$("#tdMsg").html("사용할 수 있는 아이디 입니다.")
			}else{
				console.log("사용할 수 없는 아이디 : cant")
				$("#tdMsg").html("다른 아이디로 가입해 주세요.")
			}			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});			
});


//폼을 subnmit할때 --> submit되기 전
$("#joinForm").on("submit" , function(){
	
	//아이디 중복체크 확인준비
	//var idCheck = $("#tdMsg").val(); 
	//console.log(idCheck)

	//패스워드 체크준비
	var pw = $("#txtPassword").val();
	console.log(pw.length)
	
	//동의여부 체크준비
	var check = $("#chkAgree").is(":checked"); //false , 체크안햇음
	console.log(check);	
	
	//아이디 중복여부 체크(안됨, 다시해보기))
	//if($("#tdMsg").val() == null){
	//	alert("아이디 중복체크 해주세요");
	//	return false;
	//}
	
	//패스워드 체크
	if(pw.length < 8){
		alert("패스워드는 8글자 이상입니다.");
		return false;
	}		
	
	//동의여부 체크
	if(!check){ //체크 안햇으면 true가 됨
		//패스워드 체크 8글자 이상 통과
		alert("동의해주세요");
		return false;
	}
	
	//위 내용을 모두 통과해야 submit 로 진입
	return true;	
})

</script>







</html>