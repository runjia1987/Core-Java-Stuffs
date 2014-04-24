package org.jackJew.designPattern;

/**
 * 单例模式研究, 单例类不能实现 Serializable 接口
 * @author zhurunjia
 */
public class Singleton1 {
	
	private volatile static Singleton1 INSTANCE;
	//注意volatile关键字的修饰, 在JDK1.5中可确保指令不被重排优化导致非预期结果.
	
	private Singleton1(){
		System.out.println("Singleton1 constructed.");
	}
	
	public static Singleton1 getInstance(){
		//Use double-check, 《Effective Java》指其有隐患, 编译器优化导致非预期结果
		if(INSTANCE == null){
			synchronized (Singleton1.class) {
				if(INSTANCE == null)	//可能多个线程满足这个条件, 再次检查
					INSTANCE = new Singleton1();				
			}
		}		
		return INSTANCE;
	}

}
/** This implementation is a well-performing and concurrent implementation valid in all versions of Java.
 * The original implementation from Bill Pugh, 
 * based on the earlier work of Steve Quirk, has been modified to reduce the scope 
 * of LazyHolder.INSTANCE to private and to make the field final.
 */
// Initialization on Demand Holder idiom (design pattern) is a lazy-loaded singleton
class Singleton2 {
	
    private Singleton2() {
    	System.out.println("Singleton2 constructed.");
    }

    private static class LazyHolder {	//延迟加载, 使用时才初始化 !!!!!!!
    	private final static Singleton2 INSTANCE = new Singleton2();
    }

    public static Singleton2 getInstance() {
    	return LazyHolder.INSTANCE;
    }
    
    public static void print(){
    	
    }    
}

class Singleton3 {
	
	private final static Singleton3 instance = new Singleton3();
	
    private Singleton3() {
    	System.out.println("Singleton3 constructed.");
    }

    public static Singleton3 getInstance() {
    	return instance;
    }
    
    public static void print(){
    	
    }    
}
 

class ThisTestClient {
	
	public static void main(String[] args) throws ClassNotFoundException{
		//Singleton2.getInstance();
		//Singleton2.print();  // best example for lazy-load
		//Singleton3.getInstance();
		//Singleton3.print();
		
		Class.forName("org.jackJew.designPattern.Singleton1");
		Class.forName("org.jackJew.designPattern.Singleton2");
		Class.forName("org.jackJew.designPattern.Singleton3");
	}
	
}