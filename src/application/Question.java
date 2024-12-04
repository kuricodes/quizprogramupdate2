package application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public abstract class Question {
    protected double TotalValue;
    protected String QuestionStr;
    public BorderPane Screen;
    public Button Submit;
    public double GetValue() {return this.TotalValue;}
    public String GetQuestion() {return this.QuestionStr;}
    abstract double SubmitAnswer();
    /*public BorderPane BuildPane(int num) {
    	return new BorderPane();
    }*/
}
