package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class QuestionBank implements Comparable<String> {

  private String topicName;
  private HashMap<Question, Answer> questions;

  public QuestionBank(String tn) {
    topicName = tn;
    questions = new HashMap<Question, Answer>();
  }

  public ArrayList<Answer> getAnswers() {
    Set<Question> questions = this.questions.keySet();

    ArrayList<Answer> answers = new ArrayList<Answer>();
    for (Question question : questions) {
      Answer answer = this.questions.get(question);
      answers.add(answer);
    }

    return answers;
  }

  public void addQA(Question q, Answer a) {
    questions.put(q, a);
  }

  /**
   * Getter for HashMap of questions and answers
   * 
   * @return
   */
  public HashMap<Question, Answer> getQuestions() {
    return questions;
  }

  /**
   * Getter for Topic String
   * 
   * @return
   */
  public String getTopicName() {
    return topicName;
  }

  @Override
  public int compareTo(String o) {
    return o.compareTo(topicName);
  }

}
