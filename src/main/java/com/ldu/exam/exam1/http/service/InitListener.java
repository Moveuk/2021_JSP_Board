package com.ldu.exam.exam1.http.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ldu.exam.exam1.app.App;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	App.init(); // 우리 앱 웹에 필요한 초기 설정을 하기 위해서 사용함.
		System.out.println("On start web app");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("On shutdown web app");
	}

}
