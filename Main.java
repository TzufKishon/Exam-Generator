package application;

import java.io.File;
import java.io.Serializable;
import exam_gen.controller.ExamGenController;
import exam_gen.model.ConvertFile;
import exam_gen.model.Manager;
import exam_gen.view.AbstractView;
import exam_gen.view.MainPageView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application implements Serializable  {
	public static File file = new File("stock.dat");
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Manager model = ConvertFile.init();
		AbstractView MainPageView = new MainPageView(new Stage());
		ExamGenController MainController = new ExamGenController(model, MainPageView);
	}
}
