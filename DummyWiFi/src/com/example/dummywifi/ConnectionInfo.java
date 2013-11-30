package com.example.dummywifi;

import java.net.InetSocketAddress;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.util.Log;
import android.os.AsyncTask;

import com.example.dummywifi.GroupOwnerAsyncTask;
public class ConnectionInfo implements ConnectionInfoListener {

	
	@Override
	public void onConnectionInfoAvailable(WifiP2pInfo info) {
		if (MainActivity.progressDialog != null) {
			MainActivity.progressDialog.dismiss();
		}
		// TODO Auto-generated method stub
		//Give us a WifiP2pInfo
		Log.i("netcode","before requesting WifiP2pInfo");
		//info = new WifiP2pInfo();
		// InetAddress from WifiP2pInfo struct.
		Log.i("netcode","after requesting WifiP2pInfo");
		
		if (info.groupOwnerAddress != null) {
			String groupOwnerAddress = info.groupOwnerAddress.getHostAddress();
        	// After the group negotiation, we can determine the group owner.
        	if (info.groupFormed && info.isGroupOwner) {
            	// Do whatever tasks are specific to the group owner.
            	// One common case is creating a server thread and accepting
        		// incoming connections.
        		Log.i("netcode", "I am the group leader");
        		// Does the async thread start automatically once this class is instantiated?
        		//System.out.println("Group owner recognized!");
        		//Thread ownerTask = new Thread(new GroupOwnerAsyncTask());
        		//ownerTask.run();
        		GroupOwnerServerAsyncTask gosat = new GroupOwnerServerAsyncTask();
        		Log.i("netcode", "Running GroupOwnerAsyncTask");
        		gosat.execute();
        		Log.i("netcode", "GOSAT running");
        		
        	} else if (info.groupFormed) {
            	// The other device acts as the client. In this case,
            	// you'll want to create a client thread that connects to the group
            	// owner.
        		// Does the async thread start automatically once this class is instantiated?
        		//System.out.println("Client recognized");
        		//Thread clientTask = new Thread(new ClientAsyncTask(new InetSocketAddress(groupOwnerAddress, 8888)));
        		//clientTask.run();
        		Log.i("netcode", "Running GMCAT");
        		GroupMemberClientAsyncTask gmcat = new GroupMemberClientAsyncTask(new InetSocketAddress(groupOwnerAddress, 8888));
        		gmcat.execute();
        		Log.i("netcode", "GMCAT is running");
        	}
		}
	}

}