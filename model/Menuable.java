package exam_gen.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public interface Menuable {
	
void showStock(Manager stock, Scanner s) ;
void addQuestionToStock(Manager stock, Scanner s) ;
void updateQuestion(Manager stock, Scanner s) throws InvalidQuestionIDException, GeneralExamGeneratorException;
void updateAnswer(Manager stock, Scanner s) throws InvalidQuestionIDException, GeneralExamGeneratorException;
void deleteAnswer(Manager stock, Scanner s) throws InvalidQuestionIDException, GeneralExamGeneratorException;
Exam generateManualExam(Manager stock, Scanner s) throws FileNotFoundException, IOException, Exception;
Exam generateAutoExam(Manager stock, Scanner s) throws FileNotFoundException, IOException, InvalidQuestionIDException, GeneralExamGeneratorException, ClassNotFoundException;
void cloneExam(Manager stock, Scanner s, Exam savedManualExam, Exam autoManualExam) throws CloneNotSupportedException;
void exit(Manager stock, Scanner s) throws IOException;


}
