package fi.CodeRevolution.kiesi.Activitys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.CodeRevolution.kiesi.Models.*;


public class JsonBuilder
{
	private String url;
	
	
	public JsonBuilder()
	{
		this.url="http://192.168.1.104/index.php?controller=Android";
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
	
    public User parseUser(JSONObject userJson,String pwd) throws JSONException
    {
    	User user=new User(userJson.getInt("id"),userJson.getString("email"),userJson.getString("firstname"),userJson.getString("lastname"),pwd);
		return user;
    }
    public Car parseCar(JSONObject car,int userID, JSONArray costsJson) throws JSONException
    {
    	String date=car.getJSONObject("date").getString("date");
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
    	return new RepairCost(
    			costJson.getInt("id"),
    			carID,
    			date,
    			costJson.getDouble("price"),
    			costJson.getDouble("kilometers"),
    			costJson.getString("repairTarget")					
				);
		
	}
    
    private InspectionCost parseInspection(JSONObject costJson,int carID) throws JSONException {
    	String date=costJson.getJSONObject("date").getString("date");
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
