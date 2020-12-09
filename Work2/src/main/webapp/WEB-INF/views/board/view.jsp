<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLine", "\n"); %>
<!-- 페이지 내에서만 newLine("\n") 처럼 활용하겠다! (영역 등록) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./resources/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="write-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글 정보</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<!-- 
								replace 사용
									- 글 등록 시 입력된 데이터의 형태 (Enter로 인한 개행 문자)
									- HTML에 표현할 형태와 다르다. (HTML의 개행은 <br> 태그)
									- *문서 출력 시 개행이 안되니 <br> 태그로 변환
									- *가져올 데이터와 표현할 문서의 차이점! (플랫폼간 차이점 존재!)
							 -->
							${ fn:replace(boardVo.content, newLine, "<br>") }
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/board?
						p=${page}&kwd=${keyword}">글 목록</a>
					<c:if test="${ not empty authUser }">
						<a href="${pageContext.request.contextPath}/board/reply?
						no=${boardVo.no}&p=${page}&kwd=${keyword}">답글 달기</a>
						<c:if test="${ authUser.no == boardVo.userNo }">
							<a href="${pageContext.request.contextPath}/board/modify?
								no=${boardVo.no}&p=${page}&kwd=${keyword}">글 수정</a>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>












