/**
 * Filename: Main.java Project: Final Project Authors: Ved Kale, Miriam Lebowitz, Niharika Tomar,
 * and Elizaveta Stepanova
 * 
 * Final Project GUI
 * 
 */
package application;

import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Drives the GUI for the program
 * 
 * @author Miriam, Elizaveta, Niharika, and Ved
 *
 */
public class Main extends Application {

  private Desktop desktop = Desktop.getDesktop();
  public static HashTable<String, QuestionBank> topics = new HashTable<String, QuestionBank>();
  ObservableList<String> items;
  public static ArrayList<String> topicsToQuestion = new ArrayList<String>();
  int totalQuestions;

  /**
   * Runs the Home Screen
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    try {

      // Collections.sort(topicsToQuestion);
      BorderPane root = new BorderPane();

      HBox hbox = new HBox();

      hbox.setSpacing(10);

      // Choose Topic Label
      Label chooseTopic = new Label(
          "Please choose a topic. \n[To select multiple topics, hold down the CTRL button on Windows "
              + "or COMMAND on MAC.]");

      // sets text color to white
      chooseTopic.setTextFill(Color.WHITE);

      // Generate Buttons needed
      Button addQuestion = new Button("Add Question");
      Button addFile = new Button("Upload a JSON File");
      Button takeQuiz = new Button("Take Quiz");
      Button startQuiz = new Button("Start Quiz");
      Button closePopUp = new Button("Thank you");

      Button saveAndQuitButton = new Button("Save and Quit");
      Button quitButton = new Button("Quit");

      // Add buttons to a horizontal box
      hbox.getChildren().add(addQuestion);
      hbox.getChildren().add(addFile);
      hbox.getChildren().add(takeQuiz);

      hbox.getChildren().add(saveAndQuitButton);
      hbox.getChildren().add(quitButton);

      Stage numberOfQuetionsStage = new Stage();
      TextField inputBox = new TextField();

      takeQuiz.setOnAction(new EventHandler<ActionEvent>() {
        /**
         * // * This method creates a new scene with a pop up to get number of questions needed in
         * quiz. //
         */
        // public void handle(ActionEvent event) {
        // Label questionNumPrompt = new Label("How many questions would you like in
        // your quiz?");
        // VBox vBox = new VBox(questionNumPrompt, inputBox, startQuiz);
        // Scene popupScene = new Scene(vBox);
        // if (topicsToQuestion.isEmpty())
        // {
        // Alert alert = new Alert(AlertType.ERROR);
        // alert.setContentText("Choose an answer");
        // alert.show();
        // }

