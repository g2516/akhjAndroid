package fi.CodeRevolution.kiesi.Activitys;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import fi.CodeRevolution.kiesi.Models.User;
import fi.CodeRevolution.kiesi.Utils.JsonService;
import fi.CodeRevolution.kiesi.Utils.LoginService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserSettingsActivity extends ButtonBarActivity {
	
	TextView etunimi;        
    TextView sukunimi;        
    TextView email;        
    TextView oldpass;        
    TextView newpass1;        
    TextView newpass2;
    
    Button addButton;
    Button removeButton;
    User u=LoginService.getInstance().user;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usersettingslayout); 
        addButton = (Button)findViewById(R.id.addButton);
        removeButton = (Button)findViewById(R.id.removeButton);
        addButton.setText("Muokkaa");
        removeButton.setVisibility(View.INVISIBLE);
        
        etunimi = (TextView)findViewById(R.id.editText1);        
        sukunimi = (TextView)findViewById(R.id.editText2);        
        email = (TextView)findViewById(R.id.editText3);        
        oldpass = (TextView)findViewById(R.id.editText6);        
        newpass1 = (TextView)findViewById(R.id.editText5);        
        newpass2 = (TextView)findViewById(R.id.editText4); 
        
        setTitle("K‰ytt‰j‰n "+u.getFirstName()+" omat tiedot");
        this.bindFields();
        this.enableFields(false);
    }
    
    private void enableFields(boolean enabled) {
		
    	etunimi.setEnabled(enabled);
    	sukunimi.setEnabled(enabled);
    	email.setEnabled(enabled);
    	oldpass.setEnabled(enabled);
    	newpass1.setEnabled(enabled);
    	newpass2.setEnabled(enabled);
	}
    
    @Override
    public void addNew (View view) 
    {
    	if(addButton.getText().equals("Muokkaa"))
		{
			this.enableFields(true);
			addButton.setText("Tallenna");
		}
		else if(addButton.getText().equals("Tallenna"))
		{
			JSONObject loginJson = new JSONObject();
			JSONObject editData = new JSONObject();
			JSONObject dataJson = new JSONObject();
			JSONObject fullJson = new JSONObject();
			try {
				loginJson.put("email", email.getText().toString());
				loginJson.put("password", u.getPassword());
				
				editData.put("firstname", etunimi.getText().toString());
				editData.put("lastname", sukunimi.getText().toString());
				editData.put("email", email.getText().toString());
				editData.put("pass1", newpass1.getText().toString());
				editData.put("pass2", newpass2.getText().toString());
				editData.put("pass3", oldpass.getText().toString());
				
				dataJson.put("login", loginJson);
				dataJson.put("type", "user");
				dataJson.put("action", "modify");
				dataJson.put("data", editData);
				fullJson.put("json", dataJson);
				fullJson.put("url", "http://193.166.88.68/index.php?controller=Android");
				
				
				JSONObject response = new JSONObject();
    	        
	        	response = new JsonService().execute(fullJson).get();
	        	if(response.get("status").equals(true))
				{
	        		Toast.makeText(getApplicationContext(), "Tiedot tallennettu", Toast.LENGTH_LONG).show();
	        		if (newpass1.getText()!=null) {
	        			u.setPassword(newpass1.getText().toString());
	        		}
	        		oldpass.setText("");
	        		newpass1.setText("");
	        		newpass2.setText("");
	        		this.enableFields(false);
	        		addButton.setText("Muokkaa");
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
    
    private void bindFields()
    {
    	//t‰ll‰ tavalla heataan tietoja:{ MyProperties.getInstance().user } sis‰lt‰‰ kaiken datan.
        etunimi.setText(u.getFirstName());
        sukunimi.setText(u.getLastName());
        email.setText(u.getEmail());
    }
}
