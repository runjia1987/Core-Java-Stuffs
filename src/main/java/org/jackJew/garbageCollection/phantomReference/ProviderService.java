package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.ReferenceQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

/**
 * provider service
 * @author Jack
 *
 */
public class ProviderService implements InitializingBean {
	
	/**
	 * Before a connection object will be garbage collected,
	 * <br> its phantom reference will be enqueued into the referenceQueue.
	 */
	private final ReferenceQueue<Resource> queue = new ReferenceQueue<Resource>();
	
	/**
	 * This is necessary to ensure that phantom references are not garbage collected
	 * <br> as long as they have not been handled by the reference queue.
	 */
	private final List<PhantomResourceReference> referenceList =
			Collections.synchronizedList(new ArrayList<PhantomResourceReference>());

	public Resource getResource() throws SQLException{
		Resource resource = new Resource();
		
		PhantomResourceReference reference = new PhantomResourceReference(resource, queue);
		referenceList.add(reference);
		
		return resource;
	}

	/**
	 * create a task to keep checking the queue, do cleanUp stuff.
	 * <br> 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {		
		Thread thread = new Thread(new CleanTask(), "CleanTask");
		thread.start();
		
		System.out.println("CleanTask is started.");
	}
	
	/**
	 * task to keep polling the queue, and do cleanUp stuff.
	 * <br/>
	 * When a phantom reference is added to the queue by the garbage collector,
	 * <br> no further action is taken.
	 */
	class CleanTask implements Runnable {
		
		@Override
		public void run() {
			while (true) {
				PhantomResourceReference reference = null;
				try {
					// use remove(timeout)
					reference = (PhantomResourceReference)queue.remove(10 * 1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if( reference != null) {
					reference.cleanup();
					System.out.println(Thread.currentThread().getName() + " successfully cleanUp a resource.");
					
					referenceList.remove(reference);				
				} else {
					System.out.println("queue is empty.");
					break;
				}
			}
		}
	}

}
