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
	
	HashTable<String, Topic> t;
	
	public AddFile(File f, HashTable<String, Topic> topics) throws FileNotFoundException, IOException, ParseException, IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException
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
        	  JSONObject choices = (JSONObject) choicesArray.get(j);
        	  String choice = (String) choices.get("choice");
        	  boolean correct = false;
        	  if (((String) choices.get("isCorrect")).equals("T"))
        		  correct = true;     	   
        	  
        	  ans.addAnswer(choice, correct);
        	  
        	  //System.out.println(choice);
              //System.out.println(correct);
          }
          
          if (!topics.contains(topic))
    	  {
    		  topics.insert(topic, new Topic(topic));
    	  }
          System.out.println("KEUE SET CHECK " + topics.keySet());
    	  
    	  topics.get(topic).addQA(new Question(question, image), ans);
          
        }
    	t = topics;
    	//System.out.println(topics.keySet());
	}
	
	public HashTable<String, Topic> get()
	{
		return t;
	}
}
