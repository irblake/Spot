package com.example.dummywifi;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

import android.os.AsyncTask;

public class GroupMemberClientAsyncTask extends
		AsyncTask<Void, Void, String> {

	private SocketAddress groupOwnerAddress;
	
	public GroupMemberClientAsyncTask(SocketAddress groupOwnerAddress) {
		this.groupOwnerAddress = groupOwnerAddress;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		Socket socket = new Socket();
		
		try {
			socket.bind(null);
			socket.connect(groupOwnerAddress, 3000);
			
			socket.getOutputStream().write("Hello server".getBytes());
			
			socket.getOutputStream().flush();
			socket.close();
			
		} catch (IOException Ex){
			Ex.printStackTrace();
		}
		
		return null;
	}
	
	

}
