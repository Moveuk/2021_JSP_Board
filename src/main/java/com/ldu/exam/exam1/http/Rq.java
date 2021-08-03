package com.ldu.exam.exam1.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;

@Getter
public class Rq{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private boolean isInvalid = false;
	private String controllerTypeName;
	private String controllerName;
	private String actionMethodName;
	
	public Rq(HttpServletRequest request, HttpServletResponse response) {
		// 들어오는 파라미터를 UTF-8로 해석
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 서블릿이 html 만들때 utf - 8로 만듬.
		response.setCharacterEncoding("UTF-8");	
		
		// 브라우저에 html 파일이 uft-8 형식이라고 알려줌.
		response.setContentType("text/html; charset=UTF-8");
		
		this.request = request;
		this.response = response;
		
		// /2021_jsp_board/usr/article/wefwe
				// 들어오는 주소값 받아서 /기준으로 스플릿하고 5 보다 작으면 잘못된 주소 리턴
				// [] / [2021_jsp_board] / [usr] / [article] / [wefwe]
				String requestUri = request.getRequestURI();
				String[] requestUriBits = requestUri.split("/");

				int minBitsCount = 5;
				
				if(requestUriBits.length < minBitsCount) {
					isInvalid = true;
					return;
				}
				
				// /usr/article/write 이 세 개를 꺼내옴
				int controllerTypeNameIndex = 2;
				int controllerNameIndex = 3;
				int actionMethodNameIndex = 4;
				
				this.controllerTypeName = requestUriBits[controllerTypeNameIndex];
				this.controllerName = requestUriBits[controllerNameIndex];
				this.actionMethodName = requestUriBits[actionMethodNameIndex];
	}
	
	public void print(String string) {
		try {
			response.getWriter().append(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void println(String string) {
		print(string + "\n");
	}

	public void jsp(String jspPath) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/"+jspPath+".jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
