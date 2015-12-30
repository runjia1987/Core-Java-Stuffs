package org.jackJew.AOP.transaction.programmatic;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * another way: use TransactionTemplate as inner anonymous bean in
 * TransactionService bean.
 */
public class TransactionService {

	private PlatformTransactionManager transactionManager;

	public void exec() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		definition
				.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
		definition.setReadOnly(true);
		definition.setTimeout(2); // 2s timeout

		TransactionStatus status = transactionManager.getTransaction(definition);
		try {
			// call jdbcTemplate or MyBatis SqlMapClient do sth.

			transactionManager.commit(status);

		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			transactionManager.rollback(status);
		}
	}

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
