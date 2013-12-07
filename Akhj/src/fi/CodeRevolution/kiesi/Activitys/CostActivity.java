package fi.CodeRevolution.kiesi.Activitys;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Utils.CostAdapter;

public class CostActivity extends ButtonBarActivity {
	
	ListView listview;
	TextView txtview;
	String type;
	int carID;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.show_all_cars);
	        Bundle extras = getIntent().getExtras();
	        type=extras.getString("type");
	        carID=extras.getInt("carID");
	        
	       
	        
	        this.bindCost();
	    }
	 
	 private void bindCost()
	 {
		 		listview = (ListView) findViewById(R.id.carsList);
				listview.setAdapter(new CostAdapter(this,carID,type));
	        	txtview =(TextView)findViewById(R.id.userName);
	        	txtview.setText(type);
	            
	        
	        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	            @Override
	            public void onItemClick(AdapterView<?> parent, final View view,
	                int position, long id) {
	            	
	            }

	          }); 
	 }
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	 

}
