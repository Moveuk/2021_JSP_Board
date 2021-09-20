<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 리스트" />
<%@ include file="../part/head.jspf"%>

<%@ page import="java.util.List"%>
<%@ page import="com.ldu.exam.exam1.dto.Article"%>
<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
%>

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

			<div class="px-4">
				<c:forEach items="${articles }" var="article">
					<c:set var="detailUri" value="../detail?id=${article.id}"></c:set>
				
					<div class="py-4">
						<!-- css grid 기능 첫열 100px 고정 나머지 통째로 100% 설정 -->
						<div class="grid gap-3" style="grid-template-columns: 100px 1fr;">
							<a href="${detailUri }">
								<!-- class="rounded-full" : 동그랗게 잘라주는 테일윈드 기능/ 제목 사진 이미지 넣음. -->
								<!-- w-full : width를 full로 주어 확장하게끔 이유 : grid를 100px로 주었기 때문에.-->
								<img class="rounded-full w-full" src="https://i.pravatar.cc/200?img=37" alt="">
							</a>
							<a class="hover:underline cursor-pointer">
								<span class="badge badge-outline">제목</span>
								<!-- 3줄이상이면 ... 으로 표시하는 기능 -->
								<div class="line-clamp-3">${article.titleForPrint}</div>
							</a>
						</div>

						<!-- mt-3 : margin 3px / gap-3 : grid의 gap을 줄 수 있다. -->
						<!-- sm : 작은 화면일 때는 2 줄 허용 / lg 큰화면 일때는 4줄 허용 -->
						<div class="block mt-3 grid sm:grid-cols-2 lg:grid-cols-4 gap-3">
							<a href="${detailUri }" class="hover:underline">
								<span class="badge badge-primary">번호</span>
								<span>${article.id}</span>
							</a>

							<a href="${detailUri }" class="cursor-pointer hover:underline">
								<span class="badge badge-accent">작성자</span>
								<span>${article.memberId}</span>
							</a>

							<a href="${detailUri }" class="hover:underline">
								<span class="badge">등록날짜</span>
								<span class="text-gray-600 text-light">${article.regDate}</span>
							</a>

							<a href="${detailUri }" class="hover:underline">
								<span class="badge">수정날짜</span>
								<span class="text-gray-600 text-light">${article.updateDate}</span>
							</a>

							<!-- mt-3 : margin 3px -->
							<!-- sm : 작은 화면일 때는 2 줄 허용 / xl 가장 큰 화면일 때? -->
							<a class="mt-3 hover:underline cursor-pointer col-span-1 sm:col-span-2 xl:col-span-3">
								<span class="badge badge-outline">본문</span>

								<div class="mt-2">
									<img class="rounded" src="https://picsum.photos/id/237/300/300" alt="" />
								</div>

								<div class="line-clamp-3">${article.bodySummaryForPrint}</div>
							</a>
						</div>
				</c:forEach>
				<hr />
			</div>
</section>

<%@ include file="../part/foot.jspf"%>