package exam_gen.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.Serializable;
import java.util.ArrayList;
import exam_gen.listeners.ExamGenUIEventsListeners;
import exam_gen.model.Answer;
import exam_gen.model.Set;

public class MainPageView implements AbstractView, Serializable {
	private ExamGenUIEventsListeners listener;
	VBox vbRoot = new VBox();
	TabPane tabs = new TabPane();
	HomePage homePageView = new HomePage();
//constructor
	public MainPageView(Stage theStage) {
		theStage.setTitle("Exam Generator 2000");
		vbRoot.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		tabs.setPadding(new Insets(8));
		this.createTabs();
		theStage.setScene(new Scene(vbRoot, 950, 400));
		theStage.setMaximized(true);
		theStage.show();
	}
	public void registerListener(ExamGenUIEventsListeners newListener) {
		this.listener = newListener;
	}
	public VBox createTabs() {
		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		Tab tab0 = new Tab("Home Page");
		Tab tab1 = new Tab("Show Stock");
		Tab tab2 = new Tab("Add Question", new Label(""));
		Tab tab3 = new Tab("Update Question", new Label(""));
		Tab tab4 = new Tab("Update Answer", new Label(""));
		Tab tab5 = new Tab("Delete Answer", new Label(""));
		Tab tab6 = new Tab("Generate Manual Exam", new Label(""));
		Tab tab7 = new Tab("Generate Automatic Exam", new Label(""));
		Tab tab8 = new Tab("Clone Exam", new Label(""));
		tabs.getTabs().add(tab0);
		tabs.getTabs().add(tab1);
		tabs.getTabs().add(tab2);
		tabs.getTabs().add(tab3);
		tabs.getTabs().add(tab4);
		tabs.getTabs().add(tab5);
		tabs.getTabs().add(tab6);
		tabs.getTabs().add(tab7);
		tabs.getTabs().add(tab8);
		vbRoot.getChildren().add(tabs);
//--------------tab0----------------
		this.createHomePage(tab0);
//--------------tab1 EVENTS----------------
		tab1.setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event action) {
				listener.getStockFromModelUIEvent();
			}
		});
		createTab2(tab2);
		createTab3(tab3);
		createTab4(tab4);
		createTab5(tab5);
		createTab6(tab6);
		createTab7(tab7);
		createTab8(tab8);
		return vbRoot;
	}
//--------------tab0----------------	
	public void createHomePage(Tab tab) {
		VBox vbHome = homePageView.getView();
		
		Button btnSave = new Button("SAVE STOCK CHANGES");
		vbHome.getChildren().add(btnSave);
		EventHandler<ActionEvent> save = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.saveStockUIEvent();
			}
		};
		btnSave.setOnAction(save);
		tab.setContent(vbHome);
	}
	@Override
	public void returnHomePageStatusToUI(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
//--------------tab1----------------	
	public void addStockToUI(String stockText) {
		Tab stockTab = tabs.getSelectionModel().getSelectedItem();
		TextArea textArea = new TextArea(stockText);
		stockTab.setContent(textArea);
	}
//--------------tab2----------------
	public void createTab2(Tab tab) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(15);
/////OPEN QUESTION LAYOUT
		VBox vbAddOpenQ = new VBox();
		Label lblOpenQ = new Label("Add open question");
		Label lblQText = new Label("Question text:");
		TextField txfOpenQText = new TextField();
		Label lblOAText = new Label("Answer text:");
		TextField txfopenAText = new TextField();
		txfOpenQText.setPrefWidth(300);
		Button btnaddOpenQ = new Button("ADD");
		vbAddOpenQ.getChildren().addAll(lblOpenQ, lblQText, txfOpenQText, lblOAText, txfopenAText, btnaddOpenQ);
		EventHandler<ActionEvent> addOpenQuestion = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getQNumFromModelUIEvent(txfOpenQText.getText(), txfopenAText.getText());
			}
		};
		btnaddOpenQ.setOnAction(addOpenQuestion);
/////AMERICAN QUESTION LAYOUT	
		VBox vbAddAmericanQ = new VBox();
		Label lblAmericanQ = new Label("Add American question");
		Label lblAmericanQText = new Label("Question text:");
		TextField txfAmericanQText = new TextField();
		txfAmericanQText.setPrefWidth(300);
		Button btnaddAmericanQ = new Button("ADD");
		vbAddAmericanQ.getChildren().addAll(lblAmericanQ, lblAmericanQText, txfAmericanQText, btnaddAmericanQ);
		EventHandler<ActionEvent> addAmericanQuestion = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getAmericanQFromModelUIEvent(txfAmericanQText.getText());
			}
		};
		btnaddAmericanQ.setOnAction(addAmericanQuestion);
