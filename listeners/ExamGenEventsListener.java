package exam_gen.listeners;

import exam_gen.model.Answer;
import exam_gen.model.Set;

public interface ExamGenEventsListener {

//--------------tab0----------------	
	void returnedHomePageStatus(String status);
	
//--------------tab1----------------
	void returnedShowStockToControllerEvent(String stockText);

//--------------tab2----------------
//ADD OPEN QUESTION
	void returnedOpenQToControllerEvent(String status);

//ADD AMERICAN QUESTION
	void returnedAmericanQToControllerEvent(String status);

//ADD AMERICAN ANSWER
	void returnedAmericanAToControllerEvent(String status);

//--------------tab3----------------
	void returnedUpdatedQToControllerEvent(String status);

//--------------tab4----------------
	void returnedUpdatedAToControllerEvent(String status);

//--------------tab5----------------
	void returnedDeletedAToControllerEvent(String status);
	
//--------------tab6----------------	
	void returnedQTextToControllerEvent(String status);
	
	void returnedStockSizeToUI(int size);
	
	void returnedManualExamStatus(String status);
	
	void returnedManualExamAnswers(String qText, Set<Answer> answers);
	
	void ReturnedReadyManualExam(String manualExam);


//--------------tab7----------------
	void returnedAutoGenStatusToControllerEvent(String status);

	void returnedAutoGenToControllerEvent(String autoExam);
	
//--------------tab8----------------

	void returnedCloneStatus(String status);

	







	

	
	


}
