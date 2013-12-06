package fi.CodeRevolution.kiesi.Activitys;

import fi.CodeRevolution.akhj.R;
import android.os.Bundle;
import android.view.Menu;

public class CarSettingsActivity extends ButtonBarActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carsettingslayout); 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
