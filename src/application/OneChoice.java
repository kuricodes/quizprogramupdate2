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

public class OneChoice extends Question {
	//TotalValue: double
	//QuestionStr: String
	//Screen: BorderPane
	//Submit: Button
	private String[] Answers;
    private String CorrectAnswer;
    private ToggleGroup buttons = new ToggleGroup(); //moved out of the borderpane constructor
    public String[] GetAnswers() {return this.Answers;}
    public String GetCorrectAnswer() {return this.CorrectAnswer;}
    
    public OneChoice(String QuestionStr_, String[] Answers_, String CorrectAnswer_, double TotalValue_) {
    	this.QuestionStr = QuestionStr_;
    	this.Answers = Answers_;
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
    	for (String str : this.Answers) {
    		RadioButton rbutton = new RadioButton(str);
    		rbutton.setToggleGroup(buttons);
    		questionsbox.getChildren().add(rbutton);
        }
    	this.Screen.setCenter(questionsbox);
    	
    	//bottom submit button
        this.Submit = new Button("Submit");  //able to be referenced outside of function for the sake of binding actionevent in main.java
        /*EventHandler<ActionEvent> submitevent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
            	RadioButton selectedRadioButton = (RadioButton) buttons.getSelectedToggle();
            	String ans = selectedRadioButton.getText();
            	double score = SubmitAnswer(ans);
            } 
        };
        submit.setOnAction(submitevent); */
        //still working out how i want to do this part
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
    
    /*@Override
    public BorderPane BuildPane(int num) {
    	BorderPane george = new BorderPane();
    	
    	//top stuff
    	VBox topbox = new VBox();
    	HBox toptop = new HBox();
    	Label quest = new Label(this.QuestionStr);
    	StringBuilder questionnum = new StringBuilder();
    	questionnum.append("Question ");
    	questionnum.append(num);
    	Label numbers = new Label(questionnum.toString());
    	StringBuilder pointsnum = new StringBuilder();
    	pointsnum.append("Points Value: ");
    	pointsnum.append(this.TotalValue);
    	Label pointval = new Label(pointsnum.toString());
    	toptop.getChildren().addAll(numbers, pointval);
    	topbox.getChildren().addAll(toptop, quest);
    	george.setTop(topbox);
    	
    	VBox questionsbox = new VBox();
    	ToggleGroup buttons = new ToggleGroup();  
    	for (String str : this.Answers) {
    		RadioButton rbutton = new RadioButton(str);
    		rbutton.setToggleGroup(buttons);
    		questionsbox.getChildren().add(rbutton);
        }
    	george.setCenter(questionsbox);
    	
    	//bottom submit button
        Button submit = new Button("Submit");  
        EventHandler<ActionEvent> submitevent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
            	RadioButton selectedRadioButton = (RadioButton) buttons.getSelectedToggle();
            	String ans = selectedRadioButton.getText();
            	double score = SubmitAnswer(ans);
            } 
        };
        submit.setOnAction(submitevent);
        george.setBottom(submit);
    	return george;
    }*/
    //scrapped, i think i know a better way to handle this

}
