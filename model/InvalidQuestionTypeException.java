package exam_gen.model;

public class InvalidQuestionTypeException extends GeneralExamGeneratorException {

	public InvalidQuestionTypeException(String msg) {
		super(msg);
	}
	
	public InvalidQuestionTypeException() {
	super("The number you entered is not a valid, please enter 1 or 2");
	}
}
