/**
 * Filename: QuestionBank.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This class aims to implement the question bank containing all questions uploaded from a file
 * and/or inputed by the user.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
public class QuestionBank implements Comparable<String> {

  private String topicName;
  private HashMap<Question, Answer> questions;

  /**
   * Constructor initializes topicName and questions,
   */
  public QuestionBank(String tn) {
    topicName = tn;
    questions = new HashMap<Question, Answer>();
  }

  /**
   * This method gets answers from the set.
   * 
   * @return ArrayList of answers
   */
  public ArrayList<Answer> getAnswers() {
    Set<Question> questions = this.questions.keySet();

    ArrayList<Answer> answers = new ArrayList<Answer>();
    // Loop through the Set of Questions
    for (Question question : questions) {
      Answer answer = this.questions.get(question);
      answers.add(answer);
    }
    return answers;
  }

  /**
   * This method adds questions and answers to the database which is the question bank.
   */
  public void addQA(Question q, Answer a) {
    questions.put(q, a);
  }

  /**
   * Getter for HashMap of questions and answers.
   * 
   * @return questions of HashMap<Question, Answer>
   */
  public HashMap<Question, Answer> getQuestions() {
    return questions;
  }

  /**
   * Getter for Topic String.
   * 
   * @return topicName of type String
   */
  public String getTopicName() {
    return topicName;
  }

  /*
   * This method compares topic of type String.
   */
  @Override
  public int compareTo(String o) {
    return o.compareTo(topicName);
  }

}
