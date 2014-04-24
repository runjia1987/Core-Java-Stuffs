package org.jackJew.thread;

public class BankServiceRunnable implements Runnable{
	
	private BankService bs;
	private String from;
	private String to;
	
	public BankServiceRunnable(BankService bs, String from, String to){
		this.bs = bs;
		this.from = from;
		this.to = to;
	}

	@Override
	public void run() {
		while(true){
			int amount = (int)(1200 * Math.random());
			System.out.println( "下一笔: " + from + " -> " + to + " : " + amount);
			try {
				bs.transfer(from, to, amount);
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}

}
