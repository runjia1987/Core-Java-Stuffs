package org.jack.hash;

public interface HashProvider {
	
	/**
	 * calc hash value
	 * @return
	 */
	abstract int hash(Object obj);

}
