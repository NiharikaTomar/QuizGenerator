package application;

public class Question implements Comparable<Question>{
	
	String question;
	String image;
	
	public Question(String question)
	{
		this.question = question;
		this.image = "none";
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
	
	@Override
	public boolean equals(Object q)
	{
		return question.equals(((Question) q).getQuestion());
	}

	@Override
	public int compareTo(Question o) {
		return question.compareTo(o.getQuestion());
	}
}
