package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AddFile {
	
	
	
	public AddFile(File f, HashTable<String, Topic> topics) throws FileNotFoundException, IOException, ParseException
	{
		Object obj = new JSONParser().parse(new FileReader(f)); 
    	JSONObject jo = (JSONObject) obj;
    	JSONArray jsonArray = (JSONArray) jo.get("questionArray");
    	
    	for (int i = 0; i < jsonArray.size(); i++)
        {
          JSONObject objectInArray = (JSONObject) jsonArray.get(i);
          String question = (String) objectInArray.get("questionText");
          String topic = (String) objectInArray.get("topic");
          String image = (String) objectInArray.get("image");
          
          
          //System.out.println(question);
          //System.out.println(topic);
          //System.out.println(image);
          
          Answer ans = new Answer();
          JSONArray choicesArray = (JSONArray) objectInArray.get("choiceArray");
          for (int j = 0; j < choicesArray.size(); j++)
          {
        	  JSONObject choices = (JSONObject) choicesArray.get(i);
        	  String choice = (String) choices.get("choice");
        	  boolean correct = Boolean.getBoolean((String) choices.get("isCorrect"));
        	  
        	  ans.addAnswer(choice, correct);
        	  
        	  //System.out.println(choice);
              //System.out.println(correct);
          }
          
          try {
        	  if (!topics.contains(topic))
        	  {
        		  topics.insert(topic, new Topic(topic));
        	  }
        	  topics.get(topic).addQA(new Question(topic, image), ans);
        	  
		} catch (Exception e) {
			e.printStackTrace();
		}
          
        }
	}
}
