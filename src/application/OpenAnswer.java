package application;

import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OpenAnswer extends Question {
	//TotalValue: double
	//QuestionStr: String
	//Screen: BorderPane
	//Submit: Button
    private String CorrectAnswer;
    private TextField textfield = new TextField();
    public String GetCorrectAnswer() {return this.CorrectAnswer;}
    
    public OpenAnswer(String QuestionStr_, String CorrectAnswer_, double TotalValue_) {
    	this.QuestionStr = QuestionStr_;
    	this.CorrectAnswer = CorrectAnswer_;
    	this.TotalValue = TotalValue_;
    	this.Screen = new BorderPane();
    	//top stuff
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
    	
    	VBox questionsbox = new VBox();
    	questionsbox.setAlignment(Pos.CENTER);
		questionsbox.getChildren().add(textfield);
    	this.Screen.setCenter(questionsbox);
        this.Submit = new Button("Submit");
        this.Screen.setBottom(this.Submit);
        BorderPane.setAlignment(topbox, Pos.CENTER);
        BorderPane.setAlignment(this.Submit, Pos.CENTER);
        this.Screen.setPadding(new Insets(10, 20, 10, 20));
    }
    
    public double SubmitAnswer() {
    	String ans = textfield.getText();
    	if (ans.equals("")) {return (double) -1;}
    	if (ans.equals(this.CorrectAnswer)) {return this.TotalValue;}
    	else {return (double) 0;}
    }
}
