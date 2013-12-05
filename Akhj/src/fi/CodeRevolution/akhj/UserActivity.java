package fi.CodeRevolution.akhj;

import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import fi.CodeRevolution.akhj.Models.Car;
import fi.CodeRevolution.akhj.Models.CarAdapter;

public class UserActivity extends ButtonBarActivity{
	ListView listview;
	TextView txtview;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlayout);
        
        ArrayList<Car> autoja = new ArrayList<Car>();
        autoja = createManyCars();
        
        if(!autoja.isEmpty() || autoja.size() > 0)
        {
        	listview = (ListView) findViewById(R.id.carsList);
            listview.setAdapter(new CarAdapter(this, createManyCars()));
        }
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                int position, long id) {
              //Toast.makeText(getApplicationContext(), "Petri hajoo", Toast.LENGTH_LONG).show();
            	Intent homepage = new Intent(UserActivity.this, CarInfoActivity.class);
                startActivity(homepage);
            }

          });
    }
    
    public void onUsernameClick(View view) {
    	Intent homepage = new Intent(UserActivity.this, UserSettingsActivity.class);
        startActivity(homepage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public ArrayList<Car> createManyCars()
    {
    	ArrayList<Car> autot = new ArrayList<Car>();
    	Date asd = new Date(System.currentTimeMillis());
    	
    	for(int i = 0; i < 5; i++)
    	{
    		autot.add(new Car(
    				i, i, "nimi"+i, "volvo", "v70", "moottori", 1991, 5, asd, 2000, 215  
    				));
    	}
    	return autot;
    }
}
