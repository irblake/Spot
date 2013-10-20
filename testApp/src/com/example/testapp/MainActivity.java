package com.example.testapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView listView;
	private ListView newView;
	private ArrayAdapter<String> arrayAdapter;
	private EditText editText1;
	private ArrayList<String> listItems=new ArrayList<String>(); 
	
	public int clickCount = 0; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Creating the clickable list, and allow actions on it. 
		arrayAdapter = new ArrayAdapter<String>(this, 
		        android.R.layout.simple_list_item_1, listItems);
		listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(arrayAdapter);
		
		//This is so the text box can be used. 
		editText1 = (EditText) findViewById(R.id.editText1);
		
		//This needs to be changed. For some reason I have to say this twice; if I remove one I get a null pointer exception on load. 
		newView = (ListView) findViewById(R.id.list);
		
		//This opens the chat layout on whatever item is clicked. It seems like a janky way of doing it, but it works so I am leaving it
		//as is for now. 
		newView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick (AdapterView<?> parent, View view, int position, long id){
				setContentView(R.layout.activity_chat);
			}
		});
		
	}
	
	//Handles clicking the Create button and adding whatever is in the text box to the list. 
	//Need to implement clearing the textbox after hitting enter, and ideally remove the button and have it use the enter key instead.
	//Also check for null string first, I will implement this after we get enter working. 
	public void addToList(View V) {
		Editable textbox = editText1.getText();
		listItems.add(textbox.toString());
		arrayAdapter.notifyDataSetChanged();
	}
	
	//I have not touched this yet.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
