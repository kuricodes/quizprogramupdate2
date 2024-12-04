package application;

import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TrueFalse extends Question {
	//TotalValue: double
	//QuestionStr: String
	//Screen: BorderPane
	//Submit: Button
    private String CorrectAnswer;
    private ToggleGroup buttons = new ToggleGroup();
    public String GetCorrectAnswer() {return this.CorrectAnswer;}
    
    public TrueFalse(String QuestionStr_, String CorrectAnswer_, double TotalValue_) {
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
    	RadioButton tbutton = new RadioButton("True");
		tbutton.setToggleGroup(buttons);
		questionsbox.getChildren().add(tbutton);
		RadioButton fbutton = new RadioButton("False");
		fbutton.setToggleGroup(buttons);
		questionsbox.getChildren().add(fbutton);
    	this.Screen.setCenter(questionsbox);
        this.Submit = new Button("Submit");
        this.Screen.setBottom(this.Submit);
        BorderPane.setAlignment(topbox, Pos.CENTER);
        BorderPane.setAlignment(this.Submit, Pos.CENTER);
        this.Screen.setPadding(new Insets(10, 20, 10, 20));
    }
    
    public double SubmitAnswer() {
    	if (buttons.getSelectedToggle() == null) {return (double) -1;}
    	RadioButton selectedRadioButton = (RadioButton) buttons.getSelectedToggle();
    	String ans = selectedRadioButton.getText();
    	if (ans.equals(this.CorrectAnswer)) {return this.TotalValue;} else {return (double) 0;}
    }
}
