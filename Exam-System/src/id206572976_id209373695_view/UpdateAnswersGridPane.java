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
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UpdateAnswersGridPane extends GridPane {
	private ComboBox<String> questions = new ComboBox<>();
	
	public UpdateAnswersGridPane(ArrayList<ViewEventsListener> listeners) {
		
		setPadding(new Insets(10));
		setHgap(15);
		setVgap(15);
		
		Label lblTitle = new Label("Choose one of the question:");
		Label lblContent = new Label("Enter the new Answer of the question:");
		TextField fieldContent = new TextField();
		
		Label lblSpn = new Label("Choose the number of answer (number start from 1): ");
		Spinner<Integer> spnIdAns = new Spinner<>(1, 10, 1);
		CheckBox ansIsCorrect = new CheckBox("Is the answer correct");
		
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
							l.updateAnswersFromView(fieldContent.getText(), ansIsCorrect.isSelected(), questions.getSelectionModel().getSelectedIndex(), spnIdAns.getValue() - 1);
							fieldContent.setText("");
							spnIdAns.getValueFactory().setValue(1);
						}
					}
					
				}
				
			}
		});
				
		add(lblTitle, 0, 0);
		add(questions, 0, 1);
		add(lblSpn, 0, 2);
		add(spnIdAns, 0, 3);
		add(lblContent, 0, 4);
		add(fieldContent, 0, 5);
		add(ansIsCorrect, 0, 6);
		add(btnUpdate, 0, 7);
		
		
	}
	public void updateListQuestions(ArrayList<String> ques) {
		questions.getItems().clear();
		questions.getItems().addAll(ques);
	}
}







