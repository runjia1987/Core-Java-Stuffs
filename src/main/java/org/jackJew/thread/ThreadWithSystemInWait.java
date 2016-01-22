package org.jackJew.thread;

import java.io.IOException;

/**
 * 多线程，并等待控制台输入
 * @author zhurunjia
 */
public class ThreadWithSystemInWait extends Thread {
	
	double d = 1.0d;
	float f = 1.0f;
	short sh = 1;
	
	public ThreadWithSystemInWait(String name){
		super(name);
		setDaemon(true);
		sh += 1;
		//sh = sh + 1; 	//compile error
	}
	
	public strictfp void run(){
		while(true){
			d += (Math.PI + Math.E) / d;
			//if(f < 2<<20) f += f/2.0f;
		}
	}
	
	public static void main(String... args) throws IOException{
		ThreadWithSystemInWait tw = new ThreadWithSystemInWait("zhurunjia");
		tw.start();
		
		//阻塞直到有用户输入
		System.in.read();
		//This method blocks until input data is available, or an exception is thrown.
		
		System.out.println(tw.d);
		System.out.println(tw.f);
	}

}
