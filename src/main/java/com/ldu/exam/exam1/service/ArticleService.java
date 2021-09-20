package com.ldu.exam.exam1.service;

import java.util.List;

import com.ldu.exam.exam1.Repository.ArticleRepository;
import com.ldu.exam.exam1.container.Container;
import com.ldu.exam.exam1.dto.Article;
import com.ldu.exam.exam1.dto.ResultData;
import com.ldu.exam.exam1.util.Ut;

public class ArticleService {
	// service가 직접 DB에 접근 하지 않고 DAO에 보냄.
	private ArticleRepository articleRepository = Container.articleRepository;
	
	public ResultData write(String title, String body) {
		int id = articleRepository.write(title, body);
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id), "id", id);
	}

	public List<Article> getForPrintArticles() {
		return articleRepository.getForPrintArticles();
	}

	public Article getForPrintArticleByid(int id) {
		return articleRepository.getForPrintArticleById(id);
	}

	public ResultData delete(int id) {
		articleRepository.delete(id);
		
		// 서비스에서 처리한 내용을 로그기록처럼 남기는 것이 좋다.
		return ResultData.from("S-1", Ut.f("%d번 게시물이 삭제되었습니다.", id), "id", id);
	}

}
