/**
 * Filename: QuestionBank.java
 * Project: Final Project 
 * Authors: Ved Kale, Miriam Lebowitz, Niharika
 * Tomar, and Elizaveta Stepanova
 * Final Project GUI
 */
package application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/*
 * This class aims to implement the question bank containing all 
 * questions uploaded from a file and/or inputed by the user
 */
public class QuestionBank implements Comparable<String> {

	private String topicName;
	private HashMap<Question, Answer> questions;

	/*
	 * initializing topicName and questions
	 */
	public QuestionBank(String tn) {
		topicName = tn;
		questions = new HashMap<Question, Answer>();
	}

	/*
	 * This method gets answers from the set
	 */
	public ArrayList<Answer> getAnswers() {
		Set<Question> questions = this.questions.keySet();

		ArrayList<Answer> answers = new ArrayList<Answer>();
		for (Question question : questions) {
			Answer answer = this.questions.get(question);
			answers.add(answer);
		}
		return answers;
	}

	/*
	 * this method adds questions and answers to the question-answer
	 * database which is the question bank
	 */
	public void addQA(Question q, Answer a) {
		questions.put(q, a);
	}

	/**
	 * Getter for HashMap of questions and answers
	 * 
	 * @return questions
	 */
	public HashMap<Question, Answer> getQuestions() {
		return questions;
	}

	/**
	 * Getter for Topic String
	 * 
	 * @return topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(String o) {
		return o.compareTo(topicName);
	}

}
