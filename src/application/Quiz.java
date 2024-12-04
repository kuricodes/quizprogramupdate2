package application;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
	private double MaxScore;
	private double TotalScore;
	public void AddScore(double score) {this.TotalScore+=score;}
	public double GetScore() {return this.TotalScore;}
	public double GetScoreMax() {return this.MaxScore;}
	public String GetPercent() {return String.format("%.0f%%",(this.TotalScore/this.MaxScore*100));}
	
	private List<Question> Questions = new ArrayList<>();
	public Question[] GetQuestions() {
		Question[] jgklfd = new Question[this.Questions.size()];
		return this.Questions.toArray(jgklfd);
	}
	public Question GetQuestion(int index) {
		if(index < Questions.size()) {
			return (Question) this.Questions.get(index);
		} else {
			return null;
		}
	}
	public <T extends Question> void AddQuestion(T quest) {
		this.MaxScore += quest.GetValue();
		this.Questions.add(quest);
	}
	
	private boolean ShowAnswer;   public boolean GetShowAnswer() {return this.ShowAnswer;} //all of this is unused at the moment, scrapped for time
	private String QuizName;      public String GetQuizName() {return this.QuizName;}      //might re-implement if time permits
	private double HighScoreNum;  public double GetHighScoreNum() {return this.HighScoreNum;}
	private String HighScoreName; public String GetHighScoreName() {return this.HighScoreName;}
	
	public Quiz(String name, String hsname, double hsnum, boolean showAnswers) {
		this.QuizName = name;
		this.HighScoreName = name;
		this.HighScoreNum = hsnum;
		this.TotalScore = 0;
	}
}
