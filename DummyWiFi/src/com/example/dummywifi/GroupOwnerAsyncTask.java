package com.example.dummywifi;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

import com.example.dummywifi.util.Connection;

import android.os.AsyncTask;
import android.util.Log;

public class GroupOwnerAsyncTask extends AsyncTask<Object,String,String> {

	//All of this I am adapting out of:
	// http://developer.android.com/guide/topics/connectivity/wifip2p.html#transferring
	private boolean stopReading = false;
	
	@Override
	protected String doInBackground(Object... params){
		Connection client;
		
		try{
            /**
             * Create a server socket and wait for client connections. This
             * call blocks until a connection is accepted from a client
             */
            ServerSocket serverSocket = new ServerSocket(8888);
            //Creating a Connection instead of a raw socket so we can use that nice abstraction layer
            client = new Connection(serverSocket.accept());
            //Right now this string is a placeholder. It will have to become something the user typed in
            String message= "dummy";
            client.sendData(message.getBytes());
			
		} catch(IOException e){
			Log.e("netcode", "Looks like I really don't know what I am doing after all");
			Log.e("netcode",e.getMessage());
			return null;
		}
		
		
		// Avoid reallocating the message buffer every loop iteration
		byte[] messageBuffer = new byte[2048];
		
		// Loop forever, reading in messages from the client and logcatting them
		// Eventually, instead of logcatting we will display them on the screen and do the message queue merging
		while (!stopReading) {
			boolean hasMessage = client.receiveData(messageBuffer);
			if (hasMessage) { // there is a new message in our buffer
				String readMessage = new String(messageBuffer);
				Log.i("message", readMessage);
				// Zero out the buffer so we dont get characters leftover
				Arrays.fill(messageBuffer, (byte)0);
			}
		}
		
		return "this probably won't return during normal execution";
		
	}
	
	
}
