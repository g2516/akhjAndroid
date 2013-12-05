package fi.CodeRevolution.akhj;

import java.util.ArrayList;
import java.util.Date;

import fi.CodeRevolution.akhj.Models.Car;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CarInfoActivity extends ButtonBarActivity{
	
	TextView txtview;
	
	private RelativeLayout lay;
	private TextView txt;
	private final int SWIPE_MIN_DISTANCE = 20;
	private float startX, stopX;
	private int pageID;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carinfolayout);
        
        Bundle param = getIntent().getExtras();
        if (param != null) {
        		pageID = param.getInt("key");
        }
        
        txt = (TextView) findViewById(R.id.costName);
        txt.setText("id: "+pageID);
        lay = (RelativeLayout) findViewById(R.id.lay);
        
        initListeners();
    }

    
	private void initListeners() {
		lay.setOnTouchListener(new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	        	
	        	switch (event.getAction()) {
	        		case MotionEvent.ACTION_DOWN: 
	        			startX = event.getX(); 
	        			break;
	        		case MotionEvent.ACTION_UP:      		
	        			stopX = event.getX(); 
	        			// direction right to left -> next page
	        			if (startX - stopX > SWIPE_MIN_DISTANCE) {
	        				pageID++;
	        				checkPageID();
	        				Intent post = getIntent();
	        				Bundle param = new Bundle();
	        				param.putInt("key", pageID);
	        				post.putExtras(param);
	        				startActivity(post);
	        			}
	        			// direction left to right -> prev page
	        			else if (stopX - startX > SWIPE_MIN_DISTANCE) {
	        				pageID--;
	        				checkPageID();
	        				Intent post = getIntent();
	        				Bundle param = new Bundle();
	        				param.putInt("key", pageID);
	        				post.putExtras(param);
	        				startActivity(post);
	        			}
	        		break;

	        	}
	        	return true;
	        }
	  });
		
	}
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onCarNameClick(View view){
    	Intent homepage = new Intent(CarInfoActivity.this, CarSettingsActivity.class);
        startActivity(homepage);
    }
    
    public void checkPageID()
    {
    	if(this.pageID > 5)
    	{
    		this.pageID = 0;
    	}
    	else if (this.pageID < 0)
    	{
    		this.pageID = 5;
    	}
    }
}
