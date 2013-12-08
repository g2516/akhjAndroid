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
}
