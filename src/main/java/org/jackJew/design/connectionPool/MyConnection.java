package org.jackJew.design.connectionPool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.pool2.PooledObject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MyConnection {
	
	private String user;	
	private String password;	
	private Set<Object> resultSet;
	
	public MyConnection() {
		this.user = "jack";
		this.password = "123456";
		
		this.resultSet = new HashSet<>(1 << 8);
		this.resultSet.add(new Date());
	}

}
