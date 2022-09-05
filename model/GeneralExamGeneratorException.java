package exam_gen.model;

public class GeneralExamGeneratorException extends Exception {
	
	public GeneralExamGeneratorException(String msg) {
		super(msg);
	}
	
	public GeneralExamGeneratorException() {
	super("Error accoured, please call support! ");
	}
}
