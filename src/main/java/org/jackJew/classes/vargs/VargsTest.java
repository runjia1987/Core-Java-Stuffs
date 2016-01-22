package org.jackJew.classes.vargs;

/**
 * 变长参数测试
 * @author zhurunjia
 */
public class VargsTest {
	
	private void pringArgs(String... args){
		for(String s: args)
			System.out.println(s);
	}
	
	private void pringArgs2(int... args){	//args参数可以不传, 并且下述语句不报错
		System.out.println("arguments length： " + args.length); //output: 0
		
		for(int i: args)
			System.out.println(i);
	}
	
	public static void main(String[] args){
		VargsTest vt = new VargsTest();
		
		vt.pringArgs("a", "b", "c");
		vt.pringArgs2();
	}

}
