package com.ldu.exam.exam1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldu.mysqlutil.MysqlUtil;

@WebServlet(urlPatterns = { "/usr/*" })
public class DispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 들어오는 파라미터를 UTF-8로 해석
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿이 html 만들때 utf - 8로 만듬.
		response.setCharacterEncoding("UTF-8");	
		
		// 브라우저에 html 파일이 uft-8 형식이라고 알려줌.
		response.setContentType("text/html; charset=UTF-8");

		// multiple url bind
		response.getWriter().append("멀티플 바인드 성공");
		
		// /2021_jsp_board/usr/article/wefwe
		// 들어오는 주소값 받아서 /기준으로 스플릿하고 5 보다 작으면 잘못된 주소 리턴
		// [] / [2021_jsp_board] / [usr] / [article] / [wefwe]
		String requestUri = request.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		int minBitsCount = 5;
		
		if(requestUriBits.length < minBitsCount) {
			response.getWriter().append("올바른 요청이 아닙니다.");
			return;
		}
		
		// /usr/article/write 이 세 개를 꺼내옴
		int controllerTypeNameIndex = 2;
		int controllerNameIndex = 3;
		int actionMethodNameIndex = 4;
		
		String controllerTypeName = requestUriBits[controllerTypeNameIndex];
		String controllerName = requestUriBits[controllerNameIndex];
		String actionMethodName = requestUriBits[actionMethodNameIndex];
		
		// usrArticleController write 가 호출되면 됨.
		response.getWriter().append("<br>");
		response.getWriter().append("controllerTypeName : " + controllerTypeName);
		response.getWriter().append("<br>");
		response.getWriter().append("controllerName : " + controllerName);
		response.getWriter().append("<br>");
		response.getWriter().append("actionMethodName : " + actionMethodName);
		
		// db 커넥션
		MysqlUtil.setDBInfo("localhost", "moveuk", "1234", "jsp_board");

		MysqlUtil.setDevMode(true);

		// MysqlUtil에서 제대로 못닫았으면 닫아주는 용도
		MysqlUtil.closeConnection();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
