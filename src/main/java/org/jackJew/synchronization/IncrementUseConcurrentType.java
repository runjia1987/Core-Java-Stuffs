package org.jackJew.synchronization;

public class IncrementUseConcurrentType implements Runnable {

	@Override
	public void run() {
		/*
		boolean success = false;
		do {
			int num = Application.num2.get();
			Application.num2.addAndGet(1);
		} while(success);
		*/
		/*
		Integer result = null;
		boolean flag;     
        do {     
            result = Application.num2.get();
            // 单线程下, compareAndSet返回永远为true,     
            // 多线程下, 在与result进行compare时, counter可能被其他线程set了新值, 这时需要重新再取一遍再比较,     
            // 如果还是没有拿到最新的值, 则一直循环下去, 直到拿到最新的那个值。即轻量级锁的CAS算法   
            flag = Application.num2.compareAndSet(result, result + 5);     
        } while (!flag);
		*/
		
		Application.num2.getAndAdd(3);
		//AtomicReference<String> str = new AtomicReference<String>("1");
		
	}

}
