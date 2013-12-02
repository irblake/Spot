package com.example.dummywifi;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

import com.example.dummywifi.util.Connection;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Message;

public class GroupMemberClientAsyncTask implements Runnable {

	private SocketAddress groupOwnerAddress;
	public static int GMCAT_JOIN_MESSAGE = 100;
	public static int GMCAT_NEW_MESSAGE = 101;
	
	private Activity mainActivity, chatActivity;
	
	public GroupMemberClientAsyncTask(Activity mainActivity, SocketAddress groupOwnerAddress) {
		this.groupOwnerAddress = groupOwnerAddress;
		this.mainActivity = mainActivity;
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
			Message msg = new Message();
			msg.what = GMCAT_JOIN_MESSAGE;
			((MainActivity)mainActivity).handler.sendMessage(msg);
			Thread.sleep(500);
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
