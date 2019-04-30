/**
 * Filename:   Main.java
 * Project:    Final Project
 * Authors:    Ved Kale, Miriam Lebowitz, Niharika Tomar, and Elizaveta Stepanova
 * 
 * Final Project GUI
 * 
 */
package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
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

	/**
	 * Runs the Home Screen
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			BorderPane root = new BorderPane();
			HBox hbox = new HBox();
			hbox.setSpacing(10);

			// Choose Topic Label
			Label chooseTopic = new Label(
					"Please choose a topic. \n[To select multiple topics, hold down the CTRL button on Windows "
							+ "or COMMAND on MAC.]");
			chooseTopic.setTextFill(Color.WHITE);

			// Generate Buttons needed
			Button addQuestion = new Button("Add Question");
			Button addFile = new Button("Upload a JSON File");
			Button edit = new Button("Edit");
			Button takeQuiz = new Button("Take Quiz");
			Button startQuiz = new Button("Start Quiz");
			Button closePopUp = new Button("Agree");

			// Add buttons to a horizontal box
			hbox.getChildren().add(addQuestion);
			hbox.getChildren().add(addFile);
			hbox.getChildren().add(edit);
			hbox.getChildren().add(takeQuiz);
			Stage numberOfQuetionsStage = new Stage();
			TextField inputBox = new TextField();

			takeQuiz.setOnAction(new EventHandler<ActionEvent>() {
				/**
				 * This method creates a new scene with a pop up to get number of questions
				 * needed in quiz.
				 */
				public void handle(ActionEvent event) {
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
				 * This method creates a new scene with a quiz questions and closes primaryStage
				 * and numberOfQuestionsStage.
				 */
				@Override
				public void handle(ActionEvent event) {
					int numQuestions = Integer.parseInt(inputBox.getText());
					Quiz quiz = new Quiz();
					quiz.setNumQuestions(numQuestions);
					Stage newStage = new Stage();
					try {
						quiz.start(newStage);
						numberOfQuetionsStage.close();
						primaryStage.close();
					} catch (Exception e) {
						numberOfQuetionsStage.close();
						Label questionNumPrompt = new Label("Choose at least one topic");

						VBox vBox = new VBox(questionNumPrompt, closePopUp);
						Scene popupScene = new Scene(vBox);
						numberOfQuetionsStage.setScene(popupScene);
						numberOfQuetionsStage.show();
					}
				}
			});

			closePopUp.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					numberOfQuetionsStage.close();

				}
			});
			// Set up behavior for Edit button
			edit.setOnAction(new EventHandler<ActionEvent>() {

				/**
				 * This method creates a new scene with a quiz questions and closes primaryStage
				 * and numberOfQuestionsStage.
				 */
				@Override
				public void handle(ActionEvent event) {
					Edit edit = new Edit();
					Stage newStage = new Stage();
					try {
						edit.start(newStage);
						primaryStage.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			// Set up behavior for addQuestion button
			addQuestion.setOnAction(new EventHandler<ActionEvent>() {

				/**
				 * Goes to AddQuestion Page
				 */
				@Override
				public void handle(ActionEvent event) {
					// ObservableList<String> options =
					// FXCollections.observableArrayList(
					// "Add Individual Question",
					// "Upload JSON File"
					// );
					// final ComboBox comboBox = new ComboBox(options);

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
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					if (file != null) {
						try {
							// addFile.start(newStage);
							// primaryStage.close();
							// Main main = new Main();
							// Stage newStage1 = new Stage();

							// main.start(newStage1);
							primaryStage.show();

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			});

			// AddFile addFile = new AddFile();
			// Stage newStage = new Stage();
			// try {
			// addFile.start(newStage);
			// primaryStage.close();
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }
			// });

			// Generates Topic List
			items = FXCollections.observableArrayList();
			// topicsToQuestion = new ArrayList<String>();
			items.clear();
			for (int i = 0; i < topics.keySet().size(); i++) {
				items.add(topics.keySet().get(i));
			}
			ListView<String> list = new ListView<>(items);
			ListView<String> selected = new ListView<>();
			HBox hBox2 = new HBox(list, selected);

			// Allows user to select multiple topics
			list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			list.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
				selected.setItems(list.getSelectionModel().getSelectedItems());
				for (String t : list.getSelectionModel().getSelectedItems()) {
					if (!topicsToQuestion.contains(t))
						topicsToQuestion.add(t);
				}
			});

			root.setTop(chooseTopic);
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
	 * Opens File Explorer so a user can upload a JSON file
	 * 
	 * @param file
	 */
	/*
	 * private void openFile(File file) { try { desktop.open(file); } catch
	 * (IOException ex) { } }
	 */

	/**
	 * Main method that runs the application
	 * 
	 * @param args
	 *            command-line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
