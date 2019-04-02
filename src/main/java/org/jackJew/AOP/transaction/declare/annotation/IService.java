package org.jackJew.AOP.transaction.declare.annotation;

import java.util.List;

public interface IService {

	void insert(List<Pojo> manList);
  void inValidInsert(Pojo man);
	
}
