package com.example.zillow;

import android.app.ActionBar;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class ResultActivity extends Activity {
	public static String myvalue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Intent intent = getIntent();
		myvalue=intent.getStringExtra("name");
//		JSONObject jsonObject=null;
//		try {
//			jsonObject = new JSONObject(myvalue);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String my=null;
//		try {
//			my = jsonObject.getString("graph2");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	TextView text = (TextView)findViewById(R.id.textView1);
//	text.setText(my);
		// create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
        tabHost.setup(mLocalActivityManager);
        
        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabSpec tab2 = tabHost.newTabSpec("Second Tab");

       // Set the Tab name and Activity
       // that will be opened when particular Tab will be selected
        tab1.setIndicator("Basic Info");
        
        tab1.setContent(new Intent(this,BasicInfo.class));
        
        tab2.setIndicator("Historical Zestimate");
        tab2.setContent(new Intent(this,HistoricalZestimate.class));

        
        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        
        
        TextView add= (TextView) findViewById(R.id.textView1);
		add.setTextSize(15);
		add.setTextColor(Color.BLACK);
		add.setText(Html.fromHtml(
	            "&#169; Zillow, Inc.,2006-2014"+"<br>"+"Use is subject to"+
	            "<a href=\"http://www.zillow.com/corp/Terms.htm\"> Terms of Use" +"<br>"+ "What's a Zestimate</a>"));
		add.setMovementMethod(LinkMovementMethod.getInstance());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
