# JSP 게시판 만들기
<br>
<hr>

## STS 설치 및 톰캣 9 환경 구성.



<br><br>
<hr>


### 오류 종류

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


<br><br>
<hr>


## git 설치 및 STS 깃 저장소 설정

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


## 서블릿 UTF-8 설정

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

## 게시물 등록 서블릿 작성

 전통적인 JSP 사이트는 요청(기능 / ex> 글쓰기 회원가입 등등) 하나하나 마다 서블릿을 작성하여 사이트를 구동한다.





























