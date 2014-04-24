package org.jackJew.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorSerice 与 Callable(异步处理的一个重要方式)
 * @author zhurunjia
 */
public class ExecutorServiceUsingCallable {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String... args) {
		ExecutorService es = Executors.newCachedThreadPool();	//自动适应成多线程并发执行
			//Executors.newFixedThreadPool(1);	//单线程顺序执行

		List<Future<Integer>> results = new ArrayList();
		for (int i = 0; i < 3; i++) {
			results.add(es.submit(new JobWithResult(i * 100)));
		}

		try {
			for (Future<Integer> fi : results){
				System.out.println("square value returned: " + fi.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			es.shutdown();
		}
	}

}

class JobWithResult implements Callable<Integer> {

	private int number;
	
	@Override
	public Integer call() throws Exception {	//模拟长时间计算任务
		Thread.sleep(1000);
		return number * number;
	}
	
	public JobWithResult(int number) {
		this.number = number;
	}

}