/////AMERICAN ANSWER LAYOUT		
		VBox vbAddAmericanA = new VBox();
		Label lblAmericanA = new Label("Add answer to american question");
		Label lblAQNum = new Label("Question number:");
		TextField txfAQNum = new TextField();
		Label lblAmericanAText = new Label("Answer text:");
		TextField txfAmericanAText = new TextField();
		Label lblAmericanACorrect = new Label("Correctness:");
		ObservableList<Boolean> correctness = FXCollections.<Boolean>observableArrayList(true, false);
		ComboBox<Boolean> cbCorrectness = new ComboBox<Boolean>(correctness);
		Button btnaddAmericanA = new Button("ADD");
		vbAddAmericanA.getChildren().addAll(lblAmericanA, lblAQNum, txfAQNum, lblAmericanAText, txfAmericanAText,
				lblAmericanACorrect, cbCorrectness, btnaddAmericanA);
		hbox.getChildren().addAll(vbAddOpenQ, vbAddAmericanQ, vbAddAmericanA);
		EventHandler<ActionEvent> addAmericanAnswer = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getAmericanAFromModelUIEvent(txfAQNum.getText(), txfAmericanAText.getText(),
						cbCorrectness.getValue());
			}
		};
		btnaddAmericanA.setOnAction(addAmericanAnswer);
		tab.setContent(hbox);
	}
	@Override
	public void returnOpenQStatusToUI(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
	@Override
	public void returnAmericanQStatusToUI(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
	@Override
	public void returnAmericanAStatusToUI(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
//--------------tab3----------------	
	public void createTab3(Tab tab) {
		VBox vbUpdateQText = new VBox();
		Label lblUpdateQ = new Label("Update Question: ");
		Label lblUpdateQNum = new Label("Question number: ");
		TextField txfUpdateQNum = new TextField();
		Label lblUpdateQText = new Label("Question text: ");
		TextField txfUpdateText = new TextField();
		Button btnUpdateQ = new Button("UPDATE");
		vbUpdateQText.getChildren().addAll(lblUpdateQ, lblUpdateQNum, txfUpdateQNum, lblUpdateQText, txfUpdateText,
				btnUpdateQ);
		EventHandler<ActionEvent> updateQuestionText = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getNewQuestionFromModelUIEvent(txfUpdateQNum.getText(), txfUpdateText.getText());
			}
		};
		btnUpdateQ.setOnAction(updateQuestionText);
		tab.setContent(vbUpdateQText);
	}
	@Override
	public void returnUpdateQStatusToUI(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
//--------------tab4----------------
	public void createTab4(Tab tab) {
		HBox hbUpdateA = new HBox();
		hbUpdateA.setPadding(new Insets(10));
		hbUpdateA.setSpacing(15);
//OPEN ANSWER LAYOUT
		VBox vbUpdateOpenA = new VBox();
		Label lblUpdateOpenA = new Label("Update Open Question's Answer:");
		Label lblOQNum = new Label("Question number:");
		TextField txfOQNum = new TextField();
		Label lblOAText = new Label("New answer text:");
		TextField txfOAText = new TextField();
		Button btnUpateOA = new Button("UPDATE");
		vbUpdateOpenA.getChildren().addAll(lblUpdateOpenA, lblOQNum, txfOQNum, lblOAText, txfOAText, btnUpateOA);
		EventHandler<ActionEvent> updateOA = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getNewAFromModelUIEvent(txfOQNum.getText(), "0", txfOAText.getText(), true, "Open");
			}
		};
		btnUpateOA.setOnAction(updateOA);
//AMERICAN ANSWER LAYOUT
		VBox vbUpdateAA = new VBox();
		Label lblUpdateAA = new Label("Update American Question's Answer:");
		Label lblAQNum = new Label("Question number:");
		TextField txfAQNum = new TextField();
		Label lblAANum = new Label("Answer number:");
		TextField txfAANum = new TextField();
		Label lblAAText = new Label("New answer text:");
		TextField txfAAText = new TextField();
		Label lblAACorrectness = new Label("Correctness:");
		ObservableList<Boolean> correctness = FXCollections.<Boolean>observableArrayList(true, false);
		ComboBox<Boolean> cbACorrectness = new ComboBox<Boolean>(correctness);
		Button btnUpateAA = new Button("UPDATE");
		vbUpdateAA.getChildren().addAll(lblUpdateAA, lblAQNum, txfAQNum, lblAANum, txfAANum, lblAAText, txfAAText,
				lblAACorrectness, cbACorrectness, btnUpateAA);
		EventHandler<ActionEvent> updateAA = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getNewAFromModelUIEvent(txfAQNum.getText(), txfAANum.getText(), txfAAText.getText(),
						cbACorrectness.getValue(), "American");
			}
		};
		btnUpateAA.setOnAction(updateAA);
		hbUpdateA.getChildren().addAll(vbUpdateOpenA, vbUpdateAA);
		tab.setContent(hbUpdateA);
	}
	@Override
	public void returnUpdatedAStatusToUI(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
//--------------tab5----------------
	public void createTab5(Tab tab) {
		HBox hbDeleteA = new HBox();
		hbDeleteA.setPadding(new Insets(10));
		hbDeleteA.setSpacing(15);

		// OPEN ANSWER LAYOUT
		VBox vbDeleteOpenA = new VBox();
		Label lblUpdateOpenA = new Label("Delete Open Question's Answer:");
		Label lblOQNum = new Label("Question number:");
		TextField txfOQNum = new TextField();
		Button btnDeleteOA = new Button("DELETE");
		vbDeleteOpenA.getChildren().addAll(lblUpdateOpenA, lblOQNum, txfOQNum, btnDeleteOA);
		EventHandler<ActionEvent> deleteOA = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getDeleteAFromModelUIEvent(txfOQNum.getText(), "0", "Open");
			}
		};
		btnDeleteOA.setOnAction(deleteOA);

		// AMERICAN ANSWER LAYOUT
		VBox vbDeleteAA = new VBox();
		Label lblUpdateAA = new Label("Delete American Question's Answer:");
		Label lblAQNum = new Label("Question number:");
		TextField txfAQNum = new TextField();
		Label lblAANum = new Label("Answer number:");
		TextField txfAANum = new TextField();
		Button btnDeleteAA = new Button("DELETE");
		vbDeleteAA.getChildren().addAll(lblUpdateAA, lblAQNum, txfAQNum, lblAANum, txfAANum, btnDeleteAA);
		EventHandler<ActionEvent> deleteAA = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getDeleteAFromModelUIEvent(txfAQNum.getText(), txfAANum.getText(), "American");
			}
		};
		btnDeleteAA.setOnAction(deleteAA);
		hbDeleteA.getChildren().addAll(vbDeleteOpenA, vbDeleteAA);
		tab.setContent(hbDeleteA);
	}
	@Override
	public void returnDeletedAStatusToUI(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
	// --------------tab6----------------
	VBox manualExamBox = new VBox();
	ScrollPane sp = new ScrollPane();
	int stockSize = 0;
	ArrayList<CheckBox> allQuestions = new ArrayList<CheckBox>(stockSize);
	public void createTab6(Tab tab) {
		manualExamBox.setPadding(new Insets(10, 10, 10, 10));
		manualExamBox.setSpacing(10);
		sp.setContent(manualExamBox);
		manualExamBox.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		sp.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		sp.setMaxSize(950, 400);
		Label lblTest = new Label();
		VBox questionsBox = new VBox();
		questionsBox.setSpacing(10);
		Label lblManualE = new Label("Generate Manual Exam:");
		Button btnStart = new Button("LET'S START!");
		Button btnPickQuestions = new Button("ADD QUESTIONS");
		Button btnPickAnswers = new Button("ADD ANSWERS");
		EventHandler<ActionEvent> start = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getStockSizeFromModelUIEvent();
				for (int i = 0; i < stockSize; i++) {
					listener.getQTextFromModelUIEvent(i);
				}
				manualExamBox.getChildren().remove(btnStart);
				manualExamBox.getChildren().add(btnPickQuestions);
			}
		};
		ArrayList<Integer> qNums = new ArrayList<Integer>();
		EventHandler<ActionEvent> choose = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getStockSizeFromModelUIEvent();
				int selectedCounter = 0;
				int qNum = 0;
				for (int i = 0; i < stockSize; i++) {
					qNum = i + 1;
					if (allQuestions.get(i).isSelected()) {
						qNums.add(qNum);
						selectedCounter++;
					}
				}
				if (selectedCounter == 0) {
					listener.addQuestionsToManualExamUIEvent(selectedCounter, qNums);
				} else {
					listener.addQuestionsToManualExamUIEvent(selectedCounter, qNums);
					listener.getAnswersToManualExamUIEvent(qNums);
					manualExamBox.getChildren().remove(btnPickQuestions);
					for (int i = 0; i < allQuestions.size(); i++) {
						manualExamBox.getChildren().remove(allQuestions.get(i));
					}
					manualExamBox.getChildren().add(btnPickAnswers);
				}
			}
		};
		EventHandler<ActionEvent> pickAnswers = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ArrayList<ArrayList<Integer>> americanAnswers = new ArrayList<ArrayList<Integer>>();
				for (int i = 0; i < allAmericanQuestions.size(); i++) {
					ArrayList<Integer> answerss = new ArrayList<Integer>();
					americanAnswers.add(answerss);
					for (int j = 0; j < allAmericanQuestions.get(i).size(); j++) {
						if (allAmericanQuestions.get(i).get(j).isSelected()) {
							americanAnswers.get(i).add(j);
						}
					}
				}
				listener.addAmericanAnswersUIEvent(qNums, americanAnswers);
				manualExamBox.getChildren().clear();
			}
		};
		btnStart.setOnAction(start);
		btnPickQuestions.setOnAction(choose);
		btnPickAnswers.setOnAction(pickAnswers);
		manualExamBox.getChildren().addAll(lblManualE, btnStart, questionsBox);
		tab.setContent(sp);
	}
	@Override
	public void returnedStockSizeToUI(int size) {
		stockSize = size;
	}
	@Override
	public void returnedQTextToUI(String qText) {
		CheckBox cbQuestion = new CheckBox(qText);
		allQuestions.add(cbQuestion);
		manualExamBox.getChildren().add(cbQuestion);
	}
	ArrayList<Label> americanQuestions = new ArrayList<Label>(stockSize);
	VBox answersBox = new VBox();
	ArrayList<ArrayList<CheckBox>> allAmericanQuestions = new ArrayList<ArrayList<CheckBox>>();
	VBox americanAnswersBox;
	@Override
	public void showAmericanAnswers(String qText, Set<Answer> answers) {
		americanAnswersBox = createAmericanQBox(qText, answers);
		manualExamBox.getChildren().add(americanAnswersBox);
	}
	public VBox createAmericanQBox(String qText, Set<Answer> answers) {
		VBox americanQBox = new VBox();
		Label americanQ = new Label(qText);
		americanQuestions.add(americanQ);
		americanQBox.getChildren().add(americanQ);
		ArrayList<CheckBox> americanAnswers = new ArrayList<CheckBox>();
		for (int i = 2; i < answers.getCurrentSize(); i++) {
			CheckBox americanA = new CheckBox(answers.get(i).toString());
			americanAnswers.add(americanA);
			americanQBox.getChildren().add(americanA);
		}
		allAmericanQuestions.add(americanAnswers);
		return americanQBox;
	}
	public void returnedManualExamStatus(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
	@Override
	public void showReadyManualExam(String manualExam) {
		Tab autoGenTab = tabs.getSelectionModel().getSelectedItem();
		TextArea textArea1 = new TextArea(manualExam);
		autoGenTab.setContent(textArea1);
	}
	// --------------tab7----------------
	public void createTab7(Tab tab) {
		VBox vbAutoE = new VBox();
		vbAutoE.setPadding(new Insets(10));
		vbAutoE.setSpacing(15);
		Label lblAutoE = new Label("Generate Automatic Exam:");
		Button btnGenAuto = new Button("CREATE");
		HBox hbAutoE = new HBox();
		Label lblQAmount = new Label("Please enter questions amount:");
		TextField txfQAmount = new TextField();
		hbAutoE.getChildren().addAll(lblQAmount, txfQAmount);
		EventHandler<ActionEvent> GenAuto = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.getGenAutoFromModelUIEvent(txfQAmount.getText());
			}
		};
		btnGenAuto.setOnAction(GenAuto);
		vbAutoE.getChildren().addAll(lblAutoE, hbAutoE, btnGenAuto);
		tab.setContent(vbAutoE);
	}
	@Override
	public void returnAutoGenStatusToUI(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
	@Override
	public void returnAutoGenToUI(String autoExam) {
		Tab autoGenTab = tabs.getSelectionModel().getSelectedItem();
		TextArea textArea1 = new TextArea(autoExam);
		autoGenTab.setContent(textArea1);
	}
	// --------------tab8----------------
	public void createTab8(Tab tab) {
		VBox vbClone = new VBox();
		vbClone.setPadding(new Insets(10, 10, 10, 10));
		vbClone.setSpacing(10);
		Label lblClone = new Label("Clone Exams:");
		Button btnCloneManual = new Button("CLONE MANUAL EXAM");
		Button btnCloneAuto = new Button("CLONE AUTOMATIC EXAM");
		EventHandler<ActionEvent> CloneManual = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.cloneManualExamUIEvent();
			}
		};
		EventHandler<ActionEvent> CloneAuto = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				listener.cloneAutoExamUIEvent();
			}
		};
		vbClone.getChildren().addAll(lblClone, btnCloneManual, btnCloneAuto);
		btnCloneManual.setOnAction(CloneManual);
		btnCloneAuto.setOnAction(CloneAuto);
		tab.setContent(vbClone);
	}
	@Override
	public void returnCloneStatusToUI(String status) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(status);
		alert.showAndWait();
	}
}