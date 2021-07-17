<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP BOARD</title>
<!-- 데이지 UI 불러오기 : 테일윈드 + 추가기능 -->
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.1/dist/tailwind.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://cdn.jsdelivr.net/npm/daisyui@1.9.0/dist/full.css"
	rel="stylesheet" type="text/css" />
<!-- 폰트 구성 : 컨텍스트 패스 필요해서 불러옴. -->
<link rel="stylesheet"
	href="<%=request.getContextPath() + "/resource/font.css"%>" />
</head>
<body>
	<!-- section(의미없음) section-article-write(이름 : 글쓰기 의미) -->
	<section class="section section-article-write">
		<!-- 알아서 반응형되는 tailwind 기능 container(어느정도 폭을 줄여줌) / margin(mx) auto -->
		<div class="container mx-auto">
			<form action="">
			<!-- 데이지 UI 페이지에서 가져온 태그 -->
				<div class="form-control">
					<label class="label">
						<span class="label-text">제목</span>
					</label>
					<!-- width full 꽉차게 , input bordered 하게 -->
					<input class="input input-bordered w-full" type="text" maxlength="100" name="title"	placeholder="제목을 입력해주세요.">
				</div>
				<div class="form-control">
					<label class="label">
						<span class="label-text">내용</span>
					</label>
					<!-- height : 60(15rem) bordered 윤곽지게 -->
					<textarea class="textarea textarea-bordered h-60" name="body" maxlength="2000" placeholder="내용을 입력해주세요."></textarea>
				</div>
			</form>
		</div>
	</section>

</body>
</html>