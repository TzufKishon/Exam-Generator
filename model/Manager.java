package exam_gen.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import application.Main;
import exam_gen.listeners.ExamGenEventsListener;

public class Manager implements Serializable {

	private ArrayList<Question> questionsStock;

	private ExamGenEventsListener listener;

	public ArrayList<Question> getQuestionsStock() {
		return questionsStock;
	}

	public Manager() {
		this.questionsStock = new ArrayList<Question>(5);
	}

	public void setQuestionsStock(Object object) {
		this.questionsStock = (ArrayList<Question>) object;
	}

	public void registerListener(ExamGenEventsListener listener) {
		this.listener = listener;
	}

	public int getStockSize() {
		return questionsStock.size();
	}

	Exam manualExam = null;
	Exam savedAutoExam = null;

	////--------------tab0----------------
	
		public void saveProgram(Manager stock) {
			String filePath = Main.file.getAbsolutePath();
			ObjectOutputStream outFile;
			try {
				outFile = new ObjectOutputStream(new FileOutputStream(filePath));
			} catch (FileNotFoundException e) {
				fireHomePageStatus(e.getMessage());
				return;
			} catch (IOException e) {
				fireHomePageStatus(e.getMessage());
				return;
			}
			try {
				outFile.writeObject(questionsStock);
				outFile.flush();
				outFile.close();
			} catch (IOException e) {
			fireHomePageStatus(e.getMessage());
			return;
			}
			
			fireHomePageStatus("The program saved successfully");

		
	}
	private void fireHomePageStatus(String status) {
		listener.returnedHomePageStatus(status);	
		
	}

	////--------------tab1----------------

	private void fireShowStockEvent(String stockText) {
		listener.returnedShowStockToControllerEvent(stockText);
	}

	public void sendStock() {
		fireShowStockEvent(this.toString());
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("STOCK'S QUESTIONS: \n \n");
		for (int i = 0; i < questionsStock.size(); i++) {
			sb.append(questionsStock.get(i).toString() + "\n");
		}
		return sb.toString();
	}

////--------------tab2----------------
	public void addQuestion(Question newQuestion) {
		for (int i = 0; i < questionsStock.size(); i++) {
			if (questionsStock.get(i) != null && questionsStock.get(i).equals(newQuestion)) {
				if (questionsStock.size() > 5) {
					fireAddOpenQEvent("This question already exist!");
				}
				return;
			}
		}
		questionsStock.add(questionsStock.size(), newQuestion);
		if (questionsStock.size() > 5) {
			fireAddOpenQEvent("Open question added successfully!");
		}
	}

//ADD OPEN QUESTION
	private void fireAddOpenQEvent(String status) {
		listener.returnedOpenQToControllerEvent(status);
	}

	public void createOpenQuestion(String qText, String aText) {
		if (qText.equals("") || aText.equals("")) {
			fireAddOpenQEvent("Please enter text to the empty field");
		} else {
			Question newOpenQuestion = new Open_Question(qText, this.questionsStock.size(), aText);
			addQuestion(newOpenQuestion);
		}
	}

//ADD AMERICAN QUESTION	
	private void fireAddAmericanQEvent(String status) {
		listener.returnedAmericanQToControllerEvent(status);
	}

	public void createAmericanQuestion(String qText) {
		if (qText.equals("")) {
			fireAddOpenQEvent("Please enter text to the empty field");
		} else {
			Question newAmericanQuestion = new American_Question(qText, this.questionsStock.size());
			addQuestion(newAmericanQuestion);
		}
	}

//ADD AMERICAN ANSWER
	public void addAmericanAnswer(String qNum, String Atext, Boolean correctness) {
		int qNumi = 0;
		try {
			qNumi = Integer.parseInt(qNum) - 1;
		} catch (Exception e) {
			fireAddAmericanAEvent("Error: " + e.getMessage() + ", please enter a number");
			return;
		}
		if (qNum.equals("") || Atext.equals("") || correctness == null) {
			fireAddAmericanAEvent("Please fill all empty field");
		} else {
			if (this.questionsStock.get(qNumi) instanceof American_Question) {
				for (int i = 0; i < ((American_Question) this.questionsStock.get(qNumi)).getAnswers()
						.getCurrentSize(); i++) {
					if (((American_Question) this.questionsStock.get(qNumi)).getAnswers().get(i).getAnswerText()
							.equals(Atext)) {
						fireAddAmericanAEvent("This answer already exist!");
						return;
					}
				}
				Answer americanAnswer = new Answer(Atext, correctness);
				this.questionsStock.get(qNumi).addAnswer(americanAnswer);
				fireAddAmericanAEvent("American answer added successfully to question number " + (qNumi + 1));
			} else
				fireAddAmericanAEvent("Qustion number " + (qNumi + 1) + " is not an american question");
		}
	}

