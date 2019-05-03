/**
 * Filename: QuizResults.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
package application;

import java.awt.Scrollbar;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * This class runs QuizResults GUI page after Quiz was taken.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
public class QuizResults extends Application {

  // Array of answer chosen by user
  String[] chosenAnswers;
  LinkedHashMap<Question, Answer> askedQuestions;

  /*
   * Constructor initializes chosenAnswers and askedQuestions.
   */
  public QuizResults(String[] chsn, LinkedHashMap<Question, Answer> askd) {
    chosenAnswers = chsn;
    askedQuestions = askd;
  }

  /**
   * Runs QuizResults GUI page.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    int i = 0;
    // Keeps track of number of correct answers
    int correctAnswers = 0;

    // Loop through asked questions
    for (Entry<Question, Answer> entry : askedQuestions.entrySet()) {
      // Check if the answer entered by the user was correct
      if (entry.getValue().checkAnswer(chosenAnswers[i])) {
        correctAnswers++;
      }
      i++;
    }

    // Set up a Border Pane
    BorderPane root = new BorderPane();

    // Horizontal and Vertical boxes needed for the page
    HBox hboxTopMenu = new HBox();
    VBox vBoxResults = new VBox();
    hboxTopMenu.setSpacing(10);

    // Buttons needed for the page
    Button homeButton = new Button("Home");
    Button saveAndQuitButton = new Button("Save and Quit");
    Button quitButton = new Button("Quit");

    // Set up layout of the page
    hboxTopMenu.getChildren().add(homeButton);
    hboxTopMenu.getChildren().add(saveAndQuitButton);
    hboxTopMenu.getChildren().add(quitButton);

    // Page header
    Label headerLabel = new Label("Your Quiz Results");
    headerLabel.setTextFill(Color.WHITE);

    // Add a scrollBar
    Scrollbar scrollBar = new Scrollbar();
    scrollBar.setMinimum(0);
    scrollBar.setMaximum(100);
    scrollBar.setValue(50);
    vBoxResults.getChildren().add(headerLabel);

    // Labels needed for the results part of the page
    Label ia = new Label("Correct Answers: " + correctAnswers);
    ia.setTextFill(Color.WHITE);
    Label ca = new Label("Incorrect Answers: " + (askedQuestions.size() - correctAnswers));
    ca.setTextFill(Color.WHITE);
    Label ta = new Label("Total Quesions: " + askedQuestions.size());
    ta.setTextFill(Color.WHITE);
    double percent = ((double) correctAnswers / askedQuestions.size()) * 100;
    Label percentage = new Label("Score: " + percent + "%");
    percentage.setTextFill(Color.WHITE);

    // Set up layout of the page
    vBoxResults.getChildren().add(ia);
    vBoxResults.getChildren().add(ca);
    vBoxResults.getChildren().add(ta);
    vBoxResults.getChildren().add(percentage);

    // Event handler to take user back to home
    homeButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * This method creates a new scene with a pop up to go back to the main page.
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

    // Set up layout of the page
    root.setTop(hboxTopMenu);
    root.setCenter(vBoxResults);

    // Set up a scene
    Scene scene = new Scene(root, 1200, 600);
    // Import CSS
    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    primaryStage.setScene(scene);

    // Event handler to allow user to save their results and exit the program
    saveAndQuitButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * This method saves question bank to JSON format on a click of this button
       */
      @Override
      public void handle(ActionEvent event) {
        try {
          // FileChooser
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Save Your Json File:");
          fileChooser.setInitialFileName("Quiz");
          fileChooser.getExtensionFilters()
              .addAll(new ExtensionFilter("JSON files (*.json)", "*.json"));

          File fileToSave = fileChooser.showSaveDialog(primaryStage);

          // Check if File to Save is not equal to null
          if (fileToSave != null) {
            try {
              SaveFile saveFile = new SaveFile(fileToSave);
              primaryStage.close();
            } catch (Exception e) {
              // Alert if there was an error when saving file
              Alert alert = new Alert(AlertType.ERROR, "Unable to save file!");
              alert.showAndWait();
              return;
            }
          }
        } catch (Exception e) {
          Main main = new Main();
          Stage newStage3 = new Stage();
          try {
            main.start(newStage3);
            primaryStage.close();
          } catch (Exception e1) {
            e.printStackTrace();
          }
        }
      }
    });

    // Event handler to allow user to exit the program without saving their results.
    quitButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * This method exits the program without saving.
       */
      @Override
      public void handle(ActionEvent event) {
        primaryStage.close();
      }
    });

    // Set the title of the stage
    primaryStage.setTitle("Quiz Generator");
    primaryStage.show();
  }
}
