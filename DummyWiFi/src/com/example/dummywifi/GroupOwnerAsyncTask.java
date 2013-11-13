package com.example.dummywifi;

import java.io.IOException;
import java.net.ServerSocket;

import com.example.dummywifi.util.Connection;

import android.os.AsyncTask;
import android.util.Log;

public class GroupOwnerAsyncTask extends AsyncTask<Object,String,String> {

	
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
            
			return "oh";
		
		} catch(IOException e){
			Log.e("netcode", "Looks like I really don't know what I am doing after all");
			return null;
		}
		
	}
	
	
}
