package application;

import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//throw new RuntimeException("");

public class Utilities {
	public static Quiz FileReader() throws IOException {
		FileChooser fileChooser = new FileChooser();
	    File selectedFile = fileChooser.showOpenDialog(null);
	    if (selectedFile != null) {
	    	Scanner in = new Scanner(selectedFile);
	    	
	    	in.nextLine(); //each of these ignores a line in the file, usually a label added for the sake of the user
	    	String quizname = in.nextLine();
	    	in.nextLine();
	    	double quizscore = Double.parseDouble(in.nextLine());
	    	String scorename = in.nextLine();
	    	boolean showAnswers = Boolean.parseBoolean(in.nextLine());
	    	in.nextLine();
	    	Quiz quiz = new Quiz(quizname, scorename, quizscore, showAnswers);
	    	
	    	in.nextLine(); //"Quiz Questions"
	    	while (in.hasNextLine()) {
	    		String questiontype = in.nextLine(); //this also skips the question type line
	    		//note to self: do not add a new question type called "Question Type:" or everything will break
	    		if(questiontype.equals("Multiple Choice Single Answer")) {
	    			in.nextLine(); //"Question Text:"
	    			String qtext = in.nextLine();
	    			System.out.println(qtext);
	    			in.nextLine(); //"Options:"
	    			List<String> Answers = new ArrayList<>();
	    			while (true) {
	    				String str = in.nextLine();
	    				if(str.equals("Correct Answer:")){
	    					break;
	    				} else {
	    					Answers.add(str);
	    				}
	    	    	}
	    			String correct = in.nextLine();
	    			in.nextLine(); //"Question Points Value:"
	    			double pval = Double.parseDouble(in.nextLine());
	    			String[] answerarr = new String[Answers.size()];
	    			OneChoice ocquest = new OneChoice(qtext, Answers.toArray(answerarr), correct, pval);
	    			quiz.AddQuestion(ocquest);
	    		} else if(questiontype.equals("Multiple Choice Multiple Answers")) {
	    			in.nextLine(); //"Question Text:"
	    			String qtext = in.nextLine();
	    			System.out.println(qtext);
	    			in.nextLine(); //"Options:"
	    			List<String> Answers = new ArrayList<>();
	    			while (true) {
	    				String str = in.nextLine();
	    				if(str.equals("Correct Answer:")){
	    					break;
	    				} else {
	    					Answers.add(str);
	    				}
	    	    	}
	    			List<String> correct = new ArrayList<>();
	    			while (true) {
	    				String str = in.nextLine();
	    				if(str.equals("Question Points Value:")){
	    					break;
	    				} else {
	    					correct.add(str);
	    				}
	    	    	}
	    			double pval = Double.parseDouble(in.nextLine());
	    			String[] answerarr = new String[Answers.size()];
	    			String[] correctarr = new String[correct.size()];
	    			MultipleChoice mcquest = new MultipleChoice(qtext, Answers.toArray(answerarr), correct.toArray(correctarr), pval);
	    			quiz.AddQuestion(mcquest);
	    		} else if(questiontype.equals("True or False")) {
	    			in.nextLine(); //"Question Text:"
	    			String qtext = in.nextLine();
	    			System.out.println(qtext);
	    			in.nextLine(); //"Correct Answer:"
	    			String correct = in.nextLine();
	    			in.nextLine(); //"Question Points Value:"
	    			double pval = Double.parseDouble(in.nextLine());
	    			TrueFalse tfquest = new TrueFalse(qtext, correct, pval);
	    			quiz.AddQuestion(tfquest);
	    		} else if(questiontype.equals("Open Answer")) {
	    			in.nextLine(); //"Question Text:"
	    			String qtext = in.nextLine();
	    			System.out.println(qtext);
	    			in.nextLine(); //"Correct Answer:"
	    			String correct = in.nextLine();
	    			in.nextLine(); //"Question Points Value:"
	    			double pval = Double.parseDouble(in.nextLine());
	    			OpenAnswer oaquest = new OpenAnswer(qtext, correct, pval);
	    			quiz.AddQuestion(oaquest);
	    		}
	    	}
	    	
	    	in.close();
	    	return quiz;
	    } else {
	    	return null;
	    }
	}
}
