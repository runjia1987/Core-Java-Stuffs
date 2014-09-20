package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.ReferenceQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;

/**
 * provider service
 * @author Jack
 *
 */
public class ProviderService implements InitializingBean {
	
	/**
	 * Before a connection object will be garbage collected,
	 * <br> its phantom reference will be enqueued into the associated reference queue.
	 */
	private final ReferenceQueue<ResourceWrapper> queue = new ReferenceQueue<ResourceWrapper>();
	
	/**
	 * This is necessary to ensure that phantom references are not garbage collected
	 * <br> as long as they have not been handled by the reference queue.
	 */
	private final List<Cleanable> referenceList = new ArrayList<Cleanable>();
	
	private DataSource dataSource;

	public ResourceWrapper getResource() throws SQLException{
		ResourceWrapper resourceW = new ResourceWrapper();
		
		Cleanable reference = new PhantomResourceReference(resourceW, queue);
		referenceList.add(reference);
		
		return resourceW;
	}

	/**
	 * create a thread to keep polling the referenceQueue, do cleanUp stuff.
	 * <br> 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		PollAndCleanTask task = new PollAndCleanTask(this);
		
		Thread thread = new Thread(task, "pollingThread");		
		thread.start();
		
		System.out.println("pollingThread is started.");
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ReferenceQueue<ResourceWrapper> getQueue() {
		return queue;
	}

	public List<Cleanable> getReferenceList() {
		return referenceList;
	}

}
