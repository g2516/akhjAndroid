package fi.CodeRevolution.akhj.Models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JsonService extends AsyncTask<JSONObject,Void,String> {
	
	static InputStream is = null;
	static JSONObject jObj = null;
    static String json = "";
    
	@Override
	protected String doInBackground(JSONObject... args)
	{
		
		JSONObject json = args[0];

	    HttpClient httpclient =new DefaultHttpClient();

	   
	    try{
	    	
			String url = json.getString("url");
			JSONObject obj = json.getJSONObject("json");
			 HttpPost  httppost=new HttpPost(url);
	    	List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
	    	try
	        {
	            if(obj != null)
	            {
	                params.add(new BasicNameValuePair("json", obj.toString()));
	                Log.d("request", " " + obj.toString());
	            }
	        }catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	        httppost.setEntity(new UrlEncodedFormEntity(params));    
	        HttpResponse response = httpclient.execute(httppost);
	        

	        String responseBody="";
			/*Checking response*/
	        if(response!=null)
	        {   
	           // responseBody = EntityUtils.toString(response.getEntity());
	            HttpEntity httpEntity = response.getEntity();
	            JSONObject jsonResponse=this.parseJson(httpEntity);
	            Log.d("response", " " + jsonResponse);

	        }
	        if(responseBody.equals("ok"))
	        {

	            //...do something
	        	return "ok";

	        }
	    } catch(ClientProtocolException e) {

	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	    return "notOK";
	}
	
	@Override
	protected void onPostExecute(String response) {
	    // TODO: check this.exception 
	    // TODO: do something with the feed
	}
	public JSONObject parseJson(HttpEntity httpEntity) {
		 
		
		try {
			is = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        
		// try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }

}

