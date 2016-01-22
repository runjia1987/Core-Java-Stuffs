package org.jackJew.AOP.transaction.declare.schema;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

public class PojoDao {
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 注意: 使用JdbcTemplate会抛出DataAccessException, 此为RuntimeException, 会被事务管理器捕获;
	 * <br> 如果抛出类似SQLException这种checked exception, 则会被事务管理器忽略, 事务回滚机制就失效了.
	 */
	public void insert(Pojo man, boolean validFlag){
		if(validFlag){
			jdbcTemplate.execute("insert into man(id, name, password) values(" + man.getId()
						+ ",'" + man.getName() + "','" + man.getPassword() + "')");
		} else {
			// error sql here!
			jdbcTemplate.execute("insert into man(id, name, password) values()");
		}
	}

}
