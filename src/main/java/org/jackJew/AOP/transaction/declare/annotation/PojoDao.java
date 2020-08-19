package org.jackJew.AOP.transaction.declare.annotation;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("transactionDao")
public class PojoDao {

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PojoDao self;

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
