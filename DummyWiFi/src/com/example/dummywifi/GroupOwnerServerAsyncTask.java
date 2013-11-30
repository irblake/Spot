package com.example.dummywifi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Log.d("netcode", "Server: Socket opened");
            Socket client = serverSocket.accept();
            Log.d("netcode", "Server: connection done");
            
            
            Log.d("netcode", "server: receiving data");
            
            InputStream inputstream = client.getInputStream();
            byte readBytes[] = new byte[1024];
            inputstream.read(readBytes);
            String readString = new String(readBytes);
            
            Log.d("netcode", "received message: " + readString);
            
            //copyFile(inputstream, new FileOutputStream(f));
            serverSocket.close();
            return readString; //f.getAbsolutePath();
        } catch (IOException e) {
            Log.e("netcode", e.getMessage());
            return null;
        }
    }	
	

}
