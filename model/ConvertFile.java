package exam_gen.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import application.Main;

public class ConvertFile implements Serializable {
	public static Manager init() throws IOException, ClassNotFoundException {
		boolean enterHardCodedQuestions = false;
		Manager stock = new Manager();
		Object ob = new Object();
		if (!Main.file.exists()) {
			Main.file.createNewFile();
			enterHardCodedQuestions = true;
		} else {
			FileInputStream fs = new FileInputStream(Main.file.getAbsoluteFile());
			if (fs.available() <= 0) {
				enterHardCodedQuestions = true;
			} else {
				ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(Main.file.getAbsoluteFile()));
				  stock.setQuestionsStock(inFile.readObject());
				inFile.close();
			}
		}
		if (enterHardCodedQuestions) {
			Question q1 = new Open_Question("Who invented the milk chocolate?", stock.getQuestionsStock().size(),
					"Daniel Peter");
			stock.addQuestion(q1);
			Question q2 = new Open_Question("How long cacao trees live?", stock.getQuestionsStock().size(),
					"200 years");
			stock.addQuestion(q2);
			Question q3 = new American_Question("How many clories are in 100 gram of cheesecake on average?",
					stock.getQuestionsStock().size());
			Answer q3a3 = new Answer("240", true);
			Answer q3a4 = new Answer("250", false);
			Answer q3a5 = new Answer("600", false);
			stock.addQuestion(q3);
			stock.getQuestionsStock().get(2).addAnswer(q3a3);
			stock.getQuestionsStock().get(2).addAnswer(q3a4);
			stock.getQuestionsStock().get(2).addAnswer(q3a5);
			Question q4 = new American_Question("What is the main ingredient in crack pie?",
					stock.getQuestionsStock().size());
			stock.addQuestion(q4);
			Answer q4a3 = new Answer("Vanilla", false);
			Answer q4a4 = new Answer("Oatmeal", true);
			Answer q4a5 = new Answer("Black coffee", false);
			stock.getQuestionsStock().get(3).addAnswer(q4a3);
			stock.getQuestionsStock().get(3).addAnswer(q4a4);
			stock.getQuestionsStock().get(3).addAnswer(q4a5);
			Question q5 = new American_Question("How old is Karin Goren (the queen of desserts)?",
					stock.getQuestionsStock().size());
			stock.addQuestion(q5);
			Answer q5a3 = new Answer("47", true);
			Answer q5a4 = new Answer("50", false);
			Answer q5a5 = new Answer("34", false);
			stock.getQuestionsStock().get(4).addAnswer(q5a3);
			stock.getQuestionsStock().get(4).addAnswer(q5a4);
			stock.getQuestionsStock().get(4).addAnswer(q5a5);
		}

		return stock;
	}

	
}
