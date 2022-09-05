package exam_gen.model;

import java.io.Serializable;

public class Answer implements Serializable, Cloneable{

	private String answerText;
	private boolean correctness;

	public Answer(String answerText, boolean correctness) {
		setAnswerText(answerText);
		setCorrectness(correctness);
	}

	public String getAnswerText() {
		return answerText;
	}

	public boolean setAnswerText(String answerText){
		this.answerText = answerText;
		return true;
	}

	public boolean isCorrectness() {
		return correctness;
	}

	public boolean getCorrectness() {
		return correctness;
	}
	public boolean setCorrectness(boolean correctness) {
		this.correctness = correctness;
		return true;
	}
	
	public void deleteAnswer() {
		setAnswerText("non existing answer");
		setCorrectness(false);
		}
	
	public String toString() {
		return answerText + " --> " + correctness;
	}
	
	public boolean equals(Answer other) {
		if (this.answerText.equals(other.getAnswerText())) {
			return true;
		}
		return false;
	}
	
	@Override
	public Answer clone () throws CloneNotSupportedException {
		return (Answer)super.clone();
	}
}