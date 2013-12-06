package fi.CodeRevolution.kiesi.Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
	
	public StringHelper(){}
	
	public static String sanitateName(String type, String value){
		
		Pattern namePattern = Pattern.compile("\\[\"(\\w+)\",\"(\\w+)\",\"(\\w+)\"\\]");
		Matcher nameMatcher = namePattern.matcher(value);
		
		return value;
	}

}
