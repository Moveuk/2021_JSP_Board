<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="게시물 리스트"/>
<%@ include file="../part/head.jspf"%>

<%@ page import="java.util.List" %>
<%@ page import="com.ldu.exam.exam1.dto.Article" %>
<% List<Article> articles = (List<Article>) request.getAttribute("articles"); %>

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
					<c:forEach items="${articles }" var="article">
					<div>
						번호 : ${article.id }<br>
						작성 : ${article.regDate  }<br>
						갱신 : ${article.updateDate }<br>
						제목 : ${article.title }<br>
						내용 : ${article.body }<br>
					</div>					
					</c:forEach>
					<hr/>
				</div>
	</section>
	
<%@ include file="../part/foot.jspf"%>