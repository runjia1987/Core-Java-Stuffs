package org.jackJew.spring.MyMapperScanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestClient {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
		jdbcTemplate.execute("insert into man(id, name, password) values(100, 'admin', 'test');");
		
		TeacherDao teacherDao = context.getBean(TeacherDao.class);
		System.out.println("mapper teacherDao.getId(): " + teacherDao.getId());
		
		StudentDao studentDao = context.getBean(StudentDao.class);
		System.out.println("mapper studentDao.getName(): " + studentDao.getName());
		
		jdbcTemplate.execute("delete from man where id=100;");
		
		((AbstractApplicationContext)context).close();
	}

}
