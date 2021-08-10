package com.ldu.exam.exam1.container;

import com.ldu.exam.exam1.Repository.ArticleRepository;
import com.ldu.exam.exam1.http.controller.UsrArticleController;
import com.ldu.exam.exam1.service.ArticleService;

public class Container {
	// 조금 더 근본적인 것부터 만들어 갈 것.
	public static ArticleRepository articleRepository;

	public static ArticleService articleService;

	public static UsrArticleController usrArticleController;

	public static void init() {
		articleRepository = new ArticleRepository();
		articleService = new ArticleService();
		usrArticleController = new UsrArticleController();
	}
}