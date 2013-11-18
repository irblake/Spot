package com.example.dummywifi;

import java.io.IOException;
import java.net.ServerSocket;

import com.example.dummywifi.util.Connection;

import android.os.AsyncTask;
import android.util.Log;

public class GroupOwnerAsyncTask extends AsyncTask<Object,String,String> {

	//All of this I am adapting out of:
	// http://developer.android.com/guide/topics/connectivity/wifip2p.html#transferring
	
	@Override
	protected String doInBackground(Object... params){
		try{
            /**
             * Create a server socket and wait for client connections. This
             * call blocks until a connection is accepted from a client
             */
            ServerSocket serverSocket = new ServerSocket(8888);
            //Creating a Connection instead of a raw socket so we can use that nice abstraction layer
            Connection client = new Connection(serverSocket.accept());
            //Right now this string is a placeholder. It will have to become something the user typed in
            String message= "dummy";
            client.sendData(message.getBytes());
			return message;
		} catch(IOException e){
			Log.e("netcode", "Looks like I really don't know what I am doing after all");
			Log.e("netcode",e.getMessage());
			return null;
		}
		
	}
	
	
}
