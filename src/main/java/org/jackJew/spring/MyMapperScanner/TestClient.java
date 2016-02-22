package org.jackJew.spring.MyMapperScanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TeacherDao teacherDao = context.getBean(TeacherDao.class);
		System.out.println("mapper teacherDao.getId(): " + teacherDao.getId());
		
		StudentDao studentDao = context.getBean(StudentDao.class);
		System.out.println("mapper studentDao.getName(): " + studentDao.getName());
		
		((AbstractApplicationContext)context).close();
	}

}
