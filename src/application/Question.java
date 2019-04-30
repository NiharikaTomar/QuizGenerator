package application;

public class Question implements Comparable<Question>{
	
	String question;
	String image;
	
	public Question(String question)
	{
		this.question = question;
		this.image = "null";
	}
	
	public Question(String question, String image)
	{
		this.question = question;
		this.image = image;
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	public boolean equals(String q)
	{
		return question.equals(q);
	}
	
	public boolean equals(Question q)
	{
		return question.equals(q.getQuestion());
	}

	@Override
	public int compareTo(Question o) {
		return question.compareTo(o.getQuestion());
	}

}
