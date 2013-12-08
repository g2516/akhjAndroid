package fi.CodeRevolution.kiesi.Activitys;


import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.*;
import fi.CodeRevolution.kiesi.Utils.JsonBuilder;
import fi.CodeRevolution.kiesi.Utils.JsonService;
import fi.CodeRevolution.kiesi.Utils.LoginService;

public class CostsSettings extends ButtonBarActivity  {
	
	private int carID,costID,type;
	private TextView price,kilometers,date,repairTarget,notes,
	inspectionStation,maintenceTarget,liters,insuraceType,insuranceCompany;
	private Button addButton,cancelButton;
	private Car car;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle extras = getIntent().getExtras();
        carID=extras.getInt("carID");
        costID=extras.getInt("costID");
        type=extras.getInt("type");
        
        /*
         * 0 Tankkaus
         * 1 huolto
         * 2 vakuutus
         * 3 katsastus
         * 4 korjaus
         * 
         * minkä tyyppinen kulu
         */
        switch(type)
        {
        case 0:
        	setContentView(R.layout.refill_cost);
        	liters = (TextView)findViewById(R.id.txtLitres);  
        	
        	break;
        case 1:
        	setContentView(R.layout.maintenance_cost);
        	maintenceTarget = (TextView)findViewById(R.id.txtMaintenanceTarget);  
        	break;
        case 2:
        	setContentView(R.layout.insurance_cost);
        	 insuraceType = (TextView)findViewById(R.id.txtInsuranceType);  
             insuranceCompany = (TextView)findViewById(R.id.txtInsuranceCompany); 
        	break;
        case 3:
        	setContentView(R.layout.inspection_cost);
        	notes = (TextView)findViewById(R.id.txtNotes);  
            inspectionStation = (TextView)findViewById(R.id.txtInspectionStation); 
        	break;
        case 4:
        	setContentView(R.layout.repair_cost);
        	repairTarget = (TextView)findViewById(R.id.txtRepairTarget);  
        	break;
        }
        

        
        //auto jonka kuluja muokataan
        car=LoginService.getInstance().user.getCars().get(carID);
        
        //formi inputit
        price = (TextView)findViewById(R.id.txtPrice);  
        kilometers = (TextView)findViewById(R.id.txtKilometers);  
        date = (TextView)findViewById(R.id.txtPvm);  
        
         
        
        
        
       
        
        //nappien alustus
        addButton=(Button)findViewById(R.id.addButton);
        addButton.setText("Muokkaa");
        cancelButton=(Button)findViewById(R.id.removeButton);
        cancelButton.setText("Peruuta");
        cancelButton.setVisibility(View.INVISIBLE);
        
        


