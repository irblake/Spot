package com.example.dummywifi.test;

 
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dummywifi.MainActivity;


public class MainActivityUnitTest extends
	android.test.ActivityUnitTestCase<MainActivity>{

	private int buttonID;
	private MainActivity activity;
	
	public MainActivityUnitTest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setUp() throws Exception {
	    super.setUp();
	    Intent intent = new Intent(getInstrumentation().getTargetContext(),
	        MainActivity.class);
	    startActivity(intent, null, null);
	    activity = getActivity();
	  }
	
	public void testDiscoverButton(){
		buttonID = com.example.dummywifi.R.id.atn_direct_discover;
		assertNotNull(activity.findViewById(buttonID));
		Button view = (Button) activity.findViewById(buttonID);
		assertEquals("The button has an incorrect label","Discover",view.getText());
	}
	
	public void testP2PButton() {
		buttonID = com.example.dummywifi.R.id.atn_direct_enable;
		assertNotNull(activity.findViewById(buttonID));
		Button view = (Button) activity.findViewById(buttonID);
		assertEquals("The button has an incorrect label","P2P On/Off",view.getText());
	}
	
	public void testCreateButton() {
		buttonID = com.example.dummywifi.R.id.button1;
		assertNotNull(activity.findViewById(buttonID));
		Button view = (Button) activity.findViewById(buttonID);
		assertEquals("The button has an incorrect label","Create",view.getText());
	}
	
	public void testEditableText() {
		buttonID = com.example.dummywifi.R.id.editText1;
		assertNotNull(activity.findViewById(buttonID));
		EditText editText = (EditText) activity.findViewById(buttonID);
		assertEquals("The EditText's hint is incorrect","Room name", editText.getHint());
	}
	/*
	public void testRelativeLayoutofMainActivity(){
		int targetColor = com.example.dummywifi.R.color.YotsubaB;
	}
	*/

	public void testListView() {
		buttonID = com.example.dummywifi.R.id.listView1;
		assertNotNull(activity.findViewById(buttonID));
	}
	
}
