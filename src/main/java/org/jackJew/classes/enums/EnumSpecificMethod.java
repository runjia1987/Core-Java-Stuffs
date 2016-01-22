package org.jackJew.classes.enums;

import java.lang.Enum;

/**
 *
 * @author zhurunjia
 */
public enum EnumSpecificMethod {
	
    SPRING(100) {
        String getInfo() {
            return "spring";
        }
    },
    SUMMER(5) {
        public String getInfo() {
            return "summer";
        }
    },
    AUTUMN(88) {
        String getInfo() {
            return "autumn";
        }
    },
    WINTER(0) {
        String getInfo() {
            return "winter";
        }
    };
    
    abstract String getInfo();
    
    String print(){
    	return this.getInfo();
    }
    
    EnumSpecificMethod(int i){
    	//constructor
    }
    
    public static void main(String... args){
        
        for(EnumSpecificMethod esm: EnumSpecificMethod.values()) {
            System.out.println(esm.print());
        }
        
        EnumSpecificMethod.SPRING.fn();
        
    }

	public void fn() {
		
	}
    
}
