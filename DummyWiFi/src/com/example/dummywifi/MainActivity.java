package com.example.dummywifi;

import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.view.Menu;

public class MainActivity extends Activity {

	//We need an intent filter to catch only the intents we care about
	private final IntentFilter intentFilter = new IntentFilter();
	
	//Declare the WifiP2pManager
	WifiP2pManager mManager;
	
	//Declare the Channel
	Channel mChannel;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*We care about 4 intents:
        When the WiFi status changes, when the list of available peers changes,
        when the state of the WiFi connectivity has changed, and when the state
        of the device has changed */
        
        //The "WiFi has changed status" intent
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        
        //The "peers list has changed" intent
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        
        //The "state of the WiFi connectivity has changed" intent
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        
        //Indicates the device's details have changed
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        
        //This creates an instance of a WiFiP2pManager object
        //We are going to use it to make a WiFiP2pManager.Channel object
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        //This makes the WifiP2pManager.CHannel object
        mChannel = mManager.initialize(this,getMainLooper(), null);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
