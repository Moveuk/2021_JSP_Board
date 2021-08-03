package com.ldu.exam.exam1.controller;

import com.ldu.exam.exam1.http.Rq;

public class UsrArticleController extends Controller {

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
		// TODO Auto-generated method stub
		
	}

	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
	}

}
