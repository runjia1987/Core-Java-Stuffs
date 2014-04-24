package org.jackJew.io.NIO;

import java.util.ArrayList;

public class TestClient {
	
	final static int THREADS_SIZE = 5;
	
	public static void main(String[] args) {		
		ArrayList<NIOClient> clientsList = new ArrayList<NIOClient>();
		
		// setup multiple clients to listen
		for(int i = 0; i < THREADS_SIZE; i++){
			clientsList.add(new NIOClient("client" + i));
		}
				
		// start running
		for(int i = 0; i < THREADS_SIZE; i++){
			new ClientThread(clientsList.get(i)).start();
		}
	}

}
