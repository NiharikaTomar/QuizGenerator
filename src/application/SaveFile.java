package application;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class SaveFile {
  public SaveFile() throws FileNotFoundException, IOException, ParseException {
    
	    {
	        //First Employee
			JSONObject userAddedTopic = new JSONObject();
	        userAddedTopic.put("meta-data", "unused");
	        userAddedTopic.put("questionText", "Gupta");
	        userAddedTopic.put("topic", "howtodoinjava.com");
	        userAddedTopic.put("image", "null");
	        
//	        JSONObject employeeObject = new JSONObject();
//	        employeeObject.put("employee", userAddedTopic);
//	         
//	        //Second Employee
//	        JSONObject userAddedTopic2 = new JSONObject();
//	        userAddedTopic2.put("firstName", "Brian");
//	        userAddedTopic2.put("lastName", "Schultz");
//	        userAddedTopic2.put("website", "example.com");
//	         
//	        JSONObject employeeObject2 = new JSONObject();
//	        employeeObject2.put("employee", userAddedTopic2);
	         
	        //Add employees to list
	        JSONArray employeeList = new JSONArray();
//	        employeeList.add(employeeObject);
//	        employeeList.add(employeeObject2);
	         
	        //Write JSON file
	        try (FileWriter file = new FileWriter("employees.json")) {
	 
	            file.write(employeeList.toJSONString());
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}}
