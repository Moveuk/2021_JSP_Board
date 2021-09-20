package com.ldu.exam.exam1.http.controller;

import java.util.List;

import com.ldu.exam.exam1.container.Container;
import com.ldu.exam.exam1.dto.Article;
import com.ldu.exam.exam1.dto.ResultData;
import com.ldu.exam.exam1.http.Rq;
import com.ldu.exam.exam1.service.ArticleService;
import com.ldu.exam.exam1.util.Ut;

public class UsrArticleController extends Controller {
	private ArticleService articleService = Container.articleService;

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "list":
			actionShowList(rq);
			break;
		case "detail":
			actionDetailList(rq);
			break;
		case "write":
			actionShowWrite(rq);
			break;
		case "doWrite":
			actionDoWrite(rq);
			break;
		case "doDelete":
			actionDoDelete(rq);
			break;
		default:
			// 아무것도 없을시 404 띄워야함
			// 우리는 무시하고 print 메세지 넣어줌.
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionDoDelete(Rq rq) {
		int id = rq.getIntParam("id", 0);
		String redirectUri = rq.getParam("redirectUri", "../article/list");

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleByid(id);

		if (article == null) {
			rq.historyBack(Ut.f("게시물이 존재하지 않습니다.", id));
			return;
		}

		articleService.delete(id);

		rq.replace(Ut.f("게시물을 삭제하였습니다.", id), redirectUri);
	}

	private void actionDetailList(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleByid(id);

		if (article == null) {
			rq.historyBack(Ut.f("게시물이 존재하지 않습니다.", id));
			return;
		}

		rq.setAttr("article", article);
		rq.jsp("usr/article/detail");
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
		String redirectUri = rq.getParam("redirectUri", "../article/list");

		// title test
//		title = "";

		if (title.length() == 0) {
			rq.historyBack("title을 입력해주세요.");
			return;
		}
		if (body.length() == 0) {
			rq.historyBack("body를 입력해주세요.");
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
