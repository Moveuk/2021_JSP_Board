package com.ldu.exam.exam1.app;

import com.ldu.exam.exam1.container.Container;
import com.ldu.mysqlutil.MysqlUtil;

public class App {

	public static boolean isDevMode() {
		// 이 부분을 false로 바꾸면 production 모드이다.
		return true;
	}
	
	public static void init() {
		// db 커넥션
		MysqlUtil.setDBInfo("localhost", "moveuk", "1234", "jsp_board");
		MysqlUtil.setDevMode(isDevMode());
		// DispatcherServlet에서 이식해옴. 최초에 한번만 하기 위해서

		// 공용 객체 세팅
		Container.init();
	}
	
}
