<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page</title>
<link href="./resources/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<!-- 페이지 상단 (header) -->
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		
		<!-- 페이지 내용 (content) -->
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="./resources/assets/images/main.jpg">
					<h2>어서오세요.<br>저희 사이트에 오신 것을 환영합니다.</h2>
					<p>
						본 사이트는 스프링 프레임워크 실습 예제 사이트 입니다.<br>
						Java, Database, Web Programming이 모두 포함되어 있습니다.
					</p>
				</div>
			</div>
		</div>
		
		<!-- 페이지 메뉴 (navigation) -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main"/>
		</c:import>
		
		<!-- 페이지 하단 (footer) -->
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>












