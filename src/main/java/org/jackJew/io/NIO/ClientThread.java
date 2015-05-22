package org.jackJew.io.NIO;

public class ClientThread extends Thread {
	
	private NIOClient client;
	
	public ClientThread(NIOClient client){
		this.client = client;
	}
	
	@Override
	public void run(){
		client.startListen();
	}

}