	public void fireAddAmericanAEvent(String status) {
		listener.returnedAmericanAToControllerEvent(status);
	}

////--------------tab3----------------
	public void changeQuestionTextById(int id, String newText) {
		for (int i = 0; i < questionsStock.size(); i++) {
			if (questionsStock.get(i).getQuestionText().equals(newText)) {
				fireUpdateQEvent("This question already exist");
				return;
			}
		}
		for (int i = 0; i < questionsStock.size(); i++) {
			if (questionsStock.get(i).getId() == id) {
				questionsStock.get(i).setQuestionText(newText);
				fireUpdateQEvent("The Question text was changed successfully!");
				return;
			}
		}
	}

	public void upadteQText(String qNum, String qText) {
		int qNumi = 0;
		try {
			qNumi = Integer.parseInt(qNum);
		} catch (Exception e) {
			fireUpdateQEvent("Error: " + e.getMessage() + ", please enter a number");
			return;
		}
		if (qNumi <= 0 || qNumi > questionsStock.size() || questionsStock.get(qNumi - 1) == null) {
			fireUpdateQEvent("Error: non existing question");
			return;
		}
		for (int i = 0; i < questionsStock.size(); i++) {
			if (questionsStock.get(i).getQuestionText().equals(qText)) {
				fireUpdateQEvent("Error: this question already exists! ");
				return;
			}
		}
		if (qNum.equals("") || qText.equals("")) {
			fireUpdateQEvent("Please fill all empty field");
		} else {
			changeQuestionTextById(qNumi, qText);
		}
	}

	public void fireUpdateQEvent(String status) {
		listener.returnedUpdatedQToControllerEvent(status);
	}

////--------------tab4----------------	
	public void changeAnswer(String newText, Question question, int answerNum, boolean correctness) {
		if (question instanceof American_Question) {
			if (answerNum == 1 || answerNum == 2) {
				fireUpdateAEvent("sorry, this answer can't be changed.");
				return;
			}
			((American_Question) question).changeCorrectnessByAnwerNum(answerNum - 1, correctness);
			((American_Question) question).getAnswers().get(answerNum - 1).setAnswerText(newText);
		} else {
			((Open_Question) question).getAnswer().setAnswerText(newText);
		}
		fireUpdateAEvent("The answer was changed successfully!");
	}

	public void updateAnswer(String qNum, String aNum, String Atext, Boolean correctness, String type) {
		int qNumi = 0;
		int aNumi = 0;
		try {
			qNumi = Integer.parseInt(qNum);
			aNumi = Integer.parseInt(aNum);
		} catch (Exception e) {
			fireUpdateAEvent("Error: " + e.getMessage() + ", please enter a number");
			return;
		}
		try {
			this.isQuestionNumValid(qNumi);

		} catch (InputMismatchException e) {
			fireUpdateAEvent("Error: You were asked to enter a number, please try again");
			return;
		} catch (Exception e) {
			fireUpdateAEvent("Error: " + e.getMessage());
			return;
		}

		Question question;
		try {
			question = this.returnQuestionById(qNumi);
		} catch (InvalidQuestionIDException e) {
			fireUpdateAEvent("Error: " + e.getMessage());
			return;
		} catch (GeneralExamGeneratorException e) {
			fireUpdateAEvent("Error: " + e.getMessage());
			return;
		}
		if (type.equals("Open")) {
			if (question instanceof Open_Question) {
				this.changeAnswer(Atext, question, aNumi, correctness);
				return;
			} else {
				fireUpdateAEvent("Error: this question is an american question, please try again");
				return;
			}
		} else {
			if (question instanceof American_Question) {
				try {
					this.isAnswerNumValid(aNumi, (American_Question) question);
				} catch (InputMismatchException e) {
					fireUpdateAEvent("Error: You were asked to enter a number, please try again");
					return;
				} catch (Exception e) {
					fireUpdateAEvent("Error: " + e.getMessage());
					return;
				}
				this.changeAnswer(Atext, question, aNumi, correctness);
			} else {
				fireUpdateAEvent("Error: this question is an open question, please try again");
				return;
			}
		}
	}

