package org.jackJew.JUnitTest;

import org.junit.Assert;
import org.junit.Test;

public class TableSizeFor {
	private static final int MAXIMUM_CAPACITY = 1 << 30;
	
	/**
	 * @see java.util.HashMap#tableSizeFor
	 */
	static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
	
	@Test
	public void testTableSizeFor() {
		Assert.assertEquals(16, tableSizeFor(15));
		
		Assert.assertEquals(32, tableSizeFor(32));
		
		Assert.assertEquals(512, tableSizeFor(300));
		
		Assert.assertEquals(1024, tableSizeFor(1000));
	}

}
