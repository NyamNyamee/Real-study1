<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<link rel="stylesheet" type="text/css" href="./resources/assets/css/board.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search-form" method="get" action="${pageContext.request.contextPath}/board">
					<input type="text" id="kwd" name="kwd">
					<input type="submit" value="검색">	
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${map.list}" var="vo" varStatus="status">
						<tr>
							<td>${map.totalCount - (map.page - 1) * map.listSize - status.index}</td>
							<c:choose>
								<c:when test="${vo.depth > 0}">
									<td class="left" style="padding-left:${15*vo.depth}px">
										<img src="./resources/assets/images/reply.png">
										<a href="${pageContext.request.contextPath}/board/view?
											no=${vo.no}&p=${map.page}&kwd=${map.keyword}">${vo.title}</a>
									</td>
								</c:when>
								<c:otherwise>
									<td class="left">
										<a href="${pageContext.request.contextPath}/board/view?
											no=${vo.no}&p=${map.page}&kwd=${map.keyword}">${vo.title}</a>
									</td>									
								</c:otherwise>
							</c:choose>
							<td>${vo.userName}</td>
							<td>${vo.hit}</td>
							<td>${vo.regDate}</td>
							<td>
								<c:choose>
									<c:when test="${not empty authUser && authUser.no == vo.userNo}">
										<a href="${pageContext.request.contextPath}/board/delete?
											no=${vo.no}&p=${map.page}&kwd=${map.keyword}"
											class="del">삭제</a>
									</c:when>
									<c:otherwise>
										&nbsp;
									</c:otherwise>
								</c:choose>
							</td>						
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:if test="${map.prevPage > 0}">
							<li><a href="${pageContext.request.contextPath}/board?
									p=${map.prevPage}&kwd=${map.keyword}">◀</a></li>
						</c:if>
						
						<c:forEach begin="${map.beginPage}" end="${map.beginPage + map.listSize - 1}" var="page">
							<c:choose>
								<c:when test="${map.endPage < page}">
									<li>${page}</li>
								</c:when>
								<c:when test="${map.page == page}">
									<li class="selected">${page}</li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath}/board?
											p=${page}&kwd=${map.keyword}">${page}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${map.nextPage > 0}">
							<li><a href="${pageContext.request.contextPath}/board?
									p=${map.nextPage}&kwd=${map.keyword}">▶</a></li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser}">
						<a href="${pageContext.request.contextPath}/board/write"
							id="new-book">글쓰기</a>
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













