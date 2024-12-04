package application;

import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MultipleChoice extends Question {
	//TotalValue: double
	//QuestionStr: String
	//Screen: BorderPane
	//Submit: Button
	private String[] Answers;
    private String[] CorrectAnswer;
    private VBox buttonsvbox = new VBox();
    public String[] GetAnswers() {return this.Answers;}
    public String[] GetCorrectAnswer() {return this.CorrectAnswer;}
    
    public MultipleChoice(String QuestionStr_, String[] Answers_, String[] CorrectAnswer_, double TotalValue_) {
    	this.QuestionStr = QuestionStr_;
    	this.Answers = Answers_;
    	this.CorrectAnswer = CorrectAnswer_;
    	this.TotalValue = TotalValue_;
    	this.Screen = new BorderPane();
    	VBox topbox = new VBox();
    	HBox toptop = new HBox();
    	Label quest = new Label(this.QuestionStr);
    	quest.setWrapText(true);
    	StringBuilder pointsnum = new StringBuilder();
    	pointsnum.append("Points Value: ");
    	pointsnum.append(this.TotalValue);
    	Label pointval = new Label(pointsnum.toString());
    	toptop.getChildren().addAll(pointval);
    	topbox.getChildren().addAll(toptop, new Label(""), quest);
    	this.Screen.setTop(topbox);
    	
    	buttonsvbox.setAlignment(Pos.CENTER);
    	for (String str : this.Answers) {
    		CheckBox cbox = new CheckBox(str);
    		buttonsvbox.getChildren().add(cbox);
        }
    	this.Screen.setCenter(buttonsvbox);
        this.Submit = new Button("Submit");
        this.Screen.setBottom(this.Submit);
        BorderPane.setAlignment(topbox, Pos.CENTER);
        BorderPane.setAlignment(this.Submit, Pos.CENTER);
        this.Screen.setPadding(new Insets(10, 20, 10, 20));
    }
    
    public double SubmitAnswer() {
    	double finalpoints = 0;
    	double pointsval = this.TotalValue / this.Answers.length;
    	for (Node cb : this.buttonsvbox.getChildren()) {
    		String str1 = ((CheckBox) cb).getText();
    		boolean checked = ((CheckBox) cb).isSelected();
    		boolean correctanswer = false;
    		for (String str2 : this.CorrectAnswer) {
    			if(str1.equals(str2)) {
    				correctanswer = true;
    			} //looks at every checkbox and see if it corresponds to a correct answer
    		} //if it does and the checkbox is checked, award points. if it does not and
    		if(checked == correctanswer) { //the checkbox is not checked, award points.
    			finalpoints += pointsval; //in all other cases do not award points
    		}
    	}
    	return finalpoints;
    }

}
