package exam_gen.model;

import java.io.Serializable;

public class Open_Question extends Question implements Comparable<Question>{

	private Answer answer;

	public Open_Question(String questionText, int currentNumOfQuestions, String answerText) {
		super(questionText, currentNumOfQuestions);
		this.answer= new Answer(answerText, true);	
	}
	
	public Answer getAnswer() {
		return this.answer;
	}
	
	@Override
	public void answersCheck() {
	}
	
	public boolean addAnswer(Answer answer) {
		this.answer = answer;
		return true;
	}
	
	@Override
	public void changeCorrectnessByAnwerNum(int answerNum, boolean correctness) {
	}

	@Override
	public String toString() {
		return super.toString() + "Answer: " + answer.getAnswerText() + "\n";
	}

	public int answerCharsCounter() {
	return answer.getAnswerText().length();	
	}




}
