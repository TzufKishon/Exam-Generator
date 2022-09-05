package exam_gen.model;

public class InvalidQuestionIDException extends GeneralExamGeneratorException {

	public InvalidQuestionIDException(String msg) {
		super(msg);
	}
	
	public InvalidQuestionIDException() {
	super("Invalid question ID!");
	}
}
