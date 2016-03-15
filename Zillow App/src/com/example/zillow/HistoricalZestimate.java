package com.example.zillow;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class HistoricalZestimate extends Activity {
	 private ImageSwitcher imageSwitcher;
	 private TextSwitcher textSwitcher;
	 
     Button btnNext;
     Button btnPrev;
     static String h="heeloo";
     Drawable imageIds[]={MainActivity.d,MainActivity.d2,MainActivity.d3};
     private static final String[] TEXTS = { "Historical Zestimate for past 1 year"+"\n"+MainActivity.addressdisplay, "Historical Zestimate for past 5 years"+"\n"+MainActivity.addressdisplay, "Historical Zestimate for past 10 years"+"\n"+MainActivity.addressdisplay };
     int messageCount=imageIds.length;
     // to keep current Index of ImageID array
     int currentIndex=-1; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historical_zestimate);
		
		
		
		   // get The references 
        btnNext=(Button)findViewById(R.id.buttonNext);
        btnPrev=(Button)findViewById(R.id.button1);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        
        // Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked
        imageSwitcher.setFactory(new ViewFactory() {
            
            public View makeView() {
                // TODO Auto-generated method stub
                
                    // Create a new ImageView set it's properties 
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
                    return imageView;
            }
        });
        
     // Declare the animations and initialize them
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        
     // set the animation type to imageSwitcher
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        
        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher1);
        textSwitcher.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				TextView textView = new TextView(getApplicationContext());
				textView.setGravity(Gravity.CENTER);
				textView.setTextSize(15);
                textView.setTextColor(Color.BLACK);
				return textView;
			}
		});
        
    	textSwitcher.setInAnimation(this, android.R.anim.fade_in);
		textSwitcher.setOutAnimation(this, android.R.anim.fade_out);
		textSwitcher.setText(TEXTS[0]);
     //   imageSwitcher.
        imageSwitcher.setImageDrawable(MainActivity.d);
        btnNext.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 currentIndex++;
                   // If index reaches maximum reset it
                    if(currentIndex==messageCount)
                        currentIndex=0;
                    textSwitcher.setText(TEXTS[currentIndex]);
                    imageSwitcher.setImageDrawable(imageIds[currentIndex]);
            }
        });
        
        btnPrev.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 currentIndex--;
                   // If index reaches maximum reset it
                    if(currentIndex<0)
                        currentIndex=TEXTS.length-1;
                    textSwitcher.setText(TEXTS[currentIndex]);
                    imageSwitcher.setImageDrawable(imageIds[currentIndex]);
            }
        });
	}

	
}
