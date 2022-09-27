package id206572976_id209373695_view;

import java.util.ArrayList;

import id206572976_id209373695_listeners.ViewEventsListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddMultiChoiceGridPane extends GridPane {
	ArrayList<String> answers;
	ArrayList<Boolean> isCorrect;
	int count = 1;
	
	public AddMultiChoiceGridPane(ArrayList<ViewEventsListener> listeners) {
		answers = new ArrayList<>();
		isCorrect = new ArrayList<>();

		setPadding(new Insets(10));
		setHgap(15);
		setVgap(15);
		
		Label lblTitle = new Label("Add an Multi Choice question:");
		Label lblContent = new Label("Enter the content of the question:");
		
		TextField fieldContent = new TextField();
		Label lblAnswer = new Label("Enter the Answer " + count + ":");
		TextField fieldAnswer = new TextField();
		
		CheckBox ansIsCorrect = new CheckBox("Is the answer correct");
		
		Label lblAnswers = new Label("Answers: ");
		
		Button btnAddAns = new Button("Add Answer");
		btnAddAns.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				if (fieldContent.getText().isEmpty() || fieldAnswer.getText().isEmpty()) {
					ViewMethods.dialog("Do not leave empty fields");
				} else {
						answers.add(fieldAnswer.getText());
						isCorrect.add(ansIsCorrect.isSelected());
						fieldAnswer.setText("");
						ansIsCorrect.setSelected(false);
						count++;
						lblAnswer.setText("Enter the Answer " + count + ":");
						lblAnswers.setText("Answers: " + answers.toString());
				}
				
			}
		});
		
		

		Button btnResetOptions = new Button("Reset the data");
		btnResetOptions.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
					answers = new ArrayList<>();
					isCorrect = new ArrayList<>();
					fieldAnswer.setText("");
					fieldContent.setText("");
					ansIsCorrect.setSelected(false);
					lblAnswers.setText("Answers: ");
					count = 1;
					lblAnswer.setText("Enter the Answer " + count + ":");
				}
				
			
		});
		
		Button btnAdd = new Button("Add Multi Choice Question");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				if (fieldContent.getText().isEmpty()) {
					ViewMethods.dialog("Do not leave empty fields");
				} else {
					for (ViewEventsListener l : listeners) {
						l.addMultiQuestionFromView(fieldContent.getText(), answers, isCorrect);
					}
					
					answers = new ArrayList<>();
					isCorrect = new ArrayList<>();
					fieldContent.setText("");
					fieldAnswer.setText("");
					ansIsCorrect.setSelected(false);
					lblAnswers.setText("Answers: ");
					count = 1;
					lblAnswer.setText("Enter the Answer " + count + ":");
					
				}
				
			}
		});
				
		add(lblTitle, 0, 0);
		add(lblContent, 0, 1);
		add(fieldContent, 0, 2);
		add(lblAnswer, 0, 3);
		add(ansIsCorrect, 1, 4);
		add(fieldAnswer, 0, 4);
		add(btnAddAns, 0, 5);
		add(lblAnswers, 0, 6);
		add(btnResetOptions, 1, 6);
		add(btnAdd, 0, 7);
		

		
	}

}
