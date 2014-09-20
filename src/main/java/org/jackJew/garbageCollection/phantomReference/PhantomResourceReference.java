package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Map;

/**
 * 
 * @author Jack
 *
 */
public class PhantomResourceReference extends PhantomReference<ResourceWrapper> implements Cleanable {

	/**
	 * Note: never point a member field to the referent object  !!!
	 * <br> otherwise the referent will never become phantom reachable !!!
	 */
	private Map<String, Object> resourcesMap;
	
	public PhantomResourceReference(ResourceWrapper referent,
			ReferenceQueue<? super ResourceWrapper> q) {
		super(referent, q);
		this.resourcesMap = referent.getResourcesMap();
	}

	@Override
	public void cleanup() {
		System.out.println("starting to clear resourcesMap hash: " + resourcesMap.hashCode());
		
		this.resourcesMap.clear();		
	}

}
