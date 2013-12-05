package fi.CodeRevolution.akhj;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.akhj.Models.*;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlayout); 
        
        Intent homepage = new Intent(MainActivity.this, UserActivity.class);
        startActivity(homepage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	    public void login_onClick(View view)
    {
 
    	String username ="teppo.testaaja@foo.bar";
    	String pwd ="Salakala1";
    	
    	
    	String url="http://193.166.88.68/index.php?controller=Android";
    	Date asd = new Date(System.currentTimeMillis());
    	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
    	String sad = simpleDate.format(asd);
    	RepairCost kulu = new RepairCost(1,7,sad,12,12,"etuvalo");
    	MaintenanceCost huolto = new MaintenanceCost(1, 7, sad, 12, 12, "etuvalo");
    	
    	JSONObject newJson = new JSONObject();
    	
    	JSONObject jsonData = new JSONObject();
    	JSONObject fullJson = new JSONObject();
    	
    	try {
    		Gson jason = new Gson();
    	    String newCost = jason.toJson(huolto);

			jsonData.put("email",username);
			jsonData.put("password",pwd);
			newJson.put("data", newCost);
			newJson.put("login", jsonData);
			newJson.put("type", "cost");
			newJson.put("costType", "maintenance");
			newJson.put("action", "add");
			fullJson.put("json", newJson);
			fullJson.put("url", url);
			new JsonService().execute(fullJson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    }
}
