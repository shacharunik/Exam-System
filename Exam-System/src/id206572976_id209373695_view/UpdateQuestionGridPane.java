package id206572976_id209373695_view;

import java.util.ArrayList;

import id206572976_id209373695_listeners.ViewEventsListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UpdateQuestionGridPane extends GridPane {
	private ComboBox<String> questions = new ComboBox<>();
	
	public UpdateQuestionGridPane(ArrayList<ViewEventsListener> listeners) {
		

		setPadding(new Insets(10));
		setHgap(15);
		setVgap(15);
		
		Label lblTitle = new Label("Update Content of the question:");
		Label lblContent = new Label("Enter the new content of the question:");
		TextField fieldContent = new TextField();
		
		
		
		Button btnUpdate = new Button("Update");
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				if (fieldContent.getText().isEmpty()) {
					ViewMethods.dialog("Do not leave empty fields");
				} else {
					for (ViewEventsListener l : listeners) {
						if (questions.getSelectionModel().getSelectedIndex() == -1) {
							ViewMethods.dialog("Do not leave empty fields");
						} else {
							l.updateQuestionContent(questions.getSelectionModel().getSelectedIndex(), fieldContent.getText());
							fieldContent.setText("");
						}
							
					}
					
				}
				
			}
		});
				
		add(lblTitle, 0, 0);
		add(lblContent, 0, 1);
		add(fieldContent, 0, 3);
		add(questions, 0, 2);
		add(btnUpdate, 0, 4);
		
		
	}
	public void updateListQuestions(ArrayList<String> ques) {
		questions.getItems().clear();
		questions.getItems().addAll(ques);
	}
}







