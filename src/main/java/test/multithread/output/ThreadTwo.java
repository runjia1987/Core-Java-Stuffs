package test.multithread.output;

import static test.multithread.output.MultiThreadOutput.*;

public class ThreadTwo extends Thread {

	public void run() {
		while (true) {
			if (timesCount == maxTimes) { // completed
				System.out.println("threadtwo exit");
				break;
			} else {				
				lock.lock();
				System.out.println("threadtwo stringCount: " + stringCount);
				while (stringCount == 0 || strings[stringCount - 1] == null
						|| !strings[stringCount - 1].equals("A") && timesCount < maxTimes) {
					try {
						System.out.println("threadtwo awaits");
						A_condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(timesCount < maxTimes) {
					strings[stringCount++] = "l";
					System.out.println("l");
					l_condition.signal();
				}
				lock.unlock();
			}
		}
	}

}
