package fi.CodeRevolution.kiesi.Activitys;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import fi.CodeRevolution.kiesi.Utils.CostAdapter;
import fi.CodeRevolution.kiesi.Utils.LoginService;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CostsActivity extends ButtonBarActivity {
	
	TextView txtview;
	

	private TextView txt;
	private int pageID;
    private CostAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private Button previusButton;
    private Button nextButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_costs);
        
        Bundle extras = getIntent().getExtras();
        pageID=extras.getInt("carID");
        bindCar();
        nextButton=(Button)findViewById(R.id.addButton);
        nextButton.setText("Edellinen");
        previusButton=(Button)findViewById(R.id.removeButton);
        previusButton.setText("Seuraava");
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        
        // bind list data
        bindListData();
        initListeners();
    }
    
    
   
    public void addNewCost(View view) {
    	int position=Integer.parseInt(view.getTag().toString());
    	Intent homepage = new Intent(CostsActivity.this, CostsSettings.class);
    	homepage.putExtra("carID", pageID);
    	homepage.putExtra("costID", -1);
    	homepage.putExtra("type", position);
        startActivity(homepage);
    }
    @Override
    public void backTo(View view) {
    	Intent homepage = new Intent(CostsActivity.this, CarsActivity.class);
        startActivity(homepage);
    }
    
    @Override
    public void addNew(View view) {
    	pageID++;
		checkPageID();
		bindListData();
		bindCar();
    }
	@Override
	public void removeSelected(View view) {
		pageID--;
		checkPageID();
		
		bindListData();
		bindCar();
	    }
    
    
    
    /*
     * Preparing the list data
     */
    private void bindListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        Car c=LoginService.getInstance().user.getCars().get(pageID);
 
        // Adding child data
        List<String> refill = new ArrayList<String>();
        double yhtRefill=0;
        for(int i=0;i<c.getRefillCosts().size();i++)
        {
        	yhtRefill+=c.getRefillCosts().get(i).getPrice();
        	refill.add(c.getRefillCosts().get(i).toString());
        }
        double yhtMaintennace=0;
        List<String> maintenance = new ArrayList<String>();
        for(int i=0;i<c.getMaintenanceCosts().size();i++)
        {
        	yhtMaintennace+=c.getMaintenanceCosts().get(i).getPrice();
        	maintenance.add(c.getMaintenanceCosts().get(i).toString());
        }
        double yhtRepair=0;
        List<String> repair = new ArrayList<String>();
        for(int i=0;i<c.getRepairCosts().size();i++)
        {
        	yhtRepair+=c.getRepairCosts().get(i).getPrice();
        	repair.add(c.getRepairCosts().get(i).toString());
        }
        double yhtInsurance=0;
        List<String> insurance = new ArrayList<String>();
        for(int i=0;i<c.getInsuranceCosts().size();i++)
        {
        	yhtInsurance+=c.getInsuranceCosts().get(i).getPrice();
        	insurance.add(c.getInsuranceCosts().get(i).toString());
        }
        double yhtInspection=0;
        List<String> inspection = new ArrayList<String>();
        for(int i=0;i<c.getInspectionCosts().size();i++)
        {
        	yhtInspection+=c.getInspectionCosts().get(i).getPrice();
        	inspection.add(c.getInspectionCosts().get(i).toString());
        }
        
        // Adding Header data
        listDataHeader.add("Tankkaus kulut yht: "+yhtRefill);
        listDataHeader.add("Huolto kulut yht: "+yhtMaintennace);
        listDataHeader.add("Vakuutus kulut yht: "+yhtInsurance);
        listDataHeader.add("Katsastus kulut yht: "+yhtInspection);
        listDataHeader.add("Korjaus kulut yht: "+yhtRepair);
        
        listDataChild.put(listDataHeader.get(0), refill); // Header, Child data
        listDataChild.put(listDataHeader.get(1), maintenance);
        listDataChild.put(listDataHeader.get(2), insurance);
        listDataChild.put(listDataHeader.get(3), inspection);
        listDataChild.put(listDataHeader.get(4), repair);
        
        listAdapter = new CostAdapter(this, listDataHeader, listDataChild);
        
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }
    private void bindCar()
    {
    	checkPageID();
    	 Car c=LoginService.getInstance().user.getCars().get(pageID);
         setTitle("Kulut autolle "+c.getName()+" : ");
         
    }

	private void initListeners() {
		
		 
 
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
            	Intent homepage = new Intent(CostsActivity.this, CostsSettings.class);
            	homepage.putExtra("carID", pageID);
            	homepage.putExtra("costID", childPosition);
            	homepage.putExtra("type", groupPosition);
                startActivity(homepage);
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
    
    public void checkPageID()
    {
    	if(this.pageID > LoginService.getInstance().user.getCars().size()-1)
    	{
    		this.pageID = 0;
    	}
    	else if (this.pageID < 0)
    	{
    		this.pageID = LoginService.getInstance().user.getCars().size()-1;
    	}
    }
}
