<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
	<h1>Spring MySite</h1>
	<ul>
		<!-- if~else와 switch가 복합적으로 사용되는 JSTL 조건 부여! -->
		<c:choose>
			<c:when test="${ empty authUser }">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/joinform">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/modifyform">정보수정</a></li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
				<li>${authUser.name}님 반갑습니다.</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>










