package org.jackJew.classes.genericSuperClass;

/**
 * generic parent
 * @author zhurunjia192
 *
 * @param <REQUEST>
 * @param <RESPONSE>
 */
public abstract class GenericParent<REQUEST, RESPONSE> {
	
	abstract RESPONSE handle(REQUEST request);

}
