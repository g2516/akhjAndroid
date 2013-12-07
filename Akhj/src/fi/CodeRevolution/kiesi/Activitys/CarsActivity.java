package fi.CodeRevolution.kiesi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.MyProperties;
import fi.CodeRevolution.kiesi.Utils.*;

public class CarsActivity extends ButtonBarActivity{
	ListView listview;
	TextView txtview;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_cars);
        
        
        if(!MyProperties.getInstance().user.getCars().isEmpty() || MyProperties.getInstance().user.getCars().size() > 0)
        {
        	listview = (ListView) findViewById(R.id.carsList);
        	txtview =(TextView)findViewById(R.id.userName);
        	txtview.setText(MyProperties.getInstance().user.getFirstName());
            listview.setAdapter(new CarAdapter(this, MyProperties.getInstance().user.getCars()));
        }
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                int position, long id) {
            	Intent homepage = new Intent(CarsActivity.this, CarInfoActivity.class);
            	homepage.putExtra("carID", position);
                startActivity(homepage);
            }

          });
    }
    
    public void onUsernameClick(View view) {
    	Intent homepage = new Intent(CarsActivity.this, UserSettingsActivity.class);
        startActivity(homepage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
