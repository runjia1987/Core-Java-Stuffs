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
	
	private List<ConnectionReference> referenceList;
	
	public PollAndCleanTask(ReferenceQueue<?> queue, List<ConnectionReference> referenceList){
		this.queue = queue;
		this.referenceList = referenceList;
	}

	@Override
	public void run() {
		while (true) {
			ConnectionReference reference = (ConnectionReference) queue.poll();
			if( reference != null) {
				reference.getConnectionWrapper().cleanUp();
				System.out.println(Thread.currentThread().getName() + " successfully cleanUp a connection.");
				
				referenceList.remove(reference);
			} else {
				System.out.println("queue is empty");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

}
