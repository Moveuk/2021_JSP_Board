package com.ldu.exam.exam1.http.Repository;

import com.ldu.mysqlutil.MysqlUtil;
import com.ldu.mysqlutil.SecSql;

public class ArticleRepository {

	public int write(String title, String body) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("Set regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		int id = MysqlUtil.insert(sql);
		
		// MysqlUtil 에서 insert시 id값을 받도록 했음.
		return id;
	}

}
