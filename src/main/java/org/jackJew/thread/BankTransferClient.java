package org.jackJew.thread;

public class BankTransferClient {
	
	public static void main(String[] args){
		BankService bs = new BankService();
		
		BankServiceRunnable bsr1 = new BankServiceRunnable(bs, "账户A", "账户B");
		Thread t1 = new Thread(bsr1, "线程一");
		t1.start();
		
		BankServiceRunnable bsr2 = new BankServiceRunnable(bs, "账户B", "账户A");
		Thread t2 = new Thread(bsr2, "线程二");
		t2.start();
	}

}
