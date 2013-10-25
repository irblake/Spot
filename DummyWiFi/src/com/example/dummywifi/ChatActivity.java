package com.example.dummywifi;


import android.os.Bundle;
import android.view.KeyEvent;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.inputmethod.EditorInfo;

public class ChatActivity extends Activity {
	
	String username = "username";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		final EditText editText = (EditText) findViewById(R.id.send);
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
