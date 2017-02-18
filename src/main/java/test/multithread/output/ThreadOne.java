package test.multithread.output;

import static test.multithread.output.MultiThreadOutput.*;

public class ThreadOne extends Thread {

	private int times;

	public ThreadOne(int times) {
		this.times = times;
	}

	@Override
	public void run() {
		while (true) {
			if (timesCount == times) { // completed
				System.out.println("threadOne exit");
				lock.lock();
				A_condition.signal();
				lock.unlock();
				break;
			} else if (stringCount == 0) { // new start

				lock.lock();
				strings[stringCount++] = "A";
				System.out.println("A");
				A_condition.signal();
				lock.unlock();
				
			} else {
				lock.lock();
				System.out.println("threadone stringCount: " + stringCount);
				while (strings[stringCount - 1] == null || !strings[stringCount - 1].equals("i")) {
					try {
						i_condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (timesCount < times) {
					strings[stringCount++] = "A";					
					System.out.println("A");					
					A_condition.signal();
				}	
				lock.unlock();
			}
		}
	}

	public int getTimes() {
		return times;
	}

}
