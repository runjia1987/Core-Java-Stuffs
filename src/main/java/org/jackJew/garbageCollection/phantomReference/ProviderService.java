package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.ReferenceQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;

/**
 * provider
 * @author Jack
 *
 */
public class ProviderService implements InitializingBean {
	
	/**
	 * Before a connection object will be garbage collected,
	 * <br> its phantom reference will be enqueued into the associated reference queue.
	 */
	private final ReferenceQueue<Connection> queue = new ReferenceQueue<Connection>();
	
	/**
	 * This is necessary to ensure that phantom references are not garbage collected
	 * <br> as long as they have not been handled by the reference queue.
	 */
	private List<PhantomConnection> connectionList = new ArrayList<PhantomConnection>();
	
	private DataSource dataSource;	

	public PhantomConnection getConnection() throws SQLException{
		Connection con = dataSource.getConnection();
		System.out.println("connection" + con + " is retrieved from dataSource.");
		
		PhantomConnection pCon = new PhantomConnection(con, queue);		
		connectionList.add(pCon);
		
		return pCon;
	}
	
	/**
	 * create a thread to keep polling the referenceQueue, do cleanUp stuff.
	 * <br> 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		PollAndCleanTask task = new PollAndCleanTask(queue, connectionList);
		
		Thread thread = new Thread(task, "pollingThread");		
		thread.setDaemon(true);		
		thread.start();
		
		System.out.println("pollingThread is started");
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
