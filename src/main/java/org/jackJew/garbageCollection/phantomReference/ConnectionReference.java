package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * ConnectionReference extends PhantomReference
 * @author Jack
 *
 */
public class ConnectionReference extends PhantomReference<ConnectionWrapper> {
	
	private ConnectionWrapper connectionWrapper;

	public ConnectionWrapper getConnectionWrapper() {
		return connectionWrapper;
	}

	public ConnectionReference(ConnectionWrapper connectionWrapper,
					ReferenceQueue<? super ConnectionWrapper> q) {
		super(connectionWrapper, q);
		this.connectionWrapper = connectionWrapper;
	}

}
