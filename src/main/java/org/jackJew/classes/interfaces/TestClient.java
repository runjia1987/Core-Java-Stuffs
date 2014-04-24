package org.jackJew.classes.interfaces;

public class TestClient extends AbstractClassWithDuplicateMethod implements Interface {
	
	static {
		
		//str = "321";	//compile error, final field
		System.out.println(str);
		
		//package access modifier in the interface
		WithDefaultIdetifierInterface wii = null;
		
		AbstractClass ac = new AbstractClass() {
			@Override
			public void job() {
				// TODO Auto-generated method stub
				
			}
		};
			
		Interface ni = new Interface() {

			@Override
			public void job() {
				//
			}
		};
	}
	
	{
		
	}
	
	@Override
	public void job() {
		
	}
	
}