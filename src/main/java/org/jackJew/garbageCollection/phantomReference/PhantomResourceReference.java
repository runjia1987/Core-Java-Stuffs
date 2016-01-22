package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Map;

/**
 * 
 * @author Jack
 *
 */
public class PhantomResourceReference extends PhantomReference<Resource> {

	/**
	 * Note: never hold a member pointer directly to the referent object  !!!
	 * <br> otherwise the referent will never become phantom reachable !!!
	 */
	private Map<String, Object> field;
	
	public PhantomResourceReference(Resource referent,
			ReferenceQueue<? super Resource> q) {
		super(referent, q);
		this.field = referent.getField();
	}

	public void cleanup() {
		System.out.println("starting to clear resourcesMap: " + field.hashCode());
		
		field.clear();		
	}

}
