package org.jackJew.io.NIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.util.Iterator;

public class NIOClient {

	final String HOST = "127.0.0.1";
	final int PORT = 9999;
	final int MAX_REQUESTS = 20;
	private ByteBuffer buffer = ByteBuffer.allocate(1 << 10);
	private String clientName;
	private boolean firstContact = true;
	
	public NIOClient(String name){
		this.clientName = name;
	}

	public void startListen() {
		SocketChannel channel = null;
		Selector selector = null;
		try {
			channel = SocketChannel.open();
			selector = Selector.open();
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_CONNECT);
			channel.connect(new InetSocketAddress(HOST, PORT));					
			
			int requestTimes = 0;
			while(requestTimes++ < MAX_REQUESTS){
				System.out.println(this.clientName + " wait for incoming events... times: " + requestTimes);
				// wait for incomming events
				selector.select();
				
				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
				while(keys.hasNext()){
					SelectionKey key = keys.next();
					keys.remove();
					if(key.isValid()) {
						handleKey(key, selector);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				selector.close();
				channel.close();
				System.out.println(this.clientName + " closed channel.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void handleKey(SelectionKey key, Selector selector) throws Exception {
		System.out.println(key.isConnectable() + "," + key.isReadable() + "," + key.isWritable());
		SocketChannel channel = (SocketChannel) key.channel();
		
		if(key.isConnectable()){
			boolean connected = true;
			if(! channel.isConnected()){
				if(channel.finishConnect()){  // notice !!!
					System.out.println(this.clientName + " connected to " + channel.socket().getInetAddress());
				} else{
					// continue the connect event key.
					connected = false;
				}
			}
			if(connected){
				channel.register(selector, SelectionKey.OP_READ);
			}
		} else if(key.isReadable()){
			buffer.clear();
			int read = channel.read(buffer);
			if(read > 0){
				buffer.flip();
				System.out.println(this.clientName + " receive: " + new String(buffer.array(), 0, read));								
				channel.register(selector, SelectionKey.OP_WRITE);
			} else {
				System.out.println(this.clientName + " Warn: receive nothing. ");
				key.cancel();
			}
			
		} else if(key.isWritable()){
			System.out.println("client will write...");
			if( firstContact ) {
				firstContact = false;
				// write client name to server
				channel.write(ByteBuffer.wrap(this.clientName.getBytes()));
			} else {
				System.out.println(this.clientName + " sends ACK to server.");
				channel.write(ByteBuffer.wrap(Constants.NEXT_MESSAGE.getBytes()));
			}
			channel.register(selector, SelectionKey.OP_READ);
		}
	}

}
