package com.example.zillow;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.client.utils.URIUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	String code1="";
	public String value;
	public String graph;
	public static String v2;
	public String v3;
	public String v4;
	public static Drawable d;
	public static Drawable d2;
	public static Drawable d3;
	public static String addressdisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
     // Create an ArrayAdapter using the string array and a default spinner layout
     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
             R.array.planets_array, android.R.layout.simple_spinner_item);
     // Specify the layout to use when the list of choices appears
     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     // Apply the adapter to the spinner
     spinner.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public void sendMessage(View view) throws UnsupportedEncodingException  {
        // Do something in response to button click
    	TextView add= (TextView) findViewById(R.id.textView1);
    	TextView add2= (TextView) findViewById(R.id.textView2);
    	TextView add3= (TextView) findViewById(R.id.textView3);
    	EditText text= (EditText) findViewById(R.id.editText1);
    	String address = text.getText().toString();
    	EditText text1= (EditText) findViewById(R.id.editText2);
    	String city = text1.getText().toString();
    	String state = ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
    	String enadd = URLEncoder.encode(address, "UTF-8");
    	String encity = URLEncoder.encode(city, "UTF-8");
     value="http://webassignment-env1.elasticbeanstalk.com/?streetinput="+enadd+"&city="+encity+"&stateinput="+state;
//     Intent intent = new Intent(MainActivity.this,ResultActivity.class);
//   	intent.putExtra("name",value);
//   	startActivity(intent);
     if(address.length()>0 && city.length()>0 && !state.equals("Choose State")){
    	 new JSONParse().execute();
    	 add.setText(""); 
    	 add2.setText(""); 
    	 add3.setText(""); 
    	 
     }
     else{
    	 if(address.length()==0){
    	 
    	 add.setText("This Field is Required");
    	 add.setTextColor(Color.parseColor("#FF0000"));}
    	 else{
    		 add.setText(""); 
    	 }
    	 if(city.length()==0){
        	 
        	 add2.setText("This Field is Required");
        	 add2.setTextColor(Color.parseColor("#FF0000"));}
    	 else{
    		 add2.setText(""); 
    	 }
    	 if(state.equals("Choose State")){
        	 
        	 add3.setText("This Field is Required");
        	 add3.setTextColor(Color.parseColor("#FF0000"));}
    	 else{
    		 add3.setText(""); 
    	 }
     }
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
    	TextView add4= (TextView) findViewById(R.id.textView4);
       @Override
         protected JSONObject doInBackground(String... args) {
         JSONParser jParser = new JSONParser();
         // Getting JSON from URL
         JSONObject json = jParser.getJSONFromUrl(value);
         System.out.println(json);
         String code="graph2";
         String c1;
         String c2;
         String c3;
         String c4;
         
         try {
			//graph=json.getJSONObject(code).toString();
			graph=json.toString();
			v2 = json.getJSONObject("graph1").getString("url1");
			code1 = json.getJSONObject("xml").getString("code");
			v3 = json.getJSONObject("graph2").getString("url2");
			v4 = json.getJSONObject("graph3").getString("url3");
			
			c1 = json.getJSONObject("xml").getString("street");
			c2 = json.getJSONObject("xml").getString("city");
			c3 = json.getJSONObject("xml").getString("zipcode");
			c4 = json.getJSONObject("xml").getString("state");
			addressdisplay=c1+","+c2+","+c4+"-"+c3;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         try{
             InputStream is = (InputStream) new URL(v2).getContent();
              d = Drawable.createFromStream(is, "src name");
              InputStream is1 = (InputStream) new URL(v3).getContent();
              d2 = Drawable.createFromStream(is1, "src name1");
              InputStream is2 = (InputStream) new URL(v4).getContent();
              d3 = Drawable.createFromStream(is2, "src name2");
             System.out.println(d);
             System.out.println(d2);
             System.out.println(d3);
             System.out.println(code1);
           }catch (Exception e) {
         	  System.out.println("here");
             System.out.println("Exc="+e);
             return null;
           }
         return json;
       }
        @Override
          protected void onPostExecute(JSONObject json) {
         if(code1.equals("0")){
        	  Intent intent = new Intent(MainActivity.this,ResultActivity.class);
          	intent.putExtra("name",graph);
          	startActivity(intent);
          	 add4.setText("");
         }
         else{
        	 
        	 add4.setText("No exact match found--Verify that the given address is correct");
        	 add4.setTextColor(Color.parseColor("#FFFFFF"));
         }
         
        }
}
}
