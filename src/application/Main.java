package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	Quiz quiz;
	int index = 0;
	BorderPane root = new BorderPane();
	
	EventHandler<ActionEvent> submitevent = new EventHandler<ActionEvent>() { //it feels so dirty to be doing all this in the class layer
        public void handle(ActionEvent e)
        {
        	if (quiz.GetQuestion(index+1) != null) {
        		double score = quiz.GetQuestion(index).SubmitAnswer();
        		if (score != -1) { //quick and dirty flag to indicate that there is no answer provided yet
        			quiz.AddScore(score);
        			BorderPane bpane = RenameMeLater();
                	root.setCenter(null);
                	root.setCenter(bpane);
        		}
        	} else { //end of quiz, show final screen
        		double score = quiz.GetQuestion(index).SubmitAnswer();
        		if (score != -1) {
        			quiz.AddScore(score);
        			BorderPane steve = new BorderPane();
            		Label toplabel;
            		double percentage = quiz.GetScore()/quiz.GetScoreMax();
            		if(percentage>=0.9) {
            			toplabel = new Label("Wow, you got an A! Incredible work!");
            		} else if (percentage>=0.7) {
            			toplabel = new Label("You passed the quiz! Good job!");
            		} else if (percentage>=0.4) {
            			toplabel = new Label("Sorry, you failed the quiz. Better luck next time!");
            		} else {
            			toplabel = new Label("You failed the quiz badly. Please try harder next time.");
            		}
            		StringBuilder mid = new StringBuilder();
                	mid.append("total points: ");
                	mid.append(quiz.GetScore());
                	mid.append("/");
                	mid.append(quiz.GetScoreMax());
            		Label midlabel = new Label(mid.toString());
            		StringBuilder bot = new StringBuilder();
                	bot.append("your final score: ");
                	bot.append(quiz.GetPercent());
            		Label botlabel = new Label(bot.toString());
            		VBox introvbox = new VBox();
            		introvbox.getChildren().addAll(toplabel, new Label(""), midlabel, new Label(""), botlabel);
            		steve.setCenter(introvbox);
            		BorderPane.setAlignment(introvbox, Pos.CENTER);
            		introvbox.setAlignment(Pos.CENTER);
            		root.setCenter(null);
                	root.setCenter(steve);
        		}
        	}
        } 
    };
	
	public BorderPane RenameMeLater() {
		index += 1;
		Question joe = quiz.GetQuestion(index);
		if (joe != null) {
			BorderPane george = joe.Screen;
			quiz.GetQuestion(index).Submit.setOnAction(submitevent);
			return george;
		} else {return null;}
		
		
	} //thank god java lets you reference functions that are defined later in the class
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Label introlabel = new Label("Welcome to my quiz program!");
			Button selectfile = new Button("Click to select file");
			VBox introvbox = new VBox();
			introvbox.getChildren().addAll(introlabel, new Label(), selectfile);
			root.setCenter(introvbox);
			BorderPane.setAlignment(introvbox, Pos.CENTER);
			introvbox.setAlignment(Pos.CENTER);
			
			VBox prequizvbox = new VBox();
			Label quiznamelabel = new Label();
			Label quizquestionslabel = new Label();
			Label quizpointslabel = new Label();
			Button takequiz = new Button("Take quiz");
			prequizvbox.getChildren().addAll(new Label("Quiz Name:"), quiznamelabel, new Label(), new Label("Number of questions:"), quizquestionslabel);
			prequizvbox.getChildren().addAll(new Label(), new Label("Total Points:"), quizpointslabel, new Label(), takequiz);
			prequizvbox.setAlignment(Pos.CENTER);
			
			EventHandler<ActionEvent> selectevent = new EventHandler<ActionEvent>() { 
	            public void handle(ActionEvent e) 
	            {
	            	try {quiz = Utilities.FileReader();} catch (IOException e1) {e1.printStackTrace();}
	            	if (quiz != null) {
	            		root.setCenter(null);
	            		quiznamelabel.setText(quiz.GetQuizName());
	            		quizquestionslabel.setText(Integer.toString(quiz.GetQuestions().length));
	            		quizpointslabel.setText(String.format("%.2f",quiz.GetScoreMax()));
	                	root.setCenter(prequizvbox);
	            	}
	            } 
	        };
	        selectfile.setOnAction(selectevent);
	        EventHandler<ActionEvent> startevent = new EventHandler<ActionEvent>() { 
	            public void handle(ActionEvent e) 
	            {
	            	root.setCenter(null);
	                root.setCenter(quiz.GetQuestion(0).Screen);
	                quiz.GetQuestion(0).Submit.setOnAction(submitevent);
	            } 
	        };
	        takequiz.setOnAction(startevent);
			
			Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

/*
okay i'm writing this shortly after getting the file opening function to work on the starter ui
i'm not entirely sure how i am going to fit the modular variable length quizzes into the button event framework
i have scrapped the scenebuilder function in favor of creating the scene as part of the question object inside the constructor

okay, after some work, i have figured out a way i think i'll be able to move the game from one scene to the next
using a separate function that keeps an iterator. now the question is will i be able to use that function to create an
actionevent that can be used to bind itself to the next submit button

WAIT A SECOND
WHAT IF ITS JUST THE SAME SUBMIT BUTTON EVERY TIME AND I JUST MOVE IT TO THE NEXT SCENE
OH MY GOD

update two hours later: no that did not work. that was not a good idea.
however, exposing each question's button and borderpane to main by making them public class variables may be what i'm looking for

that worked FLAWLESSLY
its genuinely incredible, after fixing one typecasting issue with the arraylist toarray function the code worked without a hitch
if its not just ego. i really am the smartest person on earth

*/
