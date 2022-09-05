package exam_gen.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Exam implements Serializable, Cloneable {
	private int numOfQuestions;
	private ArrayList<Question> questions = new ArrayList <Question>(numOfQuestions); 
	
	public Exam(int numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
		this.questions = new ArrayList<Question>(numOfQuestions);
	}


	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	@Override
	public Exam clone () throws CloneNotSupportedException {
		return (Exam)super.clone();
	}
	
	public void addQuestion(Question newQ) {
		questions.add(newQ);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("EXAM: \n");
		for (int i = 0; i < numOfQuestions; i++) {
			if (questions.get(i) instanceof American_Question)
				sb.append(((American_Question) questions.get(i)).examAnswerToString() + "\n");
			if (questions.get(i) instanceof Open_Question)
				sb.append(questions.get(i).toString() + "\n");
		}
		return sb.toString();
	}

	public void setQuestions(ArrayList questions2) {
	this.questions = questions2;		
	}

}
