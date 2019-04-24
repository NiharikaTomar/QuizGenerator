package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      BorderPane root = new BorderPane();
      HBox hbox = new HBox();
      hbox.setSpacing(10);

      // Generate Buttons needed
      Button addQuestion = new Button("Add Question");
      Button edit = new Button("Edit");
      Button takeQuiz = new Button("Take Quiz");
      Button startQuiz = new Button("Start Quiz");

      // Add buttons to a horizontal box
      hbox.getChildren().add(addQuestion);
      hbox.getChildren().add(edit);
      hbox.getChildren().add(takeQuiz);

      Stage numberOfQuetionsStage = new Stage();

      takeQuiz.setOnAction(new EventHandler<ActionEvent>() {
        /**
         * This method creates a new scene with a pop up to get number of questions needed in quiz.
         */
        public void handle(ActionEvent event) {
          Label questionNumPrompt = new Label("How many questions would you like in your quiz?");
          TextField inputBox = new TextField();
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
          Quiz quiz = new Quiz();
          Stage newStage = new Stage();
          try {
            quiz.start(newStage);
            numberOfQuetionsStage.close();
            primaryStage.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });

      // Generates Topic List
      ObservableList<String> items =
          FXCollections.observableArrayList("Computer Science", "Math", "Science");
      ListView<String> list = new ListView<>(items);
      ListView<String> selected = new ListView<>();
      HBox hBox2 = new HBox(list, selected);

      // Allows user to select multiple topics
      list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      list.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
        selected.setItems(list.getSelectionModel().getSelectedItems());
      });

      root.setCenter(hbox);
      root.setLeft(hBox2);

      Scene scene = new Scene(root, 1200, 600);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);

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
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
}