	public void fireUpdateAEvent(String status) {
		listener.returnedUpdatedAToControllerEvent(status);
	}

////--------------tab5----------------		
	public void deleteAnswer(Question question, int answerNum) {
		if (question instanceof American_Question) {
			if (answerNum == 1 || answerNum == 2) {
				fireDeleteAEvent("sorry, this answer can't be changed.");
				return;
			}
			((American_Question) question).getAnswers().get(answerNum - 1).deleteAnswer();
			((American_Question) question).answersCheck();
		} else {
			((Open_Question) question).getAnswer().deleteAnswer();
		}
		fireDeleteAEvent("The answer was deleted successfully!");

	}

	public void deleteAnswerCreator(String qNum, String aNum, String type) {
		int qNumi = 0;
		int aNumi = 0;
		try {
			qNumi = Integer.parseInt(qNum);
			aNumi = Integer.parseInt(aNum);
		} catch (Exception e) {
			fireDeleteAEvent("Error: " + e.getMessage() + ", please enter a number");
		}
		try {
			this.isQuestionNumValid(qNumi);
		} catch (InputMismatchException e) {
			fireDeleteAEvent("Error: You were asked to enter a number");
			return;
		} catch (Exception e) {
			fireDeleteAEvent("Error: " + e.getMessage());
			return;
		}
		Question question;
		try {
			question = this.returnQuestionById(qNumi);
		} catch (InvalidQuestionIDException e) {
			fireDeleteAEvent("Error: " + e.getMessage());
			return;
		} catch (GeneralExamGeneratorException e) {
			fireDeleteAEvent("Error: " + e.getMessage());
			return;
		}
		if (type.equals("American")) {
			if (question instanceof American_Question) {

				try {
					this.isAnswerNumValid(aNumi, question);
					this.deleteAnswer(question, aNumi);
				} catch (InputMismatchException e) {
					fireDeleteAEvent("Error: You were asked to enter a number");
					return;
				} catch (Exception e) {
					fireDeleteAEvent("Error: " + e.getMessage());
					return;
				}
			} else {
				fireDeleteAEvent("Error: this question is an open question, please try again");
				return;
			}
		} else {
			if (question instanceof Open_Question) {
				this.deleteAnswer(question, aNumi);
			} else {
				fireDeleteAEvent("Error: this question is an American question, please try again");
				return;
			}
		}

	}

	public void fireDeleteAEvent(String status) {
		listener.returnedDeletedAToControllerEvent(status);
	}

////--------------tab6----------------

	// ADD AMERICAN ANSWER

