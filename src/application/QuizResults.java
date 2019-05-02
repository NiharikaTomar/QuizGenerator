/**
 * Filename: QuizResults.java Project: Final Project Authors: Ved Kale, Miriam Lebowitz, Niharika
 * Tomar, and Elizaveta Stepanova
 * 
 * Final Project GUI
 * 
 */
package application;

import java.awt.Scrollbar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
		Button exitButton = new Button("Exit");

		hboxTopMenu.getChildren().add(homeButton);
		hboxTopMenu.getChildren().add(exitButton);


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
		root.setCenter(vBoxResults);

		Scene scene = new Scene(root, 1200, 600);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		Stage exitStage = new Stage();
		Button agreeButton = new Button("Yes");
		Button disagreeButton = new Button("No");
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Label exitLabel = new Label("Save and exit?");
				HBox buttonBox = new HBox(agreeButton, disagreeButton);
				buttonBox.setSpacing(10);
				VBox vBox = new VBox(exitLabel, buttonBox);
				Scene popupScene = new Scene(vBox);
				exitStage.setScene(popupScene);
				exitStage.show();
			}
		});

		agreeButton.setOnAction(new EventHandler<ActionEvent>() {

			/**
			 * This method saves the program
			 */
			@Override
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

		disagreeButton.setOnAction(new EventHandler<ActionEvent>() {

			/**
			 * This method exits the program
			 */
			@Override
			public void handle(ActionEvent event) {
				exitStage.close();
			}
		});

		// Set the title
		primaryStage.setTitle("Quiz Generator");
		primaryStage.show();

	}
}
