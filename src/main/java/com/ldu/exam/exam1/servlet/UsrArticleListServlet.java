package com.ldu.exam.exam1.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldu.exam.exam1.dto.Article;
import com.ldu.mysqlutil.MysqlUtil;
import com.ldu.mysqlutil.SecSql;

@WebServlet(name = "UsrArticleWriteServlet", urlPatterns = { "/usr/article/list" })
public class UsrArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		
		// 들어오는 파라미터를 UTF-8로 해석
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿이 html 만들때 utf - 8로 만듬.
		response.setCharacterEncoding("UTF-8");	
		
		// 브라우저에 html 파일이 uft-8 형식이라고 알려줌.
		response.setContentType("text/html; charset=UTF-8");
		
		// db 커넥션
		MysqlUtil.setDBInfo("localhost", "moveuk", "1234", "jsp_board");

		MysqlUtil.setDevMode(true);

		
		// 넘어오는것 확인용도
//		response.getWriter().append(title + "<br>");
//		response.getWriter().append(body + "<br>");
	
		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append("from article AS A");
		sql.append("ORDER BY A.id DESC");
		List<Article> articles = MysqlUtil.selectRows(sql, Article.class);
		
		request.setAttribute("articles", articles);
		
		// MysqlUtil에서 제대로 못닫았으면 닫아주는 용도
		MysqlUtil.closeConnection();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/usr/article/list.jsp");
		dispatcher.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
