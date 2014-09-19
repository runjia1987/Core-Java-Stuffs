package org.jackJew.garbageCollection.phantomReference;


/**
 * task to keep polling the referenceQueue, and do cleanUp stuff.
 * <br>
 * When a phantom reference is added to the queue by the garbage collector,
 * <br> no further action is taken.
 * @author Jack
 *
 */
public class PollAndCleanTask implements Runnable {
	
	private ProviderService provider;
	
	public PollAndCleanTask(ProviderService provider){
		this.provider = provider;
	}

	@Override
	public void run() {
		while (true) {
			ConnectionReference reference = (ConnectionReference) provider.getQueue().poll();
			if( reference != null) {
				reference.cleanUp();
				System.out.println(Thread.currentThread().getName() + " successfully cleanUp a connection.");
				
				provider.getReferenceList().remove(reference);
				
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