package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Quiz extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane root = new BorderPane();
    HBox hboxTopMenu = new HBox();
    HBox hboxBottomMenu = new HBox();

    // Buttons needed for the page
    Button homeButton = new Button("Home");
    Button newQuizButton = new Button("Take New Quiz");
    Button nextQuestionButton = new Button("Next Question");
    Button submitButton = new Button("Sumbit");

    // Add questions to an Horizontal Box
    hboxTopMenu.getChildren().add(homeButton);
    hboxTopMenu.getChildren().add(newQuizButton);
    hboxBottomMenu.getChildren().add(nextQuestionButton);
    hboxBottomMenu.getChildren().add(submitButton);


    root.setTop(hboxTopMenu);
    root.setBottom(hboxBottomMenu);

    Scene scene = new Scene(root, 1200, 600);

    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    primaryStage.setScene(scene);

    // Set the title
    primaryStage.setTitle("Quiz Generator");
    primaryStage.show();
  }

}
