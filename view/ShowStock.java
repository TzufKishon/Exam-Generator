//package exam_gen.view;
//
//import exam_gen.controller.ExamGenController;
//import exam_gen.listeners.ExamGenUIEventsListeners;
//import javafx.event.ActionEvent;
//import javafx.event.Event;
//import javafx.event.EventHandler;
//import javafx.scene.control.Label;
//import javafx.scene.control.Tab;
//import javafx.scene.control.TabPane;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.VBox;
//
//public class ShowStock implements ShowStockAbstractView {
//	
//	ExamGenUIEventsListeners listener;
//	TabPane tabs;
//	
//	public ShowStock(TabPane tabs, ExamGenUIEventsListeners listener) {
//		super();
//		this.listener = listener;
//		this.tabs =  tabs;
//	}
//
////	public VBox getStock() {
////		VBox vbRoot = new VBox(); 	
////		TextArea stockText= new TextArea(listener.getStockFromModel());
////		return vbRoot;
////	}
//	
//	public void getStock(Tab tab) {
//		//Tab stockTab = tabs.getSelectionModel().getSelectedItem();
//		tab.setOnSelectionChanged(new EventHandler<Event>() {
//			@Override
//			public void handle(Event action) {
//				listener.getStockFromModel();
//			}
//		});
//		
//		
//	}
//	
//	public void addStockToTab(String stockText) {
//		Tab stockTab = tabs.getSelectionModel().getSelectedItem();
//		TextArea textArea = new TextArea(stockText);
//		stockTab.setContent(textArea);
//	}
//
//}
