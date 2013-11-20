package com.example.dummywifi;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

import com.example.dummywifi.util.Connection;

import android.os.AsyncTask;
import android.util.Log;
import java.lang.Void;

public class ClientAsyncTask extends AsyncTask<Void,Void,Void> {

	private SocketAddress serverAddress;
	private Connection serverConnection;
	
	public ClientAsyncTask(SocketAddress serverAddress) {
		this.serverAddress = serverAddress;
	}
	
	@Override
	protected Void doInBackground(Void... voids) {
		
		Log.i("netcode","Client asynchronous task has started");
		Socket clientSocket = new Socket();
		try {
			clientSocket.connect(serverAddress, 3000); // 3 second timeout
			
		} catch (IOException e) {
			Log.e("netcode", "Unable to connect to server (address=" + serverAddress + ")");
			return null;
		}
		
		serverConnection = new Connection(clientSocket);
		//this is a test to what data looks like when sent by client
		String clientConnectMessage = "sup server its yo boy client";
		
		serverConnection.sendData(clientConnectMessage.getBytes());
		
		
		return null;
	}

}
