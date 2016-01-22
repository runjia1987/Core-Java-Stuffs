package org.jackJew.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用 CyclicBarrier 类实现可重复多次的多线程相互检测完成某个阶段的状态,
 * <br> 一组线程相互等待, 直至到达某个公共屏障点(common barrier point)
 * @author zhurunjia
 */
public class CyclicBarrierClient {
	
	// 三个参与者即三个独立的线程
	final static int NUMBER = 3;
	/**
	 * A CyclicBarrier supports an optional Runnable command that is run once
	 * per barrier point when the last thread arrives, but before any threads
	 * are released. This barrier action is useful for updating shared-state before
	 * any of the parties continue.
	 */
	private CyclicBarrier barrier;
	
	// 徒步 需要的时间
    private int[] timeWalk = { 3, 4, 4, 8 };  
    // 自驾游 
    private int[] timeDrive = { 1, 2, 2, 4 };  
    // 坐大巴 
    private int[] timeBus = { 2, 3, 3, 5 };
    // 当前进行阶段
    private int index;
    
    public int getIndex() {
		return index;
	}

	public void increment() {
		this.index++;
	}
    
    //内部类, 描述过程
    private static class Journey implements Runnable {
    	
    	private CyclicBarrier _barrier;
    	private String journeyName;
    	private int[] timeNeed;
    	
    	public Journey(CyclicBarrier barrier, String journeyName, int[] timeNeed){
    		this._barrier = barrier;
    		this.journeyName = journeyName;
    		this.timeNeed = timeNeed;
    	}

		@Override
		public void run() {
			try {
				Thread.sleep(timeNeed[0] * 1000);
				System.out.println(journeyName + "到达北京");
				_barrier.await();
				
				Thread.sleep(timeNeed[1] * 1000);
				System.out.println(journeyName + "到达上海");
				_barrier.await();
				
				Thread.sleep(timeNeed[2] * 1000);
				System.out.println(journeyName + "到达合肥");
				_barrier.await();
				
				Thread.sleep(timeNeed[3] * 1000);
				System.out.println(journeyName + "到达杭州");
				_barrier.await();
				
			} catch(InterruptedException ie){
				ie.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
    	
    }

	public static void main(String[] args) throws Exception {
		final CyclicBarrierClient cbc = new CyclicBarrierClient();
		List<Future<?>> futureList = new ArrayList<Future<?>>();
		
		cbc.barrier = new CyclicBarrier(NUMBER, new Runnable() {
			@Override
			public void run() {
				if(cbc.getIndex() == 0)System.out.println(":::已到达北京");
				else if(cbc.getIndex() == 1)System.out.println(":::已到达上海");
				else if(cbc.getIndex() == 2)System.out.println(":::已达到合肥");
				else if(cbc.getIndex() == 3)System.out.println(":::已达到杭州");
				cbc.increment();
			}
		});
		
		ExecutorService es = Executors.newFixedThreadPool(NUMBER);
		System.out.println("开始出发...");
		
		futureList.add(es.submit(new Journey(cbc.barrier, "walk", cbc.timeWalk)));
		futureList.add(es.submit(new Journey(cbc.barrier, "drive", cbc.timeDrive)));
		futureList.add(es.submit(new Journey(cbc.barrier, "bus", cbc.timeBus)));
		
		for(int i = 0; i < NUMBER; i++){
			futureList.get(i).get();
		}
		
		System.out.println("全部队伍都已到达终点!!!");
		es.shutdown();
	}

}
