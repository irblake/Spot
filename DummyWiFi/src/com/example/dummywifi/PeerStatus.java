package com.example.dummywifi;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.util.Log;

public class PeerStatus implements PeerListListener {

	private int nPeers;
	private WifiP2pDeviceList peerList;
	
	public int getPeerCount() {
		return nPeers;
	}
	
	public WifiP2pDeviceList getPeerList() {
		return peerList;
	}
	
	@Override
	public void onPeersAvailable(WifiP2pDeviceList peers) {
		Log.i("netcode","Hey your peer list changed fyi");
		nPeers = peers.getDeviceList().size();
		peerList = peers;		
		Log.i("netcode", "You have " + nPeers + " available.");
		
		for (WifiP2pDevice d : peerList.getDeviceList()) {
			Log.i("netcode", "Found peer: " + d.deviceName + " (" + d.deviceAddress + ")");
		}
	}
	
	public PeerStatus() {
		nPeers = 0;
	}
	
	

}
