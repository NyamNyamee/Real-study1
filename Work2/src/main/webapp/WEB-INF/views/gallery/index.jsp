<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="./resources/assets/css/lightbox.css" rel="stylesheet" type="text/css">
<link href="./resources/assets/css/gallery.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<script src="./resources/assets/js/lightbox.js" type="text/javascript"></script>

<c:if test="${not empty authUser}">
	<script type="text/javascript">
		$(function() {
			// 대화상자 (다이얼로그)
			var dialogUpload = $('#dialog-upload-form').dialog({
				autoOpen : false,
				height:280,
				width:300,
				modal:true,
				buttons:{
					'업로드':function() {
						$('#dialog-upload-form form').submit();
					},
					'취소':function() {
						$(this).dialog('close');
					}
				},
				close:function() {
					$('#dialog-upload-form form').get(0).reset();
				}
			});
			
			$('#upload-image').click(function(event) {
				event.preventDefault();
				dialogUpload.dialog('open');
			});
		});
	</script>
</c:if>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="gallery">
				<div>
					<h1>갤러리</h1>
					<c:if test="${ not empty authUser }">
						<a href="" id="upload-image">이미지 올리기</a>
					</c:if>
				</div>
				<ul>
					<c:forEach items="${list}" var="vo">
						<li>
							<a href="${pageContext.request.contextPath}${baseURL}/${vo.saveFileName}"
								data-lightbox="gallery"
								class="image"
								title="${vo.comments}"
								style="background-image:url('${pageContext.request.contextPath}${baseURL}/${vo.saveFileName}')">
								&nbsp;	
							</a>
							<c:if test="${ not empty authUser && authUser.no == vo.userNo }">
								<a href="${pageContext.request.contextPath}/gallery/delete/${vo.no}"
									class="btn-delete"
									title="삭제"></a>
							</c:if>
						</li>
					</c:forEach>
				</ul>
			</div>
			
			<c:if test="${not empty authUser}">
				<div id="dialog-upload-form" title="이미지 업로드"
					style="display:none">
					<p class="validateTips normal">이미지와 간단한 설명을 입력해 주세요.</p>
					<form method="post" enctype="multipart/form-data"
						action="${pageContext.request.contextPath}/gallery/upload">
						<label>Comment</label>
						<input type="text" id="input-comments" name="comments" value="">
						<label>Image</label>
						<input type="file" id="input-file" name="file">
						<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
					</form>
				</div>
			</c:if>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="gallery" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>







