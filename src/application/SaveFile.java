package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SaveFile {
  public SaveFile(File file) throws FileNotFoundException, IOException, ParseException {


    ArrayList<String> topics = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<>();
    List<Answer> answers = new ArrayList<>();

    try {
      JSONArray mainArray = new JSONArray();
      JSONObject mainObject = new JSONObject();

      for (int i = 0; i < Main.topics.keySet().size(); i++) {

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

        for (Answer answer : answers) {
          ArrayList<String> answers2 = answer.getAnswers();
          JSONArray choicesArray = new JSONArray();

          for (String a : answers2) {

            String correctness = String.valueOf(answer.checkAnswer(a));

            // System.out.println(a);
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

      JSONParser parser = new JSONParser();


      // Write JSON file
      PrintWriter filewriter = new PrintWriter(file, "UTF-8");
      filewriter.println(mainObject);
      filewriter.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
