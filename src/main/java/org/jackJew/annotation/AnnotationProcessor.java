package org.jackJew.annotation;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解处理器 
 * @author zhurunjia
 */
public class AnnotationProcessor {
	
	public static String getConstraints(Constraints constraint){
		StringBuilder sb = new StringBuilder(" ");
		
		if(constraint.notNull())
			sb.append("not null ");
		if(constraint.primaryKey())
			sb.append("primary key ");
		if(constraint.unique() && !constraint.primaryKey())
			sb.append("unique ");
		
		return sb.toString();
	}
	
	public static void main(String[] args){
		try {
			new AnnotationProcessor().process(new String[]{ "org.jackJew.annotation.UserInfo" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注解扫描处理
	 */
	void process(String[] classes) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		for(String className: classes){
			Class<?> cl = Class.forName(className);
			DbTable dt = cl.getAnnotation(DbTable.class);
			if(dt == null){
				System.out.println("no annotation found in the specified class!");
				continue;	// scan for next class
			}
			
			String tableName = dt.name();
			if(tableName.length() < 1){
				tableName = cl.getName().toUpperCase();	//若表名称为空, 直接使用类名
			}
			
			List<String> columnDefs = new ArrayList<String>();
			String columnName;
			Annotation[] anns;
			for(Field f : cl.getDeclaredFields()){
				anns = f.getDeclaredAnnotations();
				if(anns.length < 1) continue;	//this field is not annotated
				if(anns[0] instanceof SqlString){
					SqlString ss = (SqlString)anns[0];
					columnName = ss.name();
					if(columnName.length() < 1)
						columnName = f.getName().toUpperCase();
					
					columnDefs.add(columnName + " VARCHAR2(" + ss.length() + ")" + getConstraints(ss.constrains()));
				}
				else if(anns[0] instanceof SqlInteger){
					SqlInteger si = (SqlInteger)anns[0];
					columnName = si.name();
					if(columnName.length() < 1)
						columnName = f.getName().toUpperCase();
					
					columnDefs.add(columnName + " NUMBER DEFAULT " + si.value() + getConstraints(si.constraints()));
				}
			}
			sb.append("CREATE TABLE ").append(tableName).append("(");
			for(String cd : columnDefs)		//iterate the columns definition
				sb.append("\n").append(cd).append(",");
			
			sb.deleteCharAt(sb.length() - 1).append("\n)\n");	// one table has been defined
		}
		
		System.out.println(sb);
	}

}
