package application;

import java.util.HashMap;


public class Topic {
	
	String topicName;
	HashMap<Question, Answer> questions;
	
	public Topic(String tn)
	{
		topicName = tn;
		questions = new HashMap<Question, Answer>();
	}
	
	public void addQA(Question q, Answer a)
	{
		questions.put(q, a);
	}
	
}
