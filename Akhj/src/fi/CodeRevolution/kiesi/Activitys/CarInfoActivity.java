package fi.CodeRevolution.kiesi.Activitys;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import fi.CodeRevolution.kiesi.Models.MyProperties;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import fi.CodeRevolution.kiesi.Utils.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CarInfoActivity extends ButtonBarActivity{
	
	TextView txtview;
	
	private LinearLayout lay;
	private TextView txt;
	private final int SWIPE_MIN_DISTANCE = 20;
	private float startX, stopX;
	private int pageID;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carinfolayout);
        initListeners();
        Bundle extras = getIntent().getExtras();
        pageID=extras.getInt("carID");
        bindCar();
        
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        
        // bind list data
        bindListData();
 

    }
    /*
     * Preparing the list data
     */
    private void bindListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        Car c=MyProperties.getInstance().user.getCars().get(pageID);
        // Adding child data
        listDataHeader.add("Tankkaus");
        listDataHeader.add("Huolto");
        listDataHeader.add("Vakuutus");
        listDataHeader.add("Katsastus");
        listDataHeader.add("Korjaus");
 
        // Adding child data
        List<String> refill = new ArrayList<String>();
        for(int i=0;i<c.getRefillCosts().size();i++)
        {
        	refill.add(c.getRefillCosts().toString());
        }
        
        List<String> maintenance = new ArrayList<String>();
        for(int i=0;i<c.getMaintenanceCosts().size();i++)
        {
        	maintenance.add(c.getMaintenanceCosts().toString());
        }
        
        List<String> repair = new ArrayList<String>();
        for(int i=0;i<c.getRepairCosts().size();i++)
        {
        	repair.add(c.getRepairCosts().toString());
        }
        
        List<String> insurance = new ArrayList<String>();
        for(int i=0;i<c.getInsuranceCosts().size();i++)
        {
        	insurance.add(c.getInsuranceCosts().toString());
        }
        
        List<String> inspection = new ArrayList<String>();
        for(int i=0;i<c.getInspectionCosts().size();i++)
        {
        	inspection.add(c.getInspectionCosts().toString());
        }
        
        listDataChild.put(listDataHeader.get(0), refill); // Header, Child data
        listDataChild.put(listDataHeader.get(1), maintenance);
        listDataChild.put(listDataHeader.get(2), insurance);
        listDataChild.put(listDataHeader.get(3), inspection);
        listDataChild.put(listDataHeader.get(4), repair);
        
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }
    private void bindCar()
    {
    	checkPageID();
    	 Car c=MyProperties.getInstance().user.getCars().get(pageID);
         txt = (TextView) findViewById(R.id.carName);
         txt.setText("Kulut autolle "+c.getName()+" : ");
         
    }
    private void openCostActivity(String type)
    {
    	Intent cost=new Intent(CarInfoActivity.this,CostActivity.class);
    	cost.putExtra("type", type);
    	cost.putExtra("carID", pageID);
    	startActivity(cost);
    }
	private void initListeners() {
		lay = (LinearLayout) findViewById(R.id.lay);
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
	        			bindListData();
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
    	homepage.putExtra("carID", pageID);
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
