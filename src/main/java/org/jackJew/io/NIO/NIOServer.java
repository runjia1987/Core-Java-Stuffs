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
			channel.socket().bind(new InetSocketAddress(PORT));
			channel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("NIO server started.");
			
			while(true){
				// wait for incomming events
				selector.select();
				// there is something to process on selected keys
				Iterator<SelectionKey> keysItr = selector.selectedKeys().iterator();
				while(keysItr.hasNext()){
					SelectionKey key = keysItr.next();
					
					// 由于 select()操作会向 Selector所关联的键集合中添加元素,
					// 因此，如果不remove掉这个处理过的key，
					// 它就会在下次调用 select() 方法时仍然保留在集合中
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
		System.out.println(key.isAcceptable() + "," + key.isReadable() + "," + key.isWritable());
		
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
							System.out.println("request comes from client: " + rcvContent);
							
						}
						System.out.println("receive message: " + rcvContent);
						channel.register(selector, SelectionKey.OP_WRITE);						
					} else {
						System.out.println("server read nothing. will end the communication.");
						key.cancel();
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
					//注意 selector选择器不能关闭
					closeChannel(channel);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * channel.close() include key.cancel(), so do not both.
	 */
	private void closeChannel(SocketChannel channel) throws IOException {
		System.out.println("server closing " + channel);
		contactMap.remove(channel);
		channel.close();
	}

}
