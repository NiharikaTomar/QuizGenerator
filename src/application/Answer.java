/**
 * Filename: Answer.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */

package application;

import java.util.ArrayList;

/**
 * This class is an Answer Object needed for the Questions.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
public class Answer {

  // ArrayList of Strings to save answers
  private ArrayList<String> answers;
  // ArrayList of Boolean to save if answers are correct or not
  private ArrayList<Boolean> isCorrect;

  /**
   * No-argument constructor that initializes both ArrayLists.
   */
  public Answer() {
    answers = new ArrayList<String>();
    isCorrect = new ArrayList<Boolean>();
  }

  /**
   * This method adds an answer to an ArrayList of answers and ArrayList of correctness.
   * 
   * @param ans  - answer of type string
   * @param corr - correct/incorrect answer of type boolean
   */
  public void addAnswer(String ans, boolean corr) {
    answers.add(ans);
    isCorrect.add(corr);
  }

  /**
   * This method checks whether an answer is correct or not.
   * 
   * @param ans - answer of type string
   * @return true if answer is correct, false otherwise
   */
  public boolean checkAnswer(String ans) {
    return getCorrectAnswer().equals(ans);
  }

  /**
   * This method gets the correct answer.
   * 
   * @return answer of type String
   */
  public String getCorrectAnswer() {
    // Loop through ArrayList of answers
    for (int i = 0; i < answers.size(); i++) {
      if (isCorrect.get(i))
        return answers.get(i);
    }
    return "";
  }

  /**
   * This method returns an ArrayList of type String of answers.
   * 
   * @return ArrayList of answers
   */
  public ArrayList<String> getAnswers() {
    return answers;
  }

  /**
   * This method returns an ArrayList of type Boolean of solutions to answers.
   * 
   * @return ArrayList of solution
   */
  public ArrayList<Boolean> getIsCorrect() {
    return isCorrect;
  }

  /**
   * This method compares Answers.
   */
  @Override
  public boolean equals(Object obj) {
    Answer ans = (Answer) obj;
    if (ans.getAnswers().size() != getAnswers().size())
      return false;
    for (int i = 0; i < answers.size(); i++) {
      if (!answers.get(i).equals(ans.getAnswers().get(i)))
        return false;
      if (!isCorrect.get(i).equals(ans.getIsCorrect().get(i)))
        return false;
    }
    return true;
  }
}
