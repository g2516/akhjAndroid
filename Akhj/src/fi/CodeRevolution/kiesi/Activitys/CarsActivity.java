package fi.CodeRevolution.kiesi.Activitys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import fi.CodeRevolution.kiesi.Models.MyProperties;
import fi.CodeRevolution.kiesi.Models.User;
import fi.CodeRevolution.kiesi.Utils.*;

public class CarsActivity extends ButtonBarActivity{
	ListView listview;
	TextView txtview;
	private CarAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, Car> listDataChild;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_costs);
        Button removeButtom=(Button)findViewById(R.id.removeButton);
        removeButtom.setVisibility(View.INVISIBLE);
        txtview=(TextView)findViewById(R.id.carName);
        User u=MyProperties.getInstance().user;
        txtview.setText("Käyttäjän "+u.getFirstName()+" kaikki autot");
        
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        
     // bind list data
        bindListData();

    }
    
    public void openCosts(View view)
    {
    	int position=Integer.parseInt(view.getTag().toString());
    	Intent homepage = new Intent(CarsActivity.this, CostsActivity.class);
    	homepage.putExtra("carID", position);
        startActivity(homepage);
    }
    public void openEdit(View view)
    {
    	int position=Integer.parseInt(view.getTag().toString());
    	Intent homepage = new Intent(CarsActivity.this, CarSettingsActivity.class);
    	homepage.putExtra("carID", position);
        startActivity(homepage);
    }

    
    /*
     * Preparing the list data
     */
    private void bindListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, Car>();
 
     // Adding Header data
        for(int i=0;i<MyProperties.getInstance().user.getCars().size();i++)
        {
        	
        	listDataHeader.add(MyProperties.getInstance().user.getCars().get(i).toString());
        }
     // Header, Child data
        for(int i=0;i<listDataHeader.size();i++)
        {
        	listDataChild.put(listDataHeader.get(i),MyProperties.getInstance().user.getCars().get(i));
        	
        }
        
        listAdapter = new CarAdapter(this, listDataHeader, listDataChild);
        
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }
    
    
    @Override
    public void addNew(View view) {
    	Intent homepage = new Intent(CarsActivity.this, CarSettingsActivity.class);
    	homepage.putExtra("carID", -1);
        startActivity(homepage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
