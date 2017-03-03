package org.jackJew.io.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.UUID;

/**
 * NIO client
 * @see NIOServer
 * @author Jack
 *
 */
public class NIOClient {

	final String HOST = "127.0.0.1";
	final int MAX_REQUESTS = 10;
	private ByteBuffer buffer = ByteBuffer.allocate(1 << 10);
	private String clientName;
	
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
			channel.connect(new InetSocketAddress(HOST, NIOServer.PORT));
			
			int requestTimes = 0;
			while(requestTimes++ < MAX_REQUESTS){
				System.out.println(this.clientName + " wait for incoming events... times: " + requestTimes);
				try {
					// a blocking selection operation waiting for incomming events
					selector.select();
					
					Iterator<SelectionKey> keysItr = selector.selectedKeys().iterator();
					while(keysItr.hasNext()){
						SelectionKey key = keysItr.next();
						keysItr.remove();
						if(key.isValid()) {
							handleKey(key, selector);
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
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
		System.out.println(this.clientName + " key status: read " + key.isReadable() + ", write " + key.isWritable());
		SocketChannel channel = (SocketChannel) key.channel();
		if(key.isConnectable()){
			boolean connected = true;
			if(! channel.isConnected()){
				if(channel.finishConnect()){
					// A non-blocking connection operation,
					// true if, and only if, this channel's socket is now connected
					System.out.println(this.clientName + " connected to " + channel.socket().getRemoteSocketAddress());
				} else{
					// fail to connect in this try
					connected = false;
				}
			}
			if(connected){
				// connected, deregister OP_CONNECT
				channel.register(selector, SelectionKey.OP_READ);
			}
			// not connected, then keep selecting in next loop
		} else if(key.isReadable()){
			buffer.clear();
			int read = channel.read(buffer);
			if(read > 0){
				buffer.flip();
				System.out.println(this.clientName + " receive: " + new String(buffer.array(), 0, read));
				// don't register channels for OP_WRITE until you have something to write
				key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
				if(!"contacted".equals(key.attachment())) {
					key.attach("firstContact");
				}
			} else if(read == -1) {
				// de-register OP_READ to avoid infinite EOS events loop
				key.interestOps(key.interestOps() & ~SelectionKey.OP_READ);
			} else if(read == 0) {
				System.out.println(this.clientName + " Warn: receive nothing. ");
			}
		} else if(key.isWritable()){
			System.out.println(this.clientName + " will write...");
			if( "firstContact".equals(key.attachment()) ) {
				key.attach("contacted");
				// write client name to server
				byte[] writeContent = this.clientName.getBytes();
				if(channel.write(ByteBuffer.wrap(writeContent)) == writeContent.length)  {
					key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);					
				}
			} else {
				String uuidStr = UUID.randomUUID().toString();
				System.out.println(this.clientName + " sends response: " + uuidStr);
				byte[] writeContent = uuidStr.getBytes();
				if(channel.write(ByteBuffer.wrap(writeContent)) == writeContent.length) {
					key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
				}
			}
		}
	}

}
