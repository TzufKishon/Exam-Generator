package exam_gen.listeners;

import java.util.ArrayList;

import exam_gen.model.Answer;
import exam_gen.model.GeneralExamGeneratorException;
import exam_gen.model.InvalidQuestionIDException;
import exam_gen.model.Set;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SingleSelectionModel;

public interface ExamGenUIEventsListeners {

//--------------tab0----------------
	public void saveStockUIEvent();

//--------------tab1----------------
	public void getStockFromModelUIEvent();

//--------------tab2----------------		
//ADD OPEN QUESTION
	public void getQNumFromModelUIEvent(String OQtext, String OAText);

//ADD AMERICAN QUESTION
	public void getAmericanQFromModelUIEvent(String AQText);

//ADD AMERICAN QUESTION
	public void getAmericanAFromModelUIEvent(String qNum, String aText, Boolean correctness);

//--------------tab3----------------	
	public void getNewQuestionFromModelUIEvent(String qNum, String qText);

//--------------tab4----------------
	public void getNewAFromModelUIEvent(String qNum, String aNum, String Atext, Boolean correctness, String type);

//--------------tab5----------------
	public void getDeleteAFromModelUIEvent(String qNum, String aNum, String type);

//--------------tab6----------------
	public void getOpenQFromModelUIEvent(String text);

	public void getQTextFromModelUIEvent(int qNum);

	public void getStockSizeFromModelUIEvent();

	public void addQuestionsToManualExamUIEvent(int selectedCounter, ArrayList<Integer> qNums);

	public void getAnswersToManualExamUIEvent(ArrayList<Integer> qNums);

	public void addAmericanAnswersUIEvent(ArrayList<Integer> qNums, ArrayList<ArrayList<Integer>> americanAnswers);

//--------------tab7----------------	
	public void getGenAutoFromModelUIEvent(String qAmount);

//--------------tab8----------------	
	public void cloneManualExamUIEvent();

	public void cloneAutoExamUIEvent();

}
