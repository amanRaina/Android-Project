package com.example.zillow;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
//import android.content.pm.PackageInstaller.Session;


@SuppressLint("NewApi") public class BasicInfo extends Activity {
	String v1=null;String v2=null;String lastsoldprice=null;String homedetails=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 1, getResources().getDisplayMetrics());
		String usecode=null;String yearbuilt=null;String lastsoldate=null;
		String lotsize=null;String zpropes=null;String finishedarea=null;String bathroom=null;String zlow=null;String zhigh=null;
		String bedroom=null;String ramount=null;String taxassesyear=null;String taxasses=null;String rlow=null;String rhigh=null;
		String e1=null;String e2=null;
		super.onCreate(savedInstanceState);

        setContentView(R.layout.basic_info);
		JSONObject jsonObject=null;
		
		
		try {
			jsonObject = new JSONObject(ResultActivity.myvalue);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			homedetails = jsonObject.getJSONObject("xml").getString("homeDetails");
			usecode = jsonObject.getJSONObject("xml").getString("useCode");
			lastsoldprice = jsonObject.getJSONObject("xml").getString("lastSoldPrice");
			yearbuilt = jsonObject.getJSONObject("xml").getString("yearBuilt");
			lastsoldate = jsonObject.getJSONObject("xml").getString("lastSoldDate");
			lotsize = jsonObject.getJSONObject("xml").getString("lotSizeSqFt");
			zpropes = jsonObject.getJSONObject("xml").getString("amount");
			finishedarea = jsonObject.getJSONObject("xml").getString("finishedSqFt");
			bathroom = jsonObject.getJSONObject("xml").getString("bathrooms");
			zlow = jsonObject.getJSONObject("xml").getString("zlow");
			zhigh = jsonObject.getJSONObject("xml").getString("zhigh");
			bedroom = jsonObject.getJSONObject("xml").getString("bedrooms");
			ramount = jsonObject.getJSONObject("xml").getString("ramount");
			taxassesyear = jsonObject.getJSONObject("xml").getString("taxAssessmentYear");
			taxasses = jsonObject.getJSONObject("xml").getString("taxAssessment");
			rlow = jsonObject.getJSONObject("xml").getString("rlow");
			rhigh = jsonObject.getJSONObject("xml").getString("rhigh");
			v1 = jsonObject.getJSONObject("xml").getString("valueChange1");
			v2 = jsonObject.getJSONObject("xml").getString("valueChange2");
			e1 = jsonObject.getJSONObject("xml").getString("lastupdated");
			e2 = jsonObject.getJSONObject("xml").getString("lastupdated2");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value[]=v1.split(">");
		String valuechange1=value[1];
		String i1=value[0].replace("<img src=", "");
		String i2=i1.replace(">", "");
		
		String value1[]=v2.split(">");
		String valuechange2=value1[1];
		String i3=value1[0].replace("<img src=", "");
		String i4=i3.replace(">", "");
		
        TableLayout table = (TableLayout)findViewById(R.id.table);
        TextView add= (TextView) findViewById(R.id.textView1);
        add.setText("See more details on Zillow:");
        add.setTextSize(18);
        TableRow tr20 = new TableRow(this);
        tr20.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv20 = new TextView(this);
        tv20.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv20.setText(Html.fromHtml(
	            "<a href="+homedetails+">"+MainActivity.addressdisplay+"</a>"));
		tv20.setMovementMethod(LinkMovementMethod.getInstance());
     //   tv20.setGravity(Gravity.RIGHT);
        tv20.setTextSize(18);
        tr20.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr20.addView(tv20);
        tr20.setBackgroundResource(R.drawable.border);
        table.addView(tr20);
        
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv.setText(usecode);
        tv.setGravity(Gravity.RIGHT);
        TextView p1 = new TextView(this);
        p1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p1.setWidth(dip);
        p1.setText("Property Type:");
        p1.setTextSize(18);
        tv.setTextSize(18);
        tr.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr.addView(p1);
        tr.addView(tv);
        tr.setBackgroundResource(R.drawable.borderblue);
        table.addView(tr);
        
        TableRow tr18 = new TableRow(this);
        tr18.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv18 = new TextView(this);
        tv18.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv18.setText(lastsoldprice);
        tv18.setGravity(Gravity.RIGHT);
        TextView p3 = new TextView(this);
        p3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p3.setText("Year Built:");
        p3.setTextSize(18);
        tv18.setTextSize(18);
        tr18.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr18.addView(p3);
        tr18.addView(tv18);
        tr18.setBackgroundResource(R.drawable.border);
        table.addView(tr18);
        
        TableRow tr3 = new TableRow(this);
        tr3.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv3 = new TextView(this);
        tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv3.setText(lotsize);
        tv3.setGravity(Gravity.RIGHT);
        TextView p5 = new TextView(this);
        p5.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p5.setText("Lot Size:");
        p5.setTextSize(18);
        tv3.setTextSize(18);
        tr3.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr3.addView(p5);
        tr3.addView(tv3);
        tr3.setBackgroundResource(R.drawable.borderblue);
        table.addView(tr3);
        
        TableRow tr5 = new TableRow(this);
        tr5.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv5 = new TextView(this);
        tv5.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        TextView tv6 = new TextView(this);
        tv6.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        tv6.setText("Finshed Area:");
        tv5.setText(finishedarea);
        tv5.setGravity(Gravity.RIGHT);
        tv6.setTextSize(18);
        tv5.setTextSize(18);
        tr5.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr5.addView(tv6);
        tr5.addView(tv5);
        tr5.setBackgroundResource(R.drawable.border);
        table.addView(tr5);
        
        TableRow tr7 = new TableRow(this);
        tr7.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv7 = new TextView(this);
        tv7.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv7.setText(bathroom);
        tv7.setGravity(Gravity.RIGHT);
        TextView p8 = new TextView(this);
        p8.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p8.setText("Bathrooms:");
        p8.setTextSize(18);
        tv7.setTextSize(18);
        tr7.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr7.addView(p8);
        tr7.addView(tv7);
        tr7.setBackgroundResource(R.drawable.borderblue);
        table.addView(tr7);
        
        TableRow tr9 = new TableRow(this);
        tr9.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv9 = new TextView(this);
        tv9.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv9.setText(bedroom);
        tv9.setGravity(Gravity.RIGHT);
        TextView p10 = new TextView(this);
        p10.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p10.setText("Bedrooms:");
        p10.setTextSize(18);
        tv9.setTextSize(18);
        tr9.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr9.addView(p10);
        tr9.addView(tv9);
        tr9.setBackgroundResource(R.drawable.border);
        table.addView(tr9);
        
        TableRow tr11 = new TableRow(this);
        tr11.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv11 = new TextView(this);
        tv11.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv11.setText(taxassesyear);
        tv11.setGravity(Gravity.RIGHT);
        TextView p12 = new TextView(this);
        p12.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p12.setText("Tax Assessment Year:");
        p12.setTextSize(18);
        tv11.setTextSize(18);
        tr11.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr11.addView(p12);
        tr11.addView(tv11);
        tr11.setBackgroundResource(R.drawable.borderblue);
        table.addView(tr11);
        
        TableRow tr12 = new TableRow(this);
        tr12.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv12 = new TextView(this);
        tv12.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv12.setText(taxasses);
        tv12.setGravity(Gravity.RIGHT);
        TextView p13 = new TextView(this);
        p13.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p13.setText("Tax Assessment:");
        p13.setTextSize(18);
        tv12.setTextSize(18);
        tr12.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr12.addView(p13);
        tr12.addView(tv12);
        tr12.setBackgroundResource(R.drawable.border);
        table.addView(tr12);
        
        TableRow tr1 = new TableRow(this);
        tr1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv1.setText(lastsoldprice);
        tv1.setGravity(Gravity.RIGHT);
        TextView p2 = new TextView(this);
        p2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));    
        p2.setText("Last Sold Price:");
        p2.setTextSize(18);
        tv1.setTextSize(18);
        tr1.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr1.addView(p2);
        tr1.addView(tv1);
        tr1.setBackgroundResource(R.drawable.borderblue);
        table.addView(tr1);
        
        
        
        TableRow tr2 = new TableRow(this);
        tr2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv2 = new TextView(this);
        tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv2.setText(lastsoldate);
        tv2.setGravity(Gravity.RIGHT);
        TextView p4 = new TextView(this);
        p4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p4.setText("Last Sold Date:");
        p4.setTextSize(18);
        tv2.setTextSize(18);
        tr2.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr2.addView(p4);
        tr2.addView(tv2);
        tr2.setBackgroundResource(R.drawable.border);
        table.addView(tr2);
        
        
        
        TableRow tr4 = new TableRow(this);
        tr4.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv4 = new TextView(this);
        tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv4.setText(zpropes);
        tv4.setGravity(Gravity.RIGHT);
        TextView p6 = new TextView(this);
        p6.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p6.setText("Zestimate \u00AE Property Estimate as of "+e1);
        p6.setTextSize(18);
        tv4.setTextSize(18);
        tr4.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr4.addView(p6);
        tr4.addView(tv4);
        tr4.setBackgroundResource(R.drawable.borderblue);
        table.addView(tr4);
        
        
        
        TableRow tr15 = new TableRow(this);
        tr15.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv15 = new TextView(this);
        tv15.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv15.setText(valuechange1);
        tv15.setGravity(Gravity.RIGHT);
        TextView p7 = new TextView(this);
        p7.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p7.setText("30 days Overall Change:");
        p7.setTextSize(18);
        tv15.setTextSize(18);
        tr15.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr15.addView(p7);
        WebView web = new WebView(this);
        web.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
         web.loadUrl(i2);
         tr15.addView(web);
        tr15.addView(tv15);
        tr15.setBackgroundResource(R.drawable.border);
        table.addView(tr15);
        
       
        
        TableRow tr8 = new TableRow(this);
        tr8.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv8 = new TextView(this);
        tv8.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv8.setText(zlow +"-"+zhigh);
        tv8.setGravity(Gravity.RIGHT);
        TextView p9 = new TextView(this);
        p9.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p9.setText("All Time Property Range:");
        p9.setTextSize(18);
        tv8.setTextSize(18);
        tr8.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr8.addView(p9);
        tr8.addView(tv8);
        tr8.setBackgroundResource(R.drawable.borderblue);
        table.addView(tr8);
        
       
        
       
       
        
        TableRow tr16 = new TableRow(this);
        tr16.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv16 = new TextView(this);
        tv16.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv16.setText(valuechange2);
        tv16.setGravity(Gravity.RIGHT);
        TextView p16 = new TextView(this);
        p16.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p16.setText("30 Days Rent Change:");
        p16.setTextSize(18);
        tv16.setTextSize(18);
        tr16.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr16.addView(p16);
       WebView webw = new WebView(this);
       webw.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        webw.loadUrl(i4);
       tr16.addView(webw);
        tr16.addView(tv16);
        tr16.setBackgroundResource(R.drawable.border);
        table.addView(tr16);
        
        TableRow tr10 = new TableRow(this);
        tr10.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv10 = new TextView(this);
        tv10.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv10.setText(ramount);
        tv10.setGravity(Gravity.RIGHT);
        TextView p11 = new TextView(this);
        p11.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p11.setText("Rent Zestimate \u00AE valuation as of "+e2);
        p11.setTextSize(18);
        tv10.setTextSize(18);
        tr10.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr10.addView(p11);
        tr10.addView(tv10);
        tr10.setBackgroundResource(R.drawable.borderblue);
        table.addView(tr10);
        
        
        TableRow tr14 = new TableRow(this);
        tr14.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TextView tv14 = new TextView(this);
        tv14.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv14.setText(rlow+"-"+rhigh);
        tv14.setGravity(Gravity.RIGHT);
        TextView p14 = new TextView(this);
        p14.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,2));
        p14.setText("All Time Rent Change:");
        p14.setTextSize(18);
        tv14.setTextSize(18);
        tr14.setPadding(5*dip, 5*dip, 5*dip, 5*dip);
        tr14.addView(p14);
        tr14.addView(tv14);
        tr14.setBackgroundResource(R.drawable.border);
        table.addView(tr14);
       
        //fb
        Button fbButton = (Button) findViewById(R.id.button1);
        //facebook session
        Session session = Session.getActiveSession();

        if(session == null ){
       	 session = new Session(BasicInfo.this);}
       
        Session.setActiveSession(session);
        session.closeAndClearTokenInformation();
        
       
        fbButton.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BasicInfo.this);
    			alertDialogBuilder.setTitle("Post To Facebook");
    			
    			alertDialogBuilder.setPositiveButton("Post Property Details",new DialogInterface.OnClickListener() 
    			{
    	            public void onClick(DialogInterface dialog,int which)
    	            {
    	            	Session.openActiveSession( BasicInfo.this, true, new Session.StatusCallback(){
    	            		@Override
							public void call(Session session, SessionState state, Exception exception) {
								if (state.isOpened() || session.isOpened()){
									postInfo();
								}
    	            		}
    	            	});	
    	            }
    	        });
    			alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() 
    			{
    	            public void onClick(DialogInterface dialog,int which) 
    	            {
    	 		      Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
    	            }
    	        });
    			 
    			alertDialogBuilder.create().show();
    		}
    	});
	}

	@Override
	protected void onResume() {
	  super.onResume();
System.out.println("resume");
	  AppEventsLogger.activateApp(this);
	}
	@Override
	protected void onPause() {
	  super.onPause();
System.out.println("pause");
	  AppEventsLogger.deactivateApp(this);
	}
	
	public void postInfo()
    {
    	System.out.println("herepost");
		Bundle params = new Bundle();
		params.putString("name", MainActivity.addressdisplay);
		params.putString("caption", "Property Information from Zillow.com");
		params.putString("description", "Last Sold Price:"+lastsoldprice+" 30 Days Overall Change:"+v1);
		params.putString("link", homedetails);
		params.putString("picture",MainActivity.v2);
//		System.out.println("here45");
		WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(BasicInfo.this,Session.getActiveSession(),params))
				.setOnCompleteListener(new OnCompleteListener() 
				{	
					@Override
					public void onComplete(Bundle values, FacebookException error) 
					{System.out.println("here55");
						// TODO Auto-generated method stub
						if (error == null) 
						{
							// When the story is posted, echo the success and the post Id.
							final String postId = values.getString("post_id");
							if (postId != null) 
								Toast.makeText(BasicInfo.this,"Posted Story,\nID: "+postId,Toast.LENGTH_SHORT).show();
   
							else // User clicked the Cancel button 
								Toast.makeText(BasicInfo.this.getApplicationContext(),"Post cancelled",Toast.LENGTH_SHORT).show();
						} 

						else if (error instanceof FacebookOperationCanceledException)  // User clicked the "x" button 
							Toast.makeText(BasicInfo.this.getApplicationContext(),"Post cancelled",Toast.LENGTH_SHORT).show();

						else	// Generic, ex: network error 
							Toast.makeText(BasicInfo.this.getApplicationContext(),"Error posting story",Toast.LENGTH_SHORT).show(); 
					}
				}).build();

		feedDialog.show();
    	      
    }

//add these methods
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
}

@Override
protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
    Session session = Session.getActiveSession();
    Session.saveSession(session, outState);
}
}