        /*
         * This method creates a new scene with a pop up to get number of questions needed in quiz.
         */
        public void handle(ActionEvent event) {
          if (topicsToQuestion.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Choose a Topic");
            alert.show();
            return;
          }
          Label questionNumPrompt = new Label("How many questions would you like in your quiz?");
          VBox vBox = new VBox(questionNumPrompt, inputBox, startQuiz);
          Scene popupScene = new Scene(vBox);

          numberOfQuetionsStage.setScene(popupScene);
          numberOfQuetionsStage.show();
        }
      });

      hbox.getStyleClass().add("hbox");

      // Set up behavior for Start Quiz button
      startQuiz.setOnAction(new EventHandler<ActionEvent>() {

        /**
         * This method creates a new scene with a quiz questions and closes primaryStage and
         * numberOfQuestionsStage.
         */
        @Override
        public void handle(ActionEvent event) {
          int numQuestions = 0;
          try {
            numQuestions = Integer.parseInt(inputBox.getText());
            int q = 0;
            for (int a = 0; a < topicsToQuestion.size(); a++) {
                ArrayList<Question> questions = new ArrayList<>();
                questions.addAll(topics.get(topicsToQuestion.get(a)).getQuestions().keySet());
                q += questions.size();
              }
            if (numQuestions > q && q != 0) {
              numQuestions = q;
            }

            Quiz quiz = new Quiz();
            quiz.setNumQuestions(numQuestions);
            Stage newStage = new Stage();
            quiz.start(newStage);
            numberOfQuetionsStage.close();
            primaryStage.close();
            newStage.show();
          } catch (NumberFormatException e) {
            numberOfQuetionsStage.close();
            Label questionNumPrompt = new Label("Type number of questions");
            VBox vBox = new VBox(questionNumPrompt, closePopUp);
            Scene popupScene = new Scene(vBox);
            numberOfQuetionsStage.setScene(popupScene);
            numberOfQuetionsStage.show();
            return;
          } catch (Exception e) {
            numberOfQuetionsStage.close();
            System.out.println(e.getLocalizedMessage());
            Label questionNumPrompt = new Label("Please select a topic");
            VBox vBox = new VBox(questionNumPrompt, closePopUp);
            Scene popupScene = new Scene(vBox);
            numberOfQuetionsStage.setScene(popupScene);
            numberOfQuetionsStage.show();
          }
        }
      });

      /**
       * this method closes the stage showing number of questions
       */
      closePopUp.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          numberOfQuetionsStage.close();
        }
      });
      // Set up behavior for Edit button

      // Set up behavior for addQuestion button
      addQuestion.setOnAction(new EventHandler<ActionEvent>() {

        /**
         * Goes to AddQuestion Page
         */
        @Override
        public void handle(ActionEvent event) {
          primaryStage.show();
          AddQuestion addQuestion = new AddQuestion();
          Stage newStage = new Stage();
          try {
            addQuestion.start(newStage);
            primaryStage.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });

      // Sets up behavior for addFile button
      final FileChooser fileChooser = new FileChooser();
      addFile.setOnAction(new EventHandler<ActionEvent>() {

        /**
         * Goes to AddQuestion Page
         */
        @Override
        public void handle(ActionEvent event) {

          File file = fileChooser.showOpenDialog(primaryStage);
          Stage newStage = new Stage();
          try {
            AddFile addFile = new AddFile(file);
            items.clear();
            for (int i = 0; i < topics.keySet().size(); i++) {
              items.add(topics.keySet().get(i));
              Collections.sort(items);
            }

            totalQuestions = 0;
            for (int a = 0; a < topics.keySet().size(); a++) {
              ArrayList<Question> questions = new ArrayList<>();
              questions.addAll(topics.get(Main.topics.keySet().get(a)).getQuestions().keySet());
              totalQuestions += questions.size();
            }
            
            BorderPane bottom = new BorderPane();
            Label numQ = new Label("Total Number of Questions: " + totalQuestions);
            numQ.setTextFill(Color.WHITE);
            bottom.setRight(numQ);
            root.setBottom(bottom);
          } catch (Exception e1) {

            Main main = new Main();
            Stage newStage3 = new Stage();

            try {

              main.start(newStage3);
              primaryStage.close();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }

      });

      // Generates Topic List
      items = FXCollections.observableArrayList();

      // topicsToQuestion = new ArrayList<String>();
      items.clear();
      for (int i = 0; i < topics.keySet().size(); i++) {
        items.add(topics.keySet().get(i));

        Collections.sort(items);
      }
      ListView<String> list = new ListView<>(items);
      ListView<String> selected = new ListView<>();
      HBox hBox2 = new HBox(list, selected);

      // Allows user to select multiple topics
      list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      list.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
        selected.setItems(list.getSelectionModel().getSelectedItems());
        topicsToQuestion.clear();
        for (String t : list.getSelectionModel().getSelectedItems()) {
          if (!topicsToQuestion.contains(t))
            topicsToQuestion.add(t);
        }       
      });

      
      BorderPane bottom = new BorderPane();
      Label numQ = new Label("Total Number of Questions: " + totalQuestions);
      numQ.setTextFill(Color.WHITE);
      bottom.setRight(numQ);
      root.setBottom(bottom);

      root.setTop(chooseTopic);
      root.setCenter(hbox);
      root.setLeft(hBox2);

      Scene scene = new Scene(root, 1200, 600);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);

      saveAndQuitButton.setOnAction(new EventHandler<ActionEvent>() {

        /**
         * This method saves the program
         */
        @Override
        public void handle(ActionEvent event) {
          // Main main = new Main();
          try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Your Json File:");
            fileChooser.setInitialFileName("Quiz");
            fileChooser.getExtensionFilters()
                .addAll(new ExtensionFilter("JSON files (*.json)", "*.json"));
            File fileToSave = fileChooser.showSaveDialog(primaryStage);

            if (fileToSave != null) {
              try {
                SaveFile saveFile = new SaveFile(fileToSave);
                primaryStage.close();
              } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR, "Unable to save file!");
                alert.showAndWait();
                return;
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
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

      quitButton.setOnAction(new EventHandler<ActionEvent>() {
        /**
         * This method exits the program
         */
        @Override
        public void handle(ActionEvent event) {
          primaryStage.close();
        }
      });
      // Set the title
      primaryStage.setTitle("Quiz Generator");
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Main method that runs the application
   * 
   * @param args command-line arguments
   * 
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
}
