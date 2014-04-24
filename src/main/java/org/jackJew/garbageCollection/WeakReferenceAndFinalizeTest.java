package org.jackJew.garbageCollection;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;

public class WeakReferenceAndFinalizeTest {

	/**
	 * GC Object.finalize() vs. ReferenceQueue.poll(), there's no fixed order between them.
	 */
	public static void main(String[] args) throws InterruptedException {
		ReferenceQueue<R> queue = new ReferenceQueue<R>();
		
		R referent = new R();
		WeakReference<R> reference = new WeakReference<R>(referent, queue);
		//SoftReference<R> reference = new SoftReference<R>(new R(), queue);
		//PhantomReference<R> reference = new PhantomReference<R>(new R(), queue);
		
		referent = null;
		Reference<? extends R> refer = null;
		while( (refer = queue.poll()) == null){
			System.out.println("ReferenceQueue 空出列");
			System.gc();
		}
		System.out.println("发现引用enqueue ReferenceQueue " + refer.get() );
		
	}

}

class R {
	
	public void finalize(){
		System.out.println("finalize() method executed.");
	}
	
	public String toString(){
		return "R";
	}
	
	public R clone(){
		return null;
	}
	
}
