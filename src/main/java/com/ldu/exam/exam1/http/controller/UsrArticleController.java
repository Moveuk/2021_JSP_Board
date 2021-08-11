package com.ldu.exam.exam1.http.controller;

import java.util.List;

import com.ldu.exam.exam1.container.Container;
import com.ldu.exam.exam1.dto.Article;
import com.ldu.exam.exam1.dto.ResultData;
import com.ldu.exam.exam1.http.Rq;
import com.ldu.exam.exam1.service.ArticleService;

public class UsrArticleController extends Controller {	
	private ArticleService articleService = Container.articleService;
	
	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "list":
			actionShowList(rq);
			break;
		case "write":
			actionShowWrite(rq);
			break;
		case "doWrite":
			actionDoWrite(rq);
		default:
			// 아무것도 없을시 404 띄워야함
			// 우리는 무시하고 print 메세지 넣어줌.
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionShowList(Rq rq) {
		List<Article> articles = articleService.getForPrintArticles();

		rq.setAttr("articles", articles);
		
		rq.jsp("usr/article/list");
	}

	private void actionDoWrite(Rq rq) {
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		// 없으면 두번째 인자로 넘김.(Default)
		String redirectUri = rq.getParam("redirectUri","../article/list");
		
		// title test
//		title = "";
		
		if(title.length() == 0) {
			rq.historyBack("title을 입력해주세요.");
			return;
		}
		if(body.length() == 0) {
			rq.historyBack("title을 입력해주세요.");
			return;
		}
		
		ResultData writeRd = articleService.write(title, body);
		int id = (int) writeRd.getBody().get("id");
		// 테스트
//		rq.printf("title : %s<br>", title);
//		rq.printf("body : %s<br>", body);
		
		redirectUri = redirectUri.replace("[NEW_ID]", id + "");
		
		rq.replace(writeRd.getMsg(), redirectUri);
	}

	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
	}

}
