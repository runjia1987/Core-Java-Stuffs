package org.jackJew.io.NIO;

import java.util.ArrayList;

public class TestClient {
	
	final static int CLIENTS_NUM = 4;
	
	public static void main(String[] args) {
		final NIOServer server = new NIOServer("NIO server");
		
		new Thread(server::startServ).start();
		
		ArrayList<NIOClient> clientsList = new ArrayList<>();
		
		// setup multiple clients to listen
		for(int i = 0; i < CLIENTS_NUM; i++){
			clientsList.add(new NIOClient("client" + i));
		}
		// start
    clientsList.forEach(client -> new Thread(client::startRequest).start());
	}

}
