package org.jackJew.io.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * NIO Server
 * @see NIOClient
 * @author Jack
 *
 */
public class NIOServer {
	
	final static int PORT = 9999;
	private ByteBuffer buffer = ByteBuffer.allocate(1 << 10);
	private String serverName;
	private Map<SocketChannel, Boolean> contactMap = new HashMap<>(20);
	private final Random RAND = new Random();
	
	public NIOServer(String name){
		this.serverName = name;
	}

	/**
	 * start service
	 */
	public void startServ(){
		ServerSocketChannel channel;
		Selector selector;
		try {
			channel = ServerSocketChannel.open();
			selector = Selector.open();
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_ACCEPT);
			channel.socket().bind(new InetSocketAddress(PORT));			
			
			System.out.println("NIO server started.");			
			while(true) {
				// a blocking selection operation waiting for incomming events
				selector.select();
				
				Iterator<SelectionKey> keysItr = selector.selectedKeys().iterator();
				while(keysItr.hasNext()){
					SelectionKey key = keysItr.next();
					
					// remove this key from selected-keys
					keysItr.remove();
					if(key.isValid()) {
						handleKey(key, selector);
					}
				}
			}			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void handleKey(SelectionKey key, Selector selector) {		
		try {
			if(key.isAcceptable()){
				ServerSocketChannel sc = (ServerSocketChannel) key.channel();
				SocketChannel channel = sc.accept();
				channel.configureBlocking(false);
				System.out.println(this.serverName + " receives request from " +
						channel.socket().getRemoteSocketAddress());
				channel.write(ByteBuffer.wrap(
								("Hello, welcome to " + this.serverName).getBytes()));
				
				channel.register(selector, SelectionKey.OP_READ);
			} else if(key.isReadable()){
				SocketChannel channel = (SocketChannel) key.channel();
				buffer.clear();  // notice !!!
				try {
					int read = channel.read(buffer);
					if(read > 0){
						String rcvContent = new String(buffer.array(), 0, read);
						if( ! contactMap.containsKey(channel)){
							// first contact with this client
							contactMap.put(channel, true);
							System.out.println("server request comes from client: " + rcvContent);							
						}
						System.out.println("server receive message: " + rcvContent);
						key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
					} else if(read == -1) {
						// de-register the channel with OP_READ to avoid infinite events loop which
						// is unnecessarily wasting CPU
						key.interestOps(key.interestOps() & ~SelectionKey.OP_READ);
					} else if(read == 0) {
						System.out.println("reads empty from  channel.");
					}
				} catch(IOException cce){
					closeChannel(channel);
				}								
			} else if(key.isWritable()){
				SocketChannel channel = (SocketChannel) key.channel();
				buffer.clear();
				String content = String.valueOf(RAND.nextInt(100000));				
				buffer.put(content.getBytes());
				final int size = buffer.position();
				buffer.flip();
				try {
					System.out.println("server send: " + content);
					if(channel.write(buffer) == size) {
						key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
					}	
				} catch(IOException ioe){
					closeChannel(channel);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * channel.close() include key.cancel(), so don't need to do close both.
	 */
	private void closeChannel(SocketChannel channel) throws IOException {
		System.out.println("server closing " + channel);
		channel.close();
		contactMap.remove(channel);		
	}

}
