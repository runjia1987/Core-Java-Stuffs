package org.jackJew.jdk8;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Jdk8DateTime {
	
	public void testPeriod() {
		LocalDate date = LocalDate.of(2016, 7, 25);		
		System.out.println(date);  // 2016-07-25
		
		Period period = Period.ofMonths(1).plusDays(2);
		
		date = date.plus(period);
		System.out.println(date);  // 2016-08-27
	}
	
	public void testInstant() {
		LocalDate date = LocalDate.of(2016, 7, 25);
		LocalDateTime dateTime = date.atStartOfDay();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.CHINA)
				.withZone(TimeZone.getTimeZone("GMT+8").toZoneId());
		System.out.println(dateTimeFormatter.format(dateTime));
		
		Instant instant = dateTime.atZone(TimeZone.getDefault().toZoneId()).toInstant();
		System.out.println("epoch ms: " + instant.toEpochMilli());
		
		Date now = Date.from(instant);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now));
		
		Instant to = now.toInstant();
		System.out.println(dateTimeFormatter.format(to));
		
		Duration duration = Duration.between(instant, to);
		System.out.println(duration.getSeconds());  // 0
		
		ZonedDateTime zonedDateTime1 = dateTime.atZone(TimeZone.getDefault().toZoneId());
		TimeZone timeZone = TimeZone.getDefault();
		ZonedDateTime zonedDateTime2 = ZonedDateTime.of(dateTime, timeZone.toZoneId());
		
		System.out.println(zonedDateTime1.compareTo(zonedDateTime2));
		
		ZoneOffset zoneOffset = zonedDateTime1.getOffset();
		System.out.println(zoneOffset.getTotalSeconds());  // 28800
	}
	
	public static void main(String[] args) {
		new Jdk8DateTime().testPeriod();
		new Jdk8DateTime().testInstant();
	}
	
}