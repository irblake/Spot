/**
 * 
 */
package com.example.dummywifi;

import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager.GroupInfoListener;

/**
 * @author Ryan
 *
 */
public class ChatGroupInfo implements GroupInfoListener {

	/**
	 * 
	 */
	public ChatGroupInfo() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.net.wifi.p2p.WifiP2pManager.GroupInfoListener#onGroupInfoAvailable(android.net.wifi.p2p.WifiP2pGroup)
	 */
	@Override
	public void onGroupInfoAvailable(WifiP2pGroup arg0) {
		// TODO Auto-generated method stub

	}

}
