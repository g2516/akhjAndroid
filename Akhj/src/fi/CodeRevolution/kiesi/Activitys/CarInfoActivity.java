package fi.CodeRevolution.kiesi.Activitys;


import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import fi.CodeRevolution.kiesi.Models.MyProperties;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        initListeners();
        Bundle extras = getIntent().getExtras();
        pageID=extras.getInt("carID");
        bindCar();
    }

    private void bindCar()
    {
    	checkPageID();
    	 Car c=MyProperties.getInstance().user.getCars().get(pageID);
         txt = (TextView) findViewById(R.id.carName);
         txt.setText("Kulut autolle "+c.getName()+" : ");
         
    }
    public void insurance_onClick(View view)
    {
    	this.openCostActivity("insurance");
    }
    public void inspection_onClick(View view)
    {
    	this.openCostActivity("inspection");
    }
    public void maintenance_onClick(View view)
    {
    	this.openCostActivity("maintenance");
    }
    public void repair_onClick(View view)
    {
    	this.openCostActivity("repair");
    }
    public void refill_onClick(View view)
    {
    	this.openCostActivity("refill");
    }
    private void openCostActivity(String type)
    {
    	Intent cost=new Intent(CarInfoActivity.this,CostActivity.class);
    	cost.putExtra("type", type);
    	cost.putExtra("carID", pageID);
    	startActivity(cost);
    }
	private void initListeners() {
		lay = (RelativeLayout) findViewById(R.id.lay);
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
	        			}
	        			// direction left to right -> prev page
	        			else if (stopX - startX > SWIPE_MIN_DISTANCE) {
	        				pageID--;
	        			}
	        			bindCar();
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
    	if(this.pageID > MyProperties.getInstance().user.getCars().size()-1)
    	{
    		this.pageID = 0;
    	}
    	else if (this.pageID < 0)
    	{
    		this.pageID = MyProperties.getInstance().user.getCars().size()-1;
    	}
    }
}
