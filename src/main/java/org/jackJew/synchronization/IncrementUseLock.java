package org.jackJew.synchronization;

public class IncrementUseLock implements Runnable {

	/**
	 * 自增
	 */
	@Override
	public void run() {
		synchronized (Application.ob) {
			
			Application.num+=3;
			
		}		
	}

}
