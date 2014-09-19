package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.ReferenceQueue;
import java.util.List;

/**
 * task to keep polling the referenceQueue, and do cleanUp stuff.
 * <br>
 * When a phantom reference is added to the queue by the garbage collector,
 * <br> no further action is taken.
 * @author Jack
 *
 */
public class PollAndCleanTask implements Runnable {
	
	private ReferenceQueue<?> queue;
	
	private List<PhantomConnection> connectionList;
	
	public PollAndCleanTask(ReferenceQueue<?> queue, List<PhantomConnection> connectionList){
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			PhantomConnection pCon = (PhantomConnection) queue.poll();
			pCon.cleanUp();
			System.out.println(Thread.currentThread().getName() + " successfully cleanUp a connection.");
			
			connectionList.remove(pCon);
		}
	}

}
