<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form class="write-form" method="post" action="${pageContext.request.contextPath}/board/write">
					<%-- 이부분을 반드시 숨겨서 가져가야 한다. --%>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="groupNo" value="${boardVo.groupNo}"/>
					<input type="hidden" name="orderNo" value="${boardVo.orderNo}"/>
					<input type="hidden" name="depth" value="${boardVo.depth}"/>
					<input type="hidden" name="no" value="${boardVo.no}"/>
					<input type="hidden" name="p" value="${page}"/>
					<input type="hidden" name="kwd" value="${keyword}"/>
					<table class="tbl-ex">
						<tr>
							<th colspan="2">답글 작성</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="javascript:history.go(-1)">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>









