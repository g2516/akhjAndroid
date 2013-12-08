package fi.CodeRevolution.kiesi.Activitys;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.*;
import fi.CodeRevolution.kiesi.Utils.LoginService;


public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); 
        setTitle("Login");
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void login_onClick(View view)
    {
 
    	EditText userName =(EditText) findViewById(R.id.txtUserName);
    	EditText password =(EditText) findViewById(R.id.txtPassword);
    	String username =userName.getText().toString();
    	String pwd =password.getText().toString();;
    	
    	
    	User u=LoginService.login(username, pwd);
			if(u!=null){
				LoginService.getInstance().user=u;
				Intent post = new Intent(MainActivity.this, CarsActivity.class);
				startActivity(post);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Sis‰‰nkirjautuminen ep‰onnistui", Toast.LENGTH_LONG).show();
			}


    }


}
