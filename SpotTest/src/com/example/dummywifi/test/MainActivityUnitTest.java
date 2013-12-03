package com.example.dummywifi.test;


import android.content.Intent;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
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


}
