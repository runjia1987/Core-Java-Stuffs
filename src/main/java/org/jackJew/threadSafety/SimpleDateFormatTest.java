package org.jackJew.threadSafety;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {
	
	/**
	 * format(Date){
	 * 		calendar.setTime(date); //此处未加同步，导致并发设置丢失 
	 * 		// calendar is instance variable in DateFormat
	 * }
	 */
    public static void main(String[] arg) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime()+ 1000*60*60*24);  // +1 day
        System.out.println(sdf.format(date1) +"," + sdf.format(date2));
        
        Thread thread1 = new Thread(new Thread1(sdf, date1));
        Thread thread2 = new Thread(new Thread2(sdf, date2));
        thread1.start();
        thread2.start();    
    }
}

class Thread1 implements Runnable {
	
   private SimpleDateFormat sdf;
   private Date date;
   public Thread1(SimpleDateFormat sdf, Date date) {
       this.sdf = sdf;
       this.date = date;
   }
   public void run() {
       while(true) {
           String temp = sdf.format(date);           
           if(!"2013-05-30".equals(temp)) {
        	   System.out.println("Thread1 " + temp);
               System.out.println("error1 today:" + date);
               //break;
           } else {
               //
           }
       }
   }
}

class Thread2 implements Runnable {
   private SimpleDateFormat sdf;
   private Date date;
   
   public Thread2(SimpleDateFormat sdf, Date date) {
       this.sdf = sdf;
       this.date = date;
   }
   public void run() {
       while(true) {
           String temp = sdf.format(date);
           if(!"2013-05-31".equals(temp)) {
        	   System.out.println("Thread2 " + temp);
               System.out.println("error2 tomorrow:" + date);
               //break;
           } else {
               //
           }
       }
   }
}
