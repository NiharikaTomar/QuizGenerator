/**
 * Filename: AddQuestion.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
package application;

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * This class runs AddQuestions GUI page after AddQuestion Button was chosen.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
public class AddQuestion extends Application {

  private File imageName;
  private String topic;

  /**
   * Runs AddQuestion GUI page.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    // Set up a Border Pane
    BorderPane root = new BorderPane();

    // Horizontal and Vertical boxes needed for the page
    HBox hboxTopMenu = new HBox();
    HBox hboxBottomMenu = new HBox();
    VBox form = new VBox();
    HBox answersAndSwitches = new HBox();
    VBox answers = new VBox();
    VBox switches = new VBox();
    HBox topicBox = new HBox();
    VBox oldTopic = new VBox();
    VBox newTopic = new VBox();

    // Toggle Group for answers
    ToggleGroup answersGroup = new ToggleGroup();

    hboxTopMenu.setSpacing(10);
    hboxBottomMenu.setSpacing(10);
    form.setSpacing(5);

    // Buttons needed for the page
    Button homeButton = new Button("Home");
    Button addImage = new Button("Add Image");
    Button addQuestion = new Button("Add question");

    // Set up layout of the page
    hboxTopMenu.getChildren().add(homeButton);
    hboxBottomMenu.getChildren().add(addQuestion);

    // Set up layout of the page
    Label topicPrompt = new Label("Choose a topic          OR           ");
    topicPrompt.setTextFill(Color.WHITE);
    oldTopic.getChildren().add(topicPrompt);

    // Initialize HashTable and ArrayList needed for the topic
    HashTable<String, QuestionBank> table = Main.topics;
    ArrayList<String> topics = table.keySet();

    // Drop-down list for topics
    ComboBox<String> topicChooser = new ComboBox<String>();
    for (int i = 0; i < topics.size(); i++) {
      topicChooser.getItems().add(topics.get(i));
    }

    // Set up layout of the page
    oldTopic.getChildren().add(topicChooser);
    topicBox.getChildren().add(oldTopic);

    // Set up layout of the page
    Label newTopicPrompt = new Label("Enter a new topic:");
    newTopicPrompt.setTextFill(Color.WHITE);
    TextField topicInput = new TextField();

    // Set up layout of the page
    topicInput.setMaxWidth(150);
    newTopic.getChildren().add(newTopicPrompt);
    newTopic.getChildren().add(topicInput);
    topicBox.getChildren().add(newTopic);
    form.getChildren().add(topicBox);

    // Set up layout of of the page - Question
    Label questionPrompt = new Label("Please enter a question: ");
    questionPrompt.setTextFill(Color.WHITE);
    TextField questionInput = new TextField();
    questionInput.setMaxWidth(500);

    // Set up layout of the page
    Label instructions = new Label("Mark one answer as correct.");
    instructions.setTextFill(Color.WHITE);
    instructions.setPadding(new Insets(20, 10, 10, 0));

    // Set up layout of the page - Answers

    // Answer 1
    Label answer1 = new Label("Please enter Answer 1: ");
    answer1.setTextFill(Color.WHITE);
    TextField txt1 = new TextField();
    txt1.setMaxWidth(500);
    answers.getChildren().add(answer1);
    answers.getChildren().add(txt1);
    RadioButton switch1 = new RadioButton();
    switch1.setPadding(new Insets(20, 10, 10, 20));
    switch1.setToggleGroup(answersGroup);
    switches.getChildren().add(switch1);

    // Answer 2
    Label answer2 = new Label("Please enter Answer 2: ");
    answer2.setTextFill(Color.WHITE);
    TextField txt2 = new TextField();
    txt2.setMaxWidth(500);
    answers.getChildren().add(answer2);
    answers.getChildren().add(txt2);
    RadioButton switch2 = new RadioButton();
    switch2.setPadding(new Insets(15, 10, 5, 20));
    switch2.setToggleGroup(answersGroup);
    switches.getChildren().add(switch2);

    // Answer 3
    Label answer3 = new Label("Please enter Answer 3: ");
    answer3.setTextFill(Color.WHITE);
    TextField txt3 = new TextField();
    txt3.setMaxWidth(500);
    answers.getChildren().add(answer3);
    answers.getChildren().add(txt3);
    RadioButton switch3 = new RadioButton();
    switch3.setPadding(new Insets(20, 10, 5, 20));
    switch3.setToggleGroup(answersGroup);
    switches.getChildren().add(switch3);

    // Answer 4
    Label answer4 = new Label("Please enter Answer 4: ");
    answer4.setTextFill(Color.WHITE);
    TextField txt4 = new TextField();
    txt4.setMaxWidth(500);
    answers.getChildren().add(answer4);
    answers.getChildren().add(txt4);
    RadioButton switch4 = new RadioButton();
    switch4.setPadding(new Insets(20, 10, 5, 20));
    switch4.setToggleGroup(answersGroup);
    switches.getChildren().add(switch4);

    // Answer 5
    Label answer5 = new Label("Please enter Answer 5: ");
    answer5.setTextFill(Color.WHITE);
    TextField txt5 = new TextField();
    txt5.setMaxWidth(500);
    answers.getChildren().add(answer5);
    answers.getChildren().add(txt5);
    RadioButton switch5 = new RadioButton();
    switch5.setPadding(new Insets(20, 10, 10, 20));
    switch5.setToggleGroup(answersGroup);
    switches.getChildren().add(switch5);

    // Set up layout of the page
    answersAndSwitches.getChildren().add(answers);
    answersAndSwitches.getChildren().add(switches);

    // Set up layout of the page
    form.getChildren().add(questionPrompt);
    form.getChildren().add(questionInput);
    form.getChildren().add(addImage);
    form.getChildren().add(instructions);
    form.getChildren().add(answersAndSwitches);

    // Set up layout of the page
    root.setCenter(form);

    // Event handler to let user upload an image needed to be saved to the question bank
    addImage.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent arg0) {
        // FileChooser declaration
        FileChooser.ExtensionFilter imageFilter =
            new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);

        // Save image
        imageName = fc.showOpenDialog(primaryStage);

        // Code for only successful upload of the image
        if (imageName != null) {
          // Alert that the upload is successful
          Alert a = new Alert(AlertType.CONFIRMATION);
          a.setContentText("You have successfully uploaded an image!");
          a.show();
        }
      }
    });

    // Event handler to take user back to home
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

    // Event handler to add a Question
    addQuestion.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * This method creates a new scene with a pop up to go back to main page.
       */
      public void handle(ActionEvent event) {
        Main main = new Main();
        Stage newStage = new Stage();
        try {
          // Instantiate an Answer Object
          Answer ans = new Answer();

          // Boolean for answer that was chosen to be correct
          boolean corr1 = false;
          boolean corr2 = false;
          boolean corr3 = false;
          boolean corr4 = false;
          boolean corr5 = false;

          // Create a toggle to check which one of selected
          Toggle selectedCorrect = answersGroup.getSelectedToggle();

          // Check which boolean needs to be set as a correct answer
          if (selectedCorrect.equals(switch1)) {
            corr1 = true;
          } else if (selectedCorrect.equals(switch2)) {
            corr2 = true;
          } else if (selectedCorrect.equals(switch3)) {
            corr3 = true;
          } else if (selectedCorrect.equals(switch4)) {
            corr4 = true;
          } else if (selectedCorrect.equals(switch5)) {
            corr5 = true;
          }

          // Check if entered answer is not empty.
          // If it is, then don't add it as answer to a question
          if (!txt1.getText().trim().isEmpty()) {
            ans.addAnswer(txt1.getText(), corr1);
          }
          if (!txt2.getText().trim().isEmpty()) {
            ans.addAnswer(txt2.getText(), corr2);
          }
          if (!txt3.getText().trim().isEmpty()) {
            ans.addAnswer(txt3.getText(), corr3);
          }
          if (!txt4.getText().trim().isEmpty()) {
            ans.addAnswer(txt4.getText(), corr4);
          }
          if (!txt5.getText().trim().isEmpty()) {
            ans.addAnswer(txt5.getText(), corr5);
          }

          // Check if topic was entered.
          // If it is, then add it to the topic list in HashTable
          // If not, add question to an existing topic
          if (!topicInput.getText().trim().isEmpty()) {
            topic = topicInput.getText();
            Main.topics.insert(topicInput.getText(), new QuestionBank(topic));
          } else {
            topic = topicChooser.getValue();
          }

          // Check if image was uploaded
          // If it is, then add question, image, and answers to the HashTable
          // If not, then add question, and answers to the HashTable
          if (imageName != null) {
            Main.topics.get(topic).addQA(new Question(questionInput.getText(), imageName.getName()),
                ans);
          } else {
            Main.topics.get(topic).addQA(new Question(questionInput.getText()), ans);
          }

          // Set up layout of the page
          main.start(newStage);
          primaryStage.close();

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });


    // Set up layout of the page
    root.setTop(hboxTopMenu);
    root.setBottom(hboxBottomMenu);

    // Set up layout of the page
    Scene scene = new Scene(root, 1200, 600);

    // Import CSS
    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

    // Set up layout of the page
    primaryStage.setScene(scene);

    // Set the title
    primaryStage.setTitle("Quiz Generator");
    primaryStage.show();
  }
}
