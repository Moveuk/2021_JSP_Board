<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ldu.exam.exam1.dto.Article" %>
<% List<Article> articles = (List<Article>) request.getAttribute("articles"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 모바일에서 디자인이 축소되지 않게 하기 위한 코드 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JSP BOARD</title>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<!-- 데이지 UI 불러오기 : 테일윈드 + 추가기능 -->
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.1/dist/tailwind.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://cdn.jsdelivr.net/npm/daisyui@1.9.0/dist/full.css"
	rel="stylesheet" type="text/css" />
<!-- 폰트 구성 : 컨텍스트 패스 필요해서 불러옴. -->
<link rel="stylesheet"
	href="<%=request.getContextPath() + "/resource/font.css"%>" />
<!-- 공통 css 구성 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/common.css?ver=1" />
</head>
<body>
	<!-- section(의미없음) section-article-write(이름 : 글쓰기 의미) / padding - 4 px-->
	<section class="section section-article-write px-4">
		<!-- 알아서 반응형되는 tailwind 기능 container(어느정도 폭을 줄여줌) / margin(mx) auto -->
		<div class="container mx-auto">

			<div class="card bordered shadow-lg">
				<div class="card-title">
					<a href="javascript:history.back();" class="cursor-pointer">
						<i class="fas fa-chevron-left"></i>
					</a>
					<span>게시물 리스트</span>
				</div>

				<div class="px-4 py-4">
					<% for (Article article : articles) {%>
					<div>
						번호 : <%= article.getId() %><br>
						작성 : <%= article.getRegDate() %><br>
						갱신 : <%= article.getUpdateDate() %><br>
						제목 : <%= article.getTitle() %><br>
						내용 : <%= article.getBody() %><br>
					</div>
					<hr/>
					<% } %>
				</div>
	</section>

</body>
</html>