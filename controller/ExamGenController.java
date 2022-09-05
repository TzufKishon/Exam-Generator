package exam_gen.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import exam_gen.listeners.ExamGenEventsListener;
import exam_gen.listeners.ExamGenUIEventsListeners;
import exam_gen.model.Answer;
import exam_gen.model.ConvertFile;
import exam_gen.model.GeneralExamGeneratorException;
import exam_gen.model.InvalidQuestionIDException;
import exam_gen.model.Manager;
import exam_gen.model.Set;
import exam_gen.view.AbstractView;
import exam_gen.view.MainPageView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SingleSelectionModel;

public class ExamGenController implements ExamGenUIEventsListeners, ExamGenEventsListener, Serializable{
	
	private Manager stock;
	private AbstractView mainPageView;

//constructor
	public ExamGenController(Manager stock, AbstractView mainPageView) {
		super();
		this.stock = stock;
		this.mainPageView = mainPageView;
		 stock.registerListener(this);
		 mainPageView.registerListener(this);
	}
	
//--------------tab0----------------	
	@Override
	public void saveStockUIEvent() {
//			ConvertFile.exit(stock);
			stock.saveProgram(stock);
		
	}
	
	@Override
	public void returnedHomePageStatus(String status) {
		mainPageView.returnHomePageStatusToUI(status);
		
	}


//--------------tab1----------------
	@Override
	public void getStockFromModelUIEvent() {
		stock.sendStock();	
	}

	@Override
	public void returnedShowStockToControllerEvent(String stockText) {
		mainPageView.addStockToUI(stockText);
		
	}
	
//--------------tab2----------------
//ADD OPEN QUESTION	
	@Override
	public void getQNumFromModelUIEvent(String OQtext, String OAText) {
		stock.createOpenQuestion(OQtext, OAText);
	}

	@Override
	public void returnedOpenQToControllerEvent(String status) {
		mainPageView.returnOpenQStatusToUI(status);
}
//ADD AMERICAN QUESTION
	@Override
	public void getAmericanQFromModelUIEvent(String AQText) {
		stock.createAmericanQuestion(AQText);
	}

@Override
public void returnedAmericanQToControllerEvent(String status) {
	mainPageView.returnAmericanQStatusToUI(status);
}
//ADD AMERICAN ANSWER
@Override
public void getAmericanAFromModelUIEvent(String qNum, String aText, Boolean correctness) {
stock.addAmericanAnswer(qNum, aText, correctness);	
}

@Override
public void returnedAmericanAToControllerEvent(String status) {
	mainPageView.returnAmericanAStatusToUI(status);
	
}

//--------------tab3----------------
@Override
public void getNewQuestionFromModelUIEvent(String qNum, String qText) {
	stock.upadteQText(qNum,qText);
	
}

@Override
public void returnedUpdatedQToControllerEvent(String status) {
	mainPageView.returnUpdateQStatusToUI(status);	
}

//--------------tab4----------------
@Override
public void getNewAFromModelUIEvent(String qNum, String aNum, String Atext, Boolean correctness, String type) {
	stock.updateAnswer(qNum, aNum, Atext, correctness, type);
	
}

@Override
public void returnedUpdatedAToControllerEvent(String status) {
	mainPageView.returnUpdatedAStatusToUI(status);	
}

//--------------tab5----------------
@Override
public void getDeleteAFromModelUIEvent(String qNum, String aNum, String type) {
stock.deleteAnswerCreator(qNum, aNum, type);
}

@Override
public void returnedDeletedAToControllerEvent(String status) {
	mainPageView.returnDeletedAStatusToUI(status);		
}


//--------------tab6----------------

@Override
public void getOpenQFromModelUIEvent(String text) {
	// TODO Auto-generated method stub
}

@Override
public void getQTextFromModelUIEvent(int qNum) {
	stock.returnQTextToUI(qNum);
}


@Override
public void returnedQTextToControllerEvent(String qText) {
		mainPageView.returnedQTextToUI(qText);		
	}
	
@Override
public void getStockSizeFromModelUIEvent() {
	stock.returnStockSizeToUI();	
}

@Override
public void returnedStockSizeToUI(int size) {
	mainPageView.returnedStockSizeToUI(size);	
}

@Override
public void addQuestionsToManualExamUIEvent(int selectedCounter, ArrayList<Integer> allQuestions) {
		stock.manualQuestionsCreator(selectedCounter,allQuestions);
}

@Override
public void returnedManualExamStatus(String status) {
	mainPageView.returnedManualExamStatus(status);
	
}

@Override
public void getAnswersToManualExamUIEvent(ArrayList<Integer> qNums) {
	stock.returnAnswersToUI(qNums);
	
}

@Override
public void returnedManualExamAnswers(String qText, Set<Answer> answers) {
	mainPageView.showAmericanAnswers(qText, answers);
}

@Override
public void addAmericanAnswersUIEvent(ArrayList<Integer> qNums, ArrayList<ArrayList<Integer>> americanAnswers) {
	stock.addAmericanAnswerToExam(qNums, americanAnswers);
}

@Override
public void ReturnedReadyManualExam(String manualExam) {
	mainPageView.showReadyManualExam(manualExam);
	
}

//--------------tab7----------------
@Override
public void getGenAutoFromModelUIEvent(String qAmount) {
	stock.generateAutoExamCreator(qAmount);
}

@Override
public void returnedAutoGenStatusToControllerEvent(String status) {
	mainPageView.returnAutoGenStatusToUI(status);
}

@Override
public void returnedAutoGenToControllerEvent(String autoExam) {

	mainPageView.returnAutoGenToUI(autoExam.toString());	
}

//--------------tab8----------------

@Override
public void cloneManualExamUIEvent() {
	stock.cloneManual();
	
}

@Override
public void returnedCloneStatus(String status) {
	mainPageView.returnCloneStatusToUI(status);
	
}

@Override
public void cloneAutoExamUIEvent() {
	stock.cloneAuto();
	
}


















	
}
