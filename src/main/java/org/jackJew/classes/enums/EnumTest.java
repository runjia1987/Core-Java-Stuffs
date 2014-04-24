package org.jackJew.classes.enums;

/**
 * @author zhurunjia
 */
enum Color {
    
    RED("red"), 
    GREEN("green"), 
    BLUE("blue");
    
    private String description;
    
    //构造函数
    Color(String d){
        this.description = d;
    }
    
    public String getDescription(){
        return description;
    }

    /**
     * 覆盖toString 方法
     * @return 
     */
    @Override
    public String toString() {
        return this.description;
    }
    
    public static void main(String[] args){
        System.out.println(values().length);
        System.out.println(EnumTest.colors.length);
    }
    
}

/**
 * @author zhurunjia
 */
public class EnumTest {
    
    protected static Color[] colors = Color.values();
    
    public static void main(String[] args){
        for(Color c: colors){
            System.out.println(c + " ordinal: " + c.ordinal());     //返回声明时的次序，int值
            System.out.println(c.getDeclaringClass());
            System.out.println(c.name());
        }
    }
    
}
