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
	
	final int PORT = 9999;
	final int MAX_REQUESTS = 100;
	private ByteBuffer buffer = ByteBuffer.allocate(1 << 10);
	private String serverName;
	private Map<SocketChannel, Boolean> contactMap = new HashMap<SocketChannel, Boolean>(20);
	private final Random RAND = new Random();
	
	public NIOServer(String name){
		this.serverName = name;
	}

	/**
	 * start service
	 */
	public void startServ(){
		ServerSocketChannel channel = null;
		Selector selector = null;
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
					
					// 由于 select()操作会向 Selector所关联的keys集合中添加元素,
					// 因此，如果不remove掉这个处理过的key，
					// 在下次调用 select() 方法时仍然保留在集合中
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
						channel.socket().getInetAddress());
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
						channel.register(selector, SelectionKey.OP_WRITE);
					}
				} catch(IOException cce){
					closeChannel(channel);
				}
								
			} else if(key.isWritable()){
				SocketChannel channel = (SocketChannel) key.channel();
				buffer.clear();
				String content = String.valueOf(RAND.nextInt(100000));
				System.out.println("server send: " + content);
				buffer.put(content.getBytes());
				buffer.flip();
				try {
					channel.write(buffer);
					channel.register(selector, SelectionKey.OP_READ);
					
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
