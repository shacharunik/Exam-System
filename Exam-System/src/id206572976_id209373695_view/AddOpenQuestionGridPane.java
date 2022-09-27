package id206572976_id209373695_view;

import java.util.ArrayList;

import id206572976_id209373695_listeners.ViewEventsListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddOpenQuestionGridPane extends GridPane {

	public AddOpenQuestionGridPane(ArrayList<ViewEventsListener> listeners) {
		setPadding(new Insets(10));
		setHgap(15);
		setVgap(15);
		
		Label lblTitle = new Label("Add an open question:");
		Label lblContent = new Label("Enter the content of the question:");
		TextField fieldContent = new TextField();
		Label lblAnswer = new Label("Enter the Answer:");
		TextField fieldAnswer = new TextField();
		Button btnAdd = new Button("Add");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				if (fieldContent.getText().isEmpty() || fieldAnswer.getText().isEmpty()) {
					ViewMethods.dialog("Do not leave empty fields");
				} else {
					for (ViewEventsListener l : listeners) {
						l.addOpenQuestionFromView(fieldContent.getText(), fieldAnswer.getText());
					}
					fieldContent.setText("");
					fieldAnswer.setText("");
				}
				
			}
		});
				
		add(lblTitle, 0, 0);
		add(lblContent, 0, 1);
		add(fieldContent, 0, 2);
		add(lblAnswer, 0, 3);
		add(fieldAnswer, 0, 4);
		add(btnAdd, 0, 6);
		
	}
	

}
