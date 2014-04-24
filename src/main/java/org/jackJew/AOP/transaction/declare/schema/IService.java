package org.jackJew.AOP.transaction.declare.schema;

import java.util.List;

public interface IService {
	
	void callService(List<Pojo> manList);
	void insert(Pojo man);
	
}
