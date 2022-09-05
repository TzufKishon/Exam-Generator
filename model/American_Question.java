package exam_gen.model;

import java.io.Serializable;

public class American_Question extends Question implements Comparable<Question>{
	
	private Set<Answer> answers = new Set<Answer>();

	public American_Question(String questionText, int currentNumOfQuestions) {
		super(questionText, currentNumOfQuestions);
		addAnswer(new Answer("All answers are incorrect", false));
		addAnswer(new Answer("All answers are correct", false));
	}

	public Set<Answer> getAnswers() {
		return this.answers;
	}


	public boolean addAnswer(Answer answer) {
		answers.add(answer);
		answersCheck();
		return true;
		
	}

	public void answersCheck() { //this method will check the correctness of the first two answers- "All answers are incorrect", "All answers are correct" and 
		//will change it according to the other answers that were added/ deleted to the question.
		int correctCounter = 0;
		int inCorrectCounter = 0;
		if (answers.get(0) != null) {
		answers.get(0).setCorrectness(false);
		}
		if (answers.get(1) != null) {
		answers.get(1).setCorrectness(false);
		}
		for (int i = 2; i < answers.getCurrentSize(); i++) {
			if (answers.get(i) != null && answers.get(i).getCorrectness() == true) {
				correctCounter++;
			}
			if (answers.get(i) != null && answers.get(i).getCorrectness() == false) {
				inCorrectCounter++;
			}
		}
		if (!(inCorrectCounter == 0 && correctCounter == 0) && inCorrectCounter == answers.getCurrentSize() - 2) {

			answers.get(0).setCorrectness(true);
		}
		if (!(inCorrectCounter == 0 && correctCounter == 0) && correctCounter == answers.getCurrentSize() - 2) {
			answers.get(1).setCorrectness(true);
		}
	}

	@Override
	public void changeCorrectnessByAnwerNum(int answerNum, boolean correctness) {
		answers.get(answerNum - 1).setCorrectness(correctness);
		answersCheck();
	}

	@Override
	public String toString() {
		int counter = 1;
		StringBuffer sb = new StringBuffer("Answers: \n");
		for (int i = 0; i < answers.getCurrentSize(); i++) {
			if (!(answers.get(i).getAnswerText() == null)) {
				sb.append(counter++ + ") " + answers.get(i) + "\n");
			}
		}
		return super.toString() + sb.toString();
	}
	
	public String examAnswerToString() { //this method will be used to print an exam- it won't print the deleted answers (unlike stock printing).
		int counter = 1;
		StringBuffer sb = new StringBuffer("Answers: \n");
		for (int i = 0; i < answers.getCurrentSize(); i++) {
			if (!(answers.get(i).getAnswerText() == null || answers.get(i).getAnswerText().equals("non existing answer"))) {
				sb.append(counter++ + ") " + answers.get(i) + "\n");
			}
		}
		return super.toString() + sb.toString();
	}


	@Override
	public int answerCharsCounter() {
		int sum = 0;
		for (int i =0; i< answers.getCurrentSize(); i++) {
			if (answers.get(i) != null && !answers.get(i).getAnswerText().equals("non existing answer")) {
				sum = sum + answers.get(i).getAnswerText().length();
			}
		}
		return sum;
	}

	public int answersCounting() {
			int counter = 1;
			for (int i = 0; i < answers.getArr().length; i++) {
				if (answers.get(i) != null && !answers.get(i).getAnswerText().equals("non existing answer")) {
					counter++;
				}
			}
			return counter;
	}


}

