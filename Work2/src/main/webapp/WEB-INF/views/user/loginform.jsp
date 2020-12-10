<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/user.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="${pageContext.request.contextPath}/user/login">
					<%-- 이부분을 반드시 숨겨서 가져가야 한다. --%>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text">
					<label class="block-label" for="password">패스워드</label>
					<input id="password" name="password" type="password">
					
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>

.







