package fi.CodeRevolution.kiesi.Activitys;

import fi.CodeRevolution.akhj.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class ButtonBarActivity extends Activity  
{
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttonbar);
    }
    
    public void addNew(View view) {
    	
    	
    	if(this.getClass().getSimpleName().equals("UserActivity")){
    		Toast.makeText(getApplicationContext(), "Uusi auto saatana!", Toast.LENGTH_LONG).show();
    	}
    	else if(this.getClass().getSimpleName().equals("CarInfoActivity")){
    		Toast.makeText(getApplicationContext(), "Uusi kulu perkele!", Toast.LENGTH_LONG).show();
    	}
    	else{
    		Toast.makeText(getApplicationContext(), "Ei lis‰tt‰v‰‰ saatana!", Toast.LENGTH_LONG).show();
    	}
    }
    public void backTo(View view) {
    	if(!this.getClass().getSimpleName().equals("UserActivity") && !this.getClass().getSimpleName().equals("CarInfoActivity"))
    	{
    		super.onBackPressed();
    	}
    	else if(this.getClass().getSimpleName().equals("CarInfoActivity"))
    	{
    		Intent homepage = new Intent(ButtonBarActivity.this, CarsActivity.class);
            startActivity(homepage);
    	}
    	else
    	{
    		Toast.makeText(getApplicationContext(), "Olet jo juuressa, saatanaa!", Toast.LENGTH_LONG).show();
    	}
    	
    }
    public void removeSelected(View view) {
    	Toast.makeText(getApplicationContext(), "On niin kylm‰....", Toast.LENGTH_LONG).show();
    }

}
