package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class QuizResults extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane root = new BorderPane();
    
    Scene scene = new Scene(root, 1200, 600);

    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    primaryStage.setScene(scene);

    // Set the title
    primaryStage.setTitle("Quiz Generator");
    primaryStage.show();
    
  }
}
