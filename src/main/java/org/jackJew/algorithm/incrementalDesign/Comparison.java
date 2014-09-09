package org.jackJew.algorithm.incrementalDesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * compare and find out the differences
 * @author Jack
 *
 */
public class Comparison {
	
	private List<Entity> insertedList = new ArrayList<Entity>(10);
	
	private List<Entity> updatedList = new ArrayList<Entity>(10);
	
	/**
	 * like merge sort
	 * @param oldList asc sorted by pkId
	 * @param latestList asc sorted by pkId
	 */
	public void compare(Entity[] oldList, Entity[] latestList) {
		int a1 = 0, a2 = oldList.length;
		int b1 = 0, b2 = latestList.length;
		int lastRunIndex = 0;
		
		Entity m, n;
		while ( true) {
			if ( a1 >= a2) break;
			if ( b1 >= b2) break;
			
			m = oldList[a1];
			n = latestList[b1];
			// compare the PK id
			int comp = n.compareTo(m);
			// equal PK id
			if( comp == 0) {
				a1++;
				b1++;
				// compare the content
				if ( ! n.equals(m)) {
					// updated
					updatedList.add(n);
				}
			} else if (comp > 0) {
				// larger PK id, which means n is possibly inserted later
				// find the index of oldList to match the n pkId
				int index = lastRunIndex;
				boolean found = false;
				while (index < a2) {
					int comp1 = oldList[index].compareTo(n);
					if ( comp1 > 0) {
						lastRunIndex = index -1 ;break;
					} else if ( comp1 == 0) {
						found = true; lastRunIndex = index; break;
					} else
						index++;
				}
				
				if ( found ){
					// compare the content
					if ( ! n.equals(oldList[lastRunIndex])) {
						// updated
						updatedList.add(n);
					}					
				} else {
					insertedList.add(n);				
				}
				a1 = lastRunIndex + 1;
				b1++;
				
			} else {
				// unlikely to happen (because delete is forbidden)
			}
		}
		
		// the rest of latestList is all newly inserted
		while (b1 < b2) {
			insertedList.add(latestList[b1++]);
		}
	}
	
	/**
	 * test case
	 * @param args
	 */
	public static void main(String[] args){
		Entity[] oldList = new Entity[2];
		Entity[] latestList = new Entity[3];
		
		Entity entity1 = new Entity(10);
		oldList[0] = entity1;
		entity1 = new Entity(12);
		latestList[0] = entity1;
		
		entity1 = new Entity(12);
		entity1.setCol1("Jack");
		oldList[1] = entity1;
		entity1 = new Entity(15);
		latestList[1] = entity1;
		
		entity1 = new Entity(20);
		latestList[2] = entity1;
		
		
		Comparison c = new Comparison();
		System.out.println(Arrays.toString(oldList));
		System.out.println(Arrays.toString(latestList));
		
		c.compare(oldList, latestList);
		
		System.out.println("newly inserted: " + c.insertedList);
		System.out.println("updated: " + c.updatedList);
	}

}