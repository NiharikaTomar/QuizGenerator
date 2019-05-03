/**
 * Filename: Question.java
 * Final Project Authors: Ved Kale, Miriam Lebowitz, Niharika
 * Tomar, and Elizaveta Stepanova
 * 
 * Final Project GUI
 * 
 */

package application;

/*
 * This class aims to implement the question format (along with image) to 
 * be added to the questionBank.
 */
public class Question implements Comparable<Question> {

	String question;// question in string format to be added
	String image; // image to be added

	/*
	 * constructor for question without image
	 */
	public Question(String question) {
		this.question = question;
		this.image = "none";
	}

	/*
	 * constructor for question with image
	 */
	public Question(String question, String image) {
		this.question = question;
		this.image = image;
	}

	/*
	 * accessor method to get question
	 */
	public String getQuestion() {
		return question;
	}

	/*
	 * compares input with question(string)
	 */
	public boolean equals(String q) {
		return question.equals(q);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object q) {
		return question.equals(((Question) q).getQuestion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Question o) {
		return question.compareTo(o.getQuestion());
	}
}
