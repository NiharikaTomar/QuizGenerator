/**
 * Filename:   AddQuestion.java
 * Project:    Final Project
 * Authors:    Ved Kale, Miriam Lebowitz, Niharika Tomar, and Elizaveta Stepanova
 * 
 * Final Project GUI
 * 
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * Runs AddQuestion GUI
 * @author Miriam, Elizaveta, Niharika, and Ved
 *
 */
public class AddQuestion extends Application{

  private File imageName;
  private String topic;
  /**
   * Runs AddQuestion GUI
   */
  @Override
  public void start(Stage primaryStage) throws Exception {


    BorderPane root = new BorderPane();

    HBox hboxTopMenu = new HBox();
    HBox hboxBottomMenu = new HBox();

    VBox form = new VBox();
    form.setSpacing(5);
    HBox answersAndSwitches = new HBox();
    VBox answers = new VBox();
    VBox switches = new VBox();
    HBox topicBox = new HBox();
    ToggleGroup answersGroup = new ToggleGroup();

    hboxTopMenu.setSpacing(10);
    hboxBottomMenu.setSpacing(10);

    // Buttons needed for the page
    Button homeButton = new Button("Home");
    Button addImage = new Button("Add Image");
    Button addQuestion = new Button("Add question");

    hboxTopMenu.getChildren().add(homeButton);
    hboxBottomMenu.getChildren().add(addQuestion);

    VBox oldTopic = new VBox();
    Label topicPrompt = new Label("Choose a topic          OR           ");
    topicPrompt.setTextFill(Color.WHITE);
    oldTopic.getChildren().add(topicPrompt);
    HashTable<String, QuestionBank> table = Main.topics;
    ArrayList<String> topics = table.keySet();
    ComboBox<String> topicChooser = new ComboBox<String>();
    for (int i = 0; i < topics.size(); i++) {
      topicChooser.getItems().add(topics.get(i));
    }
    oldTopic.getChildren().add(topicChooser);
    topicBox.getChildren().add(oldTopic);

    //Label optional = new Label("              OR              ");
    //optional.setTextFill(Color.WHITE);
    //topicBox.getChildren().add(optional);
    VBox newTopic = new VBox();
    Label newTopicPrompt = new Label("Enter a new topic:");
    newTopicPrompt.setTextFill(Color.WHITE);
    TextField topicInput = new TextField();

    topicInput.setMaxWidth(150);
    newTopic.getChildren().add(newTopicPrompt);
    newTopic.getChildren().add(topicInput);
    topicBox.getChildren().add(newTopic);
    form.getChildren().add(topicBox);

    //Question
    Label questionPrompt = new Label("Please enter a question: ");
    questionPrompt.setTextFill(Color.WHITE);
    TextField questionInput = new TextField();
    questionInput.setMaxWidth(500);

    Label instructions = new Label("Mark one answer as correct.");
    instructions.setTextFill(Color.WHITE);
    instructions.setPadding(new Insets(20, 10, 10, 0));

    //Answers
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

    answersAndSwitches.getChildren().add(answers);
    answersAndSwitches.getChildren().add(switches);

    //form.getChildren().add(topicChooser);
    form.getChildren().add(questionPrompt);
    form.getChildren().add(questionInput);
    form.getChildren().add(addImage);
    form.getChildren().add(instructions);
    form.getChildren().add(answersAndSwitches);

    root.setCenter(form);

    String topicChosen ="";

    final FileChooser fileChooser = new FileChooser();
    addImage.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent arg0) {
        imageName = fileChooser.showOpenDialog(primaryStage);
        if (imageName != null) {
          Alert a = new Alert(AlertType.CONFIRMATION);
          a.setContentText("You have successfully uploaded an image!");
          a.show();
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

    addQuestion.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * This method creates a new scene with a pop up to go back to main page.
       */
      public void handle(ActionEvent event) {
        Main main = new Main();
        Stage newStage = new Stage();
        try {
          Answer ans = new Answer();
          boolean corr1 = false;
          boolean corr2 = false;
          boolean corr3 = false;
          boolean corr4 = false;
          boolean corr5 = false;

          Toggle selectedCorrect = answersGroup.getSelectedToggle();
//          if( selectedCorrect.equals(switch1.getUserData())) {
//            corr1 = true;
//          }
//          if( selectedCorrect.equals(switch2.getUserData())) {
//            corr2 = true;
//          }
//          if( selectedCorrect.equals(switch3.getUserData())) {
//            corr3 = true;
//          }
//          if( selectedCorrect.equals(switch4.getUserData())) {
//            corr4 = true;
//          }
//          if( selectedCorrect.equals(switch5.getUserData())) {
//            corr5 = true;
//          }
          if (switch1.isSelected()) {
            corr1 = true;
          }
          if (switch2.isSelected()) {
            corr2 = true;
          }
          if (switch3.isSelected()) {
            corr3 = true;
          }
          if (switch4.isSelected()) {
            corr4 = true;
          }
          if (switch5.isSelected()) {
            corr5 = true;
          }
          if (!txt1.equals(null)) {
            ans.addAnswer(txt1.getText(), corr1);
          }
          if (!txt2.equals(null)) {
            ans.addAnswer(txt2.getText(), corr2);
          }
          if (!txt3.equals(null)) {
            ans.addAnswer(txt3.getText(), corr3);
          }
          if (!txt4.equals(null)) {
            ans.addAnswer(txt4.getText(), corr4);
          }
          if (!txt5.equals(null)) {
            ans.addAnswer(txt5.getText(), corr5);
          }
//          ans.addAnswer(txt2.getText(), corr2);
//          ans.addAnswer(txt3.getText(), corr3);
//          ans.addAnswer(txt4.getText(), corr4);
//          ans.addAnswer(txt5.getText(), corr5);
          
          if (!topicInput.equals(null)) {
            topic = topicInput.getText();
            Main.topics.insert(topicInput.getText(), new QuestionBank(topic)); 
          }
          else {
            topic = topicChooser.getValue();
          }
          if (imageName != null) {
            Main.topics.get(topic).addQA(new Question(questionInput.getText(), imageName.getName()), ans); 
          }
          else {
            Main.topics.get(topic).addQA(new Question(questionInput.getText()), ans);
          }
          main.start(newStage);
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
