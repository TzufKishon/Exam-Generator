package exam_gen.model;

public class SelectionException extends GeneralExamGeneratorException{
	
	public SelectionException() {
		super("Please enter a number between 1 - 9.");
	}
}