	public void addAmericanAnswerToExam(ArrayList<Integer> qNums, ArrayList<ArrayList<Integer>> americanAnswers) {
		American_Question q = null;
		for (int i = 0; i < manualExam.getQuestions().size(); i++) {
			if (manualExam.getQuestions().get(i) instanceof American_Question) {
				q = (American_Question) returnQuestionByText(manualExam.getQuestions().get(i).getQuestionText());

				for (int j = 0; j < americanAnswers.get(i).size(); j++) {
					manualExam.getQuestions().get(i).addAnswer(q.getAnswers().get(americanAnswers.get(i).get(j) + 2));
				}

			}
		}
		File fileSave = new File("stockSave.dat");
		String fileSavePath = fileSave.getAbsolutePath();
		ObjectOutputStream outfileSave;
		try {
			outfileSave = new ObjectOutputStream(new FileOutputStream(fileSavePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			outfileSave.writeObject((Object) questionsStock);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			outfileSave.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			outfileSave.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}

		ObjectInputStream inFileSave;
		try {
			inFileSave = new ObjectInputStream(new FileInputStream(fileSave.getAbsoluteFile()));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		Manager stockSave = new Manager();
		try {
			stockSave.setQuestionsStock(inFileSave.readObject());
			;
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			inFileSave.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		Collections.sort(manualExam.getQuestions());
		fireAutoGenEvent(manualExam.toString());
		LocalDate toDay = LocalDate.now();
		DateTimeFormatter ymd = DateTimeFormatter.ofPattern("_yyyy_MM_dd");
		File filetxtExam = new File("exam" + toDay.format(ymd) + ".txt");
		try {
			filetxtExam.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		PrintWriter pwE;
		try {
			pwE = new PrintWriter(filetxtExam);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			stockSave.save(pwE, manualExam, "manualExam", "exam");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		pwE.close();
		File filetxtSolution = new File("solution" + toDay.format(ymd) + ".txt");
		try {
			filetxtSolution.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		PrintWriter pwS;
		try {
			pwS = new PrintWriter(filetxtSolution);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			stockSave.save(pwS, manualExam, "manualExam", "solution");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		pwS.close();
		fireReturnManualExam(manualExam.toString());
	}

	private void fireReturnManualExam(String manualExam) {
		listener.ReturnedReadyManualExam(manualExam);

	}

	public Question returnQuestionByText(String Qtext) {
		Question q = null;
		for (int i = 0; i < questionsStock.size(); i++) {
			if (questionsStock.get(i).getQuestionText().equals(Qtext))
				q = questionsStock.get(i);
		}
		return q;
	}

	public void returnQTextToUI(int qNum) {
		String qText = questionsStock.get(qNum).getQuestionText();
		fireGetQTextEvent(qText);

	}

	public void fireGetQTextEvent(String qText) {
		listener.returnedQTextToControllerEvent(qText);
	}

	public void returnStockSizeToUI() {
		int size = this.getStockSize();
		fireGetStockSizeEvent(size);
	}

	private void fireGetStockSizeEvent(int size) {
		listener.returnedStockSizeToUI(size);
	}

	private void fireManualEventStatus(String status) {
		listener.returnedManualExamStatus(status);
	}

	public Exam manualQuestionsPicker(int numOfQuestions, ArrayList<Integer> questionsIds)
			throws InvalidQuestionIDException, GeneralExamGeneratorException {
		Exam exam = new Exam(numOfQuestions);
		for (int i = 0; i < numOfQuestions; i++) {
			exam.getQuestions().add(i, returnQuestionById(questionsIds.get(i)));
		}
		return exam;
	}

	public void manualQuestionsCreator(int selectedCounter, ArrayList<Integer> allQuestions) {
		try {
			checkSelectedCounter(selectedCounter);
			manualExam = new Exam(allQuestions.size());
			ArrayList<Question> examQuestions = new ArrayList<Question>();
			for (int i = 0; i < allQuestions.size(); i++) {
				Question q = returnQuestionById(allQuestions.get(i));
				if (q instanceof Open_Question) {
					manualExam.addQuestion(q);
				} else {
					Question newQ = new American_Question(q.getQuestionText(), i);
					manualExam.addQuestion(newQ);
				}

			}
			fireManualEventStatus("questions added successfully");
		} catch (InvalidQuestionIDException e) {
			fireManualEventStatus(e.getMessage());
			return;
		} catch (GeneralExamGeneratorException e) {
			fireManualEventStatus(e.getMessage());
			return;
		} catch (Exception e) {
			fireManualEventStatus(e.getMessage());
			return;
		} 

	}
	
	public void checkSelectedCounter (int counter) throws Exception{
		if (counter ==0) {
			throw new Exception("please pick questions");
		}
		
	}

	public void returnAnswersToUI(ArrayList<Integer> qNums) {
		String qText = "";
		Set<Answer> answers = new <Answer>Set();
		for (int i = 0; i < qNums.size(); i++) {
			try {
				Question q = returnQuestionById(qNums.get(i));
				if (q instanceof American_Question) {
					qText = q.getQuestionText();
					answers = ((American_Question) q).getAnswers();
				}
				;
			} catch (InvalidQuestionIDException e) {
				e.printStackTrace();
				return;
			} catch (GeneralExamGeneratorException e) {
				e.printStackTrace();
				return;
			}
			fireManualAnswers(qText, answers);
			qText = "";
			answers = new <Answer>Set();

		}
	}

	private void fireManualAnswers(String qText, Set<Answer> answers) {
		listener.returnedManualExamAnswers(qText, answers);
	}

////--------------tab7----------------
	public void generateAutoExamCreator(String qAmount) {
		int qAmounti = 0;
		try {
			qAmounti = Integer.parseInt(qAmount);
		} catch (Exception e) {
			fireAutoGenStatusEvent("Error: " + e.getMessage() + ", please enter a number");
			return;
		}
		File fileSave = new File("stockSave.dat");
		String fileSavePath = fileSave.getAbsolutePath();
		ObjectOutputStream outfileSave;
		try {
			outfileSave = new ObjectOutputStream(new FileOutputStream(fileSavePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			outfileSave.writeObject((Object) questionsStock);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			outfileSave.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			outfileSave.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}

		ObjectInputStream inFileSave;
		try {
			inFileSave = new ObjectInputStream(new FileInputStream(fileSave.getAbsoluteFile()));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		Manager stockSave = new Manager();
		try {
			stockSave.setQuestionsStock(inFileSave.readObject());
			;
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			inFileSave.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			stockSave.isQuestionNumValid(qAmounti);
		} catch (InputMismatchException e) {
			fireAutoGenStatusEvent("error: You were asked to enter a number, please try again");
			return;
		} catch (Exception e) {
			fireAutoGenStatusEvent("error: " + e.getMessage());
			return;
		}
		Exam autoExam;
		try {
			autoExam = stockSave.randomQuestionsPicker(qAmounti);
		} catch (InvalidQuestionIDException e1) {
			fireAutoGenStatusEvent(e1.getMessage());
			return;
		} catch (GeneralExamGeneratorException e1) {
			fireAutoGenStatusEvent(e1.getMessage());
			return;
		}
		ArrayList<Integer> answers = new ArrayList(4);
		int answersAmount = 0;
		for (int i = 0; i < qAmounti; i++) {
			if (autoExam.getQuestions().get(i) instanceof American_Question) {
				answersAmount = ((American_Question) autoExam.getQuestions().get(i)).getAnswers().getCurrentSize();
				answers = stockSave.randomAnswersGenerator(answersAmount,
						(American_Question) autoExam.getQuestions().get(i));
				stockSave.answerPicker(autoExam.getQuestions().get(i), answers, answers.size());
			}
		}
		Collections.sort(autoExam.getQuestions());
		fireAutoGenEvent(autoExam.toString());
		LocalDate toDay = LocalDate.now();
		DateTimeFormatter ymd = DateTimeFormatter.ofPattern("_yyyy_MM_dd");
		File filetxtExam = new File("exam" + toDay.format(ymd) + ".txt");
		try {
			filetxtExam.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		PrintWriter pwE;
		try {
			pwE = new PrintWriter(filetxtExam);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			stockSave.save(pwE, autoExam, "autoExam", "exam");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		pwE.close();
		File filetxtSolution = new File("solution" + toDay.format(ymd) + ".txt");
		try {
			filetxtSolution.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		PrintWriter pwS;
		try {
			pwS = new PrintWriter(filetxtSolution);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			stockSave.save(pwS, autoExam, "autoExam", "solution");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		pwS.close();

		savedAutoExam = autoExam;

	}

	public void fireAutoGenStatusEvent(String status) {
		listener.returnedAutoGenStatusToControllerEvent(status);
	}

	public void fireAutoGenEvent(String status) {
		listener.returnedAutoGenToControllerEvent(status);
	}

	public Question returnQuestionById(int id) throws InvalidQuestionIDException, GeneralExamGeneratorException {
		for (int i = 0; i < questionsStock.size(); i++) {
			if (questionsStock.get(i).getId() == id) {
				return questionsStock.get(i);
			}
		}
		throw new InvalidQuestionIDException();
	}

	public Question answerPicker(Question question, ArrayList<Integer> answersIds, int answersAmount) {
		int counter = 0;
		if (question instanceof American_Question) {
			for (int i = 2; i < ((American_Question) question).getAnswers().getCurrentSize(); i++) {
				for (int j = 0; j < answersAmount; j++) {
					if (i + 1 == answersIds.get(j)) {
						counter++;
					}
				}
				if (counter == 0 && ((American_Question) question).getAnswers().get(i) != null) {
					((American_Question) question).getAnswers().get(i).deleteAnswer();
				}
				counter = 0;
			}
			((American_Question) question).answersCheck();
		}
		return question;
	}

	public ArrayList<Integer> randomAnswersGenerator(int numOfAnswers, American_Question question) {
		ArrayList<Integer> randomAnswers = new ArrayList(4);
		randomAnswers.add(0, 1);
		randomAnswers.add(1, 2);
		int randomID;

		for (int i = 2; i < 4; i++) {
			randomID = (int) ((int) 3 + (Math.random() * (numOfAnswers - 2)));
			while (checkIfAlreadyWasPicked(randomAnswers, randomID) == true) {
				randomID = (int) ((int) 3 + (Math.random() * (numOfAnswers - 2)));
			}
			randomAnswers.add(i, randomID);
		}
		return randomAnswers;
	}

	public Exam randomQuestionsPicker(int numOfQuestions)
			throws InvalidQuestionIDException, GeneralExamGeneratorException {
		Exam exam = new Exam(numOfQuestions);
		int randomID = -1;
		ArrayList<Integer> randomIDs = new ArrayList(numOfQuestions);
		for (int i = 0; i < numOfQuestions; i++) {
			randomID = (int) ((int) 1 + (Math.random() * questionsStock.size()));

			while (checkIfAlreadyWasPicked(randomIDs, randomID) == true
					|| checkIfAmericanHas4Answers(randomID) == false) {
				randomID = (int) ((int) 1 + (Math.random() * questionsStock.size()));
			}
			exam.getQuestions().add(i, returnQuestionById(randomID));
			randomIDs.add(i, randomID);
		}
		return exam;
	}

	public void save(PrintWriter pw, Exam exam, String examType, String examOrSolution) throws FileNotFoundException {
		if (examOrSolution == "exam") {
			pw.println(examType + " QUESTIONS: \n \n");
			for (int i = 0; i < exam.getQuestions().size(); i++) {
				if (exam.getQuestions().get(i) instanceof Open_Question) {
					pw.println(
							"question number " + (i + 1) + ": " + exam.getQuestions().get(i).getQuestionText() + "\n");
				}
				if (exam.getQuestions().get(i) instanceof American_Question) {
					pw.println("question number " + (i + 1) + ": " + exam.getQuestions().get(i).getQuestionText());
					int counter = 0;
					for (int j = 0; j < ((American_Question) exam.getQuestions().get(i)).getAnswers()
							.getCurrentSize(); j++) {
						if (!(((American_Question) exam.getQuestions().get(i)).getAnswers().get(j)
								.getAnswerText() == null
								|| ((American_Question) exam.getQuestions().get(i)).getAnswers().get(j).getAnswerText()
										.equals("non existing answer"))) {
							pw.println(++counter + ") " + ((American_Question) exam.getQuestions().get(i)).getAnswers()
									.get(j).getAnswerText());
						}
					}
					pw.println();
				}
			}
		}
		if (examOrSolution == "solution") {
			pw.println(examType + " QUESTIONS + solutions: \n \n");
			for (int i = 0; i < exam.getQuestions().size(); i++) {
				if (exam.getQuestions().get(i) instanceof American_Question) {
					pw.println("question number " + (i + 1) + ": " + exam.getQuestions().get(i).getQuestionText());
					int counter = 0;
					for (int j = 0; j < ((American_Question) exam.getQuestions().get(i)).getAnswers()
							.getCurrentSize(); j++) {
						counter++;
						if ((!(((American_Question) exam.getQuestions().get(i)).getAnswers().get(j)
								.getAnswerText() == null
								|| ((American_Question) exam.getQuestions().get(i)).getAnswers().get(j).getAnswerText()
										.equals("non existing answer")))
								&& ((American_Question) exam.getQuestions().get(i)).getAnswers().get(j)
										.getCorrectness() == true) {
							pw.println("Answer: number " + counter + ") "
									+ ((American_Question) exam.getQuestions().get(i)).getAnswers().get(j)
											.getAnswerText());
						}
					}
					pw.println();
				}
				if (exam.getQuestions().get(i) instanceof Open_Question) {
					pw.println("question number " + (i + 1) + ": " + exam.getQuestions().get(i).getQuestionText());
					pw.println("Answer: " + ((Open_Question) exam.getQuestions().get(i)).getAnswer().getAnswerText()
							+ "\n");
				}
			}
		}
	}

//---------------tab 8 -------------	
	public Exam cloningExam(Exam e) throws CloneNotSupportedException {
		Exam clonedExam = null;
		clonedExam = e.clone();
		return clonedExam;
	}

	public void cloneManual() {
		Exam clonedManual = new Exam(0);
		try {
			cloningExam(manualExam);
		} catch (CloneNotSupportedException e) {
			fireCloneStatus("Before you clone exam, you need to make one!");
			return;
		}
		 catch (NullPointerException e) {
				fireCloneStatus("Before you clone exam, you need to make one!");
				return;
			}
		fireCloneStatus("Exam cloned successfully");
	}

	public void cloneAuto() {
		Exam clonedManual = new Exam(0);
		try {
			cloningExam(savedAutoExam);
		} catch (CloneNotSupportedException e) {
			fireCloneStatus("Before you clone exam, you need to make one!");
			return;
		}
	 catch (NullPointerException e) {
		fireCloneStatus("Before you clone exam, you need to make one!");
		return;
	}
		
		fireCloneStatus("Exam cloned successfully");
	}

	private void fireCloneStatus(String status) {
		listener.returnedCloneStatus(status);

	}

	// ---------------exceptions-------------
	public void isAnswerNumValid(int answerNum, Question question) throws Exception {
		if (answerNum == 1 || answerNum == 2) {
			throw new Exception(
					"The answer number you entered is not valid, there is no need to pick answers 1,2! please try again");
		}
		if (answerNum < 1 || answerNum > ((American_Question) question).getAnswers().getCurrentSize()) {
			throw new Exception("The answer number you entered is not valid, please try again");
		}
	}

	public void isQuestionNumValid(int questionId) throws Exception {
		if (questionId < 1 || questionId > questionsStock.size()) {
			throw new Exception("The question number you entered is not valid, please choose again from the stock");
		}
		if (questionsStock.get(questionId - 1) == null
				|| questionsStock.get(questionId - 1).getQuestionText().equals("")) {
			throw new Exception(
					"The question number you entered is not valid, this question is empty, please choose again");
		}
	}

	public void isNumOfQuestionsValid(int numOfQuestions) throws Exception {
		if (numOfQuestions < 0 || numOfQuestions > questionsStock.size()) {
			throw new Exception(
					"The number you entered is not valid, please choose again acording to the stock's corrent number of questions");
		}
	}

	public void isQuestionTypeValid(int questionType) throws InvalidQuestionTypeException {
		if (questionType != 1 && questionType != 2) {
			throw new InvalidQuestionTypeException();
		}
	}

	public void isAnswersAmountValid(int answersAmount) throws InvalidAnswerAmountException {
		if (answersAmount < 2) {
			throw new InvalidAnswerAmountException("Please enter more than 1 answers");
		}
		if (answersAmount > 8) {
			throw new InvalidAnswerAmountException("Please enter less than 9 answers");
		}
	}

	public void selectionCheck(int selection) throws SelectionException {
		if (selection < 1 || selection > 9) {
			throw new SelectionException();
		}
	}

	public boolean checkIfAmericanHas4Answers(int randomID)
			throws InvalidQuestionIDException, GeneralExamGeneratorException {
		if (returnQuestionById(randomID) instanceof American_Question) {
			if (((American_Question) returnQuestionById(randomID)).getAnswers().getCurrentSize() >= 4) {
				return true;
			}
			return false;
		} else
			return true;
	}
	
	public boolean checkIfAlreadyWasPicked(ArrayList<Integer> randomAnswers, int randomID) {
		for (int i = 0; i < randomAnswers.size(); i++) {
			if (randomAnswers.get(i) == randomID) {
				return true;
			}
		}
		return false;
	}



}
