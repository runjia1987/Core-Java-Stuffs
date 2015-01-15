package org.jackJew.AOP.transaction.programmatic;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * another way: use TransactionTemplate as inner anonymous bean in TransactionService bean.
 */
public class TransactionService {
	
	private PlatformTransactionManager transactionManager;
	
	private static Logger logger = Logger.getLogger(TransactionService.class);
	
	public void service1(){
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		
		System.out.println("calling PojoDao.");
		definition.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
		definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
		definition.setReadOnly(true);
		definition.setTimeout(5);  // 5s timeout
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {			
			// jdbcTemplate or Ibatis SqlMapClient do sth.
			
			transactionManager.commit(status);
			
		} catch(RuntimeException e){
			System.out.println(e.getMessage());
			transactionManager.rollback(status);
		}
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}	
	

}
