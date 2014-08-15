package org.jack.MVEL.test;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.junit.BeforeClass;
import org.junit.Test;

public class MVEL_Test {
	
	@BeforeClass
	public static void beforeClass(){
		//
	}
	
	/**
	 * Bean property
	 */
	@Test
	public void propertyBeanExpression (){
		Serializable PropertyExpression1 = MVEL.compileExpression("this.getName().substring(2)");
		// this.name.toString()
		// Note: In situations that the field in the object is declared as public, 
		// MVEL will still prefer to access the property via its getter method.
		
		User user = new User("jack", "123456");
		Object result = MVEL.executeExpression(PropertyExpression1, user);
		System.out.println("propertyBeanExpression： " + result);
	}
	
	/**
	 * list access by index
	 */
	@Test
	public void propertyListExpression(){
		Serializable PropertyExpression2 = MVEL.compileExpression("this[0].password");
		User user = new User("jack", "123456");
		List<User> userList = new LinkedList<User>();
		userList.add(user);
		Object result = MVEL.executeExpression(PropertyExpression2, userList);
		System.out.println("propertyListExpression: " + result);
	}
	
	/**
	 * Map expression
	 */
	@Test
	public void propertyMapExpression (){
		Serializable PropertyExpression2 = MVEL.compileExpression("key1.substring(1, 6)");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "123456");
		map.put("key2", "654321");
		Object result = MVEL.executeExpression(PropertyExpression2, map);
		System.out.println("propertyMapExpression: " + result);
	}
	
	/**
	 * Boolean expression
	 */
	@Test
	public void booleanExpression (){
		Serializable PropertyExpression1 = MVEL.compileExpression("name.length()>1&&password!=null");
		
		User user = new User("jack", "123456");
		Object result = MVEL.executeExpression(PropertyExpression1, user);
		System.out.println("booleanExpression: " + Boolean.valueOf(result.toString()));
	}
	
	/**
	 * Java-style if else return clause
	 */
	@Test
	public void ifElseReturnExpression (){
		Serializable PropertyExpression1 = MVEL.compileExpression("if(name.equals(\"jack\")) {"
											+ " this.setName(\"jack123\");"
											+ "}"
											+ "return this;");
		User user = new User("jack", "123456");
		Object result = MVEL.executeExpression(PropertyExpression1, user);
		System.out.println("ifElseReturnExpression: " + result);
	}
	
	/**
	 * Java-style import package clause
	 */
	@Test
	public void importPackageExpression() throws ParseException{
		Serializable propertyExpression1 = MVEL.compileExpression("import java.util.Date;"
				+ "import java.text.SimpleDateFormat;"
				+ " return new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\").format(new Date());");
		Object result = MVEL.executeExpression(propertyExpression1);
		System.out.println("importPackageExpression: " + result);
		
		propertyExpression1 = MVEL.compileExpression("import java.util.Date;return dateParam.compareTo(new Date());");
		Map<String, Object> paramMap = new HashMap<String, Object>(4);
		paramMap.put("dateParam", new SimpleDateFormat("yyyy-MM-dd").parse("1999-09-09"));
		Object comparedResult = MVEL.executeExpression(propertyExpression1, paramMap);
		System.out.println("importPackageExpression: " + comparedResult);
		
	}
	
	/**
	 * assign variable
	 */
	@Test
	public void variableAssignExpression(){
		VariableResolverFactory varFactory = new MapVariableResolverFactory();
		
		Serializable PropertyExpression1 = MVEL.compileExpression("String s = \"abc\";return s;");
		Object result = MVEL.executeExpression(PropertyExpression1, varFactory);
		System.out.println("variableAssignExpression: " + result);
	}
	
	/**
	 * define custom function to invoke
	 */
	@Test
	public void defFunctionExpression() {
		VariableResolverFactory varFactory = new MapVariableResolverFactory();
		MVEL.eval("def func(str){ return str.length(); }", varFactory);
				
		Serializable expression = MVEL.compileExpression("func(\"ABCDEFGHIJK\");");
		Object result = MVEL.executeExpression(expression, varFactory);
		System.out.println("defFunctionExpression: " + result);
	}
	
	@Test
	public void dateCompare() throws InterruptedException{
		Map<String, Date> map = new HashMap<String, Date>();
		map.put("key1", new Date());
		Thread.sleep(100);
		map.put("key2", new Date());
		
		Serializable expr = MVEL.compileExpression("key1.compareTo(key2)");
		Object result = MVEL.executeExpression(expr, map);
		
		System.out.println("dateCompare: " + result);  // -1 as expected
	}

}
