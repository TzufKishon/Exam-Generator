package exam_gen.model;

import java.io.Serializable;

public abstract class Question implements Serializable, Comparable<Question>, Cloneable{

	protected String questionText;
	protected final int ID;
	

	public Question(String questionText, int currentNumOfQuestions) {
		ID =++currentNumOfQuestions;
		setQuestionText(questionText);
	}

	public boolean setQuestionText(String questionText) {
		this.questionText = questionText;
		return true;
	}

	public String getQuestionText() {
		return questionText;
	}

	public int getId() {
		return ID;
	}

	public abstract boolean addAnswer(Answer answer);

	public abstract void answersCheck();

	public abstract void changeCorrectnessByAnwerNum(int answerNUm, boolean correctness);

	public int compareTo(Question q) {
		if (this.answerCharsCounter() < q.answerCharsCounter()) return -1;
		else if (this.answerCharsCounter() > q.answerCharsCounter()) return 1;
		else return 0;
		}
	
	public abstract int answerCharsCounter();		

	public boolean equals(Question other) {
		if ((this instanceof American_Question && other instanceof American_Question)
				|| (this instanceof Open_Question && other instanceof Open_Question)) {
			if (this.questionText.equals(other.questionText))
				return true;
		}
		return false;
	}

	public String toString() {
		return "question number " + ID + ": " + questionText + "\n";
	}
	
	@Override
	public Question clone () throws CloneNotSupportedException {
		return (Question)super.clone();
	}
}