package fi.CodeRevolution.akhj;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import fi.CodeRevolution.akhj.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void login_onClick(View view)
    {
    	EditText u=(EditText) findViewById(R.id. txtUserName);
    	EditText p=(EditText) findViewById(R.id. txtPwd);
    	String username =u.getText().toString();
    	String pwd =p.getText().toString();
    	
    	Toast.makeText(getApplicationContext(), username+" "+ pwd, 
    			 Toast.LENGTH_SHORT).show(); 
    	
    	String url="http://192.168.1.104/index.php?controller=Android";
    	JSONObject newJson = new JSONObject();
    	JSONObject jsonData = new JSONObject();
    	JSONObject fullJson = new JSONObject();
    	
    	try {
			jsonData.put("email",username);
			jsonData.put("password",pwd);
			newJson.put("login", jsonData);
			newJson.put("type", "car");
			newJson.put("action", "get");
			fullJson.put("json", newJson);
			fullJson.put("url", url);
			new JsonService().execute(fullJson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    }
    
}
