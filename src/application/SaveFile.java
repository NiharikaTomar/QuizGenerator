/**
 * Filename: SaveFile.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
 * This class saves the question database to a JSON file.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
public class SaveFile {
  @SuppressWarnings("unchecked")
  public SaveFile(File file) throws FileNotFoundException, IOException, ParseException {


    // Initialize ArrayLists and Lists needed
    ArrayList<String> topics = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<>();
    List<Answer> answers = new ArrayList<>();

    try {
      // Initialize JSONArray and JSONObject
      JSONArray mainArray = new JSONArray();
      JSONObject mainObject = new JSONObject();

      // Loop through the HashTable
      for (int i = 0; i < Main.topics.keySet().size(); i++) {

        // Initialize JSONObject for each question
        JSONObject questionsObject = new JSONObject();
        
        // Prints out a meta-data
        questionsObject.put("meta-data", "unused");

        // Prints out a topic
        topics.add(Main.topics.keySet().get(i));
        questionsObject.put("topic", topics.get(i));

        // Prints out a questions text
        questions.addAll(Main.topics.get(topics.get(i)).getQuestions().keySet());
        questionsObject.put("questionText", questions.get(i).question);

        // Prints out an image
        questionsObject.put("image", questions.get(i).image);

        answers.addAll(Main.topics.get(topics.get(i)).getAnswers());

        // Loop through the List of answers and add the to a choiceArray
        for (Answer answer : answers) {
          ArrayList<String> answers2 = answer.getAnswers();
          JSONArray choicesArray = new JSONArray();

          for (String a : answers2) {

            String correctness = String.valueOf(answer.checkAnswer(a));

            JSONObject choiceJsonObject = new JSONObject();

            choiceJsonObject.put("isCorrect", correctness);
            choiceJsonObject.put("choice", a);
            choicesArray.add(choiceJsonObject);
          }
          questionsObject.put("choiceArray", choicesArray);
        }

        mainArray.add(questionsObject);
      }

      mainObject.put("questionArray", mainArray);

      // Write JSON file
      PrintWriter filewriter = new PrintWriter(file, "UTF-8");
      filewriter.println(mainObject);
      filewriter.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
