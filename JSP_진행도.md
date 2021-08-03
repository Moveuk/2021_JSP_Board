# JSP 게시판 만들기
<br>
<hr>

## 1~6. 개발환경세팅 : 
 1. STS 설치
 2. 깃 저장소 생성
 3. STS에 JSP, JAVASCRIPT, emmet 플러그인 설치   
	- emmet 사용을 위한 emmet에 JSP 설정     
	![image](https://user-images.githubusercontent.com/84966961/126035645-dda625d7-3dab-41d8-8dca-a7c5a8c3ed47.png)   

 4. 톰캣 9 설치   
 5. STS utf-8 및 폰트 설정
		- STS 내부 모든 인코딩 utf-8로 변경 2021_JSP 저장소 1강에 정리되어있음.   
		- 폰트 d2coding 사용   

<br><br>
<hr>

## 7. 첫 서블릿 생성

	프로젝트를 오른쪽 클릭하여 서블릿 생성.

<br><br>
<hr>

## 8. git 설치 및 STS 깃 저장소 설정

1. git 설치. 구글링   

2. STS 프로젝트 터미널 open   
	- STS에서 왼쪽 프로젝트를 클릭한 상태로 consol open
	 	- 마우스 오른쪽 버튼 > show in > Terminal 
	 	- 프로젝트 선택된 상태로 `Ctrl + Alt + T` 단축키(프로젝트 선택 안하고 하면 이상한 폴더로 기본이 잡힘)   
   
3. git 연동을 위한 이름, 이메일 설정.
 	- Terminal 창에 `git config --global user.name "내 git 이름"`, `git config --global user.email "내 git 이메일"`을 각각 입력하여 연동.   

4. git : local 저장소와 github 연동
	- `git remote add origin 깃_저장소_주소` 를 입력하여 연동. origin은 저장 이름.   

5. .gitingnore 설정 및 저장
	- 깃 업로드시 필요없는 파일들은 커밋하지 않기 위해서 설정하는 파일.
	- 깃 프로젝트를 오른쪽 버튼을 눌러 other > file 검색하여 `.gitignore` 파일 생성 후 다음 내용 삽입.
	- `.gitignore` 파일은 프로젝트 최외곽에 존재해야 git에서 인지함.

```
	.classpath
	.project
	.settings/
	target/
	build/
	bin/
	.factorypath
```

![image](https://user-images.githubusercontent.com/84966961/126033030-1534d585-d717-4419-9ee7-f6b180a77c5f.png)


6. git push
	- Terminal에 `git push origin main` 입력

![image](https://user-images.githubusercontent.com/84966961/126033053-fa0b79f5-d392-465c-a805-1b41971504dc.png)

<br><br><br>

**깃 연결 순서**    
![image](https://user-images.githubusercontent.com/84966961/126033441-0e073fbb-00ae-4cd0-a1ce-f371a3ffdf27.png)


<br><br>
<hr>


## 9. 서블릿 UTF-8 설정

```java
@WebServlet(name = "UsrHomeMainServlet", urlPatterns = { "/usr/home/main" })
public class HomeMainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		
		// 들어오는 파라미터를 UTF-8로 해석
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿이 html 만들때 utf - 8로 만듬.
		response.setCharacterEncoding("UTF-8");	
		
		// 브라우저에 html 파일이 uft-8 형식이라고 알려줌.
		response.setContentType("text/html; charset=UTF-8");
		
		response.getWriter().append("안녕").append(request.getContextPath());
		response.getWriter().append("<br>"+name);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```


<br><br>
<hr>

## 10~11. 게시물 등록 서블릿 작성
 
 1. 전통적인 JSP 사이트는 요청(기능 / ex> 글쓰기 회원가입 등등) 하나하나 마다 서블릿을 작성하여 사이트를 구동한다.   
   
![image](https://user-images.githubusercontent.com/84966961/126035362-89812c21-7724-4760-8d73-55d128708ab3.png)   

위 그림 처럼 직접 유저가 JSP 로 접근 못하게 하려면 폴더를 `WEB-INF`에 넣으면 서블릿 내부에서만 접근이 가능해진다.   
   
<img src="https://user-images.githubusercontent.com/84966961/126035397-3a6367bd-997c-4fe2-82e3-bee74b0baa4a.png" width="40%">   

 2. 데이지 UI 홈페이지에 찾아가 URL 링크를 얻어 jsp 파일에 넣어줌.
 3. 리소스 폴더를 만들어 font.css 작성 : 롯데마트 폰트 사용
 4. 다음과 같은 코드 작성

**write.jsp**
```jsp
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
```





<br><br>
<hr>

## 12. 게시물 등록 서블릿 작성
 주요 기능 
  - 전송시 최초 1회만 전송 되도록 스크립트 구성
  - 내용 체크 후 `length == 0` 일시 경고 메세지
  - 버튼 사용 : https://daisyui.com/components/button
   
![image](https://user-images.githubusercontent.com/84966961/126053431-ead191b4-af65-4f6f-8430-e365afff2394.png)


git commit :    
https://github.com/Moveuk/2021_JSP_Board/commit/cdbd9c69e2b18a8f6ee147f70fe0984b10678ad7


<br><br>
<hr>

## 13. 모바일 환경에서 축소 방지 
 주요 기능 
  - code pen > setting > HTML > `↑ Insert the most common viewport meta tag` click > copy contents in `Stuff for <head>`   
   
![image](https://user-images.githubusercontent.com/84966961/126053398-34d59a96-3002-4e74-8de3-c62a42ae5c2d.png)


git commit :    
 https://github.com/Moveuk/2021_JSP_Board/commit/2145c0e136b813297e75e1b2fc00379598095fc9
 

<br><br>
<hr>

## 14. 게시물 작성 처리 서블릿
 주요 기능 
  - post로 보낸 데이터 넘어오는지 확인 : getParameter > response.getWriter().append(title); > 출력
  - URL pattern 정리 해줘야함. : /usr/article/doWrite
   


git commit :    
 https://github.com/Moveuk/2021_JSP_Board/commit/c8dcd03177509f838704690e447703cd4fb61500


 

<br><br>
<hr>

## 15. 깃 저장소를 clone 해서 프로젝트 이어나가기
 주요 기능 
  - 기존 local 파일 다 날리고 git에서 받아오기
  - git bash > git clone https://github.com/Moveuk/2021_JSP_Board   
    
![image](https://user-images.githubusercontent.com/84966961/126053774-24f0c880-fe19-4c83-81ab-d37f897c6257.png)
   
  - STS > File > Import > General > Projects from Folder or Archive > Directory... > git clone한 위치 > Finish
    
![image](https://user-images.githubusercontent.com/84966961/126053800-6cbe0658-0d53-4f9a-93a8-b499fd8b81ac.png)

  - 오류난 상태 > 해당 프로젝트 Properties > Project Facets > Dynamic Web Module check > Runtimes > Apache 연결 > Tomcat check   
  - 이 과정을 통해서 STS가 불러온 프로젝트가 동적 웹 프로젝트임을 알려주고 톰캣을 사용한다는 것을 알려줘야함.

![image](https://user-images.githubusercontent.com/84966961/126053855-9e3e78e8-4340-4613-9806-d3361760bab0.png)

  - 임포트 후 톰캣 포트가 다시 8080으로 초기화되므로 원하는 포트로 다시 잡아줘야함   
![image](https://user-images.githubusercontent.com/84966961/126053915-8d466e25-852f-4c30-b855-9e232dcb8ca1.png)   

  - 서버가 정신 못차리면 Project > clean 해주고 Project 우클릭 > refresh 해주고 STS 껐다 킨다.

git commit :    
  


 

<br><br>
<hr>

## 16. MysqlUtil 프로젝트 세팅
 주요 기능 
  - util 적인 기능을 구성하려면 기존 프로젝트 내부가 아니라 격리된 프로젝트를 새로 만들어서 진행한다.
  - 새로운 git 저장소와 새로운 mysql_util 기능 구현을 위한 java 프로젝트를 만든다.
  - 깃 저장소 연결
![image](https://user-images.githubusercontent.com/84966961/126054257-db29e87e-30b8-4cba-818b-e4173b9c6e82.png)
  - vim .gitignore로 만드는 무시하는 종류들은 다음과 같다.   
```
.classpath
.settings
.project
bin/
```
  - `esc`를 누르고 :wq(저장 후 vim 종료)를 입력 후 엔터 하면 빠져나온다.
  - git status를 입력하면 add 해야할 종류들이 줄어든 것을 확인 가능하다.    
참고 게시글 : https://gbsb.tistory.com/11   

git commit :    
 https://github.com/Moveuk/java_mysql_util/commit/5aad6aface1161327a14a853fa15c798f357376a


 

<br><br>
<hr>

## 17. Mysql 설치 및 환경 세팅 후 DB 연결 
 주요 기능 
  - Mysql 설치
MySQL : 오픈소스 
오라클 : 기업

오라클이 MySQL을 샀고 오픈소스 진영에서 다른 DB를 개발함.

MySQL => MariaDB : 정신 계승하여 오픈소스 개발. 사실상 MySQL

맥 => MySQL이 이미 깔려있음.

<br>

 - XAMPP % sqlyog community edition 최신 버전 설치   
XAMPP (X(크로스 플랫폼의 뜻에서) Apache MariaDB PHP Peal)   
   
https://www.apachefriends.org/download.html   
   
설치시 거의 모든 체크박스(웹서버 관련) 제거 후 설치   
   
https://github.com/webyog/sqlyog-community/wiki/Downloads   
   
XAMPP에서 MySQL config 클릭 > my.ini 텍스트 파일 클릭   
![image](https://user-images.githubusercontent.com/84966961/126055202-eb6f86c8-636d-466a-b2d0-272b34cf7cd9.png)     

MySQL 서버 내부에서 대소문자 구별하게 해주는 속성 추가   
 ![image](https://user-images.githubusercontent.com/84966961/126055185-886d4156-fafc-47c4-b3b8-05faafdc3634.png)   

uft-8 사용을 위한 주석 제거   
![image](https://user-images.githubusercontent.com/84966961/126055230-5e495e9b-9cae-42e1-8283-cae94d25372f.png)   

속성 설정 후 XAMPP에서 실행   
![image](https://user-images.githubusercontent.com/84966961/126055244-37a4770f-19bf-4242-8413-91020185d04e.png)   
   
만약 3306 port를 다른 소프트웨어가 쓰고 있다면 my.ini에서 port 번호 변경 ex)3307   
![image](https://user-images.githubusercontent.com/84966961/126055274-d8c8ce53-aad1-4519-9b29-cfa512de0083.png)   


<br>

  - sqlyog 설치 후 실행 : mysql 속성 과정을 진행 해야 접속 가능   
![image](https://user-images.githubusercontent.com/84966961/126054865-b60762d8-22d4-43cb-832d-6733145597dd.png)

  - new 새 계정 `root@xampp` 열고 만듬(맨처음 xampp는 id : root에 비밀번호가 없음.)   
변경사항에 대해서 변경 허락   

  - 권한 부여 : sql 명령어   
```
GRANT ALL PRIVILEGES ON *.* TO moveuk@`%` IDENTIFIED BY '1234';

id moveuk / pwd 1234 
`%` : 어디서든 접근 가능 (`localhost`로 하면 원격으로 접속 못함.)
moveuk이 없으니 만들어줌.
```
![image](https://user-images.githubusercontent.com/84966961/126055345-99c187ed-1ea7-418d-a3d1-032d12b6dbcd.png)   

`Ctrl + A` 눌러서 전체 선택후 `F9` 눌러 실행.   
   
![image](https://user-images.githubusercontent.com/84966961/126055388-9cf590fa-c8c3-4613-b6e8-021490f8f65b.png)
   

<br>

  - 권한 부여 후 끄고 새로 만든 계정으로 로그인   
![image](https://user-images.githubusercontent.com/84966961/126055435-04a4f728-ad15-41e5-8f4f-a2d4535eb4e3.png)    
   
moveuk으로 연결 성공.
![image](https://user-images.githubusercontent.com/84966961/126055446-f55246ef-b17a-490b-a136-676cf98f6a88.png)   
   

<br>

  - DB 세팅 완료 후 DB 연동

```
DROP DATABASE IF EXISTS mysqlutil;
CREATE DATABASE mysqlutil;
USE mysqlutil;
```
   
실행   
![image](https://user-images.githubusercontent.com/84966961/126055490-25a56767-0e4a-4d7e-a34f-b6ebf6925357.png)   
   

<br>

  - 프로젝트에 db.sql 파일 만들어 sql문 저장   
![image](https://user-images.githubusercontent.com/84966961/126055518-95760abc-693e-432a-8186-161dfa204d62.png)   
![image](https://user-images.githubusercontent.com/84966961/126055533-e6c380b6-15bb-489e-a6ee-131d2b26b277.png)   


<br>

  - DB_URL 분석   

 속성 | 설명
 -|-
 `jdbc:mysql://` | JDBC 통해 mysql 접속
 `localhost:3306/` | 호스트 번호
 `mysqlutil?` | DB 이름
 `useUnicode=true&characterEncoding=utf8&` | 한글 관련 설정 
 `autoReconnect=true&` | 끊겼을 때 다시 연결
 `serverTimezone=Asia/Seoul&` | 시간대 설정
 `useOldAliasMetadataBehavior=true&` | 테이블 칼럼이랑 데이터 베이스 대소문자가 잘 반영되게하는 속성
 `zeroDateTimeNehavior=convertToNull&` | 0000-00-00 00:00:00 이런 문법은 자바에서 허용되지 않으므로 null로 치환
 `connectTimeout=60";` | 60초까지 연결 가능


<br>

  - db 접근 계정 변경   
```java
	// Database credentials
	static final String USER = "moveuk";
	static final String PASS = "1234";
```



<br>

  - DB 테이블 생성 및 테스트
```
DROP DATABASE IF EXISTS mysqlutil;
CREATE DATABASE mysqlutil;
USE mysqlutil;

CREATE TABLE article (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` TEXT NOT NULL
);

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';
```

<br>

  - 들어온 데이터 확인   
![image](https://user-images.githubusercontent.com/84966961/126055737-23b7ed52-54c3-444b-8a66-9858d2d09cab.png)   
  
<br>
  
  - STS test.java 파일 실행   
![image](https://user-images.githubusercontent.com/84966961/126055786-b7b64c60-9949-464e-a308-b844e9a32c64.png)

  - 쿼리문 하나 가져오는데 너무 많은 code 소요. 나중에 바꾸자.

<br>

   
git commit :    
 
 https://github.com/Moveuk/java_mysql_util/commit/9d7d45d0f86deeb49917ad0828332e398bc40a35


<br><br>
<hr>

## 18. 커밋 메세지 바꾸기 
 주요 기능    
  - 최근 커밋 메세지 바꾸기   
   ![image](https://user-images.githubusercontent.com/84966961/126055922-e2be10af-66e0-4d0f-ad88-12d16cab2993.png)   

git commit :    
 https://github.com/Moveuk/java_mysql_util/commit/9d7d45d0f86deeb49917ad0828332e398bc40a35



<br><br>
<hr>

## 19~20. MySQL 라이브러리 작성 및 테스트 
 주요 기능 : 디테일하게 적으면 적을 게 많음(주석확인) 중요 내용들만.     
  - MysqlUtil 이라는 DBCP 접속 객체 DAO와 SecSql 이라는 sql문 정리 객체를 나눔.
  - 게시판에 대한 db 값을 Map 으로 받아 select할 경우에 여러 튜플이 나올경우 List로 받아옴.
  - 예외처리의 경우 Runtime Exception을 상속 받아 만들어 예외 발생시 해당 스레드가 바로 죽고 사라지게 만들어줌.
  - 여러 튜플, 단일 튜플, 단일 String 값, 단일 int 값, boolean 값등 다양하게 받을 수 있도록 메서드 작성.


git commit :    
 https://github.com/Moveuk/java_mysql_util/commit/6433463ad6b453fe26dcf71e6b885c7f4ea5a438





<br><br>
<hr>


## 21. 잭슨 기본 예제
 주요 기능      
  - 값들을 DTO 객체로도 바꿀 수 있고, HashMap으로도 바꿀 수 있고, JSON으로도 변형이 가능함. 
  - 다음 3가지 받은 후 STS 빌드 패스 추가.   
![image](https://user-images.githubusercontent.com/84966961/127147058-678ab4ef-208a-461d-9b1d-dcb90578483a.png)   

git commit :    
 https://github.com/Moveuk/java_mysql_util/commit/ca80ab7fbeca4ccd6cd58f484951ea2971463c27




<br><br>
<hr>



## 22.  잭슨, 리스트 파싱 및 제너릭 적용
 주요 기능      
  - 리스트 파싱 테스트 1 : 리스트 형태로 받음
  - 리스트 파싱 테스트 2 : 각 객체를 맵으로 받고 튜플을 리스트로 받음
  - 리스트 파싱 테스트 3 : 제이슨 스트링 집합을 타입레퍼런스 자바빈 형태 리스트로 변환함
  - 리스트 파싱 테스트 4 : 스트링을 2번째처럼 한 번 바꾸고 다시 세번째 테스트 형태로 바꿀 수 있음.


  -> `Json`, `List<Map<String, Object>>`, `List<Article>`(자바 빈 형태) 이 세 형태는 자유롭게 변환이 가능함.

git commit :    
 https://github.com/Moveuk/java_mysql_util/commit/11d3ab6ac1fd66245ff8fea31853d6eb18b308de

	
	
<br><br>
<hr>

## 23. MysqlUtil 라이브러리에서 파싱한 데이터를 Map이 아닌 특정 객체 형태로 만드는 기능 추가 
 주요 기능      
  - Select 했을 때 두번째 인자로 주면 Article DTO 형태로 보냄.

git commit :    
 https://github.com/Moveuk/java_mysql_util/commit/127a30690de96b788b5e5b79612072a53726cab1


<br><br>
<hr>



## 24. 게시물 작성페이지에 카드디자인 적용
 주요 기능      
  - 카드 디자인 적용 디자인 시스템 적용
  - 폰트 어썸 : cdnjs.com 홈페이지에서 구할 수 있음.

![image](https://user-images.githubusercontent.com/84966961/127516383-d1ba3a4f-1a3a-43df-a9c0-ff63cbc45538.png)


git commit :    
https://github.com/Moveuk/2021_JSP_Board/commit/3c6eec789737d603facd1cad0c424ecd58a9b4cd

<br><br>
<hr>



## 25. 
 주요 기능      
  - 테스트 db 환경을 jsp_board 프로젝트에 구성함. 메이븐을 사용하면 라이브러리가 자동으로 구성되나봄.
  - title을 바로 넣어주는 것이 아니라 따로 매개변수를 넣어주어야 sqlInjection에 관련된 해킹 위협이 사라진채로 들어가게 된다.
```
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
```



git commit :    
 https://github.com/Moveuk/2021_JSP_Board/commit/d8af2a7bf00726b5903955b97e798fa6af754379

<br><br>
<hr>



## 26. 게시물 리스트, MysqlUtil로 SELECT
 주요 기능      
  - 캡슐화 dto 객체를 만들 때는 필드를 `private`하게 만들어 감싸주자.
  - list 페이지 구현

느낀 점
  - 주요 기술(여기에서는 sql 접속 및 CRUD 기능 구현)만 제대로 구현 가능하다면 나머지 페이지 구성은 생각보다 쉽다.
  - 지금 하는 것은 학원에서와 마찬가지로 전통적인 jsp 페이지 구현이다.
  - 이 개념을 이해하고 SPRING 개발로 들어간다.


git commit :    
  https://github.com/Moveuk/2021_JSP_Board/commit/61e8894f5a0e39e090a223d80a63cb815cd0020a

<br><br>
<hr>




## 27. 모든 서블릿 없애고, 디스패처 서블릿 구현
 주요 기능      
  - 실무에서는 디스패처 서블릿으로 통일하고 컨트롤러를 만들어 일을 분배한다. (요청 하나하나마다 서블릿을 만들기 힘드므로 ) 
  - `*`을 통해 multiple url bind 가 가능하다. : 문제점 : 아무런 주소나 다 들어 올 수 있음.
```java
@WebServlet(urlPatterns = { "/usr/*" })
public class DispatcherServlet extends HttpServlet {
```
**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/127773179-6f8db016-5d79-40ec-bdd8-420ca8d68034.png)

  - 변수명 일괄 변경 : `alt + shift + R` 
  - 여기에서는 들어오는 url을 String.split으로 쪼개서 들어오는 값으로 알맞는 Controller를 붙여준다.
 -> `/2021_jsp_board/usr/article/write` 를 String 배열로 쪼갬
**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/127773730-004b21c4-77fc-4254-98d1-38f92677689b.png)



git commit :    
  https://github.com/Moveuk/2021_JSP_Board/commit/6252efbdca79aebc282d9d2c2427689e42e50c60

<br><br>
<hr>


## 28 ~ 29 . Req와 Resp의 어탭터 클래스인 Rq 클래스 구현
 주요 기능      
  - SpringMVC : 디스패처 서블릿과 컨트롤러
  - 컨테이너 : Autowired, new 없이 객체 생성 및 활용.
  - Request를 Rq 클래스로 만들어서 adaptor 로 활용함. : adaptor pattern 찾아보기

git commit :    
  https://github.com/Moveuk/2021_JSP_Board/commit/640c7e1fb048e40ceb5d3af50bf4632226b94bc7

<br><br>
<hr>


## 30. Rq클래스에 jsp 연결기능 구현
 주요 기능      
  - 분리한 url을 jsp에 불러 정리하게끔.

git commit :    
  https://github.com/Moveuk/2021_JSP_Board/commit/b4b533c2879c6567b358d56891ec06866d42abab

<br><br>
<hr>


## . 
 주요 기능      
  - 

git commit :    
  

<br><br>
<hr>







## 진행 도중 오류 종류

1. 콘솔 버퍼가 작아서 경고창 뜸.
 내용대로 늘려주었음.
 
![image](https://user-images.githubusercontent.com/84966961/126031220-4aa1c972-2928-41d9-bd34-e5a60d3b238c.png)

2. 기존 SQL 포트를 8080 으로 쓰기 때문에 충돌하여 새로운 톰캣 9 버전의 포트를 8282 로 옴겨줌.   
   
 옴기는 방법 : 
 - 본인은 STS 에서 Server를 연동해놨기 때문에 Servers > server.xml 파일에서 포트번호 `8080`을 원하는 포트 번호로 바꿔주었음. 또 다시 포트가 충돌 날 수 있으므로 아래 기준에 따라서 잘 보고 사용할것. 높은 숫자일수록 덜 충돌날 확률이 있다고 함.


```
0번 ~ 1023번: 잘 알려진 포트 (well-known port)

1024번 ~ 49151번: 등록된 포트 (registered port)

49152번 ~ 65535번: 동적 포트 (dynamic port)

(출처 위키백과)
```

![image](https://user-images.githubusercontent.com/84966961/126031364-82d159d9-268b-4321-a9cf-f074ff872d2c.png)

 - 다음과 같은 방법으로 포트 번호를 바꿔줄 수 있음.
	1. server 창에서 서버 더블클릭.
	2. 밑에 `overview` 탭인지 확인.
	3. 오른쪽 위 html 1.1 포트번호 더블클릭 후 변경.

![image](https://user-images.githubusercontent.com/84966961/126031516-fdbce506-c6b0-4eea-bc54-a2d9b149eaf7.png)

3. git 초기 위치를 잘못 잡아서 submodule이 생긴 상황 ( 2시간 오류처리 걸림..ㅜㅜ)

```
폴더 구조
	project - src - exam - .git
			- .git

이렇게 최초 .git을 만들고 실수로 터미널 위치를 잘못 파악하여 git init 을 통해 깃 폴더 생성.

```
   
 이런 오류로 내부에 submodule이 있는 것으로 파악하여 깃허브에서는 폴더 내부에 화살표 모양이 생겨 접근이 불가능했음.    
 터미널에서는 접근이 안되니 add도 안되고 commit도 안되고 push를 하자니 오류가 발생함.   
 그래서 2시간 구글링을 통해 2곳의 .git 폴더를 없애고 난 후 정상 작동함.

