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
	
	
	
	public AddFile(File f) throws FileNotFoundException, IOException, ParseException
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
          
          
          Answer ans = new Answer();
          JSONArray choicesArray = (JSONArray) objectInArray.get("choiceArray");
          for (int j = 0; j < choicesArray.size(); j++)
          {
        	  JSONObject choices = (JSONObject) choicesArray.get(j);
        	  String choice = (String) choices.get("choice");
        	  boolean correct = Boolean.getBoolean((String) choices.get("isCorrect"));
        	  
        	  ans.addAnswer(choice, correct);
          }
          
          try {
        	  if (!Main.topics.contains(topic))
        	  {
        		  Main.topics.insert(topic, new QuestionBank(topic));
        	  }
        	  Main.topics.get(topic).addQA(new Question(question, image), ans);
        	  
		} catch (Exception e) {
			e.printStackTrace();
		}
          
        }
	}
}