        //onko uusi auto vai muokataanko vanhaa
        if(costID != -1)
        {
        	setTitle("Muokkaa kulua");
        this.enableFields(false);
        bindCost();
        }
        else
        {
        	addButton.setText("Tallenna");
        	setTitle("Luo uusi kulu");
        }
    }
	
	 @Override
	  public void backTo(View view)
	    {
	    	Intent post = new Intent(CostsSettings.this, CostsActivity.class);
	    	post.putExtra("carID", carID);
			startActivity(post);
	    }
	 
	 @Override
		public void removeSelected(View view) {
			if(carID > -1)
	        {
	        	this.bindCost();
	        	this.enableFields(false);
	        	addButton.setText("Muokkaa");
	        }
			else
			{
				//todo kaikki kentät tyhjiksi koska peruttiin muutokset ja kyseessä on uuden auton luonti
			}
			cancelButton.setVisibility(View.INVISIBLE);
			
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
				
				
                JsonBuilder builder = new JsonBuilder();
	        try {
	        	
	        	String username=LoginService.getInstance().user.getEmail();
	        	String pass=LoginService.getInstance().user.getPassword();
	        	JSONObject costData;
	        	Object cost=null;
	        	String costType="";
	
	    			
	    	
		                switch(type)
		                {
		                case 0:
		                	costType="refill";
		                	if(costID != -1)
		                	{
		                		cost=car.getRefillCosts().get(costID);
		                	}else
		                	{
		                		cost=new RefillCost();
		                	}
		                	 
		                	  ((RefillCost) cost).setLitres(Double.parseDouble(liters.getText().toString()));

		                	break;
		                case 1:
		                	costType="maintenance";
		                	if(costID != -1)
		                	{
		                		 cost=car.getMaintenanceCosts().get(costID);
		                	}else
		                	{
		                		 cost=new MaintenanceCost();
		                	}
		                	
		                	 ((MaintenanceCost) cost).setMaintenanceTarget(maintenceTarget.getText().toString());
		                	break;
		                case 2:
		                	costType="insurance";
		                	if(costID != -1)
		                	{
		                		cost=car.getInsuranceCosts().get(costID);
		                	}else
		                	{
		                		cost=new InsuranceCost();
		                	}
		                	 
		                	 ((InsuranceCost) cost).setInsuranceCompany(insuranceCompany.getText().toString());
		                	 ((InsuranceCost) cost).setInsuranceType(insuraceType.getText().toString());
		                	break;
		                case 3:
		                	costType="inspection";
		                	if(costID != -1)
		                	{
		                		cost=car.getInspectionCosts().get(costID);
		                	}else
		                	{
		                		cost=new InspectionCost();
		                	}
		                	 
		                	 ((InspectionCost) cost).setInspectionStation(inspectionStation.getText().toString());
		                	 ((InspectionCost) cost).setNotes(notes.getText().toString());
		                	break;
		                case 4:
		                	costType="repair";
		                	if(costID != -1)
		                	{
		                		cost=car.getRepairCosts().get(costID);
		                	}else
		                	{
		                		cost=new RepairCost();
		                	}
		            		 
		            		 ((RepairCost) cost).setRepairTarget(repairTarget.getText().toString());
		                	
		                	break;
		                }
		                ((Cost) cost).setDate(date.getText().toString());
		                ((Cost) cost).setPrice(Double.parseDouble(price.getText().toString()));
		                ((Cost) cost).setKilometers(Double.parseDouble(kilometers.getText().toString()));
		                ((Cost) cost).setCarId(car.getId());
		                if(costID != -1)
	                	{
		                	 costData = builder.buildJson(cost, "cost", username, pass, "modify",costType);
	                	}else
	                	{
	                		 costData = builder.buildJson(cost, "cost", username, pass, "add",costType);
	                	}
	    			
	    	        	
	        	JSONObject response = new JSONObject();
	        
	        	response = new JsonService().execute(costData).get();
	        	if(response.get("status").equals(true))
				{
	        		Toast.makeText(getApplicationContext(), "Tiedot tallennattu", Toast.LENGTH_LONG).show();
	        		
	        		if(costID== -1)
	        		{
	        				LoginService.getInstance().user=LoginService.login(username, pass);
	        				Intent post = new Intent(CostsSettings.this, CostsActivity.class);
	        				post.putExtra("carID", carID);
	        				startActivity(post);
	        		}
	        		else
	        		{
	        			this.enableFields(false);
	        			addButton.setText("Muokkaa");
	        		}
	        		
				}
	        	else
	        	{
	        		Toast.makeText(getApplicationContext(), "Tietojen tallennus epäonnistui", Toast.LENGTH_LONG).show();
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
	
	private void enableFields(boolean b) {
		
		price.setEnabled(b);
        kilometers.setEnabled(b); 
        date.setEnabled(b);
        
        switch(type)
        {
        case 0:
        	liters.setEnabled(b);
        	
        	break;
        case 1:
        	 maintenceTarget.setEnabled(b);
        	break;
        case 2:
        	 insuraceType.setEnabled(b); 
             insuranceCompany.setEnabled(b);  
        	
        	break;
        case 3:
        	 notes.setEnabled(b);
             inspectionStation.setEnabled(b);  
        	break;
        case 4:
        	repairTarget.setEnabled(b); 
        	
        	break;
        }
        
        
	}

	private void bindCost()
	{
		
		
		Object cost=null;
        
        /*
         * 0 Tankkaus
         * 1 huolto
         * 2 vakuutus
         * 3 katsastus
         * 4 korjaus
         * 
         * minkä tyyppinen kulu
         */
        switch(type)
        {
        case 0:
        	 cost=car.getRefillCosts().get(costID);
        	liters.setText(""+((RefillCost) cost).getLitres());
        	
        	break;
        case 1:
        	 cost=car.getMaintenanceCosts().get(costID);
        	 maintenceTarget.setText(""+((MaintenanceCost) cost).getMaintenanceTarget());
        	break;
        case 2:
        	 cost=car.getInsuranceCosts().get(costID);
            insuranceCompany.setText(""+((InsuranceCost) cost).getInsuranceCompany());
            insuraceType.setText(""+((InsuranceCost) cost).getInsuranceType());
        	
        	break;
        case 3:
        	 cost=car.getInspectionCosts().get(costID);
        	 inspectionStation.setText(""+((InspectionCost) cost).getInspectionStation());
        	 notes.setText(""+((InspectionCost) cost).getNotes());
        	break;
        case 4:
    		 cost=car.getRepairCosts().get(costID);
            repairTarget.setText(""+((RepairCost) cost).getRepairTarget());
        	
        	break;
        }
        
        
		price.setText(""+((Cost) cost).getPrice());
        kilometers.setText(""+((Cost) cost).getKilometers());
        date.setText(""+((Cost) cost).getDate());
        
        
        
	}
	

}
