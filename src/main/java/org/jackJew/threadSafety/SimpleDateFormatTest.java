package org.jackJew.threadSafety;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

	/**
	 * format(Date){
	 *   calendar.setTime(date); //此处未加同步，导致并发设置丢失
	 *   // calendar is instance variable in DateFormat 
	 * }
	 */
	public static void main(String[] arg) throws InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime() + 1000 * 60 * 60 * 24); // +1 day
		System.out.println("date1: " + sdf.format(date1));
		System.out.println("date2: " + sdf.format(date2));

		Thread thread1 = new Thread(new Thread1(sdf, date1));
		Thread thread2 = new Thread(new Thread2(sdf, date2));
		thread1.start();
		thread2.start();
	}
}

class Thread1 implements Runnable {

	private SimpleDateFormat sdf;
	private Date date;
	private final String dateStr;

	public Thread1(SimpleDateFormat sdf, Date date) {
		this.sdf = sdf;
		this.date = date;
		dateStr = new SimpleDateFormat("yyyy-MM-dd").format(this.date);
		System.out.println("Thread1 dateStr: " + dateStr + ", date : " + date);
	}

	public void run() {
		int successCount = 0;
		while (true) {
			String temp = sdf.format(date);
			if (!dateStr.equals(temp)) {
				System.out.println("Thread1 format result: " + temp + ", successCount: " + successCount);
				System.out.println("Thread1 error, input date is: " + date);
				break;
			} else {
		        successCount++;
		      }
		}
	}
}

class Thread2 implements Runnable {
	private SimpleDateFormat sdf;
	private Date date;
	private final String dateStr;

	public Thread2(SimpleDateFormat sdf, Date date) {
		this.sdf = sdf;
		this.date = date;
		dateStr = new SimpleDateFormat("yyyy-MM-dd").format(this.date);
		System.out.println("Thread2 dateStr: " + dateStr + ", date : " + date);
	}

	public void run() {
		int successCount = 0;
		while (true) {
			String temp = sdf.format(date);
			if (!dateStr.equals(temp)) {
				System.out.println("Thread2 format result: " + temp + ", successCount: " + successCount);
				System.out.println("Thread2 error, input date is: " + date);
				break;
			} else {
				successCount++;
			}
		}
	}
}
