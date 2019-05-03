/**
 * Filename: Question.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */

package application;

/**
 * This class implements the question format (along with image) to be added to the questionBank.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
public class Question implements Comparable<Question> {

  String question;// question in string format to be added
  String image; // image to be added

  /**
   * Constructor for question without image.
   */
  public Question(String question) {
    this.question = question;
    this.image = "none";
  }

  /**
   * Constructor for question with image.
   */
  public Question(String question, String image) {
    this.question = question;
    this.image = image;
  }


  /**
   * Getter method for a question
   * 
   * @return question of type String
   */
  public String getQuestion() {
    return question;
  }

  /**
   * This method compares input with question of type String.
   * 
   * @param q - question of type String
   * @return true when it is equal, false otherwise
   */
  public boolean equals(String q) {
    return question.equals(q);
  }

  /**
   * This method compares input with question of type Object.
   * 
   * @param q - question of type Object
   * @return true when it is equal, false otherwise
   */
  @Override
  public boolean equals(Object q) {
    return question.equals(((Question) q).getQuestion());
  }

  /**
   * This method compares input with question of type Question.
   * 
   * @param q - question of type Question
   */
  @Override
  public int compareTo(Question o) {
    return question.compareTo(o.getQuestion());
  }
}
