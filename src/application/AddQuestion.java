/**
 * Filename:   AddQuestion.java
 * Project:    Final Project
 * Authors:    Ved Kale, Miriam Lebowitz, Niharika Tomar, and Elizaveta Stepanova
 * 
 * Final Project GUI
 * 
 */
package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * Runs AddQuestion GUI
 * @author Miriam, Elizaveta, Niharika, and Ved
 *
 */
public class AddQuestion extends Application{
  /**
   * Runs AddQuestion GUI
   */
  @Override
  public void start(Stage primaryStage) throws Exception {


    BorderPane root = new BorderPane();

    HBox hboxTopMenu = new HBox();
    VBox form = new VBox();

    hboxTopMenu.setSpacing(10);

    // Buttons needed for the page
    Button homeButton = new Button("Home");

    hboxTopMenu.getChildren().add(homeButton);
    
    Label topicPrompt = new Label("Choose a topic");
    topicPrompt.setTextFill(Color.WHITE);
    form.getChildren().add(topicPrompt);
    HashTable table = new HashTable();
    ArrayList<String> topics = table.keySet();
    ComboBox<String> topicChooser = new ComboBox<String>();
    for (int i = 0; i < topics.size(); i++) {
      topicChooser.getItems().add(topics.get(i));
    }
    Label questionPrompt = new Label("Please enter a question: ");
    questionPrompt.setTextFill(Color.WHITE);
    TextField questionInput = new TextField();
    questionInput.setPrefWidth(20);
    form.getChildren().add(topicChooser);
    form.getChildren().add(questionPrompt);
    form.getChildren().add(questionInput);
    root.setCenter(form);

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

    root.setTop(hboxTopMenu);

    Scene scene = new Scene(root, 1200, 600);

    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    primaryStage.setScene(scene);

    // Set the title
    primaryStage.setTitle("Quiz Generator");
    primaryStage.show();
  }

}
