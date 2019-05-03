/**
 * Filename: AddFile.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * This class reads a json chosen by the user.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
public class AddFile {

  /**
   * This method reads a JSON file and adds it to the HashTable<String, QuestionBank>.
   * 
   * @param f file chosen by the user
   * @throws FileNotFoundException when file not found
   * @throws IOException           when I/O operations failed or interrupted
   * @throws ParseException        when parsing had an error
   */
  public AddFile(File f) throws FileNotFoundException, IOException, ParseException {
    Object obj = new JSONParser().parse(new FileReader(f));
    JSONObject jo = (JSONObject) obj;
    JSONArray jsonArray = (JSONArray) jo.get("questionArray");

    // Loop through the question array
    for (int i = 0; i < jsonArray.size(); i++) {
      // Create all the needed JSONObjects and arrays, and all the Strings needed for parsing
      JSONObject objectInArray = (JSONObject) jsonArray.get(i);
      String question = (String) objectInArray.get("questionText");
      String topic = (String) objectInArray.get("topic");
      String image = (String) objectInArray.get("image");

      // Instantiate Answer to add all answers for each question
      Answer ans = new Answer();
      // JSONArray of answers
      JSONArray choicesArray = (JSONArray) objectInArray.get("choiceArray");
      // Loop through the array of answers
      for (int j = 0; j < choicesArray.size(); j++) {
        // Create a JSONOject for each answer and corresponding boolean
        JSONObject choices = (JSONObject) choicesArray.get(j);
        String choice = (String) choices.get("choice");
        boolean correct = false;
        if (((String) choices.get("isCorrect")).equals("T"))
          correct = true;
        // Add answer to the instantiated Answer
        ans.addAnswer(choice, correct);
      }

      try {
        // Check if topic is not in the HashTable.
        if (!Main.topics.contains(topic)) {
          // Add a new Topic
          Main.topics.insert(topic, new QuestionBank(topic));
        }
        // Add to the topic question, image and answers with booleans
        Main.topics.get(topic).addQA(new Question(question, image), ans);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
