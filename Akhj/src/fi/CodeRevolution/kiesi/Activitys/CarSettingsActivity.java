package fi.CodeRevolution.kiesi.Activitys;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import fi.CodeRevolution.kiesi.Models.JsonService;
import fi.CodeRevolution.kiesi.Models.MyProperties;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CarSettingsActivity extends ButtonBarActivity {
	
	TextView nimi;        
    TextView valmistaja;        
    TextView malli;        
    TextView moottori;        
    TextView year;        
    TextView kulutus;        
    TextView date;        
    TextView price;
    TextView kilometrit;
    Button addButton;
    int carID;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carsettingslayout); 
        Bundle extras = getIntent().getExtras();
        carID=extras.getInt("carID");
        
        
         nimi = (TextView)findViewById(R.id.editName);        
         valmistaja = (TextView)findViewById(R.id.editValmistaja);        
         malli = (TextView)findViewById(R.id.editMalli);        
         moottori = (TextView)findViewById(R.id.editMoottori);        
         year = (TextView)findViewById(R.id.editVuosi);        
         kulutus = (TextView)findViewById(R.id.editKulutus);        
         date = (TextView)findViewById(R.id.editDate);        
         price = (TextView)findViewById(R.id.editHinta);
         kilometrit = (TextView)findViewById(R.id.editKilometrit);
        addButton=(Button)findViewById(R.id.addButton);
        addButton.setText("Muokkaa");
        
        
        if(carID > -1)
        {
        	this.bindFields();
        	this.enableFields(false);
        }
        else
        {
        	addButton.setText("Tallenna");
        }
        
        

        
    }
    private void enableFields(boolean enabled) {
		
    	nimi.setEnabled(enabled);
        valmistaja.setEnabled(enabled);
        malli.setEnabled(enabled);
        moottori.setEnabled(enabled);
        year.setEnabled(enabled);
        kulutus.setEnabled(enabled);
        date.setEnabled(enabled);
        price.setEnabled(enabled);
        kilometrit.setEnabled(enabled);
	}
	@Override
    public void addNew(View view) {
		if(addButton.getText().equals("Muokkaa"))
		{
			this.enableFields(true);
			addButton.setText("Tallenna");
		}
		else if(addButton.getText().equals("Tallenna"))
		{
			this.enableFields(false);
			Car c=MyProperties.getInstance().user.getCars().get(carID);
			c.setName(nimi.getText().toString());
			c.setManufacturer(valmistaja.getText().toString());
	        c.setModel(malli.getText().toString());
	        c.setMotor(moottori.getText().toString());
	        c.setYear(Integer.parseInt(year.getText().toString()));
	        c.setConsumption(Float.parseFloat(kulutus.getText().toString()));
	        c.setDate(date.getText().toString());
	        c.setPrice(Float.parseFloat(price.getText().toString()));
	        c.setKilometers(Float.parseFloat(kilometrit.getText().toString()));
	        
	        JsonBuilder builder = new JsonBuilder();
	        JsonService service = new JsonService();
	        try {
	        	JSONObject carData = builder.buildJson(c, "car", "teppo.testaaja@foo.bar", "Salakala1", "modify");
	        	JSONObject response = new JSONObject();
	        
	        	response = response = new JsonService().execute(carData).get();
	        	Toast.makeText(getApplicationContext(), "Tiedot tallennattu", Toast.LENGTH_LONG).show();
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			//todo tallenna tiedot ensin autoon ja sitten tietokantaan
			addButton.setText("Muokkaa");
		}
    }
	@Override
	public void removeSelected(View view) {
		if(carID > -1)
        {
        	this.bindFields();
        	this.enableFields(false);
        	addButton.setText("Muokkaa");
        }
		else
		{
			//todo kaikki kent�t tyhjiksi koska peruttiin muutokset ja kyseess� on uuden auton luonti
		}
		
		
	    }

    private void bindFields()
    {
    	//t�ll� tavalla heataan tietoja:{ MyProperties.getInstance().user } sis�lt�� kaiken datan.
    	Car c=MyProperties.getInstance().user.getCars().get(carID);
        nimi.setText(c.getName());
        valmistaja.setText(c.getManufacturer());
        malli.setText(c.getModel());
        moottori.setText(c.getMotor());
        year.setText(""+c.getYear());
        kulutus.setText(""+c.getConsumption());
        date.setText(c.getDate());
        price.setText(""+c.getPrice());
        kilometrit.setText(""+c.getKilometers());
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
