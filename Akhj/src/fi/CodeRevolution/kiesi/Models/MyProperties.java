package fi.CodeRevolution.kiesi.Models;


public class MyProperties {
    private static MyProperties mInstance= null;

    public User user;

    protected MyProperties(){}

    public static synchronized MyProperties getInstance(){
    	if(null == mInstance){
    		mInstance = new MyProperties();
    	}
    	return mInstance;
    }
}
