package com.example.dummywifi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.dummywifi.models.Client;
import com.example.dummywifi.util.Connection;

//import com.example.android.wifidirect.WiFiDirectActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GroupOwnerServerAsyncTask extends
		AsyncTask<Void, Void, String> {

	//private Context context;
    //private TextView statusText;

    /**
     * @param context
     * @param statusText
     */
    public GroupOwnerServerAsyncTask(/*Context context, View statusText*/) {
        /*this.context = context;
        this.statusText = (TextView) statusText;*/
    }

    @Override
    protected String doInBackground(Void... params) {
    	ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8888);
            Log.d("netcode", "Server: Socket opened");
            
            while (!serverSocket.isClosed()) { // shouldn't happen unless maybe the wifi gets turned off
            	// keep waiting for clients to come, then accept them and make a worker for them
	            Socket clientSocket = serverSocket.accept();
	            
	            Log.d("netcode", "Server: a connection with a client has been established");
	            
	            Connection connection = new Connection(clientSocket);	                 
	            Client client = new Client(connection);
	            
	            GroupOwnerWorkerAsyncTask gowat = new GroupOwnerWorkerAsyncTask(client);
	            Log.d("netcode", "Worker created, running it");
	            
	            Thread workerThread = new Thread(gowat);
	            workerThread.start();
	            
	            Log.d("netcode", "Worker thread started, status is: " + workerThread.getState());
	                                   
            }
            
            serverSocket.close();
            return null; 
        } catch (IOException e) {
            Log.e("netcode", e.getMessage());
            
            return null;
        }
    }	
	

}
