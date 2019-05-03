package application;

import java.util.ArrayList;

public class Answer{
	
	private ArrayList<String> answers;
	private ArrayList<Boolean> isCorrect;
	
	public Answer()
	{
		answers = new ArrayList<String> ();
		isCorrect = new ArrayList<Boolean> ();
	}
	
	public void addAnswer(String ans, boolean corr)
	{
		answers.add(ans);
		isCorrect.add(corr);
	}
	
	public boolean checkAnswer(String ans)
	{
		return getCorrectAnswer().equals(ans);
	}
	
	public String getCorrectAnswer()
	{
		for (int i=0;i<answers.size();i++)
		{
			if (isCorrect.get(i))
				return answers.get(i);
		}
		return "";
	}
	
	public ArrayList<String> getAnswers() {
      return answers;
    }
	
	public ArrayList<Boolean> getIsCorrect() {
      return isCorrect;
    }
	
	@Override
	public boolean equals(Object obj) {
		Answer ans = (Answer) obj;
		if (ans.getAnswers().size() != getAnswers().size())
			return false;
		for (int i=0;i<answers.size();i++)
		{
			if (!answers.get(i).equals(ans.getAnswers().get(i)))
				return false;
			if (!isCorrect.get(i).equals(ans.getIsCorrect().get(i)))
				return false;
		}
		return true;
	}
}
