/**
 * Filename: QuizResults.java Project: Final Project Authors: Ved Kale, Miriam Lebowitz, Niharika
 * Tomar, and Elizaveta Stepanova
 * 
 * Final Project GUI
 * 
 */
package application;

import java.awt.Scrollbar;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Runs QuizResults GUI
 * 
 * @author Miriam, Elizaveta, Niharika, Ved
 *
 */
public class QuizResults extends Application {
	/**
	 * Runs QuizResults GUI
	 */

	String[] chosenAnswers;
	LinkedHashMap<Question, Answer> askedQuestions;


	public QuizResults(String[] chsn,  	LinkedHashMap<Question, Answer>  askd) {
		chosenAnswers = chsn;
		askedQuestions = askd;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		int i=0;
		int correctAnswers = 0;
		for (Entry<Question, Answer> entry : askedQuestions.entrySet())  
		{
			if (entry.getValue().checkAnswer(chosenAnswers[i]))
			{
				correctAnswers++;
			}
			i++;
		}


		BorderPane root = new BorderPane();

		HBox hboxTopMenu = new HBox();

		VBox vBoxResults = new VBox();
		
		

		hboxTopMenu.setSpacing(10);

		// Buttons needed for the page
		Button homeButton = new Button("Home");
		Button saveAndQuitButton = new Button("Save and Quit");
	    Button quitButton = new Button("Quit");

		hboxTopMenu.getChildren().add(homeButton);
		hboxTopMenu.getChildren().add(saveAndQuitButton);
		hboxTopMenu.getChildren().add(quitButton);


		Label headerLabel = new Label("Your Quiz Results");
		headerLabel.setTextFill(Color.WHITE);
		
		Scrollbar scrollBar = new Scrollbar();
		scrollBar.setMinimum(0);
		scrollBar.setMaximum(100);
		scrollBar.setValue(50);


		vBoxResults.getChildren().add(headerLabel);
		
		Label ia = new Label("Correct Answers: " + correctAnswers);
		ia.setTextFill(Color.WHITE);
		Label ca = new Label("Incorrect Answers: " + (askedQuestions.size()-correctAnswers));
		ca.setTextFill(Color.WHITE);
		Label ta = new Label("Total Quesions: " + askedQuestions.size());
		ta.setTextFill(Color.WHITE);
		double percent = ((double) correctAnswers / askedQuestions.size()) * 100;
        Label percentage = new Label("Score: " + percent +"%");
        percentage.setTextFill(Color.WHITE);
		
		vBoxResults.getChildren().add(ia);
		vBoxResults.getChildren().add(ca);
		vBoxResults.getChildren().add(ta);
		vBoxResults.getChildren().add(percentage);

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
		root.setCenter(vBoxResults);

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
	            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON files (*.json)", "*.json"));
	            File fileToSave = fileChooser.showSaveDialog(primaryStage);
	            
	            if (fileToSave != null) {
	              try {
	                  SaveFile saveFile = new SaveFile(fileToSave);
	              } catch (Exception e) {
	                e.printStackTrace();
	                  Alert alert = new Alert(AlertType.ERROR, "Unable to save file!");
	                  alert.showAndWait();
	                  return;
	              }
	          }
	            primaryStage.close();
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

	}
}
