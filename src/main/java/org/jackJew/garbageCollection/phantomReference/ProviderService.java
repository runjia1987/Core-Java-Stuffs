package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.ReferenceQueue;
import java.sql.Connection;
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
	private final ReferenceQueue<ConnectionWrapper> queue = new ReferenceQueue<ConnectionWrapper>();
	
	/**
	 * This is necessary to ensure that phantom references are not garbage collected
	 * <br> as long as they have not been handled by the reference queue.
	 */
	private final List<ConnectionReference> referenceList = new ArrayList<ConnectionReference>();
	
	private DataSource dataSource;

	public ConnectionWrapper getConnection() throws SQLException{
		Connection con = dataSource.getConnection();
		System.out.println("connection " + con + " is retrieved from dataSource.");
		
		ConnectionWrapper conW = new ConnectionWrapper(con);
		ConnectionReference reference = new ConnectionReference(conW, queue);
		referenceList.add(reference);
		
		return conW;
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

	public ReferenceQueue<ConnectionWrapper> getQueue() {
		return queue;
	}

	public List<ConnectionReference> getReferenceList() {
		return referenceList;
	}
}
