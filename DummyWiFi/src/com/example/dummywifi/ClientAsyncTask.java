package com.example.dummywifi;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

import com.example.dummywifi.util.Connection;

import android.os.AsyncTask;
import android.util.Log;

public class ClientAsyncTask extends AsyncTask<Object,String,String> {

	private SocketAddress serverAddress;
	private Connection serverConnection;
	
	public ClientAsyncTask(SocketAddress serverAddress) {
		this.serverAddress = serverAddress;
	}
	
	@Override
	protected String doInBackground(Object... arg0) {
		
		Socket clientSocket = new Socket();
		try {
			clientSocket.connect(serverAddress, 3000); // 3 second timeout
			
		} catch (IOException e) {
			Log.e("netcode", "Unable to connect to server (address=" + serverAddress + ")");
			return null;
		}
		
		serverConnection = new Connection(clientSocket);
		String clientConnectMessage = "sup server its yo boy client";
		
		serverConnection.sendData(clientConnectMessage.getBytes());
		
		
		return "success?";
	}

}
