package com.ldu.exam.exam1.http.service;

import com.ldu.exam.exam1.dto.ResultData;
import com.ldu.exam.exam1.http.Repository.ArticleRepository;
import com.ldu.exam.exam1.util.Ut;

public class ArticleService {
	// service가 직접 DB에 접근 하지 않고 DAO에 보냄.
	private ArticleRepository articleRepository;
	
	public ArticleService() {
		articleRepository = new ArticleRepository();
	}
	
	public ResultData write(String title, String body) {
		int id = articleRepository.write(title, body);
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성 되었습니다.", id), "id", id);
	}

}
