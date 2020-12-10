<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinform" method="post" action="${pageContext.request.contextPath}/user/modify">
					<%-- 이부분을 반드시 숨겨서 가져가야 한다. --%>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name}">
					
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${userVo.email}" readonly="readonly">
					
					<label class="block-label">패스워드</label>
					<input id="password" name="password" type="password">
					
					<fieldset>
						<legend>성별</legend>
							<label for="male1">남</label><input type="radio" id="male1" name="gender" value="male" <c:if test="${userVo.gender eq 'male'}">checked="checked"</c:if>>
							<label for="female1">여</label><input type="radio" id="female1" name="gender" value="female" <c:if test="${userVo.gender eq 'female'}">checked="checked"</c:if>>
					</fieldset>
					
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
<c:if test="${'success' == param.update}">
	<script>
		alert('회원 정보를 성공적으로 수정했습니다.');
	</script>
</c:if>
<c:if test="${'fail' == param.update}">
	<script>
		alert('패스워드를 다시 확인해주세요.');
	</script>
</c:if>
</html>










