package fi.CodeRevolution.kiesi.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.CodeRevolution.kiesi.Models.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonBuilder
{
	private String url;
	
	
	public JsonBuilder()
	{
		this.url="http://193.166.88.68/index.php?controller=Android";
	}
    public JSONObject buildLogin(String username, String password)
    {
    	JSONObject fullJson = new JSONObject();

    	try {
    		JSONObject dataJson = new JSONObject();
        	JSONObject loginJson = new JSONObject();
        	

    	    //buildataaan login json 
    	    loginJson.put("email",username);
    	    loginJson.put("password",password);
    	    //buildataan koko json
    	    dataJson.put("login", loginJson);
    	    dataJson.put("type", "getData");
    	    // buildataan post pyynty
			fullJson.put("json", dataJson);
			fullJson.put("url", this.url);
			//new JsonService().execute(fullJson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return fullJson;
    }
    
    /**
     * Muodostetaan JSONObject syötetystä oliosta.
     * @param o
     * @return
     */
    public JSONObject buildJson(Object o, String type, String uname, String upass, String action)
    {
    	Gson jason = new Gson();
    	
    	JSONObject loginJson = new JSONObject();
    	JSONObject objectJson = new JSONObject();
    	JSONObject fullJson = new JSONObject();
    	
    	try {
    		loginJson.put("email", uname); //login info
    		loginJson.put("password", upass); //login info
    		objectJson.put("login", loginJson); //lisätään login info objectJsoniin
    		objectJson.put("type", type); //toiminnon tyyppi backendissä (getData. car. cost)
    		objectJson.put("action", action); //toiminto backendissä (add, modify, delete)
    		objectJson.put("data", jason.toJson(o)); //toiminnon tarvitsema data (saadaan oliosta)
    		fullJson.put("json", objectJson); //kasataan kokonainen json, joka lähetetään backendille
    		fullJson.put("url", this.url); //backendin osoite
    		
    	}
    	catch (JSONException ex) {
    		System.out.println("Tapahtui virhe"+ex.getMessage());
    	}
    	
    	return fullJson;
    }
	
    public User parseUser(JSONObject userJson,String pwd) throws JSONException
    {
    	User user=new User(userJson.getInt("id"),userJson.getString("email"),userJson.getString("firstname"),userJson.getString("lastname"),pwd);
		return user;
    }
    public Car parseCar(JSONObject car,int userID, JSONArray costsJson) throws JSONException
    {
    	String date=car.getJSONObject("date").getString("date");
    	String dateParts[] = date.split(" ");
        date = dateParts[0];
    	Car c= new Car(
				car.getInt("id"),
				userID,
				car.getString("name"),
				car.getString("manufacturer"),
				car.getString("model"),
				car.getString("motor"),
				car.getInt("year"),
				car.getDouble("consumption"),
				date,
				car.getDouble("price"),
				car.getDouble("kilometers")					
				);
    	
    	if(costsJson != null && costsJson.length()>0)
		{
    		
			
			for(int j=0;j<costsJson.length();j++)
			{
				JSONObject costJson = costsJson.getJSONObject(j);
				if(costJson.getString("type").equals("repair"))
				{
					c.getRepairCosts().add(this.parseRepair(costJson,c.getId()));
				}
				else if(costJson.getString("type").equals("maintenance"))
				{
					c.getMaintenanceCosts().add(this.parseMaintenance(costJson,c.getId()));
				}
				else if(costJson.getString("type").equals("inspection"))
				{
					c.getInspectionCosts().add(this.parseInspection(costJson,c.getId()));
				}
				else if(costJson.getString("type").equals("insurance"))
				{
					c.getInsuranceCosts().add(this.parseInsurance(costJson,c.getId()));
				}
				else if(costJson.getString("type").equals("refill"))
				{
					c.getRefillCosts().add(this.parseRefill(costJson,c.getId()));
				
				}
			}
		}
    	return c;
    	
    }
    private MaintenanceCost parseMaintenance(JSONObject costJson,int carID) throws JSONException {
    	String date=costJson.getJSONObject("date").getString("date");
    	String dateParts[] = date.split(" ");
        date = dateParts[0];
    	return new MaintenanceCost(
    			costJson.getInt("id"),
    			carID,
    			date,
    			costJson.getDouble("price"),
    			costJson.getDouble("kilometers"),
    			costJson.getString("maintenanceTarget")					
				);
		
	}
    private InsuranceCost parseInsurance(JSONObject costJson,int carID) throws JSONException {
    	String date=costJson.getJSONObject("date").getString("date");
    	String dateParts[] = date.split(" ");
        date = dateParts[0];
    	return new InsuranceCost(
    			costJson.getInt("id"),
    			carID,
    			date,
    			costJson.getDouble("price"),
    			costJson.getDouble("kilometers"),
    			costJson.getString("insuranceType"),
    			costJson.getString("insuranceCompany")					
				);
		
	}
    private RefillCost parseRefill(JSONObject costJson,int carID) throws JSONException {
    	String date=costJson.getJSONObject("date").getString("date");
    	String dateParts[] = date.split(" ");
        date = dateParts[0];
    	return new RefillCost(
    			costJson.getInt("id"),
    			carID,
    			date,
    			costJson.getDouble("price"),
    			costJson.getDouble("kilometers"),
    			costJson.getDouble("litres")					
				);
		
	}
    private RepairCost parseRepair(JSONObject costJson,int carID) throws JSONException {
    	String date=costJson.getJSONObject("date").getString("date");
    	String dateParts[] = date.split(" ");
        date = dateParts[0];
    	return new RepairCost(
    			costJson.getInt("id"),
    			carID,
    			date,
    			costJson.getDouble("price"),
    			costJson.getDouble("kilometers"),
    			costJson.getString("repairtarget")					
				);
		
	}
    
    private InspectionCost parseInspection(JSONObject costJson,int carID) throws JSONException {
    	String date=costJson.getJSONObject("date").getString("date");
    	String dateParts[] = date.split(" ");
        date = dateParts[0];
    	return new InspectionCost(
    			costJson.getInt("id"),
    			carID,
    			date,
    			costJson.getDouble("price"),
    			costJson.getDouble("kilometers"),
    			costJson.getString("inspectionStation"),
    			costJson.getString("repairTarget")					
				);
		
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
