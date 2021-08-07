package com.ldu.exam.exam1.http.controller;

import com.ldu.exam.exam1.dto.ResultData;
import com.ldu.exam.exam1.http.Rq;
import com.ldu.exam.exam1.http.service.ArticleService;

public class UsrArticleController extends Controller {
	private ArticleService articleService;
	
	public UsrArticleController() {
		articleService = new ArticleService();
	}
	
	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "write":
			actionShowWrite(rq);
			break;
		case "doWrite":
			actionDoWrite(rq);
		default:
			break;
		}
	}

	private void actionDoWrite(Rq rq) {
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		
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
		
		// 테스트
//		rq.printf("title : %s<br>", title);
//		rq.printf("body : %s<br>", body);
		
		rq.printf(writeRd.getMsg());
	}

	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
	}

}
