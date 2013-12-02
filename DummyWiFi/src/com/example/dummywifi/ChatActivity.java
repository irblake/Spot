package com.example.dummywifi;


import android.net.wifi.WifiInfo;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.inputmethod.EditorInfo;
import com.example.dummywifi.XUtil;
import com.example.dummywifi.ConnectionInfo;

public class ChatActivity extends Activity {
	public Handler handler;
	
	String username = "username";
	
	//Declare the WifiP2pManager
	public WifiP2pManager mManager;
	
	//Declare the Channel
	Channel mChannel;
	
	public static Activity currentChatActivity = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		final EditText editText = (EditText) findViewById(R.id.send);
		
        //This creates an instance of a WiFiP2pManager object
        //We are going to use it to make a WiFiP2pManager.Channel object
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        //This makes the WifiP2pManager.CHannel object
        mChannel = mManager.initialize(this,getMainLooper(), null);
	
        
        editText.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
            		EditText et_history = (EditText) findViewById(R.id.messages);
            		String history = et_history.getText().toString();
            		String message = editText.getText().toString();
            		if (message == "") return true;
            		editText.setText ("");
            		et_history.setText (history + username + ": " + message + "\n");
                    handled = true;
                }
                return handled;
            }
        });
        
        handler = new Handler() {
        	@Override
        	public void handleMessage(Message msg) {
        		if (msg.what == GroupMemberClientAsyncTask.GMCAT_NEW_MESSAGE){
        			String newMessage = (String)msg.obj;
        			
        			EditText et_history = (EditText) findViewById(R.id.messages);
            		String history = et_history.getText().toString();
            		
            		
            		
            		et_history.setText (history + username + ": " + newMessage + "\n");
        			
        		}
        	}
        };
        ChatActivity.currentChatActivity = this;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		Log.i("back","The back button was pressed");
		mManager.removeGroup(mChannel,new ActionListener() {
			
			@Override
            public void onSuccess() {
				Log.i("back","Disconnect Successful");
            }

            @Override
            public void onFailure(int reasonCode) {
            	Log.i("back","Disconnect Failed. Reason:" + reasonCode);
            }
		});
		super.onBackPressed();
	return;
	}


}
