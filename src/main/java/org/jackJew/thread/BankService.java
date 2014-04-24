package org.jackJew.thread;

import java.util.HashMap;

public class BankService {
	
	public static HashMap<String, Integer> accounts = new HashMap<String, Integer>();
	
	static {
		accounts.put("账户A", 1000);
		accounts.put("账户B", 1000);
	}
	
	public void transfer(String from, String to, int amount) throws InterruptedException{
		synchronized (accounts) {
			
			while(accounts.get(from) < amount)
				accounts.wait();
			
			int currentMoney = accounts.get(from);
			accounts.put(from, currentMoney-amount);
			accounts.put(to, accounts.get(to)+amount);
			
			System.out.println("结算: 账户A: " + accounts.get("账户A") + ", 账户B: " + accounts.get("账户B"));
			accounts.notifyAll();
		}
	}

}
