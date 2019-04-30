package application;

import java.util.HashMap;


public class Topic {
	
	private String topicName;
	private HashMap<Question, Answer> questions;
	
	public Topic(String tn)
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
	
}
