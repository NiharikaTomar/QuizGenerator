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
  public void start(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      HBox hbox = new HBox();
      hbox.setSpacing(10);
      Scene scene = new Scene(root,1200,800);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      
      //Generate Buttons
      Button addQuestion = new Button("Add Question");
      hbox.getChildren().add(addQuestion);

      Button edit = new Button("Edit");
      hbox.getChildren().add(edit);

      Button takeQuiz = new Button("Take Quiz");
      hbox.getChildren().add(takeQuiz);
      takeQuiz.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
          Label questionNumPrompt = new Label("How many questions would you like in your quiz?");
          TextField inputBox = new TextField();
          Button startQuiz = new Button("Start Quiz");
          VBox vBox = new VBox(questionNumPrompt, inputBox, startQuiz);
          Scene scene2 = new Scene(vBox);
          Stage stage = new Stage();
          stage.setScene(scene2);
          stage.show();
        }
      });
      
      hbox.getStyleClass().add("hbox");

      //Generates Topic List
      ObservableList<String> items = FXCollections.observableArrayList(
          "Computer Science", "Math", "Science");
      ListView<String> list = new ListView<>(items);
      ListView<String> selected = new ListView<>();
      HBox hBox2 = new HBox(list, selected);

      //Allows user to select multiple topics
      list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      list.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
        selected.setItems(list.getSelectionModel().getSelectedItems());
      });
      root.setCenter(hbox);    
      root.setLeft(hBox2);
      primaryStage.show();

    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

}