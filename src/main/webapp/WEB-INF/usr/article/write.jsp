<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="게시물 리스트"/>
<%@ include file="../part/head.jspf"%>

	<!-- section(의미없음) section-article-write(이름 : 글쓰기 의미) / padding - 4 px-->
	<section class="section section-article-write px-4">
		<!-- 알아서 반응형되는 tailwind 기능 container(어느정도 폭을 줄여줌) / margin(mx) auto -->
		<div class="container mx-auto">

			<div class="card bordered shadow-lg">
				<div class="card-title">
					<a href="javascript:history.back();" class="cursor-pointer">
						<i class="fas fa-chevron-left"></i>
					</a>
					<span>게시물 상세페이지</span>
				</div>

				<div class="px-4 py-4">
					<script>
						let ArticleWrite__submitDone = false;
						function ArticleWrite__submit() {
							// 발송 체크를 하여 두번 연속 클릭시 두번이상 발송되는 것을 막음.
							// 최초 1회 사용만 허용.
							if (ArticleWrite__submitDone) {
								return;
							}
							// 제목 내용 없을시 주의 알람
							if (form.title.value.length == 0) {
								alert('제목을 입력해 주세요.');
								form.title.focus();

								return;
							}
							form.submit();
							ArticleWrite__submitDone = true;
						}
					</script>
					<!-- onsubmit에 return false 주면 안보내짐. 조건에 따라 안보내지도록 -->
					<form action=../article/doWrite " method="post"
						onsubmit="ArticleWrite__submit(this); return false;">
						<!-- 게시물 생성 후 detail 화면으로 가기 위하여 uri를 hidden으로 보냄.
						이후 아이디가 생성되면 NEW_ID 자리에 찾아 넣어줌. -->
						<input type="hidden" name="redirectUri" value="../article/derail?id=[NEW_ID]">
						<!-- 데이지 UI 페이지에서 가져온 태그 -->
						<div class="form-control">
							<label class="label"> <span class="label-text">제목</span>>
							</label>
							<!-- width full 꽉차게 , input bordered 하게 -->
							<input class="input input-bordered w-full" type="text"
								maxlength="100" name="title" placeholder="제목을 입력해주세요.">
						</div>
						<div class="form-control">
							<label class="label"> <span class="label-text">내용</span>
							</label>
							<!-- height : 60(15rem) bordered 윤곽지게 -->
							<textarea class="textarea textarea-bordered h-60" name="body"
								maxlength="2000" placeholder="내용을 입력해주세요."></textarea>
						</div>
						<div class="btns">
							<!-- form 내부에서 버튼 누르면 발송되버림.(현재 페이지에서 현재페이지로) -->
							<!-- 그래서 타입을 버튼과 submit으로 만들어줘야함. -->
							<button type="submit" class="btn btn-link">작성</button>
							<button type="button" class="btn btn-link">작성 취소</button>
						</div>
					</form>
				</div>
	</section>
	
<%@ include file="../part/foot.jspf"%>