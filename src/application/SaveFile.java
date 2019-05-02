package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
    List<Boolean> correctness = new ArrayList<>();

    try {
      JSONArray mainArray = new JSONArray();
      JSONArray choiceArray = new JSONArray();
      JSONObject mainObject = new JSONObject();
      JSONObject questionsObject = new JSONObject();
//      JSONObject choiceObject = new JSONObject();



      for (int i = 0; i < Main.topics.keySet().size(); i++) {

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

        // Prints out answers
        for (int j = 0; j < questions.size(); j++) {
          if (Main.topics.get(topics.get(i)).getQuestions().get(questions.get(i)) != null) {

            String question = questions.get(i).question;
            
            answers.add(Main.topics.get(topics.get(i)).getQuestions().get(questions.get(j)));
            
            ArrayList<String> arrayOfAnswers = null;

            for (int k = 0; k < answers.size(); k++) {
              if (answers.get(k) != null) {
                arrayOfAnswers = answers.get(k).getAnswers();
               
              }
            }

            for (int m = 0; m < arrayOfAnswers.size(); m++) {
              JSONObject choiceObject = new JSONObject();
//              correctness.add(answers.get(m).checkAnswer("true"));
//               choiceObject.put("isCorrect", answers.get(m).checkAnswer("true"));
              
              choiceObject.put("choice", arrayOfAnswers.get(m));

              choiceArray.add(choiceObject);
            }
          }
          // // answers.add(
          // // Main.topics.get(Main.topicsToQuestion.get(a)).getQuestions().get(questions.get(b)));
        }


        mainArray.add(questionsObject);
//        choiceArray.add(choiceObject);
        questionsObject.put("choiceArray", choiceArray);

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
