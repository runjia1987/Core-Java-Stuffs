package org.jackJew.garbageCollection.phantomReference;

/**
 * interface for doing cleanup stuff
 * @author Jack
 *
 */
public interface Cleanable {
	
	/**
	 * cleanup resources before really GCed
	 */
	void cleanup();

}
