package fi.CodeRevolution.kiesi.Activitys;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import fi.CodeRevolution.kiesi.Models.MyProperties;
import fi.CodeRevolution.kiesi.Models.User;
import fi.CodeRevolution.kiesi.Utils.JsonBuilder;
import fi.CodeRevolution.kiesi.Utils.JsonService;
import android.content.Intent;
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
    Button cancelButton;
    int carID;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_settings); 
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
        cancelButton=(Button)findViewById(R.id.removeButton);
        cancelButton.setText("Peruuta");
        cancelButton.setVisibility(View.INVISIBLE);
        TextView otsikko=(TextView)findViewById(R.id.txtOtsikko);
        if(carID > -1)
        {
        	this.bindFields();
        	this.enableFields(false);
        	otsikko.setText("Muokkaa autoa");
        	
        }
        else
        {
        	addButton.setText("Tallenna");
        	otsikko.setText("Luo uusi auto");
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
			cancelButton.setVisibility(View.VISIBLE);
		}
		else if(addButton.getText().equals("Tallenna"))
		{
			cancelButton.setVisibility(View.INVISIBLE);
			this.enableFields(false);
			
                JsonBuilder builder = new JsonBuilder();
	        try {
	        	
	        	String username=MyProperties.getInstance().user.getEmail();
	        	String pass=MyProperties.getInstance().user.getPassword();
	        	JSONObject carData;
	        	if(carID != -1) {
	    			Car c=MyProperties.getInstance().user.getCars().get(carID);
	    			c.setName(nimi.getText().toString());
	    			c.setManufacturer(valmistaja.getText().toString());
	    	        c.setModel(malli.getText().toString());
	    	        c.setMotor(moottori.getText().toString());
	    	        c.setYear(Integer.parseInt(year.getText().toString()));
	    	        c.setConsumption(Double.parseDouble(kulutus.getText().toString()));
	    	        c.setDate(date.getText().toString());
	    	        c.setPrice(Double.parseDouble(price.getText().toString()));
	    	        c.setKilometers(Double.parseDouble(kilometrit.getText().toString()));
		        	carData = builder.buildJson(c, "car", username, pass, "modify");
	    			}
	    			else
	    			{
	                    Car c = new Car(0, 1, nimi.getText().toString(), valmistaja.getText().toString(), malli.getText().toString(),
	                                    moottori.getText().toString(), Integer.parseInt(year.getText().toString()),
	                                    Double.parseDouble(kulutus.getText().toString()), date.getText().toString(),
	                                    Double.parseDouble(price.getText().toString()), Double.parseDouble(kilometrit.getText().toString()));
	    			
	    	        	 carData = builder.buildJson(c, "car", username, pass, "add");
	    			}
	        	
	        	
	        	

	        	

	        	JSONObject response = new JSONObject();
	        
	        	response = new JsonService().execute(carData).get();
	        	if(response.get("status").equals(true))
				{
	        		Toast.makeText(getApplicationContext(), "Tiedot tallennattu", Toast.LENGTH_LONG).show();
	        		
	        		if(carID== -1)
	        		{
	        			JSONObject login=builder.buildLogin(username, pass);

	        				response = new JsonService().execute(login).get();
	        				if(response.get("status").equals(true))
	        				{
	        					
	        					JSONObject userJson=response.getJSONObject("data");
	        					JSONArray  carsJson=userJson.getJSONArray("cars");
	        					ArrayList<Car> cars=new ArrayList<Car>();
	        					
	        					User u=builder.parseUser(userJson, pass);
	        					
	        					for(int i=0;i<carsJson.length();i++)
	        					{
	        						JSONObject carJson = carsJson.getJSONObject(i);
	        						JSONArray costsJson = carJson.getJSONArray("costs");
	        						
	        						Car car=builder.parseCar(carJson, userJson.getInt("id"),costsJson);
	        						cars.add(car);
	        					}
	        					u.setCars(cars);
	        					MyProperties.getInstance().user=u;
	        					
	        				}
	        				Intent post = new Intent(CarSettingsActivity.this, CarsActivity.class);
	        				startActivity(post);
	        		}
	        		else
	        		{
	        			addButton.setText("Muokkaa");
	        		}
	        		
				}
	        	else
	        	{
	        		Toast.makeText(getApplicationContext(), "Tietojen tallennus ep‰onnistui", Toast.LENGTH_LONG).show();
	        	}
	        		
	        	
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			
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
			//todo kaikki kent‰t tyhjiksi koska peruttiin muutokset ja kyseess‰ on uuden auton luonti
		}
		cancelButton.setVisibility(View.INVISIBLE);
		
	    }

    private void bindFields()
    {
    	//t‰ll‰ tavalla heataan tietoja:{ MyProperties.getInstance().user } sis‰lt‰‰ kaiken datan.
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
