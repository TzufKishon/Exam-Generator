package exam_gen.view;

import exam_gen.listeners.ExamGenUIEventsListeners;
import exam_gen.model.Answer;
import exam_gen.model.Set;
import javafx.scene.control.Tab;

public interface AbstractView {
	public void registerListener(ExamGenUIEventsListeners newListener);

//--------------tab0----------------
	public void createHomePage(Tab tab);
	public void returnHomePageStatusToUI(String status);

//--------------tab1----------------
	public void addStockToUI(String stockText);
	
//--------------tab2----------------
//ADD OPEN QUESTION
	public void returnOpenQStatusToUI(String status);
//ADD AMERICAN QUESTION
	public void returnAmericanQStatusToUI(String status);
//ADD AMERICAN ANSWER
	public void returnAmericanAStatusToUI(String status);

//--------------tab3----------------
	public void returnUpdateQStatusToUI(String status);
	
//--------------tab4----------------
	public void returnUpdatedAStatusToUI(String status);

//--------------tab5----------------
	public void returnDeletedAStatusToUI(String status);
	
//--------------tab6----------------	
	public void returnedQTextToUI(String qText);

	public void returnedStockSizeToUI(int size);
	
	void showAmericanAnswers(String qText, Set<Answer> answers);
	
	public void returnedManualExamStatus(String status);
	
	public void showReadyManualExam(String manualExam);

//--------------tab7----------------
	public void returnAutoGenStatusToUI(String status);

	public void returnAutoGenToUI(String autoExam);

//--------------tab8----------------

	public void returnCloneStatusToUI(String status);



	




}
