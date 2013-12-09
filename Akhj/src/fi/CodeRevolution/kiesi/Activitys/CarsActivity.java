package fi.CodeRevolution.kiesi.Activitys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;
import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import fi.CodeRevolution.kiesi.Models.User;
import fi.CodeRevolution.kiesi.Utils.*;

public class CarsActivity extends ButtonBarActivity{
	ListView listview;
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
        Button backButtom=(Button)findViewById(R.id.backButton);
        backButtom.setText("Omat tiedot");
        //backButtom.setVisibility(View.INVISIBLE);
        User u=LoginService.getInstance().user;
        setTitle("Käyttäjän "+u.getFirstName()+" kaikki autot");
        
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
    
    @Override
    public void backTo(View view)
    {
	    	Button editButton = (Button)findViewById(R.id.backButton);
	    	if(editButton.getText().equals("Omat tiedot")) {
	    	//int position = Integer.parseInt(view.getTag().toString());
	    	Intent homepage = new Intent(CarsActivity.this, UserSettingsActivity.class);
	    	startActivity(homepage);
    	}
    }
    
    public void deleteSelected(View view)
    {
    	final int position=Integer.parseInt(view.getTag().toString());
    	AlertDialog.Builder builder = new AlertDialog.Builder(CarsActivity.this);
    	// Add the buttons
    	builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
    		
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   
    	      	        if(LoginService.removeCar(position))
    	      	        {
    	      	        	Toast.makeText(getApplicationContext(), "Auto poistettu", Toast.LENGTH_LONG).show();
    	      	        	bindListData();
    	      	        }
    	      	        else
    	      	        {
    	      	        	Toast.makeText(getApplicationContext(), "Auton poisto epäonnistui", Toast.LENGTH_LONG).show();
    	      	        	
    	      	        }
    	           }
    	       });
    	builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });
    	builder.setMessage("Haluatko varmasti poistaa auton?")
        .setTitle("Poista auto");
    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.show();
    	
    	
    }
    /*
     * Preparing the list data
     */
    private void bindListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, Car>();
 
     // Adding Header data
        for(int i=0;i<LoginService.getInstance().user.getCars().size();i++)
        {
        	
        	listDataHeader.add(LoginService.getInstance().user.getCars().get(i).toString());
        }
     // Header, Child data
        for(int i=0;i<listDataHeader.size();i++)
        {
        	listDataChild.put(listDataHeader.get(i),LoginService.getInstance().user.getCars().get(i));
        	
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
