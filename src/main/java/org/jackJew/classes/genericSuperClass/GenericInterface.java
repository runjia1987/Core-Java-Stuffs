package org.jackJew.classes.genericSuperClass;

/**
 * generic interface
 * @author zhurunjia192
 *
 * @param <K>
 * @param <V>
 */
public interface GenericInterface<K, V> {

	V get(K key);
	
}
