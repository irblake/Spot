package com.example.dummywifi;

import java.util.Hashtable;
import java.util.Map;

import com.example.dummywifi.MessengerCommands.CommandExecutor;
import com.example.dummywifi.models.Client;
import com.example.dummywifi.util.Connection;

import android.os.AsyncTask;
import android.util.Log;

/**
 * This is a worker thread, each client their own worker on the server
 * The worker listens to the clients for messages, then will either execute the command
 * (like if the client sent "!connectedusers", the worker will send back a list of all users
 * in the room, otherwise if it is not a command, the server will add the message to the message
 * queue for distribution during the next update tick
 *
 */


public class GroupOwnerWorkerAsyncTask implements Runnable {
	
	
	private Client client;
	public static Hashtable<String, CommandExecutor> commandMap = new Hashtable<String, CommandExecutor> ();
	
	public GroupOwnerWorkerAsyncTask(Client client){ 
		this.client = client;
	}
	
	private String trimNullBytes(byte[] in) {
    	StringBuffer sb = new StringBuffer();
    	
    	for (int i = 0; i < in.length; i++) {
    		if (in[i] == 0) break;
    		sb.append((char)in[i]);
    	}
    	
    	return sb.toString();
    }
	
	private void runCommand(String command, String[] args) {
		CommandExecutor exec = commandMap.get(command);
		if (exec != null) {
			exec.executeCommand(client, command, args);
		}		
	}

	@Override
	public void run() {		
		Log.d("netcode", "worker listening to client messages");
		Connection connection = client.getConnection();
		String readString = null;
		
		while (connection.isOpen()) {            	
        	byte[] buffer = new byte[10000];
        	//Log.d("message", "trying to receive a message");
            if (connection.receiveData(buffer)) {
            	//Log.d("message", "message received");
            	readString = trimNullBytes(buffer);
            	Log.d("message", "received message: " + readString);
            	
            	if (readString.startsWith("!")) {
            		// it's a command
            		String[] args = readString.split("\\s+");
            		runCommand(args[0], args);
            	} else {
            		// it's a message
            		// put it in the message queue
            		Log.d("message", "put '" + readString + "' into the message queue");
            	}
            }
            try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		Log.d("netcode", "connection with client has been closed");
		
		return;
	}
	

}
