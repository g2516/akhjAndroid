package fi.CodeRevolution.kiesi.Activitys;



import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.*;


public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); 
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void login_onClick(View view)
    {
 
		TextView userName =(TextView) findViewById(R.id.txtUserName);
		TextView password =(TextView) findViewById(R.id.txtPassword);
    	String username =userName.getText().toString();
    	String pwd =password.getText().toString();;
    	
    	
    	JsonBuilder builder=new JsonBuilder();
    	JSONObject login=builder.buildLogin(username, pwd);

    	try {
    		JSONObject response;
			
			response = new JsonService().execute(login).get();
			if(response.get("status").equals(true))
			{
				
				JSONObject userJson=response.getJSONObject("data");
				JSONArray  carsJson=userJson.getJSONArray("cars");
				ArrayList<Car> cars=new ArrayList<Car>();
				
				User u=builder.parseUser(userJson, pwd);
				
				for(int i=0;i<carsJson.length();i++)
				{
					JSONObject carJson = carsJson.getJSONObject(i);
					
					Car car=builder.parseCar(carJson, userJson.getInt("id"),carJson.getJSONArray("costs"));
					cars.add(car);
				}
				u.setCars(cars);
				MyProperties.getInstance().user=u;
				
				Intent post = new Intent(MainActivity.this, CarsActivity.class);
				startActivity(post);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Sisäänkirjautuminen epäonnistui", Toast.LENGTH_LONG).show();
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

    	
    	//Date asd = new Date(System.currentTimeMillis());
    	//SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
    	//String sad = simpleDate.format(asd);
    	//RepairCost kulu = new RepairCost(1,7,sad,12,12,"etuvalo");
    	//MaintenanceCost huolto = new MaintenanceCost(1, 7, sad, 12, 12, "etuvalo");

    }


}
