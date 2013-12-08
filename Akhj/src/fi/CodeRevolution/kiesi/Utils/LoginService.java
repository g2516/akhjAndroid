package fi.CodeRevolution.kiesi.Utils;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import fi.CodeRevolution.kiesi.Models.Car;
import fi.CodeRevolution.kiesi.Models.User;

public class LoginService {
	private static LoginService mInstance= null;
	
	public User user;
	private static JsonBuilder builder=null;
	
	protected LoginService()
	{}
	
	public static synchronized LoginService getInstance(){
    	if(null == mInstance){
    		mInstance = new LoginService();
    	}
    	return mInstance;
    }
	public static synchronized JsonBuilder Builder(){
    	if(null == builder){
    		builder = new JsonBuilder();
    	}
    	return builder;
    }
	public static User login(String username, String pass)
	{
		JSONObject login=Builder().buildLogin(username, pass);
		
		JSONObject response;
		try {
			response = new JsonService().execute(login).get();
			
			if(response.get("status").equals(true))
			{
				
				JSONObject userJson=response.getJSONObject("data");
				JSONArray  carsJson=userJson.getJSONArray("cars");
				ArrayList<Car> cars=new ArrayList<Car>();
				
				User u=Builder().parseUser(userJson, pass);
				
				for(int i=0;i<carsJson.length();i++)
				{
					JSONObject carJson = carsJson.getJSONObject(i);
					JSONArray costsJson = carJson.getJSONArray("costs");
					
					Car car=Builder().parseCar(carJson, userJson.getInt("id"),costsJson);
					cars.add(car);
				}
				u.setCars(cars);
				return u;
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
		
		return null;	
		
	}

	public static boolean removeCost(int carID, int costType,int position)
	{
		Object cost=null;
		String type="";
		switch(costType)
          {
          case 0:
        	  type="refill";
        	  cost=LoginService.getInstance().user.getCars().get(carID).getRefillCosts().get(position);
        	  
          case 1:
        	  type="maintenance";
        	  cost=LoginService.getInstance().user.getCars().get(carID).getMaintenanceCosts().get(position);
          	break;
          case 2:
        	  type="insurance";
        	  cost=LoginService.getInstance().user.getCars().get(carID).getInsuranceCosts().get(position);
          	break;
          case 3:
        	  type="inspection";
        	  cost=LoginService.getInstance().user.getCars().get(carID).getInspectionCosts().get(position);
          	break;
          case 4:
        	  type="repair";
        	  cost=LoginService.getInstance().user.getCars().get(carID).getRepairCosts().get(position);
          	break;
          }
		
		String username=LoginService.getInstance().user.getEmail();
      	String pass=LoginService.getInstance().user.getPassword();
      	JSONObject costData = Builder().buildJson(cost, "cost", username, pass, "delete",type);
      	try {
	        	JSONObject response = new JSONObject();
	        
	        	response = new JsonService().execute(costData).get();
	        	if(response.get("status").equals(true))
				{
	        		LoginService.getInstance().user=LoginService.login(username, pass);
	        		
	        		return true;
				}
	        	else
	        	{
	        		return false;
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
      	return false;
      	
      	
	}
	public static boolean removeCar(int position)
	{
		try {
	        	Car c=LoginService.getInstance().user.getCars().get(position);
	        	String username=LoginService.getInstance().user.getEmail();
	        	String pass=LoginService.getInstance().user.getPassword();
	        	
	        	JSONObject carData = Builder().buildJson(c, "car", username, pass, "delete",null);
	        	JSONObject response = new JSONObject();
	        
	        	response = new JsonService().execute(carData).get();
	        	if(response.get("status").equals(true))
				{
	        		LoginService.getInstance().user.getCars().remove(position);
	        		return true;
	        		
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
		return false;
	}

}
