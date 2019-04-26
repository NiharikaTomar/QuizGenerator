package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Quiz extends Application {

  private int numQuestions;
  private boolean nextButtonClicked; //pauses program until "Next Question" is pressed.
  private int i;

  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane root = new BorderPane();
    HBox hboxTopMenu = new HBox();
    HBox hboxBottomMenu = new HBox();
    hboxTopMenu.setSpacing(10);
    hboxBottomMenu.setSpacing(10);

    // Buttons needed for the page
    Button homeButton = new Button("Home");
    Button nextQuestionButton = new Button("Next Question");
    Button submitButton = new Button("Sumbit");

    hboxTopMenu.getChildren().add(homeButton);
    hboxBottomMenu.getChildren().add(nextQuestionButton);
    hboxBottomMenu.getChildren().add(submitButton);

    // Add questions to an Horizontal Box
    i = 1;
    nextButtonClicked = true;
    while (i <= numQuestions && nextButtonClicked) {
      nextButtonClicked = false;
      VBox questionsAndAnswers = new VBox();
      Label question = new Label("Question " + i + ": Can you choose an option?");
      questionsAndAnswers.getChildren().add(question);
      for (int j = 1; j < 6; j++) {
        RadioButton r = new RadioButton("Answer " + j);
        questionsAndAnswers.getChildren().add(r);
        root.setCenter(questionsAndAnswers);
        
      }
      nextQuestionButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent arg0) {
          nextButtonClicked = false;
          if (i <= numQuestions) {
            questionsAndAnswers.getChildren().clear();
            Label question = new Label("Question " + i + ": Can you choose an option?");
            questionsAndAnswers.getChildren().add(question);
            for (int j = 1; j < 6; j++) {
              RadioButton r = new RadioButton("Answer " + j);
              questionsAndAnswers.getChildren().add(r);
              root.setCenter(questionsAndAnswers);
            }
            i++;
          }
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
       * This method creates a new scene with a pop up to get quiz results.
       */
      public void handle(ActionEvent event) {
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

  public void setNumQuestions(int numQs) {
    numQuestions = numQs;
  }

}
