package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    // Add questions to an Horizontal Box
    hboxTopMenu.getChildren().add(homeButton);
    
    hboxBottomMenu.getChildren().add(nextQuestionButton);
    hboxBottomMenu.getChildren().add(submitButton);
    
 	  Label question1 = new Label("question 1" + ": How's life?");
	  TilePane questionHolder1 = new TilePane(); 
	  questionHolder1.getChildren().add(question1);
	  for(int j=0; j<6; j++) {
		  int num = 1;
		  RadioButton r = new RadioButton("answer"+ num);
		  num++;
		 // add label 
		  questionHolder1.getChildren().add(r); 
		  
		 
	  }
 root.setCenter(questionHolder1);
    
    nextQuestionButton.setOnAction(new EventHandler<ActionEvent>() {
        /**
         * This method creates a new scene with a pop up to get number of questions needed in quiz.
         */
        public void handle(ActionEvent event) {
        
        	
          for(int i =0; i<4; i++) {
        	  
        	  Label question1 = new Label("question " + i + ": watcha doin?");
        	  //TilePane questionHolder = new TilePane(); 
        	  questionHolder1.getChildren().add(question1);
        	  for(int j=0; j<6; j++) {
        		  int num = 1;
        		  RadioButton r = new RadioButton("answer"+ num);
        		  num++;
        		 // add label 
        		  questionHolder1.getChildren().add(r); 
        		  
        		 
        	  }
         root.setCenter(questionHolder1);	  
          }
          
        }
        
      });

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

}
