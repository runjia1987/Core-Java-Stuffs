package test.multithread.output;

import static test.multithread.output.MultiThreadOutput.*;

public class ThreadThree extends Thread {

	public void run() {
		while (true) {
			if (timesCount == maxTimes) { // completed
				System.out.println("threadthree exit");
				break;
			} else {
				lock.lock();
				System.out.println("threadthree stringCount: " + stringCount);
				while (stringCount == 0 || strings[stringCount - 1] == null
						|| !strings[stringCount - 1].equals("l")) {
					try {
						l_condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				strings[stringCount++] = "i";
				System.out.println("i");
				timesCount++;
				System.out.println("threadthree timesCount: " + timesCount);
				i_condition.signal();

				lock.unlock();
			}
		}
	}

}
