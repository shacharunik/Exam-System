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

public class DeleteAnswerGridPane extends GridPane {
	private ComboBox<String> questions = new ComboBox<>();

	public DeleteAnswerGridPane(ArrayList<ViewEventsListener> listeners) {

		setPadding(new Insets(10));
		setHgap(15);
		setVgap(15);

		Label lblTitle = new Label("Choose one of the multi choice question:");

		Label lblSpn = new Label("Choose the number of answer (number start from 1): ");
		Spinner<Integer> spnIdAns = new Spinner<>(1, 10, 1);

		Button btnUpdate = new Button("Delete");
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {

				for (ViewEventsListener l : listeners) {
					if (questions.getSelectionModel().getSelectedIndex() == -1) {
						ViewMethods.dialog("Do not leave empty fields");
					} else {
						l.deleteAnswerFromView(questions.getSelectionModel().getSelectedIndex(),
								spnIdAns.getValue() - 1);
						spnIdAns.getValueFactory().setValue(1);
					}
				}

			}
		});

		add(lblTitle, 0, 0);
		add(questions, 0, 1);
		add(lblSpn, 0, 2);
		add(spnIdAns, 0, 3);
		add(btnUpdate, 0, 4);

	}

	public void updateListQuestions(ArrayList<String> ques) {
		questions.getItems().clear();
		questions.getItems().addAll(ques);
	}
}
