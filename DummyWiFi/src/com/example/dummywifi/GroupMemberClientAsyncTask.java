package com.example.dummywifi;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

import com.example.dummywifi.util.Connection;

import android.os.AsyncTask;

public class GroupMemberClientAsyncTask implements Runnable {

	private SocketAddress groupOwnerAddress;
	
	public GroupMemberClientAsyncTask(SocketAddress groupOwnerAddress) {
		this.groupOwnerAddress = groupOwnerAddress;
	}
	
	@Override
	public void run() {
		Socket socket = new Socket();
		Connection connection = null;
		
		try {
			socket.bind(null);
			socket.connect(groupOwnerAddress, 3000);
			connection = new Connection(socket);
				
			connection.sendText("!joingroup");
			//socket.getOutputStream().write("!joingroup".getBytes());
			
			// for testing messages
			
			while (connection.isOpen()) {				
				connection.sendText("hello");
				Thread.sleep(5000);
			}
			
			//socket.getOutputStream().flush();
			
			
		} catch (IOException Ex){
			Ex.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (connection != null) 
			connection.close();
		
		return;
	}
	
	

}
