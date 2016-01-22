package org.jackJew.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class MacAddress {

	private static final String[] windowsCommands = { "ipconfig", "/all" };
	private static final String[] linuxCommands = { "/sbin/ifconfig", "-a" };
	
	private static final Pattern macPattern = Pattern.compile(".*((:?[0-9a-f]{2}[-:]){5}[0-9a-f]{2}).*", Pattern.CASE_INSENSITIVE);

	public static String[] getMacAddresses() throws IOException {
		ArrayList<String> macAddressList = new ArrayList<String>();
		final String os = System.getProperty("os.name");
		final String[] commands;
		if (os.startsWith("Windows")) {
			commands = windowsCommands;
		} else if (os.startsWith("Linux")) {
			commands = linuxCommands;
		} else {
			throw new IllegalStateException("Unknown operating system: " + os);
		}
		final Process process = Runtime.getRuntime().exec(commands);
		InputStream stdout = process.getInputStream();
		InputStream stderr = process.getErrorStream();
		// parset the MAC from stdout
		if(stdout != null) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));) {
				for (String line = null; (line = reader.readLine()) != null;) {
					Matcher matcher = macPattern.matcher(line);
					if (matcher.matches()) {  // get only the first MAC
						macAddressList.add(matcher.group(1).replaceAll("[-:]", ""));
					}
				}
				stdout.close();
			} catch(Exception e) {
				Logger.getRootLogger().error("", e);
			}
		}
		if(stderr != null) {
			try {
				StringBuffer buffer = new StringBuffer();
				int data = -1;
				while ((data = stderr.read()) != -1) {
					buffer.append(data);
				}
				Logger.getRootLogger().info("errorStream:" + buffer.toString());
				stderr.close();
			} catch (IOException e) {
				Logger.getRootLogger().error("", e);
			}
		}
		process.destroy();
		return macAddressList.toArray(new String[macAddressList.size()]);
	}
	
	public static void main(String[] args) {
		try {
			String[] array = MacAddress.getMacAddresses();
			System.out.println(Arrays.toString(array));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}