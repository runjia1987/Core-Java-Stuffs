package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Map;

/**
 * 
 * @author Jack
 *
 */
public class PhantomResourceReference extends PhantomReference<Resource> implements Cleanable {

	/**
	 * Note: never hold a member field pointed to the referent object  !!!
	 * <br> otherwise the referent will never become phantom reachable !!!
	 */
	private Map<String, Object> resourcesMap;
	
	public PhantomResourceReference(Resource referent,
			ReferenceQueue<? super Resource> q) {
		super(referent, q);
		this.resourcesMap = referent.getResourcesMap();
	}

	@Override
	public void cleanup() {
		System.out.println("starting to clear resourcesMap hash: " + resourcesMap.hashCode());
		
		this.resourcesMap.clear();		
	}

}
