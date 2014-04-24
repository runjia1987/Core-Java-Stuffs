package org.jackJew.interview;

public class RecursiveLoop {

	public static void function(int index) {
		if(index >= 2){
			//function(index - 1);  	// 1 2 3 4 5 6
			//function(--index);		// 1 1 2 3 4 5
			function(index--);			// StackOverflowError
		}
		System.out.println(index);
	}
	
	public static void main(String[] args) { // -XX:MaxPermSize=8m -Xss2k
		function(6);
	}

}
