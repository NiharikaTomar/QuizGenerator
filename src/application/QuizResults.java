/**
 * Filename:   QuizResults.java
 * Project:    Final Project
 * Authors:    Ved Kale, Miriam Lebowitz, Niharika Tomar, and Elizaveta Stepanova
 * 
 * Final Project GUI
 * 
 */
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * Runs QuizResults GUI
 * @author Miriam
 *
 */
public class QuizResults extends Application {
  /**
   * Runs QuizResults GUI
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane root = new BorderPane();

    HBox hboxTopMenu = new HBox();

    hboxTopMenu.setSpacing(10);

    // Buttons needed for the page
    Button homeButton = new Button("Home");
    Button saveJsonButton = new Button("Save Results");
    Button exitButton = new Button("Exit");

    hboxTopMenu.getChildren().add(homeButton);
    hboxTopMenu.getChildren().add(saveJsonButton);
    hboxTopMenu.getChildren().add(exitButton);
    
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

    saveJsonButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * This method allows user to save quiz Results to a JSON file.
       */
      public void handle(ActionEvent event) {
        
      }
    });
    
    exitButton.setOnAction(new EventHandler<ActionEvent>() {

      /**
       * This method exits the program
       */
      @Override
      public void handle(ActionEvent event) {
        primaryStage.close();        
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
