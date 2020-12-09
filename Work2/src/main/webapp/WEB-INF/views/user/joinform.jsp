<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	// 문서가 준비된 후 수행하라. (jQeury)
	//	-> $(document).ready(...)
	$(function() {
		$('#btn-chkemail').click(function() {
			// form:input path="email" 의 Value 추출
			let email = $('#email').val();
			if(email == '') return; // 비어있다면 종료
			
			// jQuery ajax를 활용한 이메일 중복 체크
			$.ajax("emailCheck", // 요청을 보낼 url 지정
				{
					data:{'email':email}, // 보낼 파라미터와 값
					success: function(data) // 성공시 결과(data)를 받아와 처리할 내용
					{
				    	if(data==0){ // 중복되지 않는다면,
							$('#btn-chkemail').hide(); // 중복 체크 버튼을 숨기고
							$('#img-chkemail').show(); // 체크 이미지를 보여주자
				    	}else{ // 이메일 중복이라면
				    		alert('이미 존재합니다. 다른 이메일을 사용해 주세요.'); // 유저에게 알린 후
							$('#email').val('').focus(); // 입력란을 비운 뒤 포커스
						}
					}
				});
		});
		
		$('#join-form').submit(function() { // submit 동작 시 실행할 함수(폼체크) 호출
			// 조건에 따라 submit을 할지 말지 결정
			
			// "중복 체크가 완료 된 후 나타는 체크모양 이미지"가 보여지는 상태가 아니라면
			if($('#img-chkemail').is(':visible') == false) {
				alert('이메일 중복 체크를 하셔야 합니다.'); // 유저에게 알린 후
				return false; // submit 중단
			}
		
			// "약관동의"의 체크박스가 체크 상태가 아니라면
			if($("#agree-prov").is(':checked') == false) {
				alert('약관에 동의하셔야 합니다.'); // 유저에게 알린 후
				return false; // submit 중단
			}			
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<!-- form:form : 데이터 바인딩 및 에러 처리 관련 스프링 프레임워크 태그 제공 -->
				<form:form modelAttribute="userVo" id="join-form" name="joinform" method="post" action="${pageContext.request.contextPath}/user/join">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<p style="text-align:left; color:red">
						<form:errors path="name"/>
					</p>
					
					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />
					<!-- 사용가능한 이메일이라면 이미지 보여주기 -->
					<img id="img-chkemail" align="top" style="width:16px; display:none"
						src="${pageContext.request.contextPath}/resources/assets/images/check.png">
					<input id="btn-chkemail" type="button" value="중복체크">
					<p style="text-align:left; color:red">
						<form:errors path="email" />
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password" />
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('password')}">
							<p style="test-align:left; color:red">
								<spring:message
									code="${errors.getFieldError('password').codes[0]}"
									text="${errors.getFieldError('password').defaultMessage}"
								/>
							</p>
						</c:if>
					</spring:hasBindErrors>
					
					<fieldset>
						<legend>성별</legend>
						<label>남</label><input type="radio" name="gender" value="male" checked="checked">
						<label>여</label><input type="radio" name="gender" value="female">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
				</form:form>
			</div>
		</div>
		<!-- 
			navigation의 메뉴가 아닌 header의 메뉴에서 접근하는 페이지
			navigation의 선택된 메뉴를 식별하기 위한 파라미터를 전달하지 않겠다.
		 -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>











