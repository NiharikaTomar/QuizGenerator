package application;

import java.util.HashMap;


public class QuestionBank implements Comparable<String> {
	
	private String topicName;
	private HashMap<Question, Answer> questions;
	
	public QuestionBank(String tn)
	{
		topicName = tn;
		questions = new HashMap<Question, Answer>();
	}
	
	public void addQA(Question q, Answer a)
	{
		questions.put(q, a);
	}
	
	
	/**
	 * Getter for HashMap of questions and answers
	 * @return
	 */
	public HashMap<Question, Answer> getQuestions() {
      return questions;
    } 
	
	/**
	 * Getter for Topic String
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
