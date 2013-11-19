package com.example.dummywifi;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import com.example.dummywifi.GroupOwnerAsyncTask;
public class ConnectionInfo implements ConnectionInfoListener {

	@Override
	public void onConnectionInfoAvailable(WifiP2pInfo info) {
		// TODO Auto-generated method stub
		// InetAddress from WifiP2pInfo struct.
        String groupOwnerAddress = info.groupOwnerAddress.getHostAddress();

        // After the group negotiation, we can determine the group owner.
        if (info.groupFormed && info.isGroupOwner) {
            // Do whatever tasks are specific to the group owner.
            // One common case is creating a server thread and accepting
            // incoming connections.
        	//I don't knwow ahat tthe fuck
        	GroupOwnerAsyncTask task = new GroupOwnerAsyncTask();
        } else if (info.groupFormed) {
            // The other device acts as the client. In this case,
            // you'll want to create a client thread that connects to the group
            // owner.
        }
	}

}
