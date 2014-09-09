package org.jackJew.algorithm.incrementalDesign;

/**
 * entity model
 * @author Jack
 *
 */
public class Entity implements Comparable<Entity> {
	
	private int pkId;
	
	private String col1;
	
	private String col2;
	
	private Object col3;
	
	@Override
	public int compareTo(Entity o) {
		return (this.getPkId() - o.getPkId());
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder(1 << 8);
		builder.append(this.getPkId()).append(",").append(getCol1()).append(",")
			   .append(getCol2()).append(",").append(getCol3());
		
		return builder.toString();
	}
	
	public Entity(int pkId){
		this.pkId = pkId;		
	}

	public int getPkId() {
		return pkId;
	}

	public void setPkId(int pkId) {
		this.pkId = pkId;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public Object getCol3() {
		return col3;
	}

	public void setCol3(Object col3) {
		this.col3 = col3;
	}

}
