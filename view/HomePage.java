package exam_gen.view;

import java.io.Serializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomePage implements Serializable{
	VBox vbRoot = new VBox();

	public VBox getView() {
		Label lblWelcome = new Label(
				"Welcome to Exam Generator 2000! Feel free to use all tabs as you wish, GOOD LUCK! :)");
		Label lblText = new Label("Before you leave, if you would like to save changes please press the button!");
		vbRoot.setPadding(new Insets(10, 10, 10, 10));
		vbRoot.setSpacing(10);
		vbRoot.getChildren().addAll(lblWelcome, lblText);
		return vbRoot;
	}
	

}

