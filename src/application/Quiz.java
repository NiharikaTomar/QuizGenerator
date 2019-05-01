/**
 * Filename: Quiz.java Project: Final Project Authors: Ved Kale, Miriam Lebowitz, Niharika Tomar,
 * and Elizaveta Stepanova
 * 
 * Final Project GUI
 * 
 */
package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Runs the Quiz page GUI
 * 
 * @author Miriam, Elizaveta, Niharika, and Ved
 *
 */
public class Quiz extends Application {

  private int numQuestions;
  private boolean nextButtonClicked; // pauses program until "Next Question" is pressed.
  private int i;
  private ArrayList<Question> askedQuestions;
  List<Question> questions;
  List<Answer> answers;
  List<String> chosenAnswers;
  ToggleGroup answersGroup;

  /**
   * Runs the quiz page
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    i = 1;

    BorderPane root = new BorderPane();

    HBox hboxTopMenu = new HBox();
    HBox hboxBottomMenu = new HBox();

    hboxTopMenu.setSpacing(10);
    hboxBottomMenu.setSpacing(10);

    // Buttons needed for the page
    Button homeButton = new Button("Home");
    Button nextQuestionButton = new Button("Next Question");
    Button submitButton = new Button("Submit");

    hboxTopMenu.getChildren().add(homeButton);
    hboxBottomMenu.getChildren().add(nextQuestionButton);
    hboxBottomMenu.getChildren().add(submitButton);

    questions = new ArrayList<>();
    answers = new ArrayList<>();
    chosenAnswers = new ArrayList<>();

    for (int a = 0; a < Main.topicsToQuestion.size(); a++) {
      questions.addAll(Main.topics.get(Main.topicsToQuestion.get(a)).getQuestions().keySet());
      for (int b = 0; b < questions.size(); b++) {
        if (Main.topics.get(Main.topicsToQuestion.get(a)).getQuestions()
            .get(questions.get(b)) != null)
          answers.add(
              Main.topics.get(Main.topicsToQuestion.get(a)).getQuestions().get(questions.get(b)));
      }
      if(a>=Main.topicsToQuestion.size()-1) {
        hboxBottomMenu.getChildren().remove(nextQuestionButton);
        primaryStage.show();
      }
    }
    // Add questions to a Vertical Box
    nextButtonClicked = true;
    while (i <= numQuestions && nextButtonClicked) {
      nextButtonClicked = false;
      VBox questionsAndAnswers = new VBox();

      askedQuestions = new ArrayList<Question>();

      Random random = new Random();
      int randomNumber = random.nextInt(questions.size());
      Question randomKey = questions.get(randomNumber);
      askedQuestions.add(randomKey);

      Label question = new Label("Question " + i + ": " + randomKey.getQuestion());
      System.out.println(randomKey.image);
      
      if (!(randomKey.image == null && randomKey.image.equals("none")))
      {
    	  question.setGraphic(new ImageView(new Image(randomKey.image)));
      }
      
      answersGroup = new ToggleGroup();
      question.setTextFill(Color.WHITE);
      questionsAndAnswers.getChildren().add(question);
      for (int j = 0; j < answers.get(randomNumber).getAnswers().size(); j++) { // Adds answer
        // options
        RadioButton r = new RadioButton(answers.get(randomNumber).getAnswers().get(j));
        // r.setSelected(true);
        r.setToggleGroup(answersGroup);
        r.setTextFill(Color.WHITE);
        questionsAndAnswers.getChildren().add(r);
        root.setCenter(questionsAndAnswers);

      }
      // RadioButton selectedRadioButton = (RadioButton) answersGroup.getSelectedToggle();
      // System.out.println(selectedRadioButton.getText());
      i++;

      nextQuestionButton.setOnAction(new EventHandler<ActionEvent>() {
        /**
         * This method loads a next questions when next button was clicked.
         */
        @Override
        public void handle(ActionEvent arg0) {
          RadioButton selectedRadioButton = (RadioButton) answersGroup.getSelectedToggle();
          chosenAnswers.add(selectedRadioButton.getText());
          System.out.println(chosenAnswers);
          nextButtonClicked = false;
          if (i <= numQuestions) {
            questionsAndAnswers.getChildren().clear();

            Random random = new Random();
            int randomNumber = random.nextInt(questions.size());
            Question randomKey = questions.get(randomNumber);

            while (askedQuestions.contains(randomKey)) {
              randomNumber = random.nextInt(questions.size());
              randomKey = questions.get(randomNumber);
            }

            askedQuestions.add(randomKey);

            Label question = new Label("Question " + i + ": " + randomKey.getQuestion());
            answersGroup = new ToggleGroup();
            question.setTextFill(Color.WHITE);
            questionsAndAnswers.getChildren().add(question);
            for (int j = 0; j < answers.get(randomNumber).getAnswers().size(); j++) { // Adds answer
              // options
              RadioButton r = new RadioButton(answers.get(randomNumber).getAnswers().get(j));
              r.setToggleGroup(answersGroup);
              r.setTextFill(Color.WHITE);
              questionsAndAnswers.getChildren().add(r);
              root.setCenter(questionsAndAnswers);
            }
            i++;
          }
          // if (i > numQuestions)
          // hboxBottomMenu.getChildren().remove(nextQuestionButton);
        }
      });
    }


    homeButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * This method creates a new scene with a pop up to go back to main page.
       */
      public void handle(ActionEvent event) {
        Main main = new Main();
        Stage newStage = new Stage();
        try {
          main.start(newStage);
          primaryStage.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    submitButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * This method creates a new screen with quiz results.
       */
      public void handle(ActionEvent event) {
        // RadioButton selectedRadioButton = (RadioButton) answersGroup.getSelectedToggle();
        // chosenAnswers.add(selectedRadioButton.getText());
        // System.out.println(chosenAnswers);
        QuizResults quizResults = new QuizResults();
        Stage newStage = new Stage();
        try {
          quizResults.start(newStage);
          primaryStage.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });


    root.setTop(hboxTopMenu);
    root.setBottom(hboxBottomMenu);

    Scene scene = new Scene(root, 1200, 600);

    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    primaryStage.setScene(scene);

    // Set the title
    primaryStage.setTitle("Quiz Generator");
    primaryStage.show();
  }

  /**
   * Sets numQuestions field
   * 
   * @param numQs
   */
  public void setNumQuestions(int numQs) {
    numQuestions = numQs;
  }

}
