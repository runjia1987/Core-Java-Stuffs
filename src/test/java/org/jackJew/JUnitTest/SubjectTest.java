package org.jackJew.JUnitTest;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author zhurunjia
 */
public class SubjectTest {

	/**
	 * JUnit 4.x 方式, 支持注解
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeClass");
	}

	/**
	 * 
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass");
	}

	/**
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("@Before");
	}

	/**
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("@After");
	}

	@Test
	public void testAdd() {
		int result = new Subject().add(2, 5);
		System.out.println("testAdd: " + result);
	}
	
	@Test
	public void testSubtract() {
		int result = new Subject().subtract(5, 2);
		System.out.println("testSubtract: " + result);
	}

}
