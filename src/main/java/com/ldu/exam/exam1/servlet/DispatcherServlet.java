package com.ldu.exam.exam1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldu.exam.exam1.http.Rq;
import com.ldu.exam.exam1.http.controller.Controller;
import com.ldu.exam.exam1.http.controller.UsrArticleController;
import com.ldu.mysqlutil.MysqlUtil;

@WebServlet(urlPatterns = { "/usr/*" })
public class DispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// multiple url bind
//		response.getWriter().append("멀티플 바인드 성공");
		
		Rq rq = new Rq(request, response);
		
		if (rq.isInvalid()) {
			rq.print("올바른 요청이 아닙니다.");
		}
		
		// usrArticleController write 가 호출되면 됨.
//		rq.println("controllerTypeName : " + rq.getControllerTypeName());
//		rq.println("<br>");
//		rq.println("controllerName : " + rq.getControllerName());
//		rq.println("<br>");
//		rq.println("actionMethodName : " + rq.getActionMethodName());
		
		Controller controller = null;
		
		switch (rq.getControllerTypeName()) {
		case "usr" :
			switch (rq.getControllerName()) {
			case "article" :
				controller = new UsrArticleController();
			}
			break;
		}
		
		if(controller != null) {
			// db 커넥션
			MysqlUtil.setDBInfo("localhost", "moveuk", "1234", "jsp_board");
			MysqlUtil.setDevMode(true);
			
			controller.performAction(rq);
			
			// MysqlUtil에서 제대로 못닫았으면 닫아주는 용도
			MysqlUtil.closeConnection();
		}
		

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